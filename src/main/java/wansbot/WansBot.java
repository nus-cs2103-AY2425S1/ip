package wansbot;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import wansbot.storage.Storage;
import wansbot.tasks.Deadlined;
import wansbot.tasks.Events;
import wansbot.tasks.InputEmptyException;
import wansbot.tasks.NotANumMarkingException;
import wansbot.tasks.TaskList;
import wansbot.tasks.Todos;
import wansbot.ui.UI;

/**
 * Main class in the package which has all the logic of the Bot.
 */
public class WansBot {
    private static TaskList userTaskList = new TaskList();
    private static UI ui = new UI();
    private static Storage storage = new Storage(ui);

    /**
     * Throws InputEmptyException when user doesn't input anything after command word.
     */
    protected static void emptyInput(String userInput) throws InputEmptyException {
        if (userInput.strip().equalsIgnoreCase("todos")
                || userInput.strip().equalsIgnoreCase("deadline")
                || userInput.strip().equalsIgnoreCase("event")
                || userInput.strip().equalsIgnoreCase("mark")
                || userInput.strip().equalsIgnoreCase("unmark")
                || userInput.strip().equalsIgnoreCase("remove")) {
            throw new InputEmptyException(userInput);
        }
    }

    /**
     * Throws NumberFormatException and NotANumMarkingException when a non-number is inputted and when it's not
     * a valid task number in the userTaskList.
     */
    private static void notNumInput(String userInput, int taskListSize) throws NumberFormatException,
            NotANumMarkingException {
        if (userInput.startsWith("unmark")) {
            int posTask = Integer.parseInt(userInput.substring(7));
            if (posTask > taskListSize || posTask < 1) {
                throw new NotANumMarkingException(posTask);
            }
        } else if (userInput.startsWith("mark")) {
            int posTask = Integer.parseInt(userInput.substring(5));
            if (posTask > taskListSize || posTask < 1) {
                throw new NotANumMarkingException(posTask);
            }
        } else if (userInput.startsWith("remove")) {
            int posTask = Integer.parseInt(userInput.substring(7));
            if (posTask > taskListSize || posTask < 1) {
                throw new NotANumMarkingException(posTask);
            }
        }
    }

    /**
     * Throws InputEmptyException when user does not input a deadline after "deadline /by".
     */
    private static void missingInputDeadline(String userInput) {
        String[] splitUser = userInput.split(" /by ", 2);
        if (splitUser.length < 2) {
            throw new InputEmptyException(userInput, "/by");
        }
    }

    /**
     * Throws custom InputEmptyException for events when either missing from date after /from, missing to date after
     * /to or missing both.
     */
    protected static void missingInputEvent(String userInput) {
        String[] splitUserStartDate = userInput.split(" /from ", 3);
        if (splitUserStartDate.length < 2) {
            throw new InputEmptyException(userInput, "/from");
        }
        String[] splitUserEndDate = splitUserStartDate[1].split(" /to ", 2);
        if (splitUserEndDate.length < 2) {
            throw new InputEmptyException(userInput, "/to");
        }
    }

    /**
     * Handles marking tasks function by specifying how caught exceptions are dealt with. Prints to console what
     * user needs to correct for the command to work.
     */
    private static void markTasks(String userInput) {
        try {
            notNumInput(userInput, userTaskList.numOfTasks());
            int posTask = Integer.parseInt(userInput.substring(5)) - 1;
            userTaskList.getTask(posTask).finish();
            ui.handleSuccesfulMarking(userTaskList, posTask);
        } catch (NumberFormatException e) {
            ui.handleMarkingFormat();
        } catch (NotANumMarkingException e) {
            ui.handleInvalidNum();
        }
    }

    /**
     * Handles unmarking tasks function by specifying to user what went wrong.
     */
    private static void unmarkTasks(String userInput) {
        try {
            notNumInput(userInput, userTaskList.numOfTasks());
            int posTask = Integer.parseInt(userInput.substring(7)) - 1;
            userTaskList.getTask(posTask).unfinish();
            ui.handleSuccesfulUnmarking(userTaskList, posTask);
        } catch (NumberFormatException e) {
            ui.handleUnmarkingFormat();
        } catch (NotANumMarkingException e) {
            ui.handleInvalidNum();
        }
    }

    /**
     * Adds Todos to the userTaskList.
     */
    private static void addTodos(String userInput) {
        Todos newTodo = new Todos(userInput.substring(5));
        userTaskList.add(newTodo);
        ui.handleSuccessfulAdd(newTodo);
    }

    /**
     * Adds Deadlined to userTaskList. If there is invalid input, WansBot will guide user on the correct commands.
     */
    private static void addDeadlined(String userInput) {
        try {
            missingInputDeadline(userInput);
            String[] splitUser = userInput.split(" /by ", 2);
            Deadlined newDeadlined = new Deadlined(splitUser[0].substring(8),
                    LocalDate.parse(splitUser[1].trim()));
            userTaskList.add(newDeadlined);
            ui.handleSuccessfulAdd(newDeadlined);
        } catch (InputEmptyException e) {
            ui.handleDeadlineFormat();
        } catch (DateTimeParseException e) {
            ui.handleDateTimeException();
        }
    }

    /**
     * Adds Deadlined to userTaskList. If there is invalid input, WansBot will guide user on the correct commands.
     */
    private static void addEvent(String userInput) {
        try {
            missingInputEvent(userInput);
            String[] splitUserStartDate = userInput.split(" /from ", 3);
            String[] splitUserEndDate = splitUserStartDate[1].split(" /to ", 2);
            Events newEvent = new Events(splitUserStartDate[0].substring(5),
                    LocalDate.parse(splitUserEndDate[0].trim()),
                    LocalDate.parse(splitUserEndDate[1].trim()));
            userTaskList.add(newEvent);
            ui.handleSuccessfulAdd(newEvent);
        } catch (InputEmptyException e) {
            ui.handleEventFormat();
        } catch (DateTimeParseException e) {
            ui.handleDateTimeException();
        }
    }

    /**
     * Removes the user-specified task. If removal is of an invalid task number or not a number, WansBot will guide
     * user on how to correct the command.
     */
    private static void removeTask(String userInput) {
        try {
            notNumInput(userInput, userTaskList.numOfTasks());
            int posTask = Integer.parseInt(userInput.substring(7)) - 1;
            ui.handleRemoveTask(userTaskList, posTask);
            userTaskList.removeTask(posTask);
        } catch (NumberFormatException e) {
            ui.handleRemoveFormat();
        } catch (NotANumMarkingException e) {
            ui.handleInvalidNum();
        }
    }

    /**
     * Gathers Deadlined and Event which are of exact date and in-between start and end date respectively. WansBot
     * will tell the user which tasks these are. Todos will not be able to be found
     */
    private static void findTaskDate(String userInput) {
        try {
            String[] splitDate = userInput.split(" ");
            TaskList filteredList = new TaskList();
            for (int i = 0; i < userTaskList.numOfTasks(); i++) {
                if (userTaskList.getTask(i) instanceof Events) {
                    Events event = (Events) userTaskList.getTask(i);
                    if (event.isBetweenDate(LocalDate.parse(splitDate[1]))) {
                        filteredList.add(event);
                    }
                } else if (userTaskList.getTask(i) instanceof Deadlined) {
                    Deadlined deadlined = (Deadlined) userTaskList.getTask(i);
                    if (deadlined.isOnDate(LocalDate.parse(splitDate[1]))) {
                        filteredList.add(deadlined);
                    }
                }
            }
            ui.handleFindFiles(userTaskList, splitDate[1]);
        } catch (DateTimeParseException e) {
            ui.handleDateTimeException();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ui.introduceToUser();
        userTaskList = storage.loadTasks();

        while (true) {
            System.out.println("User: ");
            String userInput = sc.nextLine();
            try {
                emptyInput(userInput);
            } catch (InputEmptyException e) {
                ui.handleInvalidCommands(userInput);
                continue;
            }

            String command = userInput.split(" ")[0];

            switch (command) {
            case "list":
                ui.handleListingTask(userTaskList);
                break;
            case "mark":
                markTasks(userInput);
                break;
            case "unmark":
                unmarkTasks(userInput);
                break;
            case "todos":
                addTodos(userInput);
                break;
            case "deadline":
                addDeadlined(userInput);
                break;
            case "event":
                addEvent(userInput);
                break;
            case "remove":
                removeTask(userInput);
                break;
            case "save":
                storage.saveTasks(userTaskList);
                break;
            case "load":
                userTaskList = storage.loadTasks();
                break;
            case "find":
                findTaskDate(userInput);
                break;
            case "bye":
                ui.handleGoodbye();
                break;
            default:
                ui.handleUnrecognisedInput(userInput);
            }
        }
    }
}
