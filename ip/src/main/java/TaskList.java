import java.util.ArrayList;

public class TaskList {

    class Task {
        String name;
        boolean done;

        public Task(String name) {
            this.name = name;
        }
    }

    ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }
    public void list() {
        for(int i = 0; i < list.size(); i++) {
            Task task = this.list.get(i);
            String checkbox = task.done ? "[X]" : "[ ]";
            System.out.printf("%d. %s %s\n", i + 1, checkbox, task.name);
        }
    }
    public void add(String x){
        this.list.add(new Task(x));
    }

    public void unmark(int i){
        this.list.get(i-1).done = false;
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  [X] %s\n", this.list.get(i-1).name);
        System.out.println("____________________________________________________________");
    }

    public void mark(int i){
        this.list.get(i-1).done = true;
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've unmarked this task:");
        System.out.printf("  [X] %s\n", this.list.get(i-1).name);
        System.out.println("____________________________________________________________");
    }

}