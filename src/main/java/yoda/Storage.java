package yoda;

import yoda.tasks.Deadline;
import yoda.tasks.Event;
import yoda.tasks.Task;
import yoda.tasks.ToDo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;


public class Storage {
    final static String FILE_PATH = "data/tasks.txt";

    public static void saveTasks(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);

        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fileWriter.write(task.getData() + "\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Error when writing file: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] splitInput = input.split(" \\| ");
                String type = splitInput[0];
                boolean isDone = splitInput[1].equals("1");
                String description = splitInput[2];

                Task newTask = null;
                switch (type) {
                case "T":
                    newTask = new ToDo(description);
                    break;
                case "D":
                    LocalDate by = LocalDate.parse(splitInput[3]);
                    newTask = new Deadline(description, by);
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(splitInput[3]);
                    LocalDateTime to = LocalDateTime.parse(splitInput[4]);
                    newTask = new Event(description, from, to);
                    break;
                }
                if (isDone && newTask != null) {
                    newTask.markDone();
                }
                tasks.add(newTask);

            }
        } catch (Exception e) {
            System.out.println("Encountered error :" + e.getMessage());
        }
        return tasks;
    }
}
