package slave;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * An object containing all the method
 * Contains a reference to the taskList as well as the address of the save file
 */
public class Storage {
    private String filePath;
    private List<Task> list;

    /**
     * @param list     is the list of tasks which the user is editing
     * @param filePath is the address of the text file to which any changes are saved to
     */
    public Storage(List<Task> list, String filePath) {
        this.list = list;
        this.filePath = filePath;
    }

    /**
     * Converts the List<Task> to a string format and writes it to the savefile "./src/main/data/savefile.txt".
     * Every line contains only 1 task.
     * String format is as per the return value of toString() method of the respective task.
     * Creates a new file at "./src/main/data" called "savefile.txt" in the event of a missing save file.
     */
    public void save() {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task t : list) {
                sb.append(t.save());
                sb.append("\n");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Save failed");
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * attempts to load the pre saved tasks from the save file "./src/main/data/savefile.txt"
     * if file does not exist at the path, does nothing
     * in the event of corrupted save file, skips the line
     * save file format: as per toString() function of each task, with one task per line
     */
    public void load() {
        try {
            Scanner sc = new Scanner(new File(filePath));
            int success = 0;
            int failed = 0;
            while (sc.hasNextLine()) {
                try {
                    String task = sc.nextLine();
                    char taskType = task.charAt(1);
                    char taskCompleted = task.charAt(4);
                    int firstSpacePos = task.indexOf(" ");
                    boolean isCompleted = false;
                    if (taskCompleted == ']') {
                        // do nothing
                    } else if (taskCompleted == 'X') {
                        isCompleted = true;
                    } else {
                        throw new InvalidSaveFileFormatException("invalid completed status");
                    }
                    // identify the type of task:
                    switch (taskType) {
                    case 'T':
                        list.add(new Todo(isCompleted, task.substring(firstSpacePos + 1)));
                        success++;
                        break;
                    case 'D':
                        // not sure how to get rid of error here for string formatting
                        String[] deadlineArray = task.split(" \\(by: ");
                        String deadlineName = deadlineArray[0].substring(firstSpacePos + 1);
                        String by = deadlineArray[1].substring(0, deadlineArray[1].length() - 1);
                        list.add(new Deadline(isCompleted, deadlineName, LocalDate.parse(by)));
                        success++;
                        break;
                    case 'E':
                        // not sure how to get rid of error here for string formatting
                        String[] eventArray = task.split(" \\(from: ");
                        String eventName = eventArray[0].substring(firstSpacePos + 1);
                        String[] eventDetails = eventArray[1].split(" to: ");
                        String from = eventDetails[0];
                        String to = eventDetails[1].substring(0, eventDetails[1].length() - 1);
                        list.add(new Event(isCompleted, eventName, LocalDate.parse(from), LocalDate.parse(to)));
                        success++;
                        break;
                    default:
                        throw new InvalidSaveFileFormatException("invalid Task type");
                    }
                } catch (InvalidSaveFileFormatException | DateTimeParseException
                         | IndexOutOfBoundsException | InvalidChronologicalOrderException e) {
                    // DateTimeParseException for incorrect LocalDate format
                    // InvalidSaveFileFormatException, IndexOutOfBoundsException for incorrect Task format
                    failed++;
                    System.out.println("error in event save file format: " + e.getMessage());
                }
            }
            if (failed > 0) {
                System.out.println("Deleting buggy save file");
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/data/savefile.txt"));
                    writer.write("");
                    writer.close();
                    System.out.println("Buggy save file deleted");
                    if (success > 0) {
                        System.out.println("Saving readable contents");
                        save();
                    }
                } catch (IOException e) {
                    System.out.println("Failed to purge buggy save file");
                }
            }
            if (success == 0) {
                System.out.println("No valid saved Tasks found");
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("no save file found, creating new save file");
            Path saveFilePath = Paths.get(filePath);
            Path directory = saveFilePath.getParent();
            //@@author mkyong -reused
            // source: https://mkyong.com/java/how-to-create-directory-in-java/
            // code for creating directory reused from the tutorial website above
            try {
                //@@author Baeldung -reused
                // source: https://www.baeldung.com/java-file-directory-exists
                // code for checking if directories and files exist reused from website above
                if (!Files.exists(directory)) {
                    Files.createDirectories(directory);
                    System.out.println("Creating directory: " + directory);
                }
                if (!Files.exists(saveFilePath)) {
                    //@@author tutorialspoint -reused
                    // source: https://www.tutorialspoint.com/javaexamples/file_dir.htm
                    // code for creating files using Files.createFile() reused from website
                    Files.createFile(saveFilePath);
                    System.out.println("Creating file: " + saveFilePath.getFileName());
                    //@@author
                }
                //@@author
                System.out.println("Save file created at " + saveFilePath);
            } catch (IOException e) {
                System.out.println("Error in creating file / directory: " + e.getMessage());
            }
            //@@author
        }
    }
}
