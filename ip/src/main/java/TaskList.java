import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }
    public void list() {
        for(int i = 0; i < list.size(); i++) {
            Task task = this.list.get(i);
            String checkbox = task.done ? "[X]" : "[ ]";
            System.out.printf("%d. %s\n", i + 1, task);
        }
    }
    public void add(String input){
        String[] words = input.split(" ");
        String[] parts = input.split(" /");
        switch(words[0]) {
        case "todo":
                String nameAndType = parts[0].substring(5);
                Task todo = new Todo(nameAndType);
                list.add(todo);

        break;
        case "event":
                nameAndType = parts[0].substring(6);
                String from = parts[1].replace("from ", "");
                String to = parts[2].replace("to ", "");
                Task event = new Event(nameAndType, from, to);
                list.add(event);


        break;
        case "deadline":
                nameAndType = parts[0].substring(6);
                String end = parts[1].replace("by ", "by: ");
                Task deadline = new Deadline(nameAndType, end);
                list.add(deadline);

        break;
        default:
        System.out.println("Unknown command");
        break;
        }
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

    public String acknowledge() {
        Task task = this.list.get(this.list.size() -1);
        String end = String.format("\nnow you have %d task(s) in the list", this.list.size());
        String result = "Got it. I've added this task:\n" + task.toString() + end;
        return result;
    }

}