package Components;

public class StorageList {
    private Task[] storageList;
    private int len;

    public StorageList() {
        this.storageList = new Task[100];
        this.len = 0;
    }

    public void addTask(String str) {
        if (str.startsWith("todo ")) {
            String textString = str.substring(5);
            Todo newToDo = new Todo(textString);
            this.addToList(newToDo);
            System.out.println("Task added:\n  " + newToDo + "\nNow you have " + len + " tasks in the list. HAVE FUN ^o^");
        } else if (str.startsWith("event")) {
            String textString = str.substring(6);
            Event newEvent = new Event(textString);
            this.addToList(newEvent);
            System.out.println("Task added:\n  " + newEvent + "\nNow you have " + len + " tasks in the list. HAVE FUN ^o^");
        } else {    /* remaining case is deadline task cases */
            String textString = str.substring(9);
            Deadline newDeadline = new Deadline(textString);
            this.addToList(newDeadline);
            System.out.println("Task added:\n  " + newDeadline + "\nNow you have " + len + " tasks in the list. HAVE FUN ^o^");
        }
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
        System.out.println("I've marked the task as done ^o^ :\n  " + storageList[index - 1].toString());
    }

    public void unmark(int index) {
        //index - 1 because task list displayed to user starts from 1
        storageList[index - 1].uncompleteTask();
        System.out.println("I've marked the task as not done T.T :\n  " + storageList[index - 1].toString());
    }

    private void addToList(Task task) {
        storageList[len] = task;
        len++;
    }
}
