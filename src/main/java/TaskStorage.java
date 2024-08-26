import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskStorage {
    private ArrayList<Task> taskList;
    private int numberOfTasks;
    private final String filePath = "./data/taskFile.txt";
    private File taskFile;

    private static void readTaskFromFile(File file, ArrayList<Task> taskList) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        if (s.hasNext()) {
            s.nextLine(); // skip first line
        }

        while (s.hasNext()) {
            String taskString = s.nextLine();
            String[] arr = taskString.split("\\|");

            Task currentLoadedTask = null; // bad implementation...
            boolean isDone = arr[1].equals("1");

            if (arr[0].equals("T")) {
                currentLoadedTask = new Todo(arr[2], isDone);
            } else if (arr[0].equals("D")) {
                currentLoadedTask = new Deadline(arr[2], arr[3], isDone);
            } else if (arr[0].equals("E")) {
                currentLoadedTask = new Event(arr[2], arr[3], arr[4], isDone);
            }

            if (currentLoadedTask != null) {
                taskList.add(currentLoadedTask);
            }
        }
    }

    private static void appendTaskToFile(File file, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.newLine();
        writer.write(text);
        writer.close();
    }

    public TaskStorage() {
        this.taskList = new ArrayList<>();

        try {
            taskFile = new File(filePath);
            File folder = new File("./data");
            if (!folder.exists()){ // Checks if directory exists
                folder.mkdirs();
            }
            taskFile.createNewFile(); // Attempts to create new file if not present
            readTaskFromFile(taskFile, this.taskList); // Read file and load all saved tasks
        } catch (IOException e) {
            System.err.println(e);
        }

        this.numberOfTasks = this.taskList.size();
    }

    public void addTask(Task task) {
        // Add task into the task list
        this.taskList.add(task);
        System.out.println("\tAdded: " + task);
        this.numberOfTasks++;
        System.out.println("\tYou now have " + this.numberOfTasks + " tasks.");

        // Save task into the file
        String text = task.getSaveTaskString();
        try {
            TaskStorage.appendTaskToFile(taskFile, text);
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    public void deleteTask(int i) throws InvalidTaskException {
        if (i <= 0 || i > this.numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(i - 1);
        this.taskList.remove(i - 1);
        System.out.println("\tDeleted: " + task);
        this.numberOfTasks--;
        System.out.println("\tYou now have " + this.numberOfTasks + " tasks.");
    }

    public void markTask(int i) throws InvalidTaskException {
        if (i <= 0 || i > this.numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(i - 1);
        task.markDone();
        System.out.println("\tGood Job! The task is now marked as done: ");
        System.out.println("\tMarked task: " + task);
    }

    public void unmarkTask(int i) throws InvalidTaskException {
        if (i <= 0 || i > this.numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(i - 1);
        task.markNotDone();
        System.out.println("\tAlright, the task is marked as not done: ");
        System.out.println("\tUnmarked task: " + task);
    }

    public void listAllTasks() {
        System.out.println("\tYou currently have " + this.numberOfTasks + " tasks.");
        for (int i = 0; i < numberOfTasks; i++) {
            int num = i + 1;
            Task task = taskList.get(i);
            System.out.println("\t" + num + ". " + task);
        }
    }
}
