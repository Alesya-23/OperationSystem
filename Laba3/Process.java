package Laba3;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Process {
    Random random = new Random();
    private List<Page> pages = new LinkedList<Page>();
    private static int ID;
    private int countPages = random.nextInt( 10 ) + 1;

    Process(int ID) {
        this.ID = ID;
        createPages();
    }

    private void createPages() {
        for (int i = 0; i < countPages; i++) {
            Page page = new Page( ID, i, "processID: " + ID + ", pageID:  " + i, 0, 0 );
            pages.add( page );
        }
    }

    public static int getID() {
        return ID;
    }


    public List<Page> getPages() {
        return pages;
    }

    public Page getPage(int index) {
        return pages.get( index );
    }

    public int getCountPages(){
        return countPages;
    }
}