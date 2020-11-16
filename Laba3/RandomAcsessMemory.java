package Laba3;

import java.util.LinkedList;
import java.util.List;

public class RandomAcsessMemory {
    private List<PagesTable> pagesTables = new LinkedList<PagesTable>();
    private List<Page> physicalTable = new LinkedList<>();
    private int sizeRAM = 10;

    public void addTable(PagesTable pagesTable) {
        pagesTables.add( pagesTable );
    }

    public PagesTable getTable(int index) {
        return pagesTables.get( index );
    }

    public void fillRAMIDList() {
        for (int i = 0; i < sizeRAM; i++) {
            physicalTable.add( i, null );
        }
    }

    public void setInTableNRU(Page page) {
        if (physicalTable.contains( page )) { //
            System.out.print( "Изменений в таблице физической памяти нет\n\n" );
            return;
        }
        page.setAppeal( 1 );
        for (int i = 0; i < sizeRAM; i++) {
            if (physicalTable.get( i ) == null) {
                physicalTable.add( i, page );
                pagesTables.get( page.getProcessID() ).setAddressInRAM( page.getID(), i );
                System.out.println( "Изменение таблицы страниц" );
                getTable( page.getProcessID() ).printTable();
                System.out.println( "Изменение таблицы физической памяти" );
                printTable();
                break;
            }
        }
        NRUAlgoritm( page );
    }

    private void NRUAlgoritm(Page page) {
        int pageID = 0;
        for (int i = 0; physicalTable.get( i ) != null && i < sizeRAM; i++) {
            if (physicalTable.get( i ).getAppeal() != 1 && physicalTable.get( i ).getModification() == 1) {
                pageID = i;
                pagesTables.get( page.getProcessID() ).deleteFromRAM( page.getID() );
            } else if (physicalTable.get( i ).getAppeal() == 1) {
                if (physicalTable.get( i ).getTimeAppeal() == 1) {
                    physicalTable.get( i ).setModification( 1 );
                } else {
                    physicalTable.get( i ).setAppeal( 0 );
                }
                physicalTable.get( i ).setModification( 1 );
            }
        }
        pagesTables.get( physicalTable.get( pageID ).getProcessID() ).deleteFromRAM( pageID );
        physicalTable.remove( pageID );
        physicalTable.add( pageID, page );
        pagesTables.get( page.getProcessID() ).setAddressInRAM( page.getID(), pageID );
        System.out.println( "Изменение таблицы страниц" );
        getTable( page.getProcessID() ).printTable();
        System.out.println( "Изменение таблицы физической памяти" );
        printTable();
    }


    public void printTable() {
        System.out.println( "Физическая таблица" );
        System.out.println( " Адрес \t || Данные страницы" );
        System.out.print( "-----------------------------------------------------\n" );
        for (int i = 0; i < sizeRAM; i++) {
            printTab( i );
            System.out.print( " ||" );
            if (physicalTable.get( i ) != null) {
                System.out.println( "\t" + physicalTable.get( i ).getData() + ", Modification: " + physicalTable.get( i ).getModification() );
            } else {
                System.out.println( "\t-" );
            }
        }
        System.out.print( "\n" + "\n" );
    }

    public void printTab(int digit) {
        if (digit < 10) {
            System.out.print( "\t" + digit + "\t" );
        } else {
            System.out.print( "\t" + digit + "\t" );
        }
    }
}