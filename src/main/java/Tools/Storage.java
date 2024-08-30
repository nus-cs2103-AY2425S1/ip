package Tools;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws FileNotFoundException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);
        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] split = line.split(" \\| ");
                switch (split[0]) {
                case "T":
                    if (split[1].equals("1")) {
                        Todo todo = new Todo(split[2]);
                        todo.markDone();
                        tasks.add(todo);
                    } else {
                        Todo todo = new Todo(split[2]);
                        tasks.add(todo);
                    }
                    break;

                case "D":
                    if (split[1].equals("1")) {
                        Deadline deadline = new Deadline(split[2], split[3]);
                        deadline.markDone();
                        tasks.add(deadline);
                    } else {
                        Deadline deadline = new Deadline(split[2], split[3]);
                        tasks.add(deadline);
                    }
                    break;
                case "E":
                    if (split[1].equals("1")) {
                        Event event = new Event(split[2], split[3], split[4]);
                        event.markDone();
                        tasks.add(event);
                    } else {
                        Event event = new Event(split[2], split[3], split[4]);
                        tasks.add(event);
                    }
                    break;
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            new File("./data").mkdirs();
            try {
                file.createNewFile();
            } catch (Exception ex) {
                System.out.println("Unable to create data file");
            }
        }

        return tasks;
    }

    public void saveTasks(TaskList tasks) {
        File file = new File(filePath);

        if (!file.exists()) {
            File directory = new File(file.getParent());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Unable to create data file: " + e.getMessage());
                return;
            }
        }

        try (PrintWriter writer = new PrintWriter(file)) {
            for (Task task : tasks.getTasks()) {
                writer.println(task.toFileFormat());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file.");
        }
    }

}
