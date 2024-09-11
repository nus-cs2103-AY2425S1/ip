package duke;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Processes the information from the user. Based upon the user's commands, calls upon and relays the necessary
 * information accordingly.
 */
public class Parser {
    private static final Set<String> VALID_COMMANDS = Set.of("list", "mark", "unmark", "todo", "event", "deadline",
            "delete", "snooze");
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
        String[] parts = inputString.split(" ", 2);
        String instruction = parts[0];
        String remainingInput = parts.length > 1 ? parts[1] : "";
        if (instruction.equals("list")) {
            ui.display(tasks);
            return;
        }
        throwInvalidInputExceptions();
        System.out.println(performCommandAndGetParseResponse(tasks, ui, instruction, remainingInput));
    }

    /**
     * This method processes the user's input as acquired the object's constructor and returns the appropriate
     * string matching the user's said input.
     * @param tasks This contains the user's tasks listed thus far.
     * @param ui The instance holding the current's user's interface for the programme.
     * @return Returns the appropriate response to match the user's instruction.
     * @throws EmptyTaskException If a task without description is provided, an exception will be thrown.
     * @throws InvalidCommandException If an invalid command is given, an exception will be thrown.
     * @throws EmptyCommandException If an empty command is given, an exception will be thrown.
     */
    public String stringProcess(TaskList tasks, Ui ui) throws EmptyTaskException, InvalidCommandException,
            EmptyCommandException, TaskListOutOfBoundsException {
        String[] parts = inputString.split(" ", 2);
        String instruction = parts[0];
        String remainingInput = parts.length > 1 ? parts[1] : "";
        if (instruction.equals("list")) {
            return ui.guiDisplay(tasks);
        }
        throwInvalidInputExceptions();
        return performCommandAndGetParseResponse(tasks, ui, instruction, remainingInput);
    }




    private static String performCommandAndGetParseResponse(
            TaskList tasks, Ui ui, String instruction, String remainingInput)
            throws InvalidCommandException, TaskListOutOfBoundsException {
        switch (instruction) {
        case "mark":
        case "unmark": {
            int idx = Integer.parseInt(remainingInput) - 1;
            tasks.set(idx, instruction.equals("mark"));
            return (instruction.equals("mark") ? "Nice! " : "OK, ")
                    + "I've marked this task as " + (instruction.equals("mark") ? "done" : "not done yet") + ":\n"
                    + tasks.get(idx);
        }
        case "todo": {
            Todo task = new Todo(remainingInput);
            tasks.add(task);
            return ui.guiTaskActionDisplay(task, "add", tasks);
        }
        case "deadline": {
            String[] deadlineParts = remainingInput.split(" /by ", 2);
            if (deadlineParts.length < 2) {
                throw new InvalidCommandException();
            }
            String name = deadlineParts[0];
            LocalDate endDate = LocalDate.parse(deadlineParts[1]);
            Deadline task = new Deadline(name, endDate);
            tasks.add(task);
            return ui.guiTaskActionDisplay(task, "add", tasks);
        }
        case "event": {
            String[] eventParts = remainingInput.split(" /from ", 2);
            if (eventParts.length < 2) {
                throw new InvalidCommandException();
            }
            String name = eventParts[0];
            String[] timeParts = eventParts[1].split(" /to ", 2);
            if (timeParts.length < 2) {
                throw new InvalidCommandException();
            }
            String start = timeParts[0];
            String end = timeParts[1];
            Event task = new Event(name, start, end);
            tasks.add(task);
            return ui.guiTaskActionDisplay(task, "add", tasks);
        }
        case "delete": {
            int idx = Integer.parseInt(remainingInput) - 1;
            Task taskToBeDeleted = tasks.get(idx);
            assert (tasks.size() > idx);
            tasks.delete(idx);
            return ui.guiTaskActionDisplay(taskToBeDeleted, "delet", tasks);
        }
        case "find": {
            TaskList matchingTasks = tasks.findAll(remainingInput);
            return ui.guiDisplaySearch(matchingTasks);

        }
        case "snooze": {
            String[] snoozeParts = remainingInput.split(" /by ", 2);
            if (snoozeParts.length < 2) {
                throw new InvalidCommandException();
            }
            LocalDate newDate = LocalDate.parse(snoozeParts[1]);
            int idx = Integer.parseInt(snoozeParts[0]) - 1;
            Task taskToBeSnoozed = tasks.get(idx);
            assert (tasks.size() > idx);
            tasks.snooze(idx, newDate);
            return ui.guiTaskActionDisplay(taskToBeSnoozed, "snooz", tasks);
        }
        default: {
            throw new InvalidCommandException();
        }
        }
    }

    private void throwInvalidInputExceptions() throws InvalidCommandException,
            EmptyNonTaskCommandException, EmptyTaskException {
        String[] parts = inputString.split(" ", 2);
        String instruction = parts[0];
        String remainingInput = parts.length > 1 ? parts[1] : "";

        if (!VALID_COMMANDS.contains(instruction)) {
            throw new InvalidCommandException();
        }
        if (remainingInput.isEmpty()) {
            if (Stream.of("todo", "event", "deadline", "delete", "snooze").anyMatch(instruction::equals)) {
                throw new EmptyTaskException();
            } else if (Stream.of("mark", "unmark", "snooze").anyMatch(instruction::equals)) {
                throw new EmptyNonTaskCommandException();
            }
        }
    }

}
