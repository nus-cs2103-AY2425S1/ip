package froggy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() {
        List<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        try {
            if (file.exists()) {
                System.out.println("Task list found.");
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        boolean isDone = (line.charAt(2) == '1');
                        switch (line.charAt(0)) {
                        case 'T':
                            Task newTodo = new Todo(line.substring(4));
                            newTodo.setStatus(isDone);
                            taskList.add(newTodo);
                            break;
                        case 'D':
                            int index = line.indexOf('|');
                            String by = line.substring(index + 2);
                            Task newDeadline = new Deadline(line.substring(4, index - 1), by);
                            newDeadline.setStatus(isDone);
                            taskList.add(newDeadline);
                            break;
                        case 'E':
                            int index1 = line.indexOf('|');
                            int index2 = line.indexOf('|', index1 + 1);
                            String from = line.substring(index1 + 2, index2 - 1);
                            String to = line.substring(index2 + 2);
                            Task newEvent = new Event(line.substring(4, index1 - 1), from, to);
                            newEvent.setStatus(isDone);
                            taskList.add(newEvent);
                            break;
                        default:
                            System.out.println("File error");
                        }
                    }
                }
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("No task list found. Created new task list.");
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to read task list file.");
        }
        return taskList;
    }

    public void saveTasks(List<Task> newTaskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task t : newTaskList) {
                writer.write(t.toTxt());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to save task list.");
        }
    }

    public void saveTasks(TaskList newTaskList) {
        saveTasks(newTaskList.getTasks());
    }
}
