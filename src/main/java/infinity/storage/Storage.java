package infinity.storage;

import infinity.infinityexception.InfinityException;
import infinity.task.Deadline;
import infinity.task.Event;
import infinity.task.Task;
import infinity.task.ToDos;
import infinity.tasklist.TaskList;
import infinity.ui.Ui;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Storage {
    
    public static final String FILEDIRPATH = "../../../../data";
    public static final String FILENAME = "save-file.txt";
    public static final String FILEPATH = FILEDIRPATH + "/" + FILENAME;
    public static final String DELIMITER = "\0";

    private final Ui botUI;

    private String combineDescription(String[] description, int startIndex) {
        StringBuilder combinedDescription = new StringBuilder();

        for (int i = startIndex; i < description.length; i++) {
            combinedDescription.append(description[i]);
            if (i != description.length - 1) {
                combinedDescription.append(DELIMITER);
            }
        }

        return combinedDescription.toString();
    }

    public ArrayList<Task> readFile() {
        @SuppressWarnings("unchecked")
        ArrayList<Task> tasks = new ArrayList<>(TaskList.MAXSIZE);

        if (doesFileExist()) {
            try {
                Scanner file = new Scanner(new File(FILEPATH));
                while (file.hasNextLine()) {

                    String currentLine = file.nextLine();

                    String[] words = currentLine.split(DELIMITER);
                    String taskType = words[0];
                    boolean isDone = words[1].equals("1");
                    Task task = null;

                    switch (taskType) {
                    case "T":
                        task = new ToDos(isDone, 
                                combineDescription(words, 2));
                        break;
                    case "D":
                        task = new Deadline(isDone, 
                                combineDescription(words, 3), words[2]);
                        break;
                    case "E":
                        task = new Event(isDone, 
                                combineDescription(words, 4), words[2], words[3]);
                        break;
                    default:
                        break;
                    }

                    if (task != null) {
                        tasks.add(task);
                    }

                }
                file.close();
            } catch (IOException e) {
                botUI.botSays(
                        "I'm sorry, I'm a noob at this, I can't read the file, can you help me debug? " 
                        + e.getMessage());
            } catch (InfinityException e) {
                botUI.botSays(
                        "I'm sorry, I'm a noob at this, I can't read the file, can you help me debug? " 
                        + e.getMessage());
            } catch (NoSuchElementException e) {
                // Do nothing, likely means end of file
            }
        }
        return tasks;
    }

    public void saveFile(ArrayList<Task> tasks) throws IOException {
        createFileIfNotExists();

        FileWriter file = new FileWriter(FILEPATH);

        for (Task task : tasks) {
            file.write(task.saveFileFormat(DELIMITER));
            file.write("\n");
        }

        file.close();

        botUI.botSays("Save Successful, Woohoo!");
    }

    private boolean doesFileExist() {
        File file = new File(FILEPATH);
        return file.exists() && !file.isDirectory();
    }

    private void createFileIfNotExists() {
        try {
            if (!doesFileExist()) {
                Files.createDirectories(Paths.get(FILEDIRPATH));
                Files.createFile(Paths.get(FILEPATH));
            }
        } catch (IOException e) {
            botUI.botSays(
                    "I'm sorry, I'm a noob at this, I can't create the file, can you help me debug? " 
                    + e.getMessage());
        }
    }

    public Storage(Ui botUI) {
        this.botUI = botUI;
    }
}