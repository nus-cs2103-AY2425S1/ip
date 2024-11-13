package elysia.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import elysia.exception.InvalidFileFormatException;
import elysia.task.Deadline;
import elysia.task.Event;
import elysia.task.Task;
import elysia.task.ToDos;

/**
 * Stores and scans the txt file in hard disk to save the progress.
 **/
public class Storage {
    private static final String FOLDER_NAME = "data";
    private static final String FILE_NAME = "elysia.txt";
    private ArrayList<Task> arrayLists;


    public Storage(ArrayList<Task> arrayLists) {
        this.arrayLists = arrayLists;
    }

    /**
     * Loads the previous tasks from the local storage into the task list.
     *
     * @throws IOException
     * @throws InvalidFileFormatException
     */
    public void load() throws IOException, InvalidFileFormatException {
        Storage.createFile();
        this.scanFileContents(makeFilePath());
    }

    /**
     * Loads the saved list.
     **/
    public void scanFileContents(String filePath) throws FileNotFoundException, InvalidFileFormatException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);


        if (f.exists() && f.isFile()) {
            while (s.hasNext()) {
                String input = s.nextLine();
                String[] str = input.split(" \\| ");

                if (str[0].equals("T")) {
                    if (str.length != 3) {
                        throw new InvalidFileFormatException();
                    }
                    addToDos(str[2], Integer.parseInt(str[1]));
                }

                if (str[0].equals("D")) {
                    if (str.length != 4) {
                        throw new InvalidFileFormatException();
                    }
                    addDeadline(str[2], Integer.parseInt(str[1]), str[3]);
                }

                if (str[0].equals("E")) {
                    if (str.length != 5) {
                        throw new InvalidFileFormatException();
                    }
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
    private void addDeadline(String s, int i, String by) throws InvalidFileFormatException {
        Deadline deadline;

        try {
            deadline = new Deadline(s, LocalDate.parse(by));
        } catch (DateTimeParseException e) {
            throw new InvalidFileFormatException();
        }

        if (i == 1) {
            deadline.markAsDone();
        }

        arrayLists.add(deadline);
    }

    /**
     * Loads the Event Tasks from the saved file.
     **/
    private void addEvent(String s, int i, String startTime, String endTime) throws InvalidFileFormatException {

        Event task;

        try {
            task = new Event(s, LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
        } catch (DateTimeParseException e) {
            throw new InvalidFileFormatException();
        }

        if (i == 1) {
            task.markAsDone();
        }
        arrayLists.add(task);
    }

    /**
     * Creates the txt file if it does not exist to prevent exception.
     **/
    public static void createFile() {
        File dataDir = new File(FOLDER_NAME);

        if (!dataDir.exists()) {
            dataDir.mkdir();

        }

        if (dataDir.exists() && dataDir.isDirectory()) {
            File txtFile = new File(dataDir, FILE_NAME);

            try {
                if (txtFile.createNewFile()) {
                    System.out.println(FILE_NAME + " successfully created.");
                } else {
                    System.out.println(FILE_NAME + " already exists");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the " + FILE_NAME);
            }
        }
    }

    /**
     * Creates a temporary file for testing purposes.
     *
     * @param folderName
     * @param fileName
     */
    protected static void createFile(String folderName, String fileName) {
        File dataDir = new File(folderName);

        if (!dataDir.exists()) {
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
    public static void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(makeFilePath(), true);
        fw.write("\n" + task.saveToTxt());
        fw.close();
    }

    /**
     * Appends the input to the temporary txt file for testing purposes.
     **/
    protected static void appendToFile(String filePath, Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write("\n" + task.saveToTxt());
        fw.close();
    }


    /**
     * Writes the input from users to the txt file and rewrites the content in the file.
     **/
    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(makeFilePath());
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes the input from users to the temporary txt file and rewrites the content in the file. For testing purposes
     * only.
     **/
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Creates a txt file if it does not exist. Saves all the input from list into txt file.
     *
     * @throws IOException
     */
    public void saveFile() throws IOException {
        createFile();

        if (!arrayLists.isEmpty()) {
            try {
                writeToFile(arrayLists.get(0).saveToTxt());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            for (int i = 1; i < arrayLists.size(); i++) {
                appendToFile(arrayLists.get(i));
            }
        } else {
            File file = new File(makeFilePath());
            Files.delete(file.toPath());
        }
    }

    /**
     * Creates a temporary text file for testing purposes.
     *
     * @param folderName
     * @param fileName
     * @param filePath
     * @throws IOException
     */
    protected void saveFile(String folderName, String fileName, String filePath) throws IOException {
        createFile(folderName, fileName);

        if (!arrayLists.isEmpty()) {
            try {
                writeToFile(filePath, arrayLists.get(0).saveToTxt());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            for (int i = 1; i < arrayLists.size(); i++) {
                appendToFile(filePath, arrayLists.get(i));
            }
        } else {
            File file = new File(filePath);
            Files.delete(file.toPath());
        }
    }


    /**
     * @return File path.
     */
    private static String makeFilePath() {
        return "./" + FOLDER_NAME + "/" + FILE_NAME;
    }

}
