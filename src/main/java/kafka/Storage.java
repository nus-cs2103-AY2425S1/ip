package kafka;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    public final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        this.getNewFile(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        this.printFileContents(tasks);
        return tasks;
    }

    public void printFileContents(ArrayList<Task> tasks) {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] fileContent = s.nextLine().trim().split(" \\| ");
                String taskType = fileContent[0];
                boolean isDone = Boolean.parseBoolean(fileContent[1]);
                String description = fileContent[2];
                switch (taskType) {
                    case "T":
                        Task todo = new Todo(description, isDone);
                        tasks.add(todo);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(fileContent[3]);
                        Task deadline = new Deadline(description, by, isDone);
                        tasks.add(deadline);
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(fileContent[3]);
                        LocalDateTime to = LocalDateTime.parse(fileContent[4]);
                        Task event = new Event(description, from, to, isDone);
                        tasks.add(event);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void writeToFile(ArrayList<Task> tasks) {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task t : tasks) {
                if (t instanceof Todo todo) {
                    fw.write("T | " + todo.isDone + " | "
                            + todo.description + System.lineSeparator());
                } else if (t instanceof Deadline deadline) {
                    fw.write("D | " + deadline.isDone + " | "
                            + deadline.description + " | " + deadline.by + System.lineSeparator());
                } else if (t instanceof Event event) {
                    fw.write("E | " + event.isDone + " | "
                            + event.description + " | " + event.from + " | " + event.to
                            + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void getNewFile(String filePath) {
        File f = new File(filePath);
        try {
            f.getParentFile().mkdirs();
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }
}
