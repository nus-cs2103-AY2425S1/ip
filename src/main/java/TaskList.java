import java.util.ArrayList;
import java.util.Objects;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<Task>();

    public Task getTask(int index) {
        try {
            return this.taskList.get(index);
        } catch (Exception e) {
            if (this.taskList.isEmpty()) {
                FormattedPrinting.emptyList();
                return null;
            }
            FormattedPrinting.outOfListBounds(this);
            return null;
        }

    }

    public void addTask(Task task) {
        if (!Objects.equals(task.description.trim(), "")) {
            Storage.appendToListFile(task);
            this.taskList.add(task);
            FormattedPrinting.addTask(task, this);
        } else {
            FormattedPrinting.descriptionEmptyError();
        }
    }

    public void loadTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(Task task) {
        if (!Objects.equals(task.description.trim(), "")) {
            this.taskList.remove(task);
            Storage.deleteTaskFromFile(this);
            FormattedPrinting.deleteTask(task, this);
        } else {
            FormattedPrinting.descriptionEmptyError();
        }
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void clearList() {
        this.taskList.clear();
        FormattedPrinting.clearList();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Task task : this.taskList) {
            output.append(task.convertToFileFormat()).append("\n");
        }
        return output.toString();
    }
}
