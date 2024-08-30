package alex.storage;

import alex.task.Deadline;
import alex.task.Event;
import alex.task.Task;
import alex.task.Todo;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;


public class Storage {
    public ArrayList<Task> tasks;

    public Storage(String directory, String file) {
        createDirectory(directory);
        loadTasksFromFile(file);
    }
    private void createDirectory(String directory) {
        File folder = new File(directory);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
    public ArrayList<Task> loadTasksFromFile(String file) {
        ArrayList<Task> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String desc;
            while ((desc = reader.readLine()) != null) {

                Task task;
                if (desc.startsWith("[T]")) {
                    String details = desc.substring(6).trim();
                    task = new Todo(details);
                } else if (desc.startsWith("[D]")) {
                    String details = desc.substring(6);
                    String[] info = details.split("//");
                    String item = info[0].trim();
                    String dueInter = info[1].substring(4).trim();
                    LocalDate dueDate = LocalDate.parse(dueInter);
                    task = new Deadline(item, dueDate);
                } else {
                    String details = desc.substring(6);
                    String[] info = details.split("//");
                    String item = info[0].trim();
                    String startInter = info[1].substring(6).trim();
                    LocalDate start = LocalDate.parse(startInter);
                    String dueInter = info[2].substring(4).trim();
                    LocalDate dueBy = LocalDate.parse(dueInter);
                    task = new Event(item, start, dueBy);
                }
                task.isDone = desc.substring(4,5).equals("X") ? true : false;
                list.add(task);
            }
        } catch (IOException e) {
            try {
                File dataFile = new File(file);
                dataFile.createNewFile();
            } catch (IOException ee) {
                System.err.println("Error reading tasks from file: " + ee.getMessage());
            }
        }
        tasks = list;
        return list;
    }

    public String saveTasksToFile(String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toBeSavedAsData());
                writer.newLine();
            }
            return "success";
        } catch (IOException e) {
            System.err.println("Error writing tasks to file: " + e.getMessage());
            return "fail";
        }
    }
}
