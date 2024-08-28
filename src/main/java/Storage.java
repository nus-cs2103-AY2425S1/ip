import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Path;


public class Storage {
    public Path path;
    public Path file;

    public Storage(String path) {
        this.path = Paths.get(path);
        this.file = Paths.get(path + "tissue.csv");
    }

    public void save(Task task) {
        String parsedTask = parseTask(task);
        try {
            Files.createDirectories(path);
            Files.write(file, parsedTask.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String taskType = values[0].strip();
                switch (taskType) {
                    case "T":
                        taskList.add(new ToDo(Integer.parseInt(values[1]), values[2]));
                        break;
                    case "E":
                        taskList.add(new Event(Integer.parseInt(values[1]), values[2].strip(),
                                values[3].strip(), values[4].strip()));
                        break;

                    case "D":
                        taskList.add(new Deadline(Integer.parseInt(values[1]), values[2].strip(),
                                values[3].strip()));
                        break;

                    default:
                        break;
                }

            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return taskList;
    }

    private String parseTask(Task task) {
        String temp = "";
        if (task instanceof ToDo) {
            temp = String.format("T,%s,%s\n", task.getDone() ? 1 : 0, task.getTask());

        } else if (task instanceof Event) {
            Event event = (Event) task;
            temp = String.format("E,%s,%s,%s,%s\n", event.getDone() ? 1 : 0, event.getTask(),
                    event.getFrom(), event.getTo());
        } else if (task instanceof Deadline) {
            Deadline dl = (Deadline) task;
            temp = String.format("D,%s,%s,%s\n", dl.getDone() ? 1 : 0, dl.getTask(), dl.getBy());
        }
        return temp;
    }

    public void delete(int line) {
        try {
            List<String> lines = Files.readAllLines(file);
            if (line <= lines.size()) {
                lines.remove(line - 1);
                Files.write(file, lines);
                System.out.println("Line " + line + " deleted successfully.");
            } else {
                System.out.println("Line number out of range.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
