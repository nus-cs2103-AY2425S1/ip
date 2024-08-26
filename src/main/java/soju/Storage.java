package soju;

import soju.tasks.Deadline;
import soju.tasks.Event;
import soju.tasks.Task;
import soju.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filePath;
    File tasksFile;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasksFile = new File(filePath);
        try {
            boolean canMakeDirectory = tasksFile.getParentFile().mkdirs();
            boolean canCreateNewFile = tasksFile.createNewFile();
            if (canMakeDirectory && canCreateNewFile) {
                System.out.println("Creating new Tasks File for you at: " + tasksFile.getPath());
            } else {
                System.out.println("Unable to make directory or create new file");
            }
        } catch (IOException e) {
            System.out.println("Unable to create new soju.tasks file: " + e);
        }
    }

    public List<Task> load() throws SojuException {
        List<Task> taskList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task newTask = null;

                switch (taskType) {
                    case "T":
                        // Create a new Tasks.Todo task
                        newTask = new Todo(description);
                        break;
                    case "D":
                        // Create a new Tasks.Deadline task
                        String doneBy = parts[3];
                        LocalDate localDate = LocalDate.parse(doneBy);
                        newTask = new Deadline(description, localDate);
                        break;
                    case "E":
                        // Create a new Tasks.Event task
                        String[] eventTimes = parts[3].split(" - ");
                        String from = eventTimes[0];
                        String to = eventTimes[1];
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                        LocalDateTime localFromDate = LocalDateTime.parse(from);
                        LocalDateTime localToDate = LocalDateTime.parse(to);
                        newTask = new Event(description, localFromDate, localToDate);
                        break;
                    default:
                        System.out.println("Unknown task type: " + taskType);
                        break;
                }

                if (newTask != null) {
                    if (isDone) {
                        newTask.markAsDone();
                    }
                    taskList.add(newTask);
                }
            }
        } catch (IOException e) {
//            System.out.println("An error occurred while reading the file.");
//            e.printStackTrace();
            throw new SojuException("Error trying to read the file! It may be corrupted!");
        }
        return taskList;
    }

    public void saveToFile(TaskList tasks) throws SojuException {
        List<Task> listOfTasks = tasks.getTasks();
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : listOfTasks) {
                fileWriter.append(task.toFileString()).append("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
//            System.out.println("Error saving to file" + e);
            throw new SojuException("Error saving file!!!");
        }
    }

    public void saveToFile(Task task) throws SojuException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.append(task.toFileString()).append("\n");
            fileWriter.close();
        } catch (IOException e) {
//            System.out.println("Error saving to file" + e);
            throw new SojuException("Error saving file!!!");
        }
    }
}
