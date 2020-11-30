import java.util.LinkedList;

public class NodeFileSystem {
    public int nodeSize;
    public LinkedList<File> fileList;
    private int nodeIndex;
    private int fileIndex;
    private File file;

    public NodeFileSystem(File file, int nodeIndex, int fileIndex) {
        nodeSize = 5;
        this.fileIndex = fileIndex;
        this.file = file;
        this.nodeIndex = nodeIndex;
    }

    public int getNodeFileSystemSize() {
        return nodeSize;
    }

    public void setNodeFileSystemSize(int nodeSize) {
        this.nodeSize = nodeSize;
    }

    public int getNodeFileSystemIndex() {
        return nodeIndex;
    }

    public void setNodeFileSystemIndex(int nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public LinkedList<File> sortByNodeFileSystem(int nodeIndex) {
        if (getNodeFileSystemIndex() == nodeIndex) {
            for (int i = fileIndex; i < nodeSize; i++) {
                fileList.add( getFile() );
            }
        }
        return fileList;
    }
}