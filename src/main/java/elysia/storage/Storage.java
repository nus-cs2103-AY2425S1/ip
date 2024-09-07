package elysia.storage;

import elysia.task.Deadline;
import elysia.task.Event;
import elysia.task.Task;
import elysia.task.ToDos;
import elysia.dateparser.DateParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores and scans the txt file in hard disk to save the progress.
 **/
public class Storage {
    private static ArrayList<Task> arrayLists;

    public Storage(ArrayList<Task> arrayLists) {
        this.arrayLists = arrayLists;
    }

    /**
     * Loads the saved list.
     **/
    public void scanFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);


        if (f.exists() && f.isFile()) {
            while (s.hasNext()) {
                String input = s.nextLine();
                String[] str = input.split(" \\| ");

                if (str[0].equals("T")) {
                    addToDos(str[2], Integer.parseInt(str[1]));
                }

                if (str[0].equals("D")) {
                    addDeadline(str[2], Integer.parseInt(str[1]), str[3]);
                }

                if (str[0].equals("E")) {
                    addEvent(str[2], Integer.parseInt(str[1]), str[3], str[4]);
                }
            }
        }
        s.close();
    }

    /**
     * Loads the ToDo Tasks from the saved file.
     **/
    private void addToDos(String s, int i) {

        Task task = new ToDos(s);
        if (i == 1) {
            task.markAsDone();
        }
        arrayLists.add(task);
    }

    /**
     * Loads the Deadline Tasks from the saved file.
     **/
    private void addDeadline(String s, int i, String by) {
        Task task = new Deadline(s, DateParser.parseDate(by));
        if (i == 1) {
            task.markAsDone();
        }
        arrayLists.add(task);
    }

    /**
     * Loads the Event Tasks from the saved file.
     **/
    private void addEvent(String s, int i, String from, String to) {

        Task task = new Event(s, from, to);
        if (i == 1) {
            task.markAsDone();
        }
        arrayLists.add(task);
    }

    /**
     * Creates the txt file if it does not exist to prevent exception.
     **/
    public static void createFile(String folderName, String fileName) throws IOException {
        File dataDir = new File(folderName);

        if (! dataDir.exists()) {
            dataDir.mkdir();

        }

        if (dataDir.exists() && dataDir.isDirectory()) {
            File txtFile = new File(dataDir, fileName);

            try {
                if (txtFile.createNewFile()) {
                    System.out.println(fileName + " successfully created.");
                } else {
                    System.out.println(fileName + " already exists");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the " + fileName);
            }
        }
    }

    /**
     * Appends the input from users to the txt file.
     **/
    private void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Writes the input from users to the txt file and rewrites the content in the file.
     **/
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Creates a txt file if it does not exist.
     * Saves all the input from list into txt file.
     *
     * @param folderName folderName in String.
     * @param fileName   fileName in String.
     * @param filePath   filePath in String.
     * @throws IOException
     */
    public void handleExit(String folderName, String fileName, String filePath) throws IOException {
        createFile(folderName, fileName);

        if (! arrayLists.isEmpty()) {
            try {
                writeToFile(filePath, arrayLists.get(0).saveToTxt());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            for (int i = 1; i < arrayLists.size(); i++) {
                appendToFile(filePath, "\n" + arrayLists.get(i).saveToTxt());
            }
        } else {
            File file = new File(filePath);
            Files.delete(file.toPath());
        }
    }
}
