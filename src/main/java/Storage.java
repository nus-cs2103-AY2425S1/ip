import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) throws AsuraException {
        this.filePath = filePath;
    }

    public List<Task> load() throws AsuraException {
        List<Task> taskList = new ArrayList<>();
        File data = new File(filePath);
        data.getParentFile().mkdirs();
        try {
            data.createNewFile();
            Scanner scanner = new Scanner(data);
            while (scanner.hasNextLine()) {
                String[] task = scanner.nextLine().split("\\|");
                int status = Integer.parseInt(task[1]);
                switch (task[0]) {
                    case "T":
                        Todo todo = new Todo(task[2]);
                        taskList.add(todo);
                        break;
                    case "E":
                        Event event = new Event(task[2], task[3], task[4]);
                        taskList.add(event);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(task[2], LocalDateTime.parse(task[3]));
                        taskList.add(deadline);
                        break;
                }
                if (status == 1) {
                    taskList.get(taskList.size() - 1).markAsDone();
                }
            }
        } catch (Exception e) {
            throw new AsuraException(e.getMessage());
        }

        return taskList;
    }

    public void save(List<Task> taskList) throws AsuraException {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            if (task instanceof Event) {
                Event event = (Event) task;
                sb.append("E|").append(event.isDone ? 1 : 0).append("|").append(event.description).append("|").append(event.start).append("|").append(event.end).append("\n");
            }
            else if (task instanceof Todo) {
                Todo todo = (Todo) task;
                sb.append("T|").append(todo.isDone ? 1 : 0).append("|").append(todo.description).append("\n");
            }
            else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                sb.append("D|").append(deadline.isDone ? 1 : 0).append("|").append(deadline.description).append("|").append(deadline.by).append("\n");
            }
        }
        try {
            Files.write(Paths.get(filePath), sb.toString().getBytes());
        } catch (Exception e) {
            throw new AsuraException(e.getMessage());
        }
    }
}
