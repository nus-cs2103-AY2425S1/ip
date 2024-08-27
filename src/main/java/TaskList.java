import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final String PATH = "./data";

    public TaskList() {
        tasks = new ArrayList<>();
        loadTasks();
    }

    public String toText() {
        int len = tasks.size();
        StringBuilder text = new StringBuilder();
        for (Task task : tasks) {
            text.append(task.toText()).append('\n');
        }
        return text.toString();
    }

    @Override
    public String toString() {
        int len = tasks.size();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < len; i++) {
            text.append(String.format("%d. %s \n", i + 1, tasks.get(i)));
        }
        return text.toString();
    }

    public void add(Task task) {
        tasks.add(task);
        saveTasks();
    }

    public void delete(int index) throws AstraException {
        try {
            tasks.remove(index - 1);
            saveTasks();
        } catch (IndexOutOfBoundsException e) {
            throw new AstraException("Invalid index.");
        }
    }

    public Task get(int index) throws AstraException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new AstraException("Invalid index.");
        }
    }

    public int length() {
        return tasks.size();
    }

    private void loadTasks() {

    }

    private void saveTasks() {
        try {
            File dir = new File(PATH);
            dir.mkdirs();
            FileWriter fw = new FileWriter(PATH + "/tasks.txt");
            fw.write(this.toText());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
