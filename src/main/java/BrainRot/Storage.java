package BrainRot;

import java.io.*;
import java.util.ArrayList;

import BrainRot.exceptions.*;

/**
 * The Storage class handles the reading and writing of tasks to and from a file.
 * It allows tasks to be saved persistently and loaded when the application is started.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
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
