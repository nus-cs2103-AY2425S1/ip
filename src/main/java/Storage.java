import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> storage;
    private static FileWriter writer = null;
    private Task recentTask;

    public Storage() {
        this.storage = new ArrayList<>(100);
    }

    //Adds the input task to the storage array
    public void addTask(Task task) throws IOException{
        this.storage.add(task);
        task.save();
    }

    //Returns a string containing all the tasks in the storage array
    public String getTasks() {
        String taskList = "";
        int i = 1;
        for (Task task : this.storage) {
            taskList += i + "." + task.toString() + "\n";
            i++;
        }
        return taskList;
    }

    //Return a specific task from the storage array based on the index
    public Task getTask(int index) {
        return this.storage.get(index);
    }

    //Returns the size of the storage array
    public int getSize() {
        return this.storage.size();
    }

    //Returns a string with the number of tasks remaining
    public String getNumOfTasks() {
        return "Now you have " + this.getSize() + " tasks in the list.";
    }

    //Deletes a task from the storage array based on the index
    public void deleteTask(int index) throws IOException{
        this.storage.remove(index);
        this.updateStorageFile();
    }

    public static void setWriter(FileWriter writer) {
        Storage.writer = writer;
    }

    public static FileWriter getWriter() {
        return Storage.writer;
    }

    public static void closeWriter() throws IOException {
        Storage.writer.close();
    }

    public void updateStorageFile() throws IOException {
        Storage.closeWriter();
        FileWriter writer = new FileWriter("ip/data/BMO.txt");
        Storage.setWriter(writer);
        for (Task task : this.storage) {
            task.save();
        }
    }

    public void markTask(int index) throws IOException {
        this.getTask(index).mark();
        this.updateStorageFile();
    }

    public void unmarkTask(int index) throws IOException {
        this.getTask(index).unmark();
        this.updateStorageFile();
    }

    public void addTodo(String description) throws IOException {
        Task todo = new ToDo(description);
        this.addTask(todo);
        setRecentTask(todo);
    }

    public void addDeadline(String description, String by) throws IOException {
        Task deadline = new Deadline(description, by);
        this.addTask(deadline);
        setRecentTask(deadline);
    }

    public void addEvent(String description, String from, String to) throws IOException {
        Task event = new Event(description, from, to);
        this.addTask(event);
        setRecentTask(event);
    }

    private void setRecentTask(Task task) {
        this.recentTask = task;
    }

    public Task getRecentTask() {
        return this.recentTask;
    }

    public void readStorageFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
             String line;
             while ((line = reader.readLine()) != null) {
                String[] taskDetails = line.split(" \\| ");
                switch (taskDetails[0]) {
                case "T":
                    this.addTodo(taskDetails[2]);
                    if (taskDetails[1].equals("1")) {
                        this.getRecentTask().mark();
                    }
                    break;
                case "D":
                    this.addDeadline(taskDetails[2], taskDetails[3]);
                    if (taskDetails[1].equals("1")) {
                        this.getRecentTask().mark();
                    }
                    break;
                case "E":
                    this.addEvent(taskDetails[2], taskDetails[3], taskDetails[4]);
                    if (taskDetails[1].equals("1")) {
                        this.getRecentTask().mark();
                    }
                    break;
                default:
                    break;
                }
                this.updateStorageFile();
             }
        } catch (IOException e) {
            throw new IOException("File not found");
        }
    }
}
