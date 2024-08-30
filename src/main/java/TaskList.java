import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) throws java.io.IOException {
        tasks.add(task);
        FileWriter fw = new FileWriter("./data/dudu.txt", true);
        fw.write("\n" + task.formatString());
        fw.close();
        String output = LineWrapper.wrap(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.", task, tasks.size()));
        System.out.println(output);
    }

//    public int getIndex(String[] input) throws MissingDescriptionException {
//        if (input.length <= 1 || input[1].replaceAll("\\D+", "").isEmpty()) {
//            throw new MissingDescriptionException("Please input a number");
//        }
//        int index = Integer.parseInt(input[1].replaceAll("\\D+", "")) - 1;
//        if (index < 0 || index >= tasks.size()) {
//            throw new IllegalArgumentException("Please input a valid count");
//        }
//        return index;
//    }

    public void markTask(int index) {
        tasks.get(index).markCompleted();
    }

    public void unmarkTask(int index) {
        tasks.get(index).markUncompleted();
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }
}
