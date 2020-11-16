package Laba3;

import java.util.Random;

public class MemoryManager {
    private int countProcess;
    Storage storage = new Storage();
    RandomAcsessMemory randomAcsessMemory = new RandomAcsessMemory();

    public void createProcess(int countProcess) {
        this.countProcess = countProcess;
        for (int i = 0; i < countProcess; i++) {
            Process newProcess = new Process( i );
            storage.addProcess( newProcess );
            PagesTable pageTable = new PagesTable( newProcess );
            randomAcsessMemory.addTable( pageTable );
        }
    }

    public void work(int count) {
        randomAcsessMemory.fillRAMIDList();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Process process = storage.getProcess( random.nextInt( countProcess ) );
            Page page = process.getPage( random.nextInt( process.getPages().size() ) );
            System.out.println( "ОС запрашивает у процесса " + page.getProcessID() + " страницу " + page.getID() );
            randomAcsessMemory.setInTableNRU( page );
        }

        System.out.println( "Финальные таблицы" );
        for (int i = 0; i < countProcess; i++) {
            randomAcsessMemory.getTable( i ).printTable();
        }
        randomAcsessMemory.printTable();
    }
}
