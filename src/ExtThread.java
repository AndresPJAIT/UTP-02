public class ExtThread extends Thread {
    public long objCreationTime;
    public long firstInstruction;

    public ExtThread(long objCreationTime) {
        this.objCreationTime = System.nanoTime();
    }

}
