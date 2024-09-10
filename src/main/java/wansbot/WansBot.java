package wansbot;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import wansbot.questions.Question;
import wansbot.questions.QuestionBank;
import wansbot.storage.Storage;
import wansbot.tasks.Deadlined;
import wansbot.tasks.Events;
import wansbot.tasks.InputEmptyException;
import wansbot.tasks.NotANumMarkingException;
import wansbot.tasks.TaskList;
import wansbot.tasks.Todos;
import wansbot.ui.UI;

/**
 * wansbot.Main class in the package which has all the logic of the Bot.
 */
public class WansBot {
    private static TaskList userTaskList = new TaskList();
    private static UI ui = new UI();
    private static Storage storage = new Storage(ui);
    private QuestionBank questionBank = new QuestionBank();

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
    private void checkNumInput(String userInput, int taskListSize) throws NumberFormatException,
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
    private static void checkMissingInputDeadline(String userInput) {
        String[] splitUser = userInput.split(" /by ", 2);
        if (splitUser.length < 2) {
            throw new InputEmptyException(userInput, "/by");
        }
    }

    private static void checkFormatQuestion(String userInput) {
        String[] splitUser = userInput.split("question ");
        String[] splitAns = splitUser[1].split("\\?");
        if (splitAns.length < 2) {
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
    private String markTasks(String userInput) {

        try {
            checkNumInput(userInput, userTaskList.numOfTasks());
            int posTask = Integer.parseInt(userInput.substring(5)) - 1;
            userTaskList.getTask(posTask).finish();
            return ui.handleSuccesfulMarking(userTaskList, posTask);
        } catch (NumberFormatException e) {
            return ui.handleMarkingFormat();
        } catch (NotANumMarkingException e) {
            return ui.handleInvalidNum();
        }
    }

    /**
     * Handles unmarking tasks function by specifying to user what went wrong.
     */
    private String unmarkTasks(String userInput) {
        try {
            checkNumInput(userInput, userTaskList.numOfTasks());
            int posTask = Integer.parseInt(userInput.substring(7)) - 1;
            userTaskList.getTask(posTask).unfinish();
            return ui.handleSuccesfulUnmarking(userTaskList, posTask);
        } catch (NumberFormatException e) {
            return ui.handleUnmarkingFormat();
        } catch (NotANumMarkingException e) {
            return ui.handleInvalidNum();
        }
    }

    /**
     * Adds Todos to the userTaskList.
     */
    private String addTodos(String userInput) {
        Todos newTodo = new Todos(userInput.substring(5));
        userTaskList.add(newTodo);
        return ui.handleSuccessfulAdd(newTodo);
    }

    /**
     * Adds Deadlined to userTaskList. If there is invalid input, WansBot will guide user on the correct commands.
     */
    private String addDeadlined(String userInput) {
        try {
            checkMissingInputDeadline(userInput);
            String[] splitUser = userInput.split(" /by ", 2);
            Deadlined newDeadlined = new Deadlined(splitUser[0].substring(8),
                    LocalDate.parse(splitUser[1].trim()));
            userTaskList.add(newDeadlined);
            return ui.handleSuccessfulAdd(newDeadlined);
        } catch (InputEmptyException e) {
            return ui.handleDeadlineFormat();
        } catch (DateTimeParseException e) {
            return ui.handleDateTimeException();
        }
    }

    /**
     * Adds Deadlined to userTaskList. If there is invalid input, WansBot will guide user on the correct commands.
     */
    private String addEvent(String userInput) {
        try {
            missingInputEvent(userInput);
            String[] splitUserStartDate = userInput.split(" /from ", 3);
            String[] splitUserEndDate = splitUserStartDate[1].split(" /to ", 2);
            Events newEvent = new Events(splitUserStartDate[0].substring(5),
                    LocalDate.parse(splitUserEndDate[0].trim()),
                    LocalDate.parse(splitUserEndDate[1].trim()));
            userTaskList.add(newEvent);
            return ui.handleSuccessfulAdd(newEvent);
        } catch (InputEmptyException e) {
            return ui.handleEventFormat();
        } catch (DateTimeParseException e) {
            return ui.handleDateTimeException();
        }
    }

    /**
     * Removes the user-specified task. If removal is of an invalid task number or not a number, WansBot will guide
     * user on how to correct the command.
     */
    private String removeTask(String userInput) {
        try {
            checkNumInput(userInput, userTaskList.numOfTasks());
            int posTask = Integer.parseInt(userInput.substring(7)) - 1;

            return ui.handleRemoveTask(userTaskList.removeTask(posTask));
        } catch (NumberFormatException e) {
            return ui.handleRemoveFormat();
        } catch (NotANumMarkingException e) {
            return ui.handleInvalidNum();
        }
    }

    /**
     * Gathers Deadlined and Event which are of exact date and in-between start and end date respectively. WansBot
     * will tell the user which tasks these are. Todos will not be able to be found
     */
    private String findTaskDate(String userInput) {
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
            return ui.handleFindFiles(filteredList, splitDate[1]);
        } catch (DateTimeParseException e) {
            return ui.handleDateTimeException();
        }
    }

    /**
     * Finds the current tasks in userTaskList that contain the keyword and prints to console in a list.
     */
    private String findTaskName(String userInput) { // "find [keyword]"
        String[] splitName = userInput.split("findName ");
        TaskList filteredList = new TaskList();
        for (int i = 0; i < userTaskList.numOfTasks(); i++) {
            if (userTaskList.getTask(i).hasName(splitName[1])) {
                filteredList.add(userTaskList.getTask(i));
            }
        }
        return ui.handleFindKeyword(filteredList);
    }

    /**
     * Takes in user input and adds the question to the question bank
     */
    private String learnQuestions(String userInput) {
        try {
            checkFormatQuestion(userInput);
            String[] splitName = userInput.split("question ");
            String[] splitAns = splitName[1].split("\\? ");
            System.out.println(splitAns[0]);
            System.out.println(splitAns[1]);
            questionBank.addQuestion(new Question(splitAns[0] + "?", splitAns[1]));
        } catch (InputEmptyException e) {
            return ui.handleWrongQuestionFormat();
        }

        return ui.handleLearnQuestions();
    }

    /**
     * Takes in userInput and returns WansBot's response as a String.
     */
    public String getResponse(String userInput) {
        String command = userInput.split(" ")[0];
        String response = "";

        switch (command) {
        case "list":
            response += ui.handleListingTask(userTaskList);
            break;
        case "mark":
            response += markTasks(userInput);
            break;
        case "unmark":
            response += unmarkTasks(userInput);
            break;
        case "todos":
            response += addTodos(userInput);
            break;
        case "deadline":
            response += addDeadlined(userInput);
            break;
        case "event":
            response += addEvent(userInput);
            break;
        case "remove":
            response += removeTask(userInput);
            break;
        case "save":
            response += storage.saveTasks(userTaskList);
            break;
        case "load":
            response += storage.loadTasks(userTaskList);
            break;
        case "findTask":
            response += findTaskDate(userInput);
            break;
        case "findName":
            response += findTaskName(userInput);
            break;
        case "bye":
            response += ui.handleGoodbye();
            break;
        case "question":
            response += learnQuestions(userInput);
            break;
        case "answer":
            response += questionBank.matchQuestion(userInput);
            break;
        default:
            return ui.handleUnrecognisedInput(userInput);
        }
        return response;
    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        ui.introduceToUser();
//        userTaskList = storage.loadTasks();
//
//        while (true) {
//            System.out.println("User: ");
//            String userInput = sc.nextLine();
//            try {
//                emptyInput(userInput);
//            } catch (InputEmptyException e) {
//                ui.handleInvalidCommands(userInput);
//                continue;
//            }
//
//            String command = userInput.split(" ")[0];
//
//            switch (command) {
//            case "list":
//                ui.handleListingTask(userTaskList);
//                break;
//            case "mark":
//                markTasks(userInput);
//                break;
//            case "unmark":
//                unmarkTasks(userInput);
//                break;
//            case "todos":
//                addTodos(userInput);
//                break;
//            case "deadline":
//                addDeadlined(userInput);
//                break;
//            case "event":
//                addEvent(userInput);
//                break;
//            case "remove":
//                removeTask(userInput);
//                break;
//            case "save":
//                storage.saveTasks(userTaskList);
//                break;
//            case "load":
//                userTaskList = storage.loadTasks();
//                break;
//            case "findTask":
//                findTaskDate(userInput);
//                break;
//            case "findName":
//                findTaskName(userInput);
//                break;
//            case "bye":
//                ui.handleGoodbye();
//                break;
//            default:
//                ui.handleUnrecognisedInput(userInput);
//            }
//        }
//    }
}
