package Components;

public class StorageList {
    private Task[] storageList;
    private int len;

    public StorageList() {
        this.storageList = new Task[100];
        this.len = 0;
    }

    public void add(String str) {
        storageList[len] = new Task(str);
        len++;
    }

    public void announceItems() {
        //i is used to print out the index of each item so it has to be incremented
        for (int i = 0; i < len; i++) {
            System.out.println((i + 1) + ". " + storageList[i] + "\n");
        }
    }

    public void mark(int index) {
        //index - 1 because task list displayed to user starts from 1
        storageList[index - 1].completeTask();
        System.out.println("I've marked the task as done ^o^ :\n    " + storageList[index - 1].toString());
    }

    public void unmark(int index) {
        //index - 1 because task list displayed to user starts from 1
        storageList[index - 1].uncompleteTask();
        System.out.println("I've marked the task as not done T.T :\n    " + storageList[index - 1].toString());
    }
}
