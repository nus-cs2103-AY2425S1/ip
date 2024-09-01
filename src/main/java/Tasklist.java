import java.util.List;
import java.util.ArrayList;

//Contains the tasklist
public class Tasklist {

    private final ArrayList<Task> TASKLIST = new ArrayList<>(100);

    public Tasklist(List<Task> taskList) {
        int size = taskList.size();

        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.TASKLIST.add(taskList.get(i));
            }
        }
    }

    public boolean addTask(Task task) {
        if (TASKLIST.size() >= 100) {
            return false;
        } else {
            TASKLIST.add(task);
            return true;
        }
    }

    public boolean deleteTask(int taskNumber) {
        Task task = TASKLIST.get(taskNumber);
        return TASKLIST.remove(task);
    }

    public String printTaskList() {
        StringBuilder toPrint = new StringBuilder("Here are the tasks in your list: \n");
        int length = TASKLIST.size();

        for (int i = 0; i < length; i++) {
            toPrint.append(i + 1).append(". ").append(TASKLIST.get(i)).append("\n");
        }

        return toPrint.toString();
    }

    public boolean deleteAll() {
        int length = TASKLIST.size();
        if (length > 0) {
            TASKLIST.subList(0, length).clear();
            return true;
        }
        return false;
    }

    public boolean markTask(int taskNumber) {
        return TASKLIST.get(taskNumber).markAsDone();
    }

    public boolean unmarkTask(int taskNumber) {
        return TASKLIST.get(taskNumber).unmark();
    }

    public int getSize() {
        return TASKLIST.size();
    }

    public String getTaskStr(int taskNumber) {
        return TASKLIST.get(taskNumber).toString();
    }

}
