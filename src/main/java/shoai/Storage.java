package shoai;

import shoai.Task; // Imports the Task class for task handling
import shoai.Parser; // Imports the Parser class for converting tasks to and from file strings
import shoai.ShoAIException; // Imports the ShoAIException class for handling exceptions
import java.io.*; // Imports classes for file handling (BufferedReader, BufferedWriter, File, FileReader, FileWriter, IOException)
import java.util.ArrayList; // Imports ArrayList to store and manipulate the task list

/**
 * Handles the storage and retrieval of tasks and clients to and from files.
 */
public class Storage {
    private String taskPath;
    private String clientPath;

    /**
     * Creates a Storage object with specified file paths.
     *
     * @param taskPath  The path to the file where tasks are stored.
     * @param clientPath The path to the file where clients are stored.
     */
    public Storage(String taskPath, String clientPath) {
        this.taskPath = taskPath;
        this.clientPath = clientPath;
    }

    /**
     * Loads tasks from the specified file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws ShoAIException If there is an error reading from the file.
     */
    public ArrayList<Task> loadTasks() throws ShoAIException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(taskPath);
        if (!file.exists()) {
            return tasks; // No file to load, return empty list
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Parser.fileStringToTask(line));
            }
        } catch (IOException e) {
            throw new ShoAIException("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Loads clients from the specified file.
     *
     * @return An ArrayList of clients loaded from the file.
     * @throws ShoAIException If there is an error reading from the file.
     */
    public ArrayList<Client> loadClients() throws ShoAIException {
        ArrayList<Client> clients = new ArrayList<>();
        File file = new File(clientPath);
        if (!file.exists()) {
            return clients; // No file to load, return empty list
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                clients.add(Parser.fileStringToClient(line)); // Replace with appropriate method for Client
            }
        } catch (IOException e) {
            throw new ShoAIException("Error reading from file: " + e.getMessage());
        }
        return clients;
    }

    /**
     * Saves the specified tasks to the file.
     *
     * @param tasks The ArrayList of tasks to save.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        File file = new File(taskPath);
        file.getParentFile().mkdirs(); // Create the directory if it doesn't exist

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (Task task : tasks) {
                writer.write(Parser.taskToFileString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Saves the specified clients to the file.
     *
     * @param clients The ArrayList of clients to save.
     */
    public void saveClients(ArrayList<Client> clients) {
        File file = new File(clientPath);
        file.getParentFile().mkdirs(); // Create the directory if it doesn't exist

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (Client client : clients) {
                writer.write(Parser.clientToFileString(client)); // Replace with appropriate method for Client
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
