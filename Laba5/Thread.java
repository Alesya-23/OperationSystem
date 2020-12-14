public class Thread {
    private int treadID;
    private int time;

    public Thread(int tID, int time) {
        this.treadID = tID;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public int getTreadID() {
        return treadID;
    }
}
