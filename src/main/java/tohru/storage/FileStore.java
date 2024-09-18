package tohru.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import tohru.command.Parser;
import tohru.exception.CorruptSaveException;
import tohru.exception.TohruException;
import tohru.task.DeadlineItem;
import tohru.task.EventItem;
import tohru.task.TodoItem;

/**
 * Represents the file storage medium used to enable persistent of data across sessions.
 */
public class FileStore {

    private static final String DELIMITER_REGEX = "\\|";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

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

        Scanner s;
        try {
            s = new Scanner(savefile);
        } catch (FileNotFoundException e) {
            throw new TohruException("Save file not found");
        }

        int errorEntriesCount = 0;
        while (s.hasNext()) {
            String entryString = s.nextLine();
            try {
                TodoItem todo = parseLine(entryString);
                todoList.add(todo);
            } catch (TohruException e) {
                errorEntriesCount++;
            }
        }

        s.close();

        if (errorEntriesCount > 0) {
            throw new CorruptSaveException(errorEntriesCount, todoList);
        }

        return todoList;
    }

    private TodoItem parseLine(String entryString) throws TohruException {
        String[] entryStringSplit = entryString.split(DELIMITER_REGEX, 3);

        // Check if there are less than 3 segments, which would indicate an invalid format
        if (entryStringSplit.length < 3) {
            throw new TohruException("Invalid entry");
        }

        String entryStatusString = entryStringSplit[1].trim();
        // Check if the status of the to-do entry is valid
        if (!entryStatusString.equals("true") && !entryStatusString.equals("false")) {
            throw new TohruException("Invalid entry");
        }
        boolean isComplete = Boolean.parseBoolean(entryStatusString);

        String type = entryStringSplit[0].trim();
        String remainingArguments = entryStringSplit[2].trim();
        TodoItem todo = null;
        switch (type) {
        case "T":
            todo = parseTodoRemainingArguments(remainingArguments);
            break;
        case "D":
            todo = parseDeadlineRemainingArguments(remainingArguments);
            break;
        case "E":
            todo = parseEventRemainingArguments(remainingArguments);
            break;
        default:
            throw new TohruException("Invalid entry"); // Invalid type encountered, entry is ignored
        }

        todo.setCompleted(isComplete);
        return todo;
    }

    private TodoItem parseTodoRemainingArguments(String remainingArguments) throws TohruException {
        String entryTodoString = remainingArguments.trim();

        if (entryTodoString.isEmpty()) {
            throw new TohruException("Invalid entry");
        }

        return new TodoItem(entryTodoString);
    }

    private DeadlineItem parseDeadlineRemainingArguments(String remainingArguments) throws TohruException {
        // Check if the number of expected components
        String[] argumentSplit = remainingArguments.split(DELIMITER_REGEX);
        if (argumentSplit.length != 2) {
            throw new TohruException("Invalid entry");
        }

        String entryDeadlineString = argumentSplit[0].trim();
        String entryDeadlineDueString = argumentSplit[1].trim();
        // Check if the components is empty
        if (entryDeadlineString.isEmpty() || entryDeadlineDueString.isEmpty()) {
            throw new TohruException("Invalid entry");
        }

        LocalDateTime deadlineDue = Parser.parseDateTimeString(entryDeadlineDueString, DATE_TIME_FORMATTER);

        return new DeadlineItem(entryDeadlineString, deadlineDue);
    }

    private EventItem parseEventRemainingArguments(String remainingArguments) throws TohruException {
        // Check if the number of expected components
        String[] argumentSplit = remainingArguments.split(DELIMITER_REGEX);
        if (argumentSplit.length != 3) {
            throw new TohruException("Invalid entry");
        }

        String entryEventString = argumentSplit[0].trim();
        String entryEventFromString = argumentSplit[1].trim();
        String entryEventToString = argumentSplit[2].trim();
        // Check if the components is empty
        if (entryEventString.isEmpty() || entryEventFromString.isEmpty() || entryEventToString.isEmpty()) {
            throw new TohruException("Invalid entry");
        }

        LocalDateTime eventFrom = Parser.parseDateTimeString(entryEventFromString, DATE_TIME_FORMATTER);
        LocalDateTime eventTo = Parser.parseDateTimeString(entryEventToString, DATE_TIME_FORMATTER);

        if (eventFrom.isAfter(eventTo)) {
            throw new TohruException("Invalid entry");
        }

        return new EventItem(entryEventString, eventFrom, eventTo);
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
