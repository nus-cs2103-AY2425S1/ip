package edith;

import edith.task.DeadlineTask;
import edith.task.EventTask;
import edith.task.Task;
import edith.task.ToDoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Storage {

    private static final String FILE_PATH = "./data/edith.txt";
    private static final File DIRECTORY = new File("./data");

    public static void saveTasks(ArrayList<Task> toDoList) {
        if (!DIRECTORY.exists()) {
            DIRECTORY.mkdirs();
        }
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : toDoList) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("oops! an error occurred while saving tasks: " + e.getMessage());
        }
    }

    public static void loadTasks(ToDoList toDoList) {
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<String> properties = List.of(line.split(" \\| "));
                if (Objects.equals(properties.get(0), "T")) {
                    ToDoTask task = new ToDoTask(properties.get(2));
                    if (Objects.equals(properties.get(1), "1")) {
                        task.check();
                    }
                    toDoList.add(task);
                } else if (Objects.equals(properties.get(0), "D")) {
                    DeadlineTask task = new DeadlineTask(properties.get(2), properties.get(3));
                    if (Objects.equals(properties.get(1), "1")) {
                        task.check();
                    }
                    toDoList.add(task);
                } else if (Objects.equals(properties.get(0), "E")) {
                    EventTask task = new EventTask(properties.get(2), properties.get(3), properties.get(4));
                    if (Objects.equals(properties.get(1), "1")) {
                        task.check();
                    }
                    toDoList.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(" psa: you currently do not have the file edith.txt under the data directory. i'll create" +
                    "one for you right now!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(" oops!!! there's something wrong with edith.txt's format... please check it! for now," +
                    "i will remove the tasks that have formatting issues.");
        }
    }
}
