package tohru.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import tohru.exception.CorruptSaveException;
import tohru.exception.TohruException;
import tohru.task.DeadlineItem;
import tohru.task.EventItem;
import tohru.task.TodoItem;

/**
 * Represents the file storage medium used to enable persistent of data across sessions.
 */
public class FileStore {

    /** Represents the savefile on local storage */
    private File savefile;

    /**
     * Creates a new FileStore object with the provided filepath.
     *
     * @param filepath File path of save file.
     */
    public FileStore(String filepath) {
        assert (filepath != null && !filepath.isEmpty()) : "Filepath should not be null or empty";

        this.savefile = new File(filepath);
    }

    /**
     * Retrieves and parse the to-do entries from the save file.
     *
     * @return An arraylist consisting of the to-do entries.
     */
    public ArrayList<TodoItem> retrieveTodoList() throws TohruException {
        ArrayList<TodoItem> todoList = new ArrayList<>();
        if (!savefile.exists()) {
            return todoList;
        }

        int errorEntriesCount = 0; //Counter to count the number of error entries
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
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
                if (entryStatusString.isEmpty() || !entryStatusString.equals("true")
                        && !entryStatusString.equals("false")) {
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
                    if (entryEventString.isEmpty() || entryEventFromString.isEmpty()
                            || entryEventToString.isEmpty()) {
                        errorEntriesCount++;
                        continue;
                    }

                    LocalDateTime eventFrom;
                    LocalDateTime eventTo;
                    try {
                        eventFrom = LocalDateTime.parse(entryEventFromString, formatter);
                        eventTo = LocalDateTime.parse(entryEventFromString, formatter);
                    } catch (DateTimeParseException e) {
                        errorEntriesCount++;
                        continue;
                    }

                    if (eventFrom.isAfter(eventTo)) {
                        errorEntriesCount++;
                        continue;
                    }

                    EventItem event = new EventItem(entryEventString, eventFrom, eventTo);
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
            throw new TohruException("Save file not found");
        }

        if (errorEntriesCount > 0) {
            throw new CorruptSaveException(errorEntriesCount, todoList);
        }

        return todoList;

    }

    /**
     * Saves the current todolist to local storage.
     *
     * @param todoList The to-do list to be saved.
     * @throws TohruException When to-do list is unable to save to local storage.
     */
    public void saveTodoList(ArrayList<TodoItem> todoList) throws TohruException {
        String[] items = new String[todoList.size()];
        for (int i = 0; i < todoList.size(); i++) {
            items[i] = todoList.get(i).getSaveString();
        }

        String stringForWriting = String.join(System.lineSeparator(), items);

        try {
            // Check if parent folder exists, if not create.
            if (!savefile.getParentFile().exists()) {
                savefile.getParentFile().mkdirs();
            }
            FileWriter fw = new FileWriter(savefile);
            fw.write(stringForWriting);
            fw.close();
        } catch (IOException e) {
            throw new TohruException("Unable to save to-do list to local storage");
        }
    }
}
