public class TaskList {
    private Task[] list;
    private int listPointer = 0;

    public TaskList(int size) {
        list = new Task[size];
    }

    public void addTask(Task task) {
        list[listPointer] = task;
        listPointer++;
    }

    public void markTask(int taskNumber) throws IndexOutOfBoundsException, NullPointerException{
        list[taskNumber-1].updateStatus(true);
    }

    public void unmarkTask(int taskNumber) throws IndexOutOfBoundsException, NullPointerException {
        list[taskNumber-1].updateStatus(false);
    }

    public String printTask(int taskNumber) throws IndexOutOfBoundsException, NullPointerException {
        return list[taskNumber-1].toString();
    }

    public String size() {
        return "" + listPointer;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < listPointer; i++) {
            output += (i+1) + "." + list[i].toString();
        }
        return output;
    }
}
