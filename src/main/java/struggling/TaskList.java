package struggling;
import struggling.task.Deadline;
import struggling.task.Event;
import struggling.task.Task;
import struggling.task.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> input) {
        tasks = new ArrayList<>();
        for(String s: input) {
            String[] args = s.split(" \\| ");
            Task task = switch (args[0]) {
                case "T" -> new ToDo(args[2]);
                case "D" -> new Deadline(args[2], LocalDate.parse(args[3]));
                case "E" -> new Event(args[2], args[3], args[4]);
                default -> throw new StrugglingException("Save file corrupted");
            };

            if (Objects.equals(args[1], "1")) {
                task.mark();
            } else if (!Objects.equals(args[1], "0")) {
                throw new StrugglingException("Save file corrupted");
            }

            this.tasks.add(task);
        }
    }

    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }

    public Task deleteTask(int i) {
        int index = i - 1;
        Task t = this.tasks.remove(index);
        return t;
    }

    public Task markTask(int i) {
        int index = i - 1;
        Task t = this.tasks.get(index);
        t.mark();
        return t;
    }

    public Task unmarkTask(int i) {
        int index = i - 1;
        Task t = this.tasks.get(index);
        t.unmark();
        return t;
    }

    public ArrayList<String> getTasksString() {
        ArrayList<String> arr = new ArrayList<>();

        for(Task t : tasks) {
            arr.add(t.toString());
        }

        return arr;
    }

    public ArrayList<String> getTasksState() {
        ArrayList<String> arr = new ArrayList<>();

        for(Task t : tasks) {
            arr.add(t.getState());
        }

        return arr;
    }

    public int getSize() {
        return this.tasks.size();
    }
}
