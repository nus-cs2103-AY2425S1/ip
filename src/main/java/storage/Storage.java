package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public Task handleInput(String input) {
        String taskType = input.split(" ")[0];
        return switch (taskType) {
            case "T" -> new ToDo(input.substring(4), input.split(" ")[1].equals("0"));
            case "D" -> new Deadline(input.substring(4), input.split(" ")[1].equals("0"));
            case "E" -> new Event(input.substring(4), input.split(" ")[1].equals("0"));
            default -> null;
        };
    }

    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            file = new File(directory,filePath.substring(5));
            file.createNewFile();
            System.out.println(" File not found. Creating new file...");
            return tasks;
        }

        if (!isFileUncorrupted(file)) {
            throw new IOException(" File is corrupted");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = this.handleInput(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }

        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                fw.write(task.writeToFile());
                fw.write("\n");
                fw.flush();
            }
        }
    }

    public boolean isFileUncorrupted(File file) {
        Pattern TODO_PATTERN = Pattern.compile("^T\\s+\\d\\s+\\w+.*$");
        Pattern EVENT_PATTERN = Pattern.compile("^E\\s+\\d\\s+\\w+.*\\s+/from\\s+\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|30)\\s+/to\\s+\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|30)$");

        Pattern DEADLINE_PATTERN = Pattern.compile("^D\\s+\\d\\s+\\w+.*\\s+/by\\s+\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|30)$");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!TODO_PATTERN.matcher(line).matches() &&
                        !EVENT_PATTERN.matcher(line).matches() &&
                        !DEADLINE_PATTERN.matcher(line).matches()) {
                    return false;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
