package kietwoforone.storage;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.tasks.Task;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.util.ArrayList;

/**
 * Represents the methods used to store and load date inputted by the user into a text file.
 */
public class Storage {

    private static String filePath = "data/tasks.txt";

    /**
     * Saves data to a file by writing the task objects to tasks.txt file.
     * Throws a KieTwoForOneException exception when the file being saved to does not exist.
     *
     * @param tasks
     * @throws KieTwoForOneException
     */
    public static void saveFile(ArrayList<Task> tasks) throws KieTwoForOneException {
        try {
            ObjectOutputStream fileSaver = new ObjectOutputStream(new FileOutputStream(filePath));
            for (int i = 0; i < tasks.size(); i++) {
                fileSaver.writeObject(tasks.get(i));
            }
            fileSaver.close();
        } catch (IOException e) {
            throw new KieTwoForOneException("File not found!");
        }
    }

    /**
     * Loads data from the tasks.txt file and adds the created objects to the task list.
     * Throws a KieTwoForOneException exception when the file being loaded from does not exist.
     * Throws a KieTwoForOneException exception when the object being loaded is a Task object.
     *
     * @param tasks
     * @throws KieTwoForOneException
     */
    public static void loadFile(ArrayList<Task> tasks) throws KieTwoForOneException {
        try {
            ObjectInputStream fileLoader = new ObjectInputStream(new FileInputStream(filePath));
            while (true) {
                try {
                    Task newTask = (Task) fileLoader.readObject();
                    tasks.add(newTask);
                } catch (EOFException e) {
                    break;
                }
            }
            fileLoader.close();
        } catch (IOException e) {
            throw new KieTwoForOneException("File not found!");
        } catch (ClassNotFoundException e) {
            throw new KieTwoForOneException("Not a task!");
        }
    }
}
