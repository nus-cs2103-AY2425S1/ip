package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    /**
     * Constructs a new Parser instance.
     */
    public Parser() {
    }

    /**
     * Converts a string representation of a task to a Task object.
     *
     * @param line the string representation of a task, formatted as
     *             "type | status | description | time"
     * @return the Task object corresponding to the string
     */
    public Task convertStringToTask(String line) {
        assert line != null : "Input line cannot be null";

        Task task;
        String[] parts = line.split(" \\| ");
        assert parts.length >= 3 : "Invalid task format";

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        assert taskType.equals("T") || taskType.equals("D") || taskType.equals("E") : "Unknown task type";

        if (taskType.equals("T")) {
            Todo todo = new Todo(description);
            if (isDone) {
                todo.markAsDone();
            }
            task = todo;
        } else if (taskType.equals("D")) {
            assert parts.length == 4 : "Invalid deadline format";

            String timeToConvert = isolateTimeToConvert(parts[3]);
            Deadline deadline = new Deadline(description, convertStringToDate(timeToConvert));
            if (isDone) {
                deadline.markAsDone();
            }
            task = deadline;
        } else {
            assert parts.length == 5 : "Invalid event format";

            String timeToConvertFrom = isolateTimeToConvert(parts[3]);
            String timeToConvertTo = isolateTimeToConvert(parts[4]);
            Event event = new Event(description, convertStringToDate(timeToConvertFrom),
                    convertStringToDate(timeToConvertTo));
            if (isDone) {
                event.markAsDone();
            }
            task = event;
        }
        return task;
    }

    private String isolateTimeToConvert(String timeContainingT) {
        String[] stringWithRemovedT = timeContainingT.split("T", 2);
        String timeToConvert = stringWithRemovedT[0] + " " + stringWithRemovedT[1];
        return timeToConvert;
    }

    /**
     * Converts a string representation of a date and time to a LocalDateTime object.
     *
     * @param dateTimeString the string representation of the date and time,
     *                       formatted as "yyyy-MM-dd HH:mm"
     * @return the LocalDateTime object parsed from the string,
     * or null if the format is invalid
     */
    public LocalDateTime convertStringToDate(String dateTimeString) {
        assert dateTimeString != null : "Date-time string cannot be null";

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date-time format. " +
                    "Please provide the date-time in 'yyyy-MM-dd HH:mm' format. " +
                    "Time should be in 24 hours format.");
        }
        return null;
    }

    private Todo parseTodoCommand(String command) {
        assert command.startsWith("todo") : "Command must start with 'todo'";

        String description = command.substring(4).trim(); // Extract description
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Description for 'todo' cannot be empty.");
        }
        return new Todo(description);
    }

    private Deadline parseDeadlineCommand(String command) {
        assert command.startsWith("deadline") : "Command must start with 'deadline'";

        String[] parts = command.split("/by");
        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Deadline command must contain '/by' followed by a date-time.");
        }

        String description = parts[0].substring(8).trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Description for 'deadline' cannot be empty.");
        }

        String dateTimeString = parts[1].trim();
        LocalDateTime by = convertStringToDate(dateTimeString);
        return new Deadline(description, by);
    }

    protected Event parseEventCommand(String command) {
        assert command.startsWith("event") : "Command must start with 'event'";

        String[] partsFrom = command.split("/from");
        if (partsFrom.length != 2) {
            throw new IllegalArgumentException(
                    "Event command must contain '/from' followed by a start date-time.");
        }

        String[] partsTo = partsFrom[1].split("/to");
        if (partsTo.length != 2) {
            throw new IllegalArgumentException(
                    "Event command must contain '/to' followed by an end date-time.");
        }

        String description = partsFrom[0].substring(5).trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Description for 'event' cannot be empty.");
        }

        String fromDateTimeString = partsTo[0].trim(); // Extract start date-time
        String toDateTimeString = partsTo[1].trim(); // Extract end date-time

        LocalDateTime from = convertStringToDate(fromDateTimeString);
        LocalDateTime to = convertStringToDate(toDateTimeString);
        return new Event(description, from, to);
    }

    private int parseIndexCommand(String[] getInstr, TaskList taskList) throws InvalidIndexException {
        assert getInstr != null : "Instruction cannot be null";
        assert taskList != null : "TaskList cannot be null";

        int index;
        if (getInstr.length <= 1) {
            throw new InvalidIndexException("Invalid index provided, please provide proper index.");
        } else {
            index = Integer.parseInt(getInstr[1]);
        }
        if (index - 1 < 0 || index - 1 >= taskList.getSize()) {
            throw new InvalidIndexException("Invalid index provided, please provide proper index.");
        }
        return index;
    }

    /**
     * Parses a user command and executes the corresponding operation on the task list.
     *
     * @param command  the user command to parse
     * @param taskList the TaskList object on which to perform the operation
     * @param storage  the Storage object to handle file operations
     * @param ui       the Ui object to interact with the user
     */
    public void parseCommand(String command, TaskList taskList, Storage storage, Ui ui) {
        String[] getInstr = command.split(" ", 2);
        String instr = getInstr[0];
        int index;
        switch (instr) {
        case "mark":
            try {
                index = parseIndexCommand(getInstr, taskList);
                taskList.mark(index, storage);
            } catch (InvalidIndexException e) {
                System.out.println(e.toString());
            }
            break;
        case "unmark":
            try {
                index = parseIndexCommand(getInstr, taskList);
                taskList.unmark(index, storage);
            } catch (InvalidIndexException e) {
                System.out.println(e.toString());
            }
            break;
        case "delete":
            try {
                index = parseIndexCommand(getInstr, taskList);
                taskList.delete(index, storage);
            } catch (InvalidIndexException e) {
                System.out.println(e.toString());
            }
            break;
        case "list":
            ui.printList(taskList);
            break;
        case "bye":
            ui.bye();
            break;
        case "todo":
            try {
                Task todo = parseTodoCommand(command);
                taskList.add(todo, storage);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "deadline":
            try {
                Task deadline = parseDeadlineCommand(command);
                taskList.add(deadline, storage);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "event":
            try {
                Task event = parseEventCommand(command);
                taskList.add(event, storage);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            break;
        case "find":
            try {
                if (getInstr.length <= 1) {
                    throw new DukeException("Please provide a keyword");
                }
                ArrayList<Task> tasksFound = taskList.findTasks(getInstr[1]);
                ui.printKeywordList(tasksFound);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        default:
            try {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
