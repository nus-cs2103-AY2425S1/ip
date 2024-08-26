import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        createFileIfDoesNotExist();
    }

    private void createFileIfDoesNotExist() {
        try {
            Files.createDirectories(filePath.getParent());
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public void save(TaskList tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(toCsv(task));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String toCsv(Task task) {
        StringBuilder sb = new StringBuilder();
        if(task instanceof ToDo) {
            sb.append("T").append(task.getStatus() ? "|1" : "|0").append("|").append(task.getDescription());
        } else if(task instanceof Deadline) {
            sb.append("D").append(task.getStatus() ? "|1" : "|0").append("|").append(task.getDescription()).append("|").append(((Deadline) task).getBy());
        } else if(task instanceof Event) {
            sb.append("E").append(task.getStatus() ? "|1" : "|0").append("|").append(task.getDescription()).append("|").append(((Event) task).getFrom()).append("|").append(((Event) task).getTo());
        }
        return sb.toString();
    }

    public TaskList load() {
        TaskList tasks = new TaskList();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] arguments = line.split("\\|", 3);
                String type = arguments[0];
                boolean isDone = arguments[1].equals("1");
                String description = arguments[2];

                switch (type) {
                case "T":
                    tasks.addTask(new ToDo(description));
                    break;
                case "D":
                    String[] deadlineArguments = description.split("\\|", 2);
                    tasks.addTask(new Deadline(deadlineArguments[0], deadlineArguments[1]));
                    break;
                case "E":
                    String[] eventArguments = description.split("\\|", 3);
                    tasks.addTask(new Event(eventArguments[0], eventArguments[1], eventArguments[2]));
                    break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

}