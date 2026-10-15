import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        final int WARMUP = 5000;
        final int N = 50000;

        for (int i = 0; i < WARMUP; i++) {
            measureExt();
            measureRun();
        }

        long[] extTimes = new long[N];
        long[] runTimes = new long[N];

        for (int i = 0; i < N; i++) {
            extTimes[i] = measureExt();
            runTimes[i] = measureRun();
        }

        try (PrintWriter pw = new PrintWriter("results_" + N + ".csv")) {
            pw.println("method,ns");
            for (long t : extTimes) pw.println("ExtThread," + t);
            for (long t : runTimes) pw.println("RunTask," + t);
        }

        System.out.println("N=" + N);
        stats("ExtThread", extTimes);
        stats("RunTask", runTimes);
    }

    private static void stats(String name, long[] data) {
        int n = data.length;
        long sum = 0;
        for (long v : data) sum += v;

        long[] s = data.clone();
        Arrays.sort(s);

        long p50 = (s[n / 2] + s[n / 2 - 1]) / 2;
        long p90 = s[(int) Math.ceil(0.90 * n) - 1];
        long p99 = s[(int) Math.ceil(0.99 * n) - 1];

        System.out.println(name + " avg=" + sum / n + " P50=" + p50
                + " P90=" + p90 + " P99=" + p99 + " (ns)");
    }

    public static long measureExt() {
        ExtThread thread1 = new ExtThread();
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return thread1.getTime();
    }

    public static long measureRun() {
        RunTask runtask1 = new RunTask();
        Thread threadrun1 = new Thread(runtask1);
        threadrun1.start();
        try {
            threadrun1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return runtask1.getTime();
    }
}