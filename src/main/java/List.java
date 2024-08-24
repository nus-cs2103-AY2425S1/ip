import java.util.ArrayList;

public class List {

    private ArrayList<Task> tasks;
    String line = "____________________________________________________________\n";

    public List(){
        this.tasks = new ArrayList<>(100);
    }

    public String numOfTasks() {
        if (tasks.size() < 2) {
            return "Now you have " + tasks.size() + " task in the list.\n";
        } else {
            return "Now you have " + tasks.size() + " tasks in the list.\n";
        }
    }
    public void addTaskToList(Task task) {
        tasks.add(task);
        System.out.println(line + task.addedString() + numOfTasks() + line);
    }

    public String displayList() {
        StringBuilder list = new StringBuilder();
        list.append("Here are the tasks in your list:\n");
        for (int i=0; i < tasks.size(); i++) {
            list.append(i+1).append(".").append(tasks.get(i).displayTask());
        }
        return line + list + line;
    }

    public Task getTask(int index) throws InvalidTaskException {
        if (tasks.get(index) == null) {
            throw new InvalidTaskException(index);
        }
        return tasks.get(index);
    }

    public void deleteTask(int index) throws InvalidTaskException {
        try {
            Task task = tasks.get(index);
            String display = line + "Noted. I've removed this task:\n"
                    + task.displayTask();
            tasks.remove(index);
            display = display + numOfTasks() + line;
            System.out.println(display);
        } catch (Exception e) {
            throw new InvalidTaskException(index);
        }
    }
}
