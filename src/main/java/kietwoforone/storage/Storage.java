package kietwoforone.storage;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.tasks.Task;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Storage {

    private static String filePath = "data/tasks.txt";

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
