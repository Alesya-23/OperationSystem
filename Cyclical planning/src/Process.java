import java.util.ArrayList;
import java.util.Random;

public class Process {
    private int pID;
    private int time = 0;
    private int sumTime = 0;
    private int necessaryTime = 0;
    ArrayList<Thread> arrThread = new ArrayList<>();
    Random rand = new Random();

    public void setNecessaryTime(int timeThread ){
        necessaryTime +=timeThread;
    }

    public int getNessesaryTime(){
        return necessaryTime;
    }

    public void setTime(int time) {
        this.sumTime = time;
    }

    public int getpID() {
        return pID;
    }

    public int getSumTime() {
        return sumTime;
    }

    public Process(int pID, int time) {
        this.pID = pID;
        this.time = time;
    }

    public void createThread() {
        for (int i = 0; i < 1 + rand.nextInt( 10 ); i++) {
            int time = 1 + rand.nextInt( 5 );
            arrThread.add( new Thread( i, time));
            sumTime += time;
        }
    }

    public void start() {
        createThread();
    }
}
//ядро сразу планирует потоки, а не процессы