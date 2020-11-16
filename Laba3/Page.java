package Laba3;

public class Page {
    private int ID;
    private int processID;
    private String data;
    private int modification;
    private int appeal;
    private int timeAppeal = 0;
    private byte sizeMemory = 4;

    public Page(int processID, int ID, String data, int appeal, int modification) {
        this.processID = processID;
        this.ID = ID;
        this.data = data;
        this.modification = modification;
        this.appeal = appeal;
    }

    public String getData() {
        return data;
    }

    public int getID() {
        return ID;
    }

    public int getProcessID() {
        return processID;
    }

    public int getModification() {
        return modification;
    }

    public void setModification(int modification) {
        if (modification == 1) {
            timeAppeal = 0;
        }
        this.modification = modification;
    }

    public int getTimeAppeal() {
        return timeAppeal;
    }

    public void setAppeal(int appeal) {
        if (appeal == 1) {
            timeAppeal = 1;
        } else {
            timeAppeal = 0;
        }
        this.appeal = appeal;
    }

    public int getAppeal() {
        return appeal;
    }
}