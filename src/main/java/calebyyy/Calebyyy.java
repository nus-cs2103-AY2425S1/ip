package calebyyy;

import calebyyy.Tasks.Task;
import calebyyy.Tasks.Todo;
import calebyyy.Tasks.Deadline;
import calebyyy.Tasks.Event;
import java.io.*;
import java.util.ArrayList;

public class Calebyyy {
    private ArrayList<Task> tasks;
    private CommandManager commandManager;
    private static final String FILE_PATH = "./data/duke.txt";


    public Calebyyy() {
        tasks = new ArrayList<>();
        commandManager = new CommandManager(this);
        loadTasks();
    }

    public void start() {
        greet();
        commandManager.startCommandLoop();
    }

    public void listTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = null;
    
                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        String by = parts[3];
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        task = new Event(description, from, to);
                        break;
                }
    
                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    public void saveTasks() {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void stop() {
        System.exit(0);
    }

    public void markTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
    }

    public void unmarkTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsNotDone();
    }

    public void deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Calebyyy");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        new Calebyyy().start();
    }
}