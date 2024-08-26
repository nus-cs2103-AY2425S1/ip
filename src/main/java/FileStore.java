import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileStore represents the file storage medium used to enable persistent of data across sessions
 */
public class FileStore {

    /** Represent the savefile on local storage **/
    private File savefile;

    /**
     * Creates a new FileStore object with the provided filepath
     *
     * @param filepath File path of save file
     */
    public FileStore(String filepath) {
        this.savefile = new File(filepath);
    }

    /**
     * Attempts to retrieve parse and retrieve the to-do entries from the save file
     *
     * @return An arraylist consisting of the to-do entries
     */
    public ArrayList<TodoItem> retrieveTodoList() {
        ArrayList<TodoItem> todoList = new ArrayList<>();
        if (savefile.exists()) {
            int errorEntriesCount = 0; //Counter to count the number of error entries
            try {
                Scanner s = new Scanner(savefile);
                while (s.hasNext()) {
                    String entryString = s.nextLine();
                    String[] entryStringSplit = entryString.split("\\|");

                    String type = entryStringSplit[0].trim();
                    // Check if there are less than 3 segments, which would indicate an invalid format
                    if (entryStringSplit.length < 3) {
                        errorEntriesCount++;
                        continue;
                    }

                    String entryStatusString = entryStringSplit[1].trim();
                    // Check if the status of the to-do entry is valid
                    if (entryStatusString.isEmpty() || !entryStatusString.equalsIgnoreCase("true") &&
                            !entryStatusString.equalsIgnoreCase("false")) {
                        errorEntriesCount++;
                        continue;
                    }
                    boolean status = Boolean.parseBoolean(entryStatusString);

                    switch (type) {
                    case "T":
                        // Check if the number of expected components
                        if (entryStringSplit.length != 3) {
                            errorEntriesCount++;
                            continue;
                        }

                        String entryTodoString = entryStringSplit[2].trim();
                        // Check if the components is empty
                        if (entryTodoString.isEmpty()) {
                            errorEntriesCount++;
                            continue;
                        }
                        TodoItem todo = new TodoItem(entryTodoString);
                        todo.setCompleted(status);
                        todoList.add(todo);
                        break;

                    case "D":
                        // Check if the number of expected components
                        if (entryStringSplit.length != 4) {
                            errorEntriesCount++;
                            continue;
                        }

                        String entryDeadlineString = entryStringSplit[2].trim();
                        String entryDeadlineDueString = entryStringSplit[3].trim();
                        // Check if the components is empty
                        if (entryDeadlineString.isEmpty() || entryDeadlineDueString.isEmpty()) {
                            errorEntriesCount++;
                            continue;
                        }

                        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                        LocalDateTime deadlineDue = null;
                        try {
                            deadlineDue = LocalDateTime.parse(entryDeadlineDueString, formatter);
                        } catch (DateTimeParseException e) {
                            errorEntriesCount++;
                            continue;
                        }

                        DeadlineItem deadline = new DeadlineItem(entryDeadlineString, deadlineDue);
                        deadline.setCompleted(status);
                        todoList.add(deadline);
                        break;

                    case "E":
                        // Check if the number of expected components
                        if (entryStringSplit.length != 5) {
                            errorEntriesCount++;
                            continue;
                        }

                        String entryEventString = entryStringSplit[2].trim();
                        String entryEventFromString = entryStringSplit[3].trim();
                        String entryEventToString = entryStringSplit[4].trim();
                        // Check if the components is empty
                        if (entryEventString.isEmpty() || entryEventFromString.isEmpty() ||
                                entryEventToString.isEmpty()) {
                            errorEntriesCount++;
                            continue;
                        }

                        EventItem event = new EventItem(entryEventString, entryEventFromString, entryEventToString);
                        todoList.add(event);
                        break;
                    default:
                        // Invalid type encountered, entry is ignored
                        errorEntriesCount++;
                        break;
                    }
                }
                s.close();
            } catch (FileNotFoundException e) {
                return todoList;
            }
            System.out.println(String.format("%d entries found in your save file is corrupt", errorEntriesCount));
            return todoList;
        } else {
            return todoList;
        }
    }

    /**
     * Attempts to save the current todolist to local storage
     *
     * @param stringsForSaving The string representation of the to-do entries in the save format
     * @return Status of the save operation
     */
    public boolean saveTodoList(String[] stringsForSaving) {
        String stringForWriting = String.join(System.lineSeparator(), stringsForSaving);
        try {
            // Check if parent folder exists, if not create.
            if (!savefile.getParentFile().exists()) {
                savefile.getParentFile().mkdirs();
            }
            FileWriter fw = new FileWriter(savefile);
            fw.write(stringForWriting);
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
