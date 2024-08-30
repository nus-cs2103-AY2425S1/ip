package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import task.Task;
import todo.ToDo;
import deadline.Deadline;
import event.Event;

public class Storage {
    public ArrayList<Task> loadTaskListFromFile(String filePath) {
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                    case "ToDo":
                        ToDo todo = new ToDo(description);
                        if (isDone) todo.markAsDone();
                        taskList.add(todo);
                        break;
                    case "Deadline":
                        String by = parts[3];
                        Deadline deadline = new Deadline(description, by);
                        if (isDone) deadline.markAsDone();
                        taskList.add(deadline);
                        break;
                    case "Event":
                        String from = parts[3];
                        String to = parts[4];
                        Event event = new Event(description, from, to);
                        if (isDone) event.markAsDone();
                        taskList.add(event);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the task list: " + e.getMessage());
        }
        return taskList;
    }

    public void saveTaskListToFile(String filePath, ArrayList<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                writer.write(taskToString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the task list: " + e.getMessage());
        }
    }

    public static String taskToString(Task task) {
        String type = "";
        String status = task.isDone() ? "1" : "0";
        String description = task.getName();

        if (task instanceof ToDo) {
            type = "ToDo";
            return type + " | " + status + " | " + description;
        } else if (task instanceof Deadline) {
            type = "Deadline";
            String by = ((Deadline) task).getBy();
            return type + " | " + status + " | " + description + " | " + by;
        } else if (task instanceof Event) {
            type = "Event";
            String from = ((Event) task).getFrom();
            String to = ((Event) task).getTo();
            return type + " | " + status + " | " + description + " | " + from + " | " + to;
        }
        return "";
    }
}
