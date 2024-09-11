package snowy;

import java.util.ArrayList;

/**
 * Represents a chatbot with the ability to keep track of tasks.
 */
public class Snowy {

    private TaskList tasks;

    private final Storage storage;

    private final Ui ui;

    public Snowy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (SnowyException e) {
            this.tasks = new TaskList();
            System.out.println(e.getMessage());
        }
    }

    public String getResponse(String input) {
        String result;
        Task task;

        String[] parsedInput = Parser.parse(input);
        assert parsedInput.length == 2: "Parsed length should be 2";
        String command = parsedInput[0];
        String description = parsedInput[1];

        assert command != null : "Command should not be null";
        switch (command) {
        case "bye":
            result = ui.printEnding();
            break;

        case "list":
            result = ui.printList(this.tasks);
            break;

        case "mark":
            try {
                int index = Integer.parseInt(description);
                task = tasks.markTask(index);
                assert task != null : "task should not be null";
                result = ui.printMarkDone(task);
            } catch (NumberFormatException | SnowyException e) {
                result = ui.printIndexError();
            }
            break;

        case "unmark":
            try {
                int index = Integer.parseInt(description);
                task = tasks.unmarkTask(index);
                assert task != null : "task should not be null";
                result = ui.printMarkIncomplete(task);
            } catch (NumberFormatException | SnowyException e) {
                result = ui.printIndexError();
            }
            break;

        case "todo":
            try {
                task = tasks.addToDo(description);
                assert task != null : "task should not be null";
                result = "New todo task added:\n" + task;
            } catch (SnowyException e) {
                result = ui.printTodoFormatError();
            }
            break;

        case "deadline":
            try {
                task = tasks.addDeadline(description);
                assert task != null : "task should not be null";
                result = ("New Deadline task added:\n" + task);
            } catch (SnowyException e) {
                result = ui.printDeadlineFormatError();
            }
            break;

        case "event":
            try {
                task = tasks.addEvent(description);
                assert task != null : "task should not be null";
                result = ("New Event task added:\n " + task);
            } catch (SnowyException e) {
                result = ui.printEventFormatError();
            }
            break;

        case "delete":
            try {
                int index = Integer.parseInt(description);
                task = tasks.deleteTask(index);
                assert task != null : "task should not be null";
                result = ui.printDeleteTask(task);
            } catch (NumberFormatException | SnowyException e) {
                result = "Invalid index format. Please try again";
            }
            break;

        case "find":
            ArrayList<Task> foundTasks = tasks.findTask(description);
            result = ui.printFoundTask(foundTasks);
            break;

        case "hello":
            result = ui.printGreeting();
            break;

        default:
            result = ui.printUnknownCommand();
            break;
        }

        try {
            storage.save(tasks.toSaveString());
        } catch (SnowyException e) {
            result = ui.printUpdateError();
        }
        return result;
    }

}


