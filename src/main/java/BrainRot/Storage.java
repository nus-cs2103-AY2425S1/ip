package brainrot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import brainrot.exceptions.UnknownLoadingError;

/**
 * The Storage class handles the reading and writing of tasks to and from a file.
 * It allows tasks to be saved persistently and loaded when the application is started.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     * Ensures that the directory for the file exists.
     *
     * @param filePath The path of the file where tasks will be saved and loaded.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        ensureDirectoryExists();
    }

    /**
     * Ensures that the directory for the storage file exists.
     * If the directory does not exist, it attempts to create it.
     */
    private void ensureDirectoryExists() {
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            parentDirectory.mkdirs();  // Create the directory if it doesn't exist
        }
    }


    /**
     * Loads the list of tasks from the file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> arr = new ArrayList<>();
        File myData = new File(filePath);

        try (BufferedReader dataR = new BufferedReader(new FileReader(myData))) {
            String line;
            while ((line = dataR.readLine()) != null) {
                String[] dataFromDisk = line.split("/", 4);
                char eventType = dataFromDisk[0].charAt(1);

                switch (eventType) {
                    case 'T':
                        ToDo T = new ToDo(dataFromDisk[1]);
                        if (dataFromDisk[1].equals("[X]")) {
                            T.isDone = true;
                        }
                        arr.add(T);
                        break;
                    case 'D':
                        Deadline D = new Deadline(dataFromDisk[1], dataFromDisk[2], true);
                        if (dataFromDisk[1].equals("[X]")) {
                            D.isDone = true;
                        }
                        arr.add(D);
                        break;
                    case 'E':
                        Event E = new Event(dataFromDisk[1], dataFromDisk[2], dataFromDisk[3]);
                        if (dataFromDisk[1].equals("[X]")) {
                            E.isDone = true;
                        }
                        arr.add(E);
                        break;
                    default :
                        System.out.println("error");
                }
            }
        } catch (IOException e) {
            throw new UnknownLoadingError(e);
        }

        return arr;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks An ArrayList of Task objects to be saved to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (FileWriter dataW = new FileWriter(filePath)) {
            for (Task task : tasks) {
                dataW.write(task.toFileString() + "\n");
            }
        }
    }
}
