package main.java.echochat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {

    private static final String FILE_NAME = "tasks.ser";

    /**
     * Save the list of tasks to file.
     * @param lst the list of tasks to save
     */
    public void save(ArrayList<Task> lst) {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(lst);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the list of tasks from file.
     * @return the list of tasks, or an empty list if the file does not exist or an error occurs
     */
    public ArrayList<Task> load() {
        ArrayList<Task> lst = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            lst = (ArrayList<Task>) in.readObject();
        } catch (Exception e) {
            // Handle the case where the file is not found or class is not found
            // e.g., file not found, class not found, or readObject fails
            if (e.getMessage().equals("tasks.ser (No such file or directory)")) {
                System.out.println("Creating tasks.ser");
            }

        }
        return lst;
    }
}
