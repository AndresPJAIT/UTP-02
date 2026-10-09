public class RunTask implements Runnable{
    long objCreationTime;
    long firstInstruction;

    public RunTask() {
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
