package duke;

import duke.Exception.DukeException;
import duke.Exception.TypeOfException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.time.format.DateTimeParseException;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that run main program
 */
public class Nameless {
    private final DateTimeFormatter PARSE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
    private final String FILE_PATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private TypeOfException exception;
    private Ui ui;
    private boolean isRunning;

    public Nameless() throws DukeException {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        exception = new TypeOfException();
        isRunning = true;
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            exception.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Run the program until user input "bye"
     */
    public void run() {
        ui.greetings();
        TextField inputField = new TextField();
        while(isRunning) {
            String input = inputField.getText();
            getResponse(input);
        }
    }

    private String bye() throws DukeException {
        storage.writeFile(tasks.getTasks());
        isRunning = false;
        return ui.goodbye();
    }

    /**
     * Adds a new todo task when input is "todo"
     * Prints the output of the added task
     */
    private String todo(String input) throws DukeException {
        assert input != null : "Input should not be null";
        String words = Parser.splitGetWords(input);
        if (words.isEmpty()) {
            exception.todoFormatError();
        }
        tasks.addTask(new Todo(words));
        return ui.showAddTask(tasks);
    }

    /**
     * Adds a new deadline task when input is "deadline"
     * Prints the output of the added task
     */
    private String deadline(String input) throws DukeException {
        assert input != null : "Input should not be null";
        String[] words = Parser.splitGetWords(input).split(" /by ", 2);
        if(words.length != 2) {
            return exception.deadlineFormatError();
        }
        try {
            LocalDateTime date = LocalDateTime.parse(words[1], PARSE_FORMAT);
            tasks.addTask(new Deadline(words[0], date));
            return ui.showAddTask(tasks);
        } catch (DateTimeParseException e) {
            return exception.timeFormatError();
        }
    }

    /**
     * Adds a new event task when input is "event"
     * Prints the output of the added task
     */
    private String event(String input) throws DukeException {
        assert input != null : "Input should not be null";
        String[] words = Parser.splitGetWords(input).split(" /from | /to ", 3);
        if (words.length != 3) {
            return exception.eventFormatError();
        }
        try {
            LocalDateTime from = LocalDateTime.parse(words[1], PARSE_FORMAT);
            LocalDateTime to = LocalDateTime.parse(words[2], PARSE_FORMAT);
            tasks.addTask(new Event(words[0], from, to));
            return ui.showAddTask(tasks);
        } catch (DateTimeParseException e) {
            return exception.timeFormatError();
        }
    }

    private String find(String input) throws DukeException {
        assert input != null : "Input should not be null";
        String word = Parser.splitGetWords(input);
        if (word.isEmpty()) {
            return exception.findFormatError();
        }
        return ui.showFindTask(tasks, word);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
            try {
                if (input.equals("bye")) {
                    return bye();
                } else if (input.equals("list")) {
                    return ui.showList(tasks);
                } else if (input.matches("mark \\d+")) {
                    return ui.showMarkTask(tasks, Parser.splitGetNum(input));
                } else if (input.matches("unmark \\d+")) {
                    return ui.showUnmarkTask(tasks, Parser.splitGetNum(input));
                } else if (input.matches("todo(?: .+)?")) {
                    return todo(input);
                } else if (input.matches("deadline(?: .+)?")) {
                    return deadline(input);
                } else if (input.matches("event(?: .+)?")) {
                    return event(input);
                } else if (input.matches("delete \\d+")) {
                    return ui.showDeleteTask(tasks, Parser.splitGetNum(input));
                } else if (input.matches("find(?: .+)?")) {
                    return find(input);
                } else {
                    return exception.noIdea();
                }

            } catch (DukeException e) {
                return e.getMessage();
            }
    }

    public static void main(String[] args) throws DukeException {
        new Nameless().run();
    }
}