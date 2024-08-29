package knight2103.tasks;

import java.util.ArrayList;

// TASKLIST CANNOT HAVE UI

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> storageData) {
        this.taskList = storageData;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public String printToList() {
        String stringToPrint = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            int bulletPoint = i + 1;
            stringToPrint += bulletPoint + ". " + this.taskList.get(i) + "\n";
        }
        return stringToPrint;
    }

    public String printToFile() {
        String stringToWrite = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            stringToWrite += this.taskList.get(i).saveToFileFormat() + "\n";
        }
        return stringToWrite;
    }

    public String searchPrintToList(String wordSearch) {
        String stringToPrint = "";
        int bulletPoint = 0;
        for (int i = 0; i < this.taskList.size(); i++) {
            Task searchedTask = this.taskList.get(i);
            if (searchedTask.getDescription().contains(wordSearch)) {
                bulletPoint++;
                stringToPrint += bulletPoint + ". " + searchedTask + "\n";
            }
        }
        if (bulletPoint == 0) {
            stringToPrint = "NIL: There is no matching tasks.\n";
        }
        return stringToPrint;
    }

    public void add(Task newTask) {
        this.taskList.add(newTask);
    }

    public Task mark(int index) throws IndexOutOfBoundsException {
        taskList.get(index).markDone();
        return taskList.get(index); // must be after to return the newly updated one
    }

    public Task unmark(int index) throws IndexOutOfBoundsException {
        taskList.get(index).unmarkDone();
        return taskList.get(index); // must be after to return the newly updated one
    }

    public Task delete(int index) throws IndexOutOfBoundsException {
        Task taskToDelete = taskList.get(index);
        taskList.remove(index);
        return taskToDelete;
    }
}
