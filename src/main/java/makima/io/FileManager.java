package makima.io;

import makima.command.Makima;
import makima.exception.FileCorruptedException;
import makima.exception.FilePermissionException;
import makima.task.Deadline;
import makima.task.Event;
import makima.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helper class to create and read files.
 */
public class FileManager {

    public static final String DATA_FOLDER = "./data";
    public static final String DATA_PATH = "./data/makima.txt";

    /**
     * Saves the current list of tasks to DATA_PATH.
     * Returns true if successful
     *
     * @param makima State of chatbot
     * @return outcome of save
     */
    public static boolean saveFile(Makima makima) {
        File file = new File(DATA_PATH);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(makima.convertTaskstoFileString());
            fw.close();
        } catch (IOException | SecurityException e) {
            throw new FilePermissionException();
        }
        return true;
    }

    /**
     * Creates a folder at DATA_FOLDER for storing tasks data. If the folder already exists, does not overwrite existing
     * file data and returns true.
     *
     * @return if the operation was successful
     */
    public static boolean createFolder() {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            if (!folder.mkdir()) {
                System.out.println("An error occurred while creating a new data file!");
                return false;
            }
        }
        return true;
    }

    /**
     * Creates a file at DATA_PATH for storing tasks data. Ideally, should be called after createFolder to ensure the
     * folder exists. If the file already exists, does not overwrite existing file data and returns true.
     *
     * @return if the operation was successful
     */
    public static boolean createFile() {
        File file = new File(DATA_PATH);
        if (file.exists()) {
            return true;
        }

        try {
            if (!file.createNewFile()) {
                System.out.println("An unexpected error occurred.");
                return false;
            }
        } catch (IOException | SecurityException e) {
            throw new FilePermissionException();
        }
        return true;
    }

    /**
     * Loads the list of tasks saved in DATA_PATH
     * Returns true if successful
     *
     * @param makima State of chatbot
     * @return outcome of load
     */
    public static boolean loadFile(Makima makima) {
        if (!createFolder() || !createFile()) {
            throw new FilePermissionException();
        }

        File file = new File(DATA_PATH);
        ArrayList<String> lines = new ArrayList<>();

        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new FilePermissionException();
        }

        int lineNumber = 0;
        while (lineNumber < lines.size()) {
            ArrayList<String> data = new ArrayList<>();

            switch (lines.get(lineNumber)) {
            case "E":
                if (lineNumber + Event.SAVE_PARAMETERS > lines.size()) {
                    throw new FileCorruptedException(lineNumber);
                }

                for (int i = lineNumber + 1; i < lineNumber + Event.SAVE_PARAMETERS; i++) {
                    data.add(lines.get(i));
                }

                Event event = Event.loadFromData(data);
                if (event == null) {
                    throw new FileCorruptedException(lineNumber);
                }

                makima.addTask(event);
                lineNumber += Event.SAVE_PARAMETERS;
                break;
            case "D":
                if (lineNumber + Deadline.SAVE_PARAMETERS > lines.size()) {
                    throw new FileCorruptedException(lineNumber);
                }

                for (int i = lineNumber + 1; i < lineNumber + Deadline.SAVE_PARAMETERS; i++) {
                    data.add(lines.get(i));
                }

                Deadline deadline = Deadline.loadFromData(data);
                if (deadline == null) {
                    throw new FileCorruptedException(lineNumber);
                }

                makima.addTask(deadline);
                lineNumber += Deadline.SAVE_PARAMETERS;
                break;
            case "T":
                if (lineNumber + ToDo.SAVE_PARAMETERS > lines.size()) {
                    throw new FileCorruptedException(lineNumber);
                }

                for (int i = lineNumber + 1; i < lineNumber + ToDo.SAVE_PARAMETERS; i++) {
                    data.add(lines.get(i));
                }

                ToDo toDo = ToDo.loadFromData(data);
                if (toDo == null) {
                    throw new FileCorruptedException(lineNumber);
                }

                makima.addTask(toDo);
                lineNumber += ToDo.SAVE_PARAMETERS;
                break;
            case "":
                lineNumber++;
                break;
            default:
                throw new FileCorruptedException(lineNumber);
            }
        }

        return true;
    }

}
