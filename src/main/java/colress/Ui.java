package colress;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import colress.command.Command;
import colress.exception.UnknownCommandException;
import colress.exception.UnknownTaskTypeException;

public final class Ui {
    private static final String MESSAGE_FAREWELL = "Well then, I'll see you soon.";
    private static final String MESSAGE_FILE_CREATED_CONFIRMATION = "New task list file created.";
    private static final String MESSAGE_FILE_EXISTS_CONFIRMATION = "Task list file exists. Retrieving file.";
    private static final String MESSAGE_GREETING = "Hello. My name is Colress.\n"
            + "What brings you here?";
    private static final String MESSAGE_LIST_EMPTY = "Your list is empty.";
    private static final String MESSAGE_NOT_A_VALID_DATE_TIME_ERROR =
            "You did not seem to have entered a valid date/time. Try Again.";
    private static final String MESSAGE_NOT_A_VALID_NUMBER_ERROR =
            "You did not seem to have entered a valid number. Try Again.";
    private static final String PROMPT_DATE = "Enter the date (in the form yyyy-mm-dd).";
    private static final String PROMPT_DEADLINE = "Enter the deadline (in the form yyyy-mm-dd).";
    private static final String PROMPT_DEADLINE_DESCRIPTION = "Enter the description of the deadline.";
    private static final String PROMPT_EVENT_DATE = "Enter the date of the event (in the form yyyy-mm-dd).";
    private static final String PROMPT_EVENT_DESCRIPTION = "Enter the description of the event.";
    private static final String PROMPT_EVENT_END_TIME = "Enter the ending time of the event (in the form hh:mm).";
    private static final String PROMPT_EVENT_START_TIME = "Enter the starting time of the event (in the form hh:mm).";
    private static final String PROMPT_KEYWORD = "Enter the keyword to find in the list.";
    private static final String PROMPT_TASK_DESCRIPTION = "Enter the description of the task.";
    private static final String PROMPT_TASK_NUMBER = "Enter the task number.";
    private static final String PROMPT_TASK_TYPE = "Enter the type of task you wish to add to your list.";
    private static final String SPACER = "____________________________________________________________________\n";
    private final Parser parser;
    private boolean hasCalledExitCommand;

    public Ui() {
        this.parser = new Parser();
        this.hasCalledExitCommand = false;
    }

    public boolean getHasCalledExitCommand() {
        return hasCalledExitCommand;
    }
    public void print(String s) {
        System.out.println(SPACER + s + "\n" + SPACER);
    }
    public void welcome() {
        print(MESSAGE_GREETING);
    }
    public void exit() {
        print(MESSAGE_FAREWELL);
        hasCalledExitCommand = true;
    }
    public void printLoadTaskStatus(boolean hasCreatedNewFile) {
        if (hasCreatedNewFile) {
            print(MESSAGE_FILE_CREATED_CONFIRMATION);
        } else {
            print(MESSAGE_FILE_EXISTS_CONFIRMATION);
        }
    }

    public void processInput(TaskList taskList) {
        Command command = null;
        while (command == null) {
            try {
                command = parser.getCommand();
            } catch (UnknownCommandException e) {
                print(String.valueOf(e));
            }
        }
        command.execute(this, taskList);
    }

    /**
     * Prompts the user for a keyword to find in the list of tasks and returns it.
     */
    public String promptKeyword() {
        print(PROMPT_KEYWORD);
        return parser.getString();
    }

    public String promptTaskType() {
        String result = null;
        while (result == null) {
            try {
                print(PROMPT_TASK_TYPE);
                result = parser.getTaskType();
            } catch (UnknownTaskTypeException e) {
                print(String.valueOf(e));
            }
        }
        return result;
    }

    public String promptDescription(String taskType) {
        switch (taskType) {
        case "deadline":
            print(PROMPT_DEADLINE_DESCRIPTION);
            break;
        case "event":
            print(PROMPT_EVENT_DESCRIPTION);
            break;
        default:
            print(PROMPT_TASK_DESCRIPTION);
        }
        return parser.getString();
    }

    public LocalDate promptDate(String taskType) {
        String prompt;
        if (taskType.equals("deadline")) {
            prompt = PROMPT_DEADLINE;
        } else if (taskType.equals("event")) {
            prompt = PROMPT_EVENT_DATE;
        } else {
            prompt = PROMPT_DATE;
        }

        print(prompt);
        LocalDate result = parser.readDate();
        while (result == null) {
            print(MESSAGE_NOT_A_VALID_DATE_TIME_ERROR);
            print(prompt);
            result = parser.readDate();
        }
        return result;
    }

    public LocalTime promptTime(String timeType) {
        String prompt;
        if (timeType.equals("from")) {
            prompt = PROMPT_EVENT_START_TIME;
        } else {
            prompt = PROMPT_EVENT_END_TIME;
        }

        print(prompt);
        LocalTime result = parser.readTime();
        while (result == null) {
            print(MESSAGE_NOT_A_VALID_DATE_TIME_ERROR);
            print(prompt);
            result = parser.readTime();
        }
        return result;
    }

    public int promptTaskNumber(TaskList taskList) {
        if (taskList.isEmpty()) {
            print(MESSAGE_LIST_EMPTY);
            return -1;
        } else {
            print(PROMPT_TASK_NUMBER);
            int result = parser.getTaskNumber();
            while (result == -1 || taskList.isOutOfBounds(result)) {
                print(MESSAGE_NOT_A_VALID_NUMBER_ERROR);
                print(PROMPT_TASK_NUMBER);
                result = parser.getTaskNumber();
            }
            return result;
        }
    }

    public void printTasks(TaskList taskList, String listType) {
        if (taskList.isEmpty()) {
            print(MESSAGE_LIST_EMPTY);
        } else if (Objects.equals(listType, "list")) {
            String result = taskList.retrieveTasks();
            print(Objects.equals(result, "") ? MESSAGE_LIST_EMPTY : result);
        } else if (Objects.equals(listType, "date")) {
            String result = taskList.retrieveTasks(promptDate("date"));
            print(Objects.equals(result, "") ? MESSAGE_LIST_EMPTY : result);
        } else if (Objects.equals(listType, "find")) {
            String result = taskList.retrieveTasks(promptKeyword());
            print(Objects.equals(result, "") ? MESSAGE_LIST_EMPTY : result);
        }
    }

    public void printConfirmationMessage(TaskList taskList, String message) {
        print(message);
        printTasks(taskList, "list");
    }
}
