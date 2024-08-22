import java.util.ArrayList;

public class HimList {
    private ArrayList<Task> list;

    public HimList() {
        this.list = new ArrayList<>();
    }

    public void add(Task item) {
        list.add(item);
    }

    public class TaskDoesNotExistException extends Exception {
        public TaskDoesNotExistException(int index) {
            super("Task " + (index + 1) + " does not exist");
        }
    }

    public void complete(int index) throws Task.AlreadyCompletedException, TaskDoesNotExistException {
        try {
            Task task = list.get(index);
            if (task == null) throw new TaskDoesNotExistException(index);
            task.complete();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskDoesNotExistException(index);
        }
    }

    public String taskAt(int index) {
        return list.get(index).toString();
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            output.append(String.format("%d.[%s][%s] %s\n", i + 1, list.get(i).getTypeIcon(), list.get(i).getStatusIcon(), list.get(i)));
        }
        return output.toString();
    }
}
