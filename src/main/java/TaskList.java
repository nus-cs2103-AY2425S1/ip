import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getArrListTask() {
        return this.tasks;
    }

    public void validateMarkTaskNumber(int taskNumber) throws JustbotException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException("Hey man there is no such task!");
        }
        if (tasks.get(taskNumber - 1).getIsDone()) {
            throw new JustbotException("Hey man this task is already marked!");
        }
    }

    public void validateUnmarkTaskNumber(int taskNumber) throws JustbotException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException("Hey man there is no such task!");
        }
        if (!tasks.get(taskNumber - 1).getIsDone()) {
            throw new JustbotException("Hey man this task is already marked!");
        }
    }


}
