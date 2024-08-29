package NextGPT;
import java.util.ArrayList;
import java.util.Scanner;
import NextGPT.task.Todo;
import NextGPT.task.Deadline;
import NextGPT.task.Event;
import NextGPT.task.Task;
public class TaskList {
    static ArrayList<Task> todo_list;
    public TaskList(){
        this.todo_list = new ArrayList<>();
    }
    public TaskList(Scanner sc) {
        this.todo_list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            String[] split = task.split(",");
            String taskType = split[0];
            Boolean isDone = split[1].equals("X");
            String desc = split[2];
            if (taskType.equals("T")) {
                Todo todo = new Todo(desc);
                this.todo_list.add(todo);
                if (isDone) {
                    todo.mark();
                }
            } else if (taskType.equals("D")) {
                String by = split[3];

                Deadline deadline = new Deadline(desc, by);
                this.todo_list.add(deadline);
                if (isDone) {
                    deadline.mark();
                }
            } else {
                String from = split[3];
                String to = split[4];
                Event event = new Event(desc, from, to);
                this.todo_list.add(event);
                if (isDone) {
                    event.mark();
                }
            }

        }
    }

    public void add(Task task) {

        this.todo_list.add(task);
    }

    public Task remove(int index) {

        return this.todo_list.remove(index);
    }
    public Task get(int index) {

        return this.todo_list.get(index);
    }

    public int size() {
        return this.todo_list.size();
    }

    @Override
    public String toString() {

        return this.todo_list.toString();
    }

}
