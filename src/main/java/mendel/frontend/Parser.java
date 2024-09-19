package mendel.frontend;

import mendel.dbmanager.Storage;
import mendel.discretetask.Deadline;
import mendel.discretetask.Event;
import mendel.discretetask.Task;
import mendel.discretetask.Todo;
import mendel.mendelexception.ConditionalExceptionHandler;
import mendel.mendelexception.MendelException;
import mendel.metacognition.LeaveCommand;
import mendel.metacognition.TaskList;

/**
 * The class processes user input and manages tasks accordingly.
 */
public class Parser {
    private final TaskList taskStorage;
    private final Storage dbContoller;

    /**
     * Constructs a Parser object and initializes task storage and database controller.
     * The database is loaded into the task storage upon initialization.
     */
    public Parser() {
        this.taskStorage = new TaskList();
        this.dbContoller = new Storage("data/dbTaskList.txt");
        dbContoller.loadInto(taskStorage);
    }

    /**
     * Processes the user's input command and performs the corresponding action.
     *
     * @param currAction The user input command.
     * @return The response message after executing the command.
     * @throws MendelException If the command is invalid or if there is an error processing it.
     */
    public String manage(String currAction) throws MendelException {
        currAction = currAction.strip().replaceAll(" +", " ");
        String[] segments = currAction.split(" ");
        String commandWord = segments[0].toLowerCase();
        if (isMonolithicCommand(commandWord)) {
            return executeMonolithicCommand(commandWord, currAction);
        } else if (isBinaryIndexCommand(commandWord)) {
            return executeBinaryIndexCommand(commandWord, currAction);
        } else if (isTaskCommand(commandWord)) {
            return executeTaskCommand(commandWord, currAction);
        } else if (isListUtilityCommand(commandWord)) {
            return executeListUtilityCommand(commandWord, currAction);
        } else {
            throw new MendelException("OOPS! I cannot understand command\nCheck the first word.");
        }

    }

    private String executeMonolithicCommand(String command, String currAction) {
        if (command.equals("bye")) {
            return new LeaveCommand().speak();
        } else if (command.equals("list")) {
            return this.taskStorage.speak();
        } else {
            throw new MendelException("OOPS! I cannot understand command\nCheck the first word.");
        }
    }
    private String executeBinaryIndexCommand(String command, String currAction) {
        String[] segments = currAction.split(" ");
        String message;
        handleSequenceNumberSizeError(segments);
        if (command.equals("mark")) {
            message = taskStorage.marker(Integer.parseInt(segments[1]) - 1);
        } else if (command.equals("unmark")) {
            message = taskStorage.unMarker(Integer.parseInt(segments[1]) - 1);
        } else if (command.equals("delete")) {
            message = taskStorage.delete(Integer.parseInt(segments[1]) - 1);
        } else {
            throw new MendelException("OOPS! I cannot understand command\nCheck the first word.");
        }
        this.dbContoller.update(this.taskStorage);
        return message;
    }
    private String executeTaskCommand(String command, String currAction) {
        Task task;
        if (command.equals("todo")) {
            task = Todo.of(currAction);
        } else if (command.equals("deadline")) {
            task = Deadline.of(currAction);
        } else if (command.equals("event")) {
            task = Event.of(currAction);
        } else {
            throw new MendelException("OOPS! I cannot understand command\nCheck the first word.");
        }
        String message = taskStorage.add(task);
        this.dbContoller.create(task, taskStorage.isFirstTask());
        return message;

    }
    private String executeListUtilityCommand(String command, String currAction) {
        if (command.equals("remind")) {
            return taskStorage.find(currAction);
        } else if (command.equals("find")) {
            return taskStorage.findDescription(currAction);
        } else {
            throw new MendelException("OOPS! I cannot understand command\nCheck the first word.");
        }
    }

    private boolean isMonolithicCommand(String command) {
        return command.equals("bye") || command.equals("list");
    }
    private boolean isBinaryIndexCommand(String command) {
        return command.equals("mark") || command.equals("unmark") || command.equals("delete");
    }
    private boolean isTaskCommand(String command) {
        return command.equals("todo") || command.equals("deadline") || command.equals("event");
    }
    private boolean isListUtilityCommand(String command) {
        return command.equals("remind") || command.equals("find");
    }

    private void handleSequenceNumberSizeError(String[] segments) {
        ConditionalExceptionHandler.of()
                .conditionTriggerException(segments.length < 2, "OOPS! No serial given.\nSpecify serial.")
                .conditionTriggerException(segments.length > 2, "OOPS! Too much stuff.\nSpecify only serial.");
        try {
            Integer.parseInt(segments[1]);
        } catch (NumberFormatException e) {
            throw new MendelException("OOPS! That is invalid serial\nSpecify integer serial");
        }
    }

}
