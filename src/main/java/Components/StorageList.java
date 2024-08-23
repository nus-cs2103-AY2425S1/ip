package Components;

public class StorageList {
    private String[] storageList;
    private int len;

    public StorageList() {
        this.storageList = new String[100];
        this.len = 0;
    }

    public void add(String str) {
        storageList[len] = str;
        len++;
    }

    public void announceItems() {
        //i is used to print out the index of each item so it has to be incremented
        for (int i = 0; i < len; i++) {
            System.out.println((i + 1) + ". " + storageList[i] + "\n");
        }
    }
}
