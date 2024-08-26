import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final ArrayList<Task> list;

    public Storage() {
        this.list = new ArrayList<>();
    }

    /**
     * Checks if the folder exists.
     *
     * @param folderPath Path of folder containing the file with the saved data
     */
    public void checkFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            boolean isFolderCreated = folder.mkdirs();
            if (!isFolderCreated) {
                System.out.println("Failed to create directory: " + folderPath);
            }
        }
    }

    /**
     * Returns a Boolean indicating if the file exists.
     *
     * @param filePath Path of file containing saved data
     * @return Boolean indicating if the file exists
     */
    public boolean checkFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                boolean isFileCreated = file.createNewFile();
                if (!isFileCreated) {
                    System.out.println("Failed to create file: " + filePath);
                }
                return false;
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        return true;
    }

    /**
     * Loads the saved data from the file.
     *
     * @param filePath Path of file containing saved data
     * @return List of tasks loaded from the file
     * @throws IOException If an I/O error occurs
     * @throws DemureBotException If the saved task has invalid format
     */
    public ArrayList<Task> load(String filePath) throws IOException, DemureBotException {
        Scanner fileScanner = new Scanner(new File(filePath));
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            Task task = getTask(line);
            list.add(task);
        }
        return list;
    }

    /**
     * Returns a Task created from saved data.
     *
     * @param line Line of text read from saved data.
     * @return Task created from saved data.
     * @throws DemureBotException If the saved task has invalid format.
     */
    private Task getTask(String line) throws DemureBotException {
        String[] split = line.split(" \\| ");
        validateTaskFormat(split, 3);
        String type = split[0];
        boolean isDone = split[1].equals("1");
        String description = split[2];
        Task task = null;
        switch (type) {
            case "T":
                task = new Todo(description, isDone);
                break;
            case "D":
                validateTaskFormat(split, 4);
                String by = split[3];
                task = new Deadline(description, by, isDone);
                break;
            case "E":
                validateTaskFormat(split, 5);
                String from = split[3];
                String to = split[4];
                task = new Event(description, from, to, isDone);
                break;
        }
        if (task == null) {
            throw new DemureBotException("Saved task has invalid format");
        }
        return task;
    }

    /**
     * Throws an exception if the array length is less than the expected length.
     *
     * @param split Array of strings.
     * @param expectedLength Expected length of array.
     * @throws DemureBotException If the array length is less than the expected length.
     */
    private void validateTaskFormat(String[] split, int expectedLength) throws DemureBotException {
        if (split.length < expectedLength) {
            throw new DemureBotException("Saved task has invalid format");
        }
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param filePath Path of file to save the list of tasks.
     * @param list List of tasks to be saved.
     */
    public void save(String filePath, TaskList list){
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
            for (int i = 0; i < list.getSize(); i++) {
                Task task = list.getTask(i);
                String formattedTask = formatTask(task);
                writer.write(formattedTask + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error closing FileWriter: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Returns a formatted string of the task to be saved.
     *
     * @param task Task to be saved.
     * @return Formatted string of the task to be saved
     */
    private String formatTask(Task task) {
        String[] parts = task.toString().split("]", 3);
        String type = parts[0].substring(1, 2);
        String status = parts[1].charAt(1) == 'X' ? "1" : "0";
        String description = parts[2].trim();

        if (type.equals("D")) {
            int byIndex = description.indexOf("(by: ");
            String by = description.substring(byIndex + 5, description.length() - 1);
            description = description.substring(0, byIndex - 1);
            return type + " | " + status + " | " + description + " | " + by;
        } else if (type.equals("E")) {
            int fromIndex = description.indexOf("(from: ");
            int toIndex = description.indexOf(" to: ");
            String from = description.substring(fromIndex + 7, toIndex);
            String to = description.substring(toIndex + 5, description.length() - 1);
            description = description.substring(0, fromIndex - 1);
            return type + " | " + status + " | " + description + " | " + from + " | " + to;
        } else {
            return type + " | " + status + " | " + description;
        }
    }
}
