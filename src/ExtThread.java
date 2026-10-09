public class ExtThread extends Thread {
    long objCreationTime;
    long firstInstruction;

    public ExtThread(long objCreationTime) {
        this.objCreationTime = System.nanoTime();
    }

    public long getTime() {
        return this.firstInstruction - this.objCreationTime;
    }

    @Override
    public void run() {
        this.firstInstruction = System.nanoTime();
    }

}
