package reo.storage;

import reo.tasks.Deadline;
import reo.tasks.Event;
import reo.tasks.Task;
import reo.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

/** Represents the required file operations for Task. */
public class TaskStorage {
    private String filePath;

    /**
     * Constructor for the TaskStorage class.
     *
     * @param filePath The file path to write / read data to / from.
     */
    public TaskStorage(String filePath) {
        assert filePath != null : "File path cannot be null";
        assert !filePath.isEmpty() : "File path cannot be empty";
        this.filePath = filePath;
    }
    /**
     * Returns true if the parameter is 1, and false otherwise.
     *
     * @param i The integer used to represent true or false in the saved file.
     * @return Boolean representation of i.
     */
    public static boolean convertIntToBool(int i) {
        return i == 1;
    }

    /**
     * Reads the saved file, and interprets its contents to create
     * an ArrayList of reo.tasks.Task objects represented by the file contents.
     *
     * @return An ArrayList of Tasks interpreted from the file.
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            final String DIRECTORY_PATH = "./data";
            File dir = new File(DIRECTORY_PATH);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File f = new File(filePath);
            if (f.createNewFile()) {
                return tasks;
            }

            try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
                lines.forEach(line -> {
                    String[] split = line.split("\\|");

                    switch (split[0].trim()) {
                    case "T":
                        boolean isDoneT = convertIntToBool(Integer.parseInt(split[1].trim()));
                        String nameT = split[2].trim();
                        tasks.add(new Todo(nameT, isDoneT));
                        break;
                    case "E":
                        boolean isDoneE = convertIntToBool(Integer.parseInt(split[1].trim()));
                        String nameE = split[2].trim();
                        String from = split[3].trim();
                        String to = split[4].trim();
                        tasks.add(new Event(nameE, isDoneE, to, from));
                        break;
                    case "D":
                        boolean isDoneD = convertIntToBool(Integer.parseInt(split[1].trim()));
                        String nameD = split[2].trim();
                        String dateString = split[3].trim();
                        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                        LocalDate date = LocalDate.parse(dateString, inputFormatter);
                        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String deadline = date.format(outputFormatter);
                        tasks.add(new Deadline(nameD, isDoneD, deadline));
                        break;
                    default:
                        assert false : "Unrecognized task type in file";
                    }
                });
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Ensure the file path starts with ./data");
        } catch (IOException e) {
            System.out.println("IO Error! Ensure the file path starts with ./data");
        }

        return tasks;
    }

    /**
     * Writes to the saved file by appending the file representation
     * of t to the end of the file.
     *
     * @param t The task to be converted to file representation and appended.
     */
    public void writeFile(Task t) {
        if (t == null) {
            throw new NullPointerException();
        }
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String text = t.toFileString() + System.lineSeparator();
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes the specified line i from the saved file.
     *
     * @param lineNumber The line number (1-indexed) to be removed.
     */
    public void removeLine(int lineNumber) {
        assert lineNumber > 0 : "Line number must be greater than 0";
        try {
            final String TEMPORARY_FILE_PATH = "./data/temp.txt";
            File f = new File(filePath);
            File temp = new File(TEMPORARY_FILE_PATH);
            FileWriter tw = new FileWriter(temp, false);
            Scanner s = new Scanner(f);
            int lineCount = 1;

            while (s.hasNext()) {
                String line = s.nextLine();
                if (lineNumber != lineCount) {
                    tw.write(line + System.lineSeparator());
                }
                lineCount ++;
            }
            tw.close();
            s.close();

            Files.move(temp.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }
    }

    /**
     * Edits the specified line i to change the recorded completion status
     * to true.
     *
     * @param lineNumber The line number (1-indexed) to be edited.
     */
    public void markLine(int lineNumber) {
        assert lineNumber > 0 : "Line number must be greater than 0"; // Ensure valid line number
        try {
            File f = new File(filePath);
            File temp = new File("./data/temp.txt");
            FileWriter tw = new FileWriter(temp, false);
            Scanner s = new Scanner(f);
            int lineCount = 1;

            while (s.hasNext()) {
                String line = s.nextLine();
                if (lineNumber != lineCount) {
                    tw.write(line + System.lineSeparator());
                } else {
                    String[] split = line.split("\\|");
                    split[1] = " 1 ";

                    String newLine = String.join("|", split);
                    tw.write(newLine + System.lineSeparator());
                }
                lineCount ++;
            }
            tw.close();
            s.close();

            Files.move(temp.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }
    }

    /**
     * Edits the specified line i to change the recorded completion status
     * to false.
     *
     * @param lineNumber The line number (1-indexed) to be edited.
     */
    public void unmarkLine(int lineNumber) {
        assert lineNumber > 0 : "Line number must be greater than 0"; // Ensure valid line number
        try {
            File f = new File(filePath);
            File temp = new File("./data/temp.txt");
            FileWriter tw = new FileWriter(temp, false);
            Scanner s = new Scanner(f);
            int lineCount = 1;

            while (s.hasNext()) {
                String line = s.nextLine();
                if (lineNumber != lineCount) {
                    tw.write(line + System.lineSeparator());
                } else {
                    String[] split = line.split("\\|");
                    split[1] = " 0 ";

                    String newLine = String.join("|", split);
                    tw.write(newLine + System.lineSeparator());
                }
                lineCount ++;
            }
            tw.close();
            s.close();

            Files.move(temp.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }
    }
}
