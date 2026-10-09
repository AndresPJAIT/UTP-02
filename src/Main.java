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

    }
}
