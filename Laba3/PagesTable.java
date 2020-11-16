package Laba3;

import java.util.List;

public class PagesTable {
    private List<Page> pages;
    private Process process;
    private int[] addressTable;

    PagesTable(Process process) {
        this.pages = process.getPages();
        this.process = process;
        fillRAMlIDList();
    }

    private void fillRAMlIDList() {
        addressTable = new int[pages.size()];
        for (int i = 0; i < pages.size(); i++) {
            addressTable[i] = -1;
        }
    }

    public void setAddressInRAM(int index, int address) {
        addressTable[index] = address;
    }

    public void deleteFromRAM(int address) {
        for (int i = 0; i < pages.size(); i++) {
            if (addressTable[i] == address) {
                addressTable[i] = -1;
            }
        }
    }

    public void printTable() {
        System.out.println( "Процесс номер " + process.getID() + " таблица: " );
        System.out.println( "Virtual || Physical || Appeal \t|| Modification" );
        System.out.print( "------------------------------------------------\n" );
        for (int i = 0; i < pages.size(); i++) {
            printTab( i );
            System.out.print( "||" );
            if (addressTable[i] != -1) {
                printTab( addressTable[i] );
                System.out.print( "\t||" );
            } else {
                System.out.print( "\t-\t\t||" );
            }
            printTab( process.getPage( i ).getAppeal() );
            System.out.print( "\t||" );
            printTab( process.getPage( i ).getModification() );
            System.out.print( "\n" );
        }
    }

    public void printTab(int digit) {
        if (digit < 10) {
            System.out.print( "\t" + digit + "\t" );
        } else {
            System.out.print( "\t" + digit + "\t" );
        }
    }
}