package Laba3;

import java.util.List;
import java.util.Random;

public class MemoryManager {
    private int countProcess;
    Storage storage = new Storage();
    RandomAcsessMemory randomAcsessMemory = new RandomAcsessMemory();

    public void createProcess(int countProcess) {
        this.countProcess = countProcess;
        for (int i = 0; i < countProcess; i++) {
            Process newProcess = new Process( i );
            PagesTable pageTable = new PagesTable( newProcess );
            randomAcsessMemory.addTable( pageTable );
            randomAcsessMemory.addProcess( newProcess );
            List<Page> listPage = newProcess.getPages();

            for(int j = 0; j < listPage.size(); j++) {
                Page page = listPage.get( j );
                storage.addPage( page );
            }
        }
    }

    public void work(int count) {
        randomAcsessMemory.fillRAMIDList();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int processID = random.nextInt( countProcess );
            Process process = randomAcsessMemory.getProcess(processID);
            Page page = storage.getPages( random.nextInt( process.getCountPages()), processID );
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