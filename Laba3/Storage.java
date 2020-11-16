package Laba3;

import java.util.LinkedList;
import java.util.List;

public class Storage {
    private List<Page> pages = new LinkedList<Page>();

    public void addPage(Page page) {
        pages.add( page );
    }

    public Page getPages(int pageID, int processID){
        for(int i = 0; i < pages.size(); i++){
            if(pages.get(i).getID() == pageID && pages.get(i).getProcessID() == processID){
                return pages.get(i);
            }
        }
        return null;
    }

}