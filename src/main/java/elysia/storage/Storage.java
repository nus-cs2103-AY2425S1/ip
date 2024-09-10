package elysia.storage;

import elysia.dateparser.DateParser;
import elysia.task.Deadline;
import elysia.task.Event;
import elysia.task.Task;
import elysia.task.ToDos;

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
    private static final String FOLDERNAME = "data";
    private static final String FILENAME = "elysia.txt";
    private static final String FILEPATH = "./" + FOLDERNAME + "/" + FILENAME;
    private ArrayList<Task> arrayLists;

    public void load() throws IOException{
        Storage.createFile();
        this.scanFileContents(FILEPATH);
    }

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
    public static void createFile() throws IOException {
        File dataDir = new File(FOLDERNAME);

        if (! dataDir.exists()) {
            dataDir.mkdir();

        }

        if (dataDir.exists() && dataDir.isDirectory()) {
            File txtFile = new File(dataDir, FILENAME);

            try {
                if (txtFile.createNewFile()) {
                    System.out.println(FILENAME + " successfully created.");
                } else {
                    System.out.println(FILENAME+ " already exists");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the " + FILENAME);
            }
        }
    }

    /**
     * Appends the input from users to the txt file.
     **/
    public static void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH, true); // create a FileWriter in append mode
        fw.write("\n" + task.saveToTxt());
        fw.close();
    }

    /**
     * Writes the input from users to the txt file and rewrites the content in the file.
     **/
    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Creates a txt file if it does not exist.
     * Saves all the input from list into txt file.
     *
     * @throws IOException
     */
    public void saveFile() throws IOException {
        createFile();

        if (! arrayLists.isEmpty()) {
            try {
                writeToFile(arrayLists.get(0).saveToTxt());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            for (int i = 1; i < arrayLists.size(); i++) {
                appendToFile(arrayLists.get(i));
            }
        } else {
            File file = new File(FILEPATH);
            Files.delete(file.toPath());
        }
    }
}
