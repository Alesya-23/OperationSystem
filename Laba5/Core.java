import java.util.ArrayList;
import java.util.Random;

public class Core {
    //все процессы
    ArrayList<Process> arrProcess = new ArrayList<>();
    //для храненния заблокированных процесов
    ArrayList<Process> blockedProcess = new ArrayList<>();
    Random rand = new Random();
    private int sumTime = 0;

    public void createProcess() {
        for (int i = 0; i < 3 + rand.nextInt( 15 ); i++) {
            int time = 1;
            Process processNew = new Process( i, time );
            processNew.setBlocked( rand.nextBoolean() );
            arrProcess.add( processNew );
            arrProcess.get( i ).start();
            time = arrProcess.get( i ).getSumTime();
            arrProcess.get( i ).setSumTime( time );
            sumTime += time;
        }
    }

    public void executePlanProcess() {
        //выполнение работы прерываний с заблокированными и незаблокированными процессами
        System.out.print( "Работа прерываний с блокировкой процессов:" + "\n" );
        executeBlockedProcess();
        System.out.print( "\n" + "Работа прерываний без блокировки:" + "\n" );
        executeUnBlockedProcess();
    }

    public void executeBlockedProcess() {
        Device device = new Device();
        for (int i = 0; i < arrProcess.size(); i++) {
            System.out.println( "Процесс " + arrProcess.get( i ).getpID() + " начал свою работу" );
            if (arrProcess.get( i ).getIsBlocked()) {
                System.out.println( "Процесс " + arrProcess.get( i ).getpID() + " был заблокирован " );
                System.out.println( "Устройство начало свою работу.Пожалуйста, дождитесь выполнения операции" );
                arrProcess.get( i ).setNecessaryTime( device.executingTimeOperationDevice() );
                blockedProcess.add( arrProcess.get( i ) );
            } else {
                for (int j = 0; j < arrProcess.get( i ).getArrThread().size(); j++) {
                    System.out.println( "Поток " + arrProcess.get( i ).getArrThread().get( j ).getTreadID() + " процесса " + arrProcess.get( i ).getpID() + " был запущен" );
                }
                System.out.print( "Процесс " + arrProcess.get( i ).getpID() + " успешно завершил свою работу!" + "\n" );
            }
            workingBlockedProcess();
        }
        workingBlockedProcess();
        System.out.print( "\n" );
    }

    public void workingBlockedProcess() {
        System.out.print( "\n" + "Возобновление работы процессов..." + "\n" );
        if (!blockedProcess.isEmpty()) {
            int i = 0;
            Process currProcess = blockedProcess.get( i );
            for (int j = 0; j < currProcess.getArrThread().size(); j++) {
                System.out.println( "Поток " + currProcess.getArrThread().get( j ).getTreadID() + " процесса " + currProcess.getpID() + " был запущен" );
            }
            System.out.print( "Процесс " + currProcess.getpID() + " успешно завершил свою работу!" + "\n" );
            blockedProcess.remove( currProcess );
        }
    }

    public void executeUnBlockedProcess() {
        Device device = new Device();
        for (int i = 0; i < arrProcess.size(); i++) {
            System.out.println( "Процесс " + arrProcess.get( i ).getpID() + " начал свою работу" );
            if (arrProcess.get( i ).getIsBlocked()) {
                System.out.println( "Процесс " + arrProcess.get( i ).getpID() + " был заблокирован " );
                System.out.println( "Устройство начало свою работу. Пожалуйста, дождитесь выполнения операции" );
                for (int j = 0; j < arrProcess.get( i ).getArrThread().size(); j++) {
                    System.out.println( "Поток " + arrProcess.get( i ).getArrThread().get( j ).getTreadID() + " процесса " + arrProcess.get( i ).getpID() + " был запущен" );
                }
            } else {
                for (int j = 0; j < arrProcess.get( i ).getArrThread().size(); j++) {
                    System.out.println( "Поток " + arrProcess.get( i ).getArrThread().get( j ).getTreadID() + " процесса " + arrProcess.get( i ).getpID() + " был запущен" );
                }
            }
        }
        System.out.print( "\n\n" );
    }

    public void printPlanProcess() {
        for (int i = 0; i < arrProcess.size(); i++) {
            System.out.println( "Процесс " + arrProcess.get( i ).getpID() );
            int timeProcess = 0;
            for (int j = 0; j < arrProcess.get( i ).getArrThread().size(); j++) {
                System.out.println( "Поток " + arrProcess.get( i ).getArrThread().get( j ).getTreadID() + " время: " + arrProcess.get( i ).getArrThread().get( j ).getTime() );
                timeProcess += arrProcess.get( i ).getArrThread().get( j ).getTime();
            }
            System.out.println( "Итого процесс " + arrProcess.get( i ).getpID() + " выполнился за время: " + timeProcess + "\n" );
        }
    }

    public void printTimeExecuteProcess() {
        int timeWithoutBlock = 0;
        int timeWithBlock = 0;
        for (int i = 0; i < arrProcess.size(); i++) {
            int timeProcess = 0;
            for (int j = 0; j < arrProcess.get( i ).getArrThread().size(); j++) {
                timeProcess += arrProcess.get( i ).getArrThread().get( j ).getTime();
                timeProcess += arrProcess.get( i ).getNecessaryTime();
            }
            String state = arrProcess.get( i ).getIsBlocked() ? " заблокирован " : "незаблокирован";
            System.out.println( "Итого процесс " + arrProcess.get( i ).getpID() + ", с состоянием: " + state + " выполнился за время " + timeProcess + "\n" );
            if (arrProcess.get( i ).getIsBlocked())
                timeWithBlock += timeProcess;
            else timeWithoutBlock += timeProcess;
        }
        System.out.print( "Итоговое время работы с блокировкой и без:\n" );
        System.out.print( "с блокировкой :" + timeWithBlock + "\n" );
        System.out.print( "без блокировки :" + timeWithoutBlock + "\n" );
    }

    public void startProgram() {
        createProcess();
        executePlanProcess();
        printTimeExecuteProcess();
        Process process = new Process();
        process.planProcessThread( arrProcess );
    }
}
