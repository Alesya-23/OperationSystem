import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Core {
    ArrayList<Process> arrProcess = new ArrayList<>();
    Random rand = new Random();
    private int sumTime = 0;

    public void createProcess() {
        for (int i = 0; i < 3 + rand.nextInt( 10 ); i++) {
            int time = 1;
            arrProcess.add( new Process( i, time ) );
            arrProcess.get( i ).start();
            time = arrProcess.get( i ).getSumTime();
            arrProcess.get( i ).setTime( time );
            sumTime += time;
        }
    }

    public void printPlanProcess() {
        for (int i = 0; i < arrProcess.size(); i++) {
            System.out.println("Процесс " + arrProcess.get(i).getpID());
            int timeProcess = 0;
            for (int j = 0; j < arrProcess.get(i).getArrThread().size(); j++) {
                System.out.println("   Поток " + arrProcess.get(i).getArrThread().get(j).getTreadID() + " время: " + arrProcess.get(i).getArrThread().get(j).getTime());
                timeProcess+=arrProcess.get(i).getArrThread().get(j).getTime();
            }
            System.out.println("Итого процесс " + arrProcess.get(i).getpID() + " выполнился за время: " + timeProcess + "\n");
        }
    }

    public void startProgram() {
        createProcess();
        printPlanProcess();
        Process process = new Process();
        process.planProcessThread(arrProcess);
    }
}
