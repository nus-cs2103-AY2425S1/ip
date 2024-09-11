package duke;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Processes the information from the user. Based upon the user's commands, calls upon and relays the necessary
 * information accordingly.
 */
public class Parser {

    private String inputString;

    public Parser(String inputString) {
        setInputString(inputString);
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public String getString() {
        return inputString;
    }


    /**
     * Calls ui to ask the user for input, process the input accordingly and calls ui again to display information back
     * to the user.
     *
     * @param tasks Stores the user's tasks inside the TaskList object while the program is running.
     * @param ui Handles the interaction between the user and program.
     */
    public void process(TaskList tasks, Ui ui) throws EmptyTaskException, InvalidCommandException,
            EmptyCommandException, TaskListOutOfBoundsException {
        String command = inputString.split(" ", 2)[0];
        if (command.equals("list")) {
            ui.display(tasks);
            return;
        }
        throwInvalidInputExceptions();
        String remainingInput = inputString.split(" ", 2)[1];
        System.out.println(performCommandAndGetParseResponse(tasks, ui, command, remainingInput));
    }

    /**
     * This method processes the user's input as acquired the object's constructor and returns the appropriate
     * string matching the user's said input.
     * @param tasks This contains the user's tasks listed thus far.
     * @param ui The instance holding the current's user's interface for the programme.
     * @return Returns the appropriate response to match the user's instruction.
     * @throws EmptyTaskException
     * @throws InvalidCommandException
     * @throws EmptyCommandException
     */
    public String stringProcess(TaskList tasks, Ui ui) throws EmptyTaskException, InvalidCommandException,
            EmptyCommandException, TaskListOutOfBoundsException {
        String command = inputString.split(" ", 2)[0];
        if (command.equals("list")) {
            return ui.guiDisplay(tasks);
        }
        throwInvalidInputExceptions();
        String remainingInput = inputString.split(" ", 2)[1];
        return performCommandAndGetParseResponse(tasks, ui, command, remainingInput);
    }

    private void throwInvalidInputExceptions() throws EmptyCommandException, InvalidCommandException {
        if (inputString.split(" ", 2).length == 0) {
            throw new EmptyCommandException();
        }
        boolean flag1 = !(Arrays.asList("list", "mark", "unmark", "todo", "event", "deadline", "delete")
                .contains(inputString.split(" ", 2)[0]));
        if (inputString.split(" ", 2).length == 1 && flag1) {
            throw new InvalidCommandException();
        }
        boolean flag2 = Arrays.asList("todo", "event", "deadline", "delete").contains(inputString.split(" ", 2)[0]);
        if (inputString.split(" ", 2).length == 1 && flag2) {
            throw new EmptyTaskException();
        }
    }

    private static String performCommandAndGetParseResponse(
            TaskList tasks, Ui ui, String command, String remainingInput)
            throws InvalidCommandException {
        switch (command) {
        case "mark" -> {
            int idx = Integer.parseInt(remainingInput) - 1;
            tasks.set(idx, true);
            return "Nice! I've marked this task as done:\n" + tasks.get(idx);
        }
        case "unmark" -> {
            int idx = Integer.parseInt(remainingInput) - 1;
            tasks.set(idx, false);
            return "OK, I've marked this task as not done yet:\n" + tasks.get(idx);
        }
        case "todo" -> {
            Todo task = new Todo(remainingInput);
            tasks.add(task);
            return ui.guiTaskAddOrDeleteDisplay(task, "add", tasks);
        }
        case "deadline" -> {
            String name = remainingInput.split(" /by ", 2)[0];
            LocalDate endDate = LocalDate.parse(remainingInput.split(" /by ", 2)[1]);
            Deadline task = new Deadline(name, endDate);
            tasks.add(task);
            return ui.guiTaskAddOrDeleteDisplay(task, "add", tasks);
        }
        case "event" -> {
            String name = remainingInput.split(" /from ", 2)[0];
            remainingInput = remainingInput.split(" /from ", 2)[1];
            String start = remainingInput.split(" /to ", 2)[0];
            String end = remainingInput.split(" /to ", 2)[1];
            Event task = new Event(name, start, end);
            tasks.add(task);
            return ui.guiTaskAddOrDeleteDisplay(task, "add", tasks);
        }
        case "delete" -> {
            int idx = Integer.parseInt(remainingInput) - 1;
            Task taskToBeDeleted = tasks.get(idx);
            assert (tasks.size() > idx);
            tasks.delete(idx);
            return ui.guiTaskAddOrDeleteDisplay(taskToBeDeleted, "delet", tasks);
        }
        case "find" -> {
            TaskList matchingTasks = tasks.findAll(remainingInput);
            return ui.guiDisplaySearch(matchingTasks);

        }
        default -> throw new InvalidCommandException();
        }
    }
}
