package utilities;

import exceptions.BadDescriptionException;
import exceptions.DateTimeException;
import exceptions.UnknownCommandException;

import java.time.format.DateTimeParseException;

import tasks.Deadlines;
import tasks.Event;
import tasks.Task;
import tasks.TaskType;
import tasks.ToDos;

/**
 * Parses user input to execute corresponding commands.
 */
public class CommandParser {
    private final static String MESSAGE_ONLIST = "Here are the tasks in your list: \n";
    private final static String REGEX_INT_PATTERN = "[^0-9]";
    private enum Commands {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        FIND("find"),
        TAG("tag"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        private final String command;

        Commands(String command) {
            this.command = command;
        }
    }

    /**
     * Parses and performs actions for the given command.
     *
     * @param input String command for processing.
     * @param taskList TaskList class for maintaining tasks.
     * @param store Storage class for writing to files.
     * @return String value for user response.
     */
    public static String parseCommand(String input, TaskList taskList, Storage store) {
        String response = "";
        if (input.startsWith(Commands.LIST.command)) {
            response += MESSAGE_ONLIST;
            response += taskList.toString();
        } else if (input.startsWith(Commands.UNMARK.command) || input.startsWith(Commands.MARK.command)) {
            // extract integer value
            int index = extractIntegerFromString(input) - 1;
            assert index <= taskList.getSize(); // add assertion to check index
            response += taskList.updateTaskListStatus(index, input.startsWith("mark"));
            store.updateTaskStatus(index, input.startsWith(Commands.MARK.command));
        } else if (input.startsWith(Commands.DELETE.command)) {
            // extract integer value
            int index = extractIntegerFromString(input) - 1;
            assert index <= taskList.getSize(); // add assertion to check index
            response += taskList.removeFromTaskList(index);
            store.removeFileTask(index);
        } else if (input.startsWith(Commands.FIND.command)) {
            String matchValue = input.replace("find", "").strip();
            assert !matchValue.isEmpty();
            response += taskList.findTasks(matchValue);
        } else if (input.startsWith(Commands.TAG.command)) {
            String[] splits = input.split("/");
            int index = extractIntegerFromString(splits[0]) - 1;
            response += taskList.addTag(index, splits[1].strip());
            store.updateTaskTag(index, splits[1].strip());
        } else {
            try {
                TaskType type;
                if (input.startsWith(Commands.TODO.command)) {
                    type = TaskType.TODO;
                } else if (input.startsWith(Commands.DEADLINE.command)) {   
                    type = TaskType.DEADLINE;
                } else if (input.startsWith(Commands.EVENT.command)) {
                    type = TaskType.EVENT;
                } else {
                    throw new UnknownCommandException();
                }
                response += createTask(type, input, taskList, store, response);
            } catch (UnknownCommandException | BadDescriptionException | DateTimeException e) {
                response += Ui.updateUserOnError(e);
            }
        }

        assert response != ""; // important check for return

        return response;
    }

    /**
     * Handles the creation of tasks.
     * 
     * @param type TaskType of the task to be created.
     * @param input String containing the parameters for the task.
     * @param taskList TaskList class for maintaining tasks.
     * @param store Storage class for writing to files
     * @param response String value of current response.
     * @return String value of user response.
     * @throws BadDescriptionException If there are missing descriptions for the task
     * @throws DateTimeException If date format is not correct
     */
    private static String createTask(TaskType type, String input, TaskList taskList, Storage store, String response) throws BadDescriptionException, DateTimeException {
        String[] splits = input.split("/");
        String name = splits[0].substring(type.name().length() + 1);
        if (name.isEmpty()) {
            throw new BadDescriptionException(type);
        }
        try {
            switch (type) {
            case TODO:
                Task t = new ToDos(name);
                response += taskList.addToTaskList(t, name);
                store.updateFileTasks(String.format("T, %d, %s, ", 0, name));
                break;
            case DEADLINE:
                if (splits.length != 2) {
                    throw new BadDescriptionException(type);
                }
                String details = splits[1].replace("by", "");
                Task t2 = new Deadlines(name, details.strip());
                response += taskList.addToTaskList(t2, name);
                store.updateFileTasks(String.format("D, %d, %s, %s, ", 0, name, t2.getWriteTaskInfo()));;
                break;
            case EVENT:
                if (splits.length != 3) {
                    throw new BadDescriptionException(type);
                }
                String startDetails = splits[1].replace("from", "");
                String endDetails = splits[2].replace("to", "");
                Task t3 = new Event(name, startDetails.strip(), endDetails.strip());
                response += taskList.addToTaskList(t3, name);
                store.updateFileTasks(String.format("E, %d, %s, %s, ", 0, name, t3.getWriteTaskInfo()));
                break;
            }
        } catch (DateTimeParseException ex) {
            // Rethrow datetime exception
            throw new DateTimeException(name);
        }

        return response;
    }

    /**
     * Extracts the integer value from string
     * @param input String to be parsed
     * @return Integer value contained in string
     */
    private static int extractIntegerFromString(String input) {
        String intValue = input.replaceAll(REGEX_INT_PATTERN, "");
        assert !intValue.isEmpty(); // add assertion to ensure index string is not null
        int index = Integer.parseInt(intValue);

        return index;
    }
}
