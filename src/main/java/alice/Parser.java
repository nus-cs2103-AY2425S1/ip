package alice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private Ui ui;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private TaskList list;
    private Storage storage;

    public Parser(String path) {
        this.ui = new Ui();
        this.list = new TaskList();
        this.storage = new Storage(path);
    }

    public Parser(Ui ui, TaskList list, Storage storage) {
        this.ui = ui;
        this.list = list;
        this.storage = storage;
    }

    public enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE,
        TODO, EVENT, DEADLINE, INVALID
    }

    public static CommandType parseCommand(String input) {
        switch (input) {
        case "bye":
            return CommandType.BYE;
            // Fallthrough
        case "list":
            return CommandType.LIST;
            // Fallthrough
        case "mark":
            return CommandType.MARK;
            // Fallthrough
        case "unmark":
            return CommandType.UNMARK;
            // Fallthrough
        case "delete":
            return CommandType.DELETE;
            // Fallthrough
        case "todo":
            return CommandType.TODO;
            // Fallthrough
        case "event":
            return CommandType.EVENT;
            // Fallthrough
        case "deadline":
            return CommandType.DEADLINE;
            // Fallthrough
        default:
            return CommandType.INVALID;
            // Fallthrough
        }
    }

    public boolean performAction(String input) {
        String[] parts = input.split(" ", 2);
        String verb = parts[0];
        CommandType command = parseCommand(verb);

        switch (command) {
        case BYE:
            exit();
            return false;
            // Fallthrough
        case LIST:
            listTasks();
            return true;
            // Fallthrough
        case MARK:
            handleMark(input);
            return true;
            // Fallthrough
        case UNMARK:
            handleUnmark(input);
            return true;
            // Fallthrough
        case DELETE:
            handleDelete(input);
            return true;
            // Fallthrough
        case TODO:
            handleTodo(input);
            return true;
            // Fallthrough
        case EVENT:
            handleEvent(input);
            return true;
            // Fallthrough
        case DEADLINE:
            handleDeadline(input);
            return true;
            // Fallthrough
        case INVALID:
            handleInvalid(input);
            return true;
            // Fallthrough
        default:
            return false;
            // Fallthrough
        }
    }

    public LocalDateTime setTime(String time) {
        try {
            return LocalDateTime.parse(time, formatter);
        } catch (Exception e) {
            System.out.println("Wrong format for the time: " + e.getMessage());
            return null;
        }
    }

    public void listTasks() {
        ui.listTasks(list);
    }

    private void greet() {
        ui.greet();
    }

    private void exit() {
        ui.exit();
        storage.save(list);
    }

    private void handleMark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]);
        list.markTask(num);
        storage.save(list);
    }

    private void handleUnmark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]);
        list.unmarkTask(num);
        storage.save(list);
    }

    private void handleDelete(String input) {
        int num = Integer.parseInt(input.split(" ")[1]);
        list.delete(num);
        storage.save(list);
    }

    private void handleTodo(String input) {
        try {
            String description = input.split(" ", 2)[1];
            Todo todo = new Todo(description);
            list.addToList(todo);
            storage.save(list);
        } catch (Exception e) {
            ui.todoMsg();
        }
    }

    private void handleEvent(String input) {
        try {
            String[] detail = input.split(" ", 2)[1].split("/from");
            String description = detail[0].trim();
            String[] time = detail[1].trim().split("/to");
            String start = time[0].trim();
            String end = time[1].trim();
            Event event = new Event(description, setTime(start), setTime(end));
            list.addToList(event);
            storage.save(list);
        } catch (Exception e) {
            ui.eventMsg();
        }
    }

    private void handleDeadline(String input) {
        try {
            String[] detail = input.split(" ", 2)[1].split("/by");
            String description = detail[0].trim();
            String time = detail[1].trim();
            Deadline deadline = new Deadline(description, setTime(time));
            list.addToList(deadline);
            storage.save(list);
        } catch (Exception e) {
            ui.deadlineMsg();
        }
    }

    private void handleInvalid(String input) {
        ui.invalidMsg();
    }
}
