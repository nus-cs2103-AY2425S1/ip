import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    protected TaskList tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new TaskList();
    }

    public void loadTasks() {
        File file = new File(this.filePath);
        try {
            // create new file if it doesn't exist
            // if file already exists, read its contents
            if (!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    createTask(line);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTasks(TaskList tasks) {
        File file = new File(this.filePath);
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < tasks.getSize(); i++) {
                writer.write(writeTask(tasks.getTask(i)));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    private void createTask(String line) {
        String[] info = line.split(",");
        String taskType = info[0];
        boolean isDone = Integer.parseInt(info[1]) == 1;
        Task newTask;
        switch (taskType) {
            case "todo":
                newTask = new Todo(info[2]);
                break;
            case "deadline":
                newTask = new Deadline(info[2], info[3]);
                break;
            case "event":
                newTask = new Event(info[2], info[3], info[4]);
                break;
            default:
                newTask = new Task(info[2]);
        }
        if (isDone) {
            newTask.markAsDone();
        }
        tasks.addTask(newTask);
    }

    private String writeTask(Task t) {
        StringBuilder s = new StringBuilder();
        if (t instanceof Todo) {
            s.append("todo").append(",")
                    .append((t.isDone ? 1 : 0)).append(",")
                    .append(t.description);
        } else if (t instanceof Deadline) {
            s.append("deadline").append(",")
                    .append((t.isDone ? 1 : 0)).append(",")
                    .append(t.description).append(",")
                    .append(((Deadline) t).deadline);
        } else if (t instanceof Event) {
            s.append("event").append(",")
                    .append((t.isDone ? 1 : 0)).append(",")
                    .append(t.description).append(",")
                    .append(((Event) t).from).append(",")
                    .append(((Event) t).to);
        } else {
            s.append("task");
        }
        s.append("\n");
        return s.toString();
    }
}
