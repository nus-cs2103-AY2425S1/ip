package killjoy.processing;

import java.time.LocalDateTime;
import java.util.ArrayList;

import killjoy.main.KillJoy;
import killjoy.main.UserInterface;
import killjoy.task.Task;

/**
 * Represents the ProcessTasks class of the KillJoy application.
 * Contains methods to process user input.
 */
public class ProcessTasks {
    public static final int COMMAND_LENGTH_WITHOUT_DESCRIPTION = 1;
    public static final int EVENT_INPUT_MIN_LENGTH = 3;
    private KillJoy kj;
    private UserInterface ui;

    /**
     * Constructor for the ProcessTasks class.
     *
     * @param kj The KillJoy object.
     * @param ui The UserInterface object.
     */
    public ProcessTasks(KillJoy kj, UserInterface ui) {
        this.kj = kj;
        this.ui = ui;
    }

    /**
     * Processes the user input.
     *
     * @param input
     * @return added task message or error message
     */
    public String processUserInput(String input) {
        Task.TaskType taskType = Parser.parseUserInput(input);
        if (taskType == null) {
            return UserInterface.displayUnknownCommandMessage();
        }
        String[] inputSplitBySlash = input.split("/");
        if (taskType != Task.TaskType.TODO && inputSplitBySlash.length == COMMAND_LENGTH_WITHOUT_DESCRIPTION) {
            return UserInterface.displayInvalidCommandFormatMessage();
        }
        if (inputSplitBySlash[0].split(" ").length == COMMAND_LENGTH_WITHOUT_DESCRIPTION) {
            return UserInterface.displayInvalidCommandFormatMessage();
        }
        switch (taskType) {
        case TODO: {
            handleTodoTask(input);
            break;
        }
        case DEADLINE: {
            String invalidCommandFormat = checkDateTimeIsNullError(input, Task.TaskType.DEADLINE);
            if (invalidCommandFormat != null) {
                return invalidCommandFormat;
            }
            handleDeadlineTask(input);
            break;
        }
        case EVENT: {
            String invalidCommandFormat = checkDateTimeIsNullError(input, Task.TaskType.EVENT);
            if (invalidCommandFormat != null) {
                return invalidCommandFormat;
            }
            handleEventTask(input);
            break;
        }
        default:
            break;
        }
        return ui.displayAddedTaskMessage();
    }

    /**
     * Handles the todo task.
     *
     * @param input The user input.
     */
    private void handleTodoTask(String input) {
        String description = Parser.getDescriptionFromInput(input);
        kj.addTask(description);
    }

    /**
     * Handles the deadline task.
     *
     * @param input The user input.
     */
    private void handleDeadlineTask(String input) {
        String description = Parser.getDescriptionFromInput(input);
        String by = Parser.getByTimeString(input);
        LocalDateTime byDateTime = Parser.parseStringToLocalDateTime(by);
        kj.addTask(description, byDateTime);
    }

    /**
     * Handles the event task.
     *
     * @param input The user input.
     */
    private void handleEventTask(String input) {
        String description = Parser.getDescriptionFromInput(input);
        String from = Parser.getFromTimeString(input);
        LocalDateTime fromDateTime = Parser.parseStringToLocalDateTime(from);
        String to = Parser.getToTimeString(input);
        LocalDateTime toDateTime = Parser.parseStringToLocalDateTime(to);
        kj.addTask(description, fromDateTime, toDateTime);
    }

    /**
     * Checks if the date and time is null.
     *
     * @param input The user input.
     * @return error message
     */
    private String checkDateTimeIsNullError(String input, Task.TaskType taskType) {
        if (taskType == Task.TaskType.DEADLINE) {
            String by = Parser.getByTimeString(input);
            LocalDateTime dateTime = Parser.parseStringToLocalDateTime(by);
            if (dateTime == null) {
                return UserInterface.displayInvalidCommandFormatMessageForDate();
            }
        } else if (taskType == Task.TaskType.EVENT) {
            String from = Parser.getFromTimeString(input);
            LocalDateTime dateTime = Parser.parseStringToLocalDateTime(from);
            if (dateTime == null) {
                return UserInterface.displayInvalidCommandFormatMessageForDate();
            }
            String to = Parser.getToTimeString(input);
            LocalDateTime dateTime2 = Parser.parseStringToLocalDateTime(to);
            if (dateTime2 == null) {
                return UserInterface.displayInvalidCommandFormatMessageForDate();
            }
        }
        return null;
    }
    /**
     * Marks or deletes a task.
     *
     * @param input The user input.
     * @return mark, unmark, or delete message
     */
    public String markOrDelete(String input) {
        String[] parsedInput = Parser.parseMarkUnmarkDelete(input);
        if (parsedInput == null) {
            return UserInterface.displayInvalidCommandFormatMessage();
        }
        String inputCommand = parsedInput[0];
        int taskIndex = Integer.parseInt(parsedInput[1]) - 1;
        if (taskIndex + 1 > kj.getTaskCount() || taskIndex < 0) {
            return UserInterface.displayTaskDoesNotExistMessage();
        }
        Task task = kj.getTask(taskIndex);
        if (inputCommand.equals("mark")) {
            task.changeStatus();
            String str = UserInterface.getMarkString() + "\n" + task + "\n";
            return str;
        } else if (inputCommand.equals("unmark")) {
            task.changeStatus();
            String str = UserInterface.getUnmarkString() + "\n" + task + "\n";
            return str;
        } else if (inputCommand.equals("delete")) {
            kj.removeTask(taskIndex);
            String str = UserInterface.getDeleteString() + "\n" + task + "\n";
            if (kj.getTaskCount() == 1) {
                str += "    Now you have 1 task in the list.";
                return str;
            } else {
                str += "    Now you have " + kj.getTaskCount() + " tasks in the list.";
                return str;
            }
        }
        return null;
    }

    /**
     * Creates tasks from the task information that is loaded from the save file.
     *
     * @param taskInfo The task information.
     */
    public void createTasks(String taskInfo) {
        String[] parts = Parser.parseTaskInfo(taskInfo);
        Task.TaskType taskType = Task.TaskType.valueOf(parts[0]);
        switch (taskType) {
        case TODO: {
            String description = parts[2];
            kj.addTask(description);
            changeStatusOfTask(kj.getTask(kj.getTaskCount() - 1), Integer.parseInt(parts[1]));
            break;
        }
        case DEADLINE: {
            String description = parts[2];
            LocalDateTime by = Parser.parseStringToLocalDateTime(parts[3]);
            kj.addTask(description, by);
            changeStatusOfTask(kj.getTask(kj.getTaskCount() - 1), Integer.parseInt(parts[1]));
            break;
        }
        case EVENT: {
            String description = parts[2];
            LocalDateTime from = Parser.parseStringToLocalDateTime(parts[3]);
            LocalDateTime to = Parser.parseStringToLocalDateTime(parts[4]);
            kj.addTask(description, from, to);
            changeStatusOfTask(kj.getTask(kj.getTaskCount() - 1), Integer.parseInt(parts[1]));
            break;
        }
        default:
            break;
        }
    }

    /**
     * Finds tasks that contain the keyword.
     *
     * @param input The user input.
     * @param taskList The list of tasks.
     * @return The tasks that contain the keyword.
     */
    public String findTask(String input, ArrayList<Task> taskList) {
        String[] inputAsList = input.split(" ");
        if (inputAsList.length == 1) {
            return UserInterface.displayNoStringMessage();
        }
        String keyword = inputAsList[1];
        ArrayList<Task> matchingTasks = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = kj.getTask(i);
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
                count++;
            }
        }
        if (count == 0) {
            return "No matching tasks found lahhh!! Siaaaa";
        } else {
            String str = "Here you go mate! These are the matching tasks:\n";
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task task = matchingTasks.get(i);
                str += (i + 1) + ". " + task + "\n";
            }
            return str;
        }
    }

    private void changeStatusOfTask(Task task, int i) {
        if (i == 1) {
            task.changeStatus();
        }
    }

}
