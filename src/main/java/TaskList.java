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

    public Task get(int index) throws AstraException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new AstraException("Invalid index.");
        }
    }

    public Task delete(int index) throws AstraException {
        try {
            Task t = this.get(index);
            tasks.remove(index - 1);
            saveTasks();
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new AstraException("Invalid index.");
        }
    }

    public Task markAsDone(int index, boolean done) throws AstraException {
        try {
            Task t = this.get(index);
            t.setDone(done);
            saveTasks();
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new AstraException("Invalid index.");
        }
    }

    public int length() {
        return tasks.size();
    }

    private void loadTasks() {
        File f = new File(PATH + "/tasks.txt");
        try {
            Scanner inp = new Scanner(f);
            while (inp.hasNextLine()) {
                String line = inp.nextLine();
                this.add(Task.fromText(line));
            }
            inp.close();
        } catch (FileNotFoundException ignored) {
        } catch (Exception e) {
            System.out.println("Data file corrupted, failed to read all tasks.");
        }
    }

    private void saveTasks() {
        try {
            File dir = new File(PATH);
            dir.mkdirs();
            FileWriter fw = new FileWriter(PATH + "/tasks.txt");
            fw.write(this.toText());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
