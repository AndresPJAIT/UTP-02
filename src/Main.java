import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        final int warmup = 5000;
        final int N = 50000;

        for(int i = 0; i < warmup ; i++){
            measureExt();
            measureRun();
        }

        long[] exttimes = new long[N];
        long[] runtimes = new long[N];
        long extaverage = 0;
        long runaverage = 0;

        for(int i = 0; i < N ; i++){
            exttimes[i] = measureExt();
            runtimes[i] = measureRun();

            extaverage += exttimes[i];
            runaverage += runtimes[i];

        }

        extaverage = extaverage / N;
        runaverage = runaverage / N;

        System.out.println(extaverage);
        System.out.println(runaverage);

        Arrays.sort(exttimes);
        Arrays.sort(runtimes);

        //Calculating P50 (As it has pair size, average of the two of the middle
        long p50ext = (exttimes[N / 2] + exttimes[(N / 2) - 1]) / 2;
        long p50run = (runtimes[N / 2] + runtimes[(N / 2) - 1]) / 2;

        System.out.println(p50ext);
        System.out.println(p50run);
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
