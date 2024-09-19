package bob.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.TaskList;
import bob.task.Todo;

/**
 * Handles operations relating to saving or loading tasks in the file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for the Storage instance.
     * @param filePath File path to the location of the file where records are saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Updates Bob's records and prints out all existing records.
     *
     * @return Arraylist of tasks based on the previous instance when the chatBot was running.
     */
    public ArrayList<Task> loadTaskList() {
        try {
            File file = getFileFromFilePath();
            ArrayList<Task> tempRecords = uploadTasksFromFileToRecords(file);
            displayCurrentRecords(tempRecords);
            return tempRecords;
        } catch (IOException e) {
            super.toString();
        }
        return null;
    }

    /**
     * Ensures that a file is present to save the records into upon command.
     *
     * @return File specified by the file path.
     * @throws IOException
     */
    private File getFileFromFilePath() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            boolean isFileCreated = file.createNewFile();
            assert isFileCreated : "File should be created if no file was found.";
        }
        return file;
    }

    /**
     * Returns an arraylist containing tasks read from a file.
     *
     * @param file
     * @return
     * @throws IOException
     */
    private ArrayList<Task> uploadTasksFromFileToRecords(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();

        ArrayList<Task> tempRecords = new ArrayList<>();
        while (line != null && !line.equals("")) {
            Task task = this.loadTask(line);
            tempRecords.add(task);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return tempRecords;
    }

    /**
     * Prints the display of the current records passed into the function.
     *
     * @param currentRecords Arraylist of current records.
     */
    private void displayCurrentRecords(ArrayList<Task> currentRecords) {
        System.out.println("\t====== Current Records =====\n");
        for (Task x: currentRecords) {
            System.out.println(x.getFileFormat());
        }
    }

    /**
     * Reads file and updates taskList for the next instruction given by user.
     *
     * @return Updated TaskList.
     */
    public TaskList loadUpdatedTaskList() {
        try {
            File file = getFileFromFilePath();
            ArrayList<Task> tempRecords = uploadTasksFromFileToRecords(file);
            return new TaskList(tempRecords);
        } catch (IOException e) {
            super.toString();
        }
        return new TaskList();
    }

    /**
     * Parses the content from a line in the saved file to return the corresponding Task object.
     *
     * @param lineInput A single line in the saved file.
     * @return Task based on the data specified in the lineInput.
     * @throws IOException
     */
    public Task loadTask(String lineInput) throws IOException {
        String[] parts = lineInput.split(" \\| ");
        String taskLetter = parts[0];
        boolean isDone = (parts[1].equals("1")) ? true : false;
        String taskDescription = parts[2];

        switch (taskLetter) {
        case "T":
            return new Todo(taskDescription, isDone);
        case "D":
            String endDate = parts[3];
            return new Deadline(taskDescription, endDate, isDone);
        case "E":
            String startDay = parts[3];
            String startTime = parts[4];
            String endTime = parts[5];
            return new Event(taskDescription, startDay, startTime, endTime, isDone);
        default:
            throw new IOException("unable to parse Data for loading.");
        }
    }

    /**
     * Saves task list to storage
     *
     * @param taskList
     */
    public void saveTaskListToStorage(TaskList taskList) {
        ArrayList<Task> records = taskList.getAllRecords();
        File file = new File(filePath);
        try {
            getParentFile(file);
            getFileFromFilePath();
            saveRecordsToFile(records, file);
        } catch (IOException e) {
            System.err.println("Unable to save records to storage.");
        }
    }

    /**
     * Saves each task in records into the specified file.
     *
     * @param records Current records
     * @param file File that stores information about the records.
     * @throws IOException
     */
    private static void saveRecordsToFile(ArrayList<Task> records, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Task x: records) {
            String fileFormatLine = x.getFileFormat();
            bufferedWriter.write(fileFormatLine);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    /**
     * Get the parent directory of the specified file.
     *
     * @param file
     */
    private static void getParentFile(File file) {
        File dataDir = file.getParentFile();
        if (dataDir != null && !dataDir.exists()) {
            boolean isDirCreated = dataDir.mkdir();
            assert isDirCreated : "Directory should be created.";
        }
    }
}
