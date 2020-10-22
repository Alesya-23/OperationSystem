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

    public void planProcess(){
        while(!arrProcess.isEmpty()){
            for(int i = 0; i < arrProcess.size(); i++){
                for(int j = 0; j < arrProcess.get( i ).arrThread.size(); j++) {
                            int timeThr = arrProcess.get( i ).arrThread.get( j ).getTime();
                            System.out.println( "поток " + arrProcess.get( i ).arrThread.get( j ).getTreadID() + " процесса " + arrProcess.get( i ).getpID() + " начал выполнение" );
                            arrProcess.get( i ).arrThread.get( j ).working();
                            arrProcess.get( i ).setNecessaryTime(arrProcess.get( i ).arrThread.get( j ).getTime());
                            arrProcess.get( i ).arrThread.remove( j );
                            arrProcess.get( i ).setTime( arrProcess.get( i ).getSumTime() - timeThr );
                            sumTime -= timeThr;
                            if (arrProcess.get( i ).getSumTime() == 0) {
                                System.out.println( "процесс " + arrProcess.get( i ).getpID() + " выполнен успешно за время " + arrProcess.get( i ).getNessesaryTime());
                                System.out.println();
                                arrProcess.remove( i );
                                break;
                            }
                }
            }
        }
    }

    public void startProgram() {
        createProcess();
        planProcess();
    }
}
//указываем начало выполнение процесса, заканчиваем выполнение, только когда кончатся все объекты массива процессов