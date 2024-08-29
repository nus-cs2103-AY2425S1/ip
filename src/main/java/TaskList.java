import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private Ui ui = new Ui();
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void save(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.stringData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void isDue(LocalDate date) {
        for(int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            if(temp instanceof Deadline) {
                Deadline tempDeadline = (Deadline) temp;

                if(tempDeadline.isEqual(date)) {
                    System.out.println(temp);
                }
            }
        }
    }

    public void list() {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i) + "\n");
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int i) throws AiException {
        tasks.remove(i);
    }
}
