public class Main {

    public static void main(String[] args){

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
