import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
/**
 * Represents a list of tasks.
 * This class manages a collection of tasks and provides methods to add and list them.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private File file;

    FileWriter fw;
    String path;

    /**
     * Constructs an empty task list.
     */
    public TaskList(File file, String path){
        tasks = new ArrayList<>();
        this.file = file;
        this.path = path;

    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) throws IOException {
        tasks.add(task);
        writeToFile();
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks left in the list", tasks.size()));
        System.out.println("____________________________________________________________");
    }

    /**
          * Writes the current tasks to the associated file.
          *
          *
          * @throws IOException If an I/O error occurs while writing to the file.
     */

    private void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(this.path);
        StringBuilder textToAdd = new StringBuilder();
        for(int i = 0; i < this.tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskString = (i + 1) + ". " + task.getType() + task.getStatus()+ " " + task.getDescription() + " " + task.getDates();
            textToAdd.append(taskString).append("\n");
        }
        fw.write(textToAdd.toString());
        fw.close();

    }

    /**
     * Deletes a task from the task list by index and writes the updated list to a file.
     *
     * @param index The index of the task to be deleted.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void delete(int index) throws IOException {
        Task task = tasks.get(index);
        tasks.remove(index);
        writeToFile();
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks left in the list", tasks.size()));
        System.out.println("____________________________________________________________");

    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone(int index) throws IOException {
        Task task = tasks.get(index);
        task.done();
        writeToFile();
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone(int index) throws IOException {
        Task task = tasks.get(index);
        task.notDone();
        writeToFile();
    }

    /**
     * Returns a string representation of the task list.
     * Each task is represented with an index and a description.
     *
     * @return A string listing all tasks with their indices.
     */
    @Override
    public String toString() {
        String listOfTasks = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return listOfTasks;

    }
}


