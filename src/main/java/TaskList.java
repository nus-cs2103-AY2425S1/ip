import java.lang.reflect.Array;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }
    public void list() {
        System.out.println("________________________________");
        for (int i = 0; i < taskList.size(); i++) {
            String output = String.valueOf(i + 1) + ". " + taskList.get(i).getString();
            System.out.println(output);
        }
        System.out.println("________________________________");
    }
    public Task addTodo(String description) {
        Task current = new Todo(description);
        taskList.add(current);
        return current;
    }

    public Task addEvent(String description) {
        String[] taskDetails = description.split(" /from ");
        String[] taskTimings = taskDetails[1].split(" /to ");
        Task current = new Event(taskDetails[0], taskTimings[0], taskTimings[1]);
        taskList.add(current);
        return current;
    }

    public Task addDeadline(String description) {
        String[] taskDetails = description.split(" /by ");
        Task current = new Deadline(taskDetails[0], taskDetails[1]);
        taskList.add(current);
        return current;
    }

    public Task mark(int index) {
        try {
            if (index < 0 || index >= taskList.size()) {
                throw new DukeException("Invalid position!");
            }
            Task curr = taskList.get(index);
            if (curr.isDone) {
                throw new DukeException("It is already marked!");
            }
            curr.mark();
            return curr;
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "\n________________________________");
            return null;
        }
    }

    public Task unmark(int index) {
        try {
            if (index < 0 || index >= taskList.size()) {
                throw new DukeException("Invalid position!");
            }
            Task curr = taskList.get(index);
            if (!curr.isDone) {
                throw new DukeException("It is already unmarked!");
            }
            curr.mark();
            return curr;
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "\n________________________________");
            return null;
        }
    }
    public Task delete(int index) {
        try {
            if (index < 0 || index >= taskList.size()){
                throw new DukeException("Invalid position!");
            }
            return taskList.remove(index);
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "\n________________________________");
            return null;
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
