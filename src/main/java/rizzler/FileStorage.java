package rizzler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the saving and loading function of Rizzler.
 * It allows Rizzler to save its tasklist and
 * load a tasklist from memory.
 */
class FileStorage {
    private final File file;

    /**
     * Constructs the <code>FileStorage</code> with a String
     * that represents the file path of the intended file to
     * save to and load from.
     * If the save file or its parent directories do not exist,
     * they are also created.
     * If an error occurs during the object creation, outputs a
     * loading error.
     *
     * @param filePath String that represents relative file path of file.
     */
    FileStorage(String filePath) {
        this.file = new File(filePath);
        String[] parsedFilePath = filePath.split("/");
        String dirPath = "";
        for (int i = 0; i < parsedFilePath.length - 1; i++) {
            dirPath += (i > 0 ? "/" : "") + parsedFilePath[i];
            File directory = new File(dirPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
        }
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                new Ui().showLoadingError();
            }
        }
        assert this.file.exists();
    }

    /**
     * Loads the text from the save file and converts the data
     * into an <code>ArrayList<Task></code> to be input to Rizzler's
     * tasklist.
     *
     * @return ArrayList of <code>Task</code>.
     */
    ArrayList<Task> load() {
        try {
            Scanner sc = new Scanner(this.file);
            ArrayList<Task> taskList = new ArrayList<>();
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] parsedInput = input.split("/");
                switch (parsedInput[0]) {
                case "T":
                    Todo todo = new Todo(parsedInput[2],
                            parsedInput[1].equals("1"));
                    taskList.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(parsedInput[2],
                            LocalDate.parse(parsedInput[3]),
                            parsedInput[1].equals("1"));
                    taskList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(parsedInput[2],
                            LocalDate.parse(parsedInput[3]),
                            LocalDate.parse(parsedInput[4]),
                            parsedInput[1].equals("1"));
                    taskList.add(event);
                    break;
                }
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Saves the ArrayList sent by Rizzler's tasklist
     * to the save file specified during the object's
     * creation.
     * If an error occurs during the object creation, outputs a
     * loading error.
     *
     * @param taskList ArrayList of <code>Task</code> sent from Rizzler.
     */
    void save(ArrayList<Task> taskList) {
        try {
            String saveData = "";
            FileWriter fileWriter = new FileWriter(this.file);
            for (Task task : taskList) {
                saveData += task.toSaveState();
            }
            fileWriter.write(saveData);
            fileWriter.close();
        } catch (IOException e) {
            new Ui().showLoadingError();
        }
    }
}
