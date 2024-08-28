import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) throws JudeException {
        this.list = list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void deleteTask(int index) throws JudeException {
        validateIndex(index);
        list.remove(index);
    }

    public void markTask(int index) throws JudeException {
        validateIndex(index);
        list.get(index).markAsDone();
    }

    public void unmarkTask(int index) throws JudeException {
        validateIndex(index);
        list.get(index).unmarkAsDone();
    }

    public Task getTask(int index) throws JudeException {
        validateIndex(index);
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public String toFileFormat() {
        String text = "";
        for (Task task : list) {
            text += task.toFileFormat() + "\n";
        }
        return text;
    }

    public String toUiFormat() {
        String string = "";
        for (int i = 0; i < list.size(); i++) {
            string += String.format("%d. %s\n", (i + 1), list.get(i).toStringDetails());
        }
        return string;
    }

    private void validateIndex(int index) throws JudeException {
        if (index < 0 || index >= list.size()) {
            throw new JudeException("You are trying to get an element of index out of the list size.");
        }
    }
}
