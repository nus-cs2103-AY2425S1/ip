package neon;

import neon.Deadline;
import neon.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(List<Task> tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toTask() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> load() throws IOException {
        try {
            Scanner scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            File directory = new File("data");
            File file = new File(directory, "data.txt");

            directory.mkdir();
            file.createNewFile();
        }

        Scanner scanner = new Scanner(new File("./data/data.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] content = line.split("/");

            boolean completed;
            switch (content[0]) {
                case "T":
                    completed = (Objects.equals(content[1], "1"));
                    Todo tempTodo = new Todo(content[2], completed);
                    tasks.add(tempTodo);
                    break;
                case "D":
                    completed = (Objects.equals(content[1], "1"));
                    Deadline tempDeadline = new Deadline(content[2], completed, content[3]);
                    tasks.add(tempDeadline);
                    break;
                case "E":
                    completed = (Objects.equals(content[1], "1"));
                    Event tempEvent = new Event(content[2], completed, content[3], content[4]);
                    tasks.add(tempEvent);
                    break;

            }
        }
        return tasks;
    }
}
