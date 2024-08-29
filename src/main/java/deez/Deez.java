package deez;

import javafx.util.Pair;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Properties;

public class Deez implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Storage storage = new Storage("./data");
    private static final DateTimeFormatter dateTimeInputFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected TaskList taskList = new TaskList(new ArrayList<>());
    protected boolean isActive = true;

    static int parseInt(String s) throws DeezException {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            throw new DeezException("Invalid input!", "Please enter a valid number.");
        }
    }

    private void handleExit() {
        isActive = false;
    }

    private void handleListTasks() {
        Ui.printList(taskList.getTasks());
    }

    private void invalidCommand() throws DeezException {
        throw new DeezException("Please enter a valid command.");
    }

    private void handleAddTodo(Properties props) throws DeezException {
        String name = props.getProperty("name");
        if (name.isBlank()) {
           throw new DeezException("Please provide a description for the todo.");
        }
        Todo t = new Todo(props.getProperty("name"));
        taskList.addTask(t);
        Ui.say("Easy. I have added your task.",
                t.toString(),
                "You have " + taskList.size() + " tasks in the " +
                        "list");
    }

    private void handleAddDeadline(Properties props) throws DeezException {
        try {
            LocalDateTime byDateTime = LocalDateTime.parse(props.getProperty("by"), dateTimeInputFormatter);
            Deadline d = new Deadline(props.getProperty("name"), byDateTime);
            taskList.addTask(d);
            Ui.say("Donezo. I have added your task.",
                    d.toString(),
                    "You have " + taskList.size() + " tasks in the " +
                            "list");
        } catch (DateTimeParseException e) {
            throw new DeezException("Failed to parse deadline date.", "Usage:", "deadline " +
                    "return book" +
                    " /by 2019-10-15 1800");
        }

    }

    private void handleAddEvent(Properties props) throws DeezException {
        try {
            LocalDateTime startDate = LocalDateTime.parse(props.getProperty("from"), dateTimeInputFormatter);
            LocalDateTime endDate = LocalDateTime.parse(props.getProperty("to"), dateTimeInputFormatter);
            if (startDate.isAfter(endDate)) {
                throw new DeezException("Start date must be before end date.", "Usage:",
                        "event project meeting /from 2019-10-15 1800 /to 2019-10-15 1900");
            }
            Event e = new Event(props.getProperty("name"), startDate, endDate);
            taskList.addTask(e);
            Ui.say("Event added",
                    e.toString(),
                    "You have " + taskList.size() + " tasks in the " +
                            "list");
        } catch (DateTimeParseException e) {
            throw new DeezException("Invalid date.", "Usage:", "event " +
                    "project meeting /from 2019-10-15 1800 /to 2019-10-15 1900");
        }
    }

    private void handleMarkUnmarkDone(boolean isMarkDone, Properties props) throws DeezException {
        int taskIdx = parseInt(props.getProperty("index"));
        try {
            Task t = taskList.get(taskIdx - 1);
            if (isMarkDone && !t.isDone()) {
                t.toggleDone();
            } else if (!isMarkDone && t.isDone()) {
                t.toggleDone();
            }
            Ui.say("Updated task:", t.toString());
        } catch (Exception e) {
            throw new DeezException("No task at index " + taskIdx, "Please try again.");
        }
    }

    private void handleDeleteTask(Properties props) throws DeezException {
        int taskIdx = parseInt(props.getProperty("index"));
        try {
            Task t = taskList.get(taskIdx - 1);
            taskList.remove(t);
            Ui.say("Deleted task:", t.toString(), taskList.size() + " tasks remain.");
        } catch (Exception e) {
            throw new DeezException("No task at index " + taskIdx, "Please try again.");
        }
    }

    private void handleFind(Properties props) throws DeezException {
        String keyword = props.getProperty("keyword");
        if (keyword == null || keyword.isEmpty()) {
            throw new DeezException("No keyword provided.", "Usage:", "find book", "Please try again.");
        }
        ArrayList<Task> foundTasks = taskList.getTasks(keyword);
        Ui.printList(foundTasks);
    }

    protected void handleCommand(Pair<Command, Properties> inputPair) {
        Command cmd = inputPair.getKey();
        Properties props = inputPair.getValue();
        switch (cmd) {
            case EXIT -> handleExit();
            case LIST -> handleListTasks();
            case TODO -> handleAddTodo(props);
            case DEADLINE -> handleAddDeadline(props);
            case EVENT -> handleAddEvent(props);
            case MARK -> handleMarkUnmarkDone(true, props);
            case UNMARK -> handleMarkUnmarkDone(false, props);
            case DELETE -> handleDeleteTask(props);
            case SAVE -> save();
            case FIND -> handleFind(props);
            case UNKNOWN -> invalidCommand();
        }
    }

    private void save() {
        storage.save(this);
        Ui.say("Saved successfully!");
    }

    public void run() throws IOException {
        Ui.say("Hello! I'm Deez!", "What can I do you for?");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (isActive) {
            // process command
            String inputStr = br.readLine();

            if (inputStr.isEmpty()) {
                continue;
            }
            try {
                Pair<Command, Properties> inputPair = Parser.parse(inputStr);
                handleCommand(inputPair);
            } catch (DeezException e) {
                Ui.say(e.getErrorMessages());
            }
        }
        Ui.say("Bye. Hope to see you soon!");
    }

    public static void main(String[] args) throws IOException {
        Deez deez = new Deez();
        if (storage.canLoad()) {
            try {
                deez = storage.load();
            } catch (Exception e) {
                System.out.println("Failed to load save-file, it might possibly be corrupted!");
            }
        }
        deez.run();
    }
}
