package bill;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The UI class allows the user to interact with the chatbot Bill, and handles routing of user requests.
 */
public class Ui {
    private Scanner userScanner;
    private Parser parser;

    private enum Route {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID, FIND
    }

    /**
     * Initializes Ui.
     */
    public Ui() {
        userScanner = new Scanner(System.in);
        parser = new Parser();
    }

    /**
     * Introduces to the user
     */
    public void introduce() {
        System.out.println("Hello! I'm Bill");
        System.out.println("What can I do for you?");
    }

    /**
     * Says bye to the user
     */
    public void conclude() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private String getUserCommand() {
        return userScanner.nextLine().trim();
    }

    /**
     * Starts an infinite loop to get user commands until user says bye which terminates the chatbot.
     *
     * @param storage All helper methods associated with storing tasks
     * @param tasks ALl helper methods associate with manipulating and showing tasks
     */
    public void handleUserCommands(Storage storage, TaskList tasks) {
        String userCommand = getUserCommand();
        while (!userCommand.equals("bye")) {
            handleRoute(userCommand, tasks.getUserList(), storage, tasks);
            userCommand = getUserCommand();
        }
    }

    /**
     * Gets the enum of the targeted user route.
     *
     * @param route All helper methods associated with storing tasks
     */
    private Route getRouteEnum(String route) {
        String routeValue = route.toUpperCase();
        try {
            return Route.valueOf(routeValue);
        } catch (IllegalArgumentException ex) {
            return Route.INVALID;
        }
    }

    /**
     * Marks the tasks, update the state of the list and save to bill.txt.
     *
     * @param parsedInput Input of user in array format seperated by blank spaces.
     * @param userList Current accessible state of mutable list.
     * @param tasks All helper methods associated with userList, such as getters.
     * @param storage All helper methods associated with storing tasks
     * @throws BillException If there is an error handling the parsing of the targeted task.
     * @throws IOException If there is an error reading from the bill.txt file.
     */
    public void handleMarkOfTask(String[] parsedInput, ArrayList<Task> userList, TaskList tasks, Storage storage)
                throws BillException, IOException {
        int targetTaskNumber = parser.handleMarkOfTaskParser(parsedInput, userList);
        tasks.markOrUnmarkTask(targetTaskNumber, parsedInput[0]);

        //update hardisk list
        storage.saveList(userList);
    }

    /**
     * Adds the todo task, update the state of the list and save to bill.txt
     *
     * @param userCommand Input of user.
     * @param userList Current accessible state of mutable list.
     * @param storage All helper methods associated with storing tasks
     * @param tasks All helper methods associated with userList, such as getters.
     * @throws BillException If there is an error handling the parsing of the targeted task.
     * @throws IOException If there is an error reading from the bill.txt file.
     */
    public void handleToDo(String userCommand, ArrayList<Task> userList, Storage storage, TaskList tasks)
                throws BillException, IOException {
        String trimmedUserCommand = parser.handleToDoParser(userCommand, userList, storage, tasks);
        tasks.addTask(new ToDo(trimmedUserCommand), userList, storage);
    }

    /**
     * Adds the deadline task, update the state of the list and save to bill.txt
     *
     * @param userCommand Input of user.
     * @param userList Current accessible state of mutable list.
     * @param storage All helper methods associated with storing tasks
     * @param tasks All helper methods associated with userList, such as getters.
     * @throws BillException If there is an error handling the parsing of the targeted task.
     * @throws IOException If there is an error reading from the bill.txt file.
     */
    public void handleDeadline(String userCommand, ArrayList<Task> userList, Storage storage, TaskList tasks)
                throws BillException, IOException {
        // data validation
        try {
            String[] trimmedUserCommand = parser.handleDeadlineParser(userCommand);
            String deadlineDescription = trimmedUserCommand[0];
            String deadlineBy = trimmedUserCommand[1];
            tasks.addTask(new Deadline(deadlineDescription, deadlineBy), userList, storage);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new BillException("Please ensure to follow the format:"
                        + " deadline <task> /by <date> where <> suggest user input.");
        } catch (DateTimeParseException ex) {
            throw new BillException(ex.getMessage()
                        + ". Please ensure to follow the format yyyy-MM-dd (e.g 2024-10-12) or"
                        + " MMM dd yyyy (Dec 10 2024) with capitalize first letter");
        }
    }


    /**
     * Adds the event task, update the state of the list and save to bill.txt
     *
     * @param userCommand Input of user.
     * @param userList Current accessible state of mutable list.
     * @param storage All helper methods associated with storing tasks
     * @param tasks All helper methods associated with userList, such as getters.
     * @throws BillException If there is an error handling the parsing of the targeted task.
     * @throws IOException If there is an error reading from the bill.txt file.
     */
    public void handleEvent(String userCommand, ArrayList<Task> userList, Storage storage, TaskList tasks)
                throws BillException, IOException {
        try {
            String[] trimmedUserCommand = parser.handleEventParser(userCommand);
            String eventDescription = trimmedUserCommand[0];
            String eventFrom = trimmedUserCommand[1];
            String eventTo = trimmedUserCommand[2];
            tasks.addTask(new Event(eventDescription, eventFrom, eventTo), userList, storage);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new BillException("Please ensure to follow the format:"
                     + " event <task> /from <date1> /to <date2>, where <> suggest user input");
        }
    }

    private void handleDelete(String[] parsedInput, ArrayList<Task> userList, TaskList tasks, Storage storage)
                throws BillException, IOException {
        int targetTaskNumber = parser.handleDeleteParser(parsedInput, userList);
        Task targetTask = userList.get(targetTaskNumber);

        // delete and update hardisk
        tasks.deleteTask(targetTaskNumber, storage);

        System.out.println("Noted. I've removed this task:");
        System.out.println(targetTask);
        System.out.println("Now you have " + userList.size() + " tasks in the list.");
    }

    private void handleFind(String[] parsedInput, TaskList tasks) throws BillException {
        String keyWord = parser.handleFindParser(parsedInput);
        tasks.showFilterList(keyWord);
    }

    /**
     * Handles routing based on user command, to call appropriate functions
     *
     * @param userCommand Input of user.
     * @param userList Current accessible state of mutable list.
     * @param storage All helper methods associated with storing tasks
     * @param tasks All helper methods associated with userList, such as getters.
     */
    public void handleRoute(String userCommand, ArrayList<Task> userList, Storage storage, TaskList tasks) {
        String[] parsedInput = parser.handleRouteParser(userCommand);
        Route route = getRouteEnum(parsedInput[0]);
        try {
            switch (route) {
            case LIST:
                tasks.showList(userList);
                break;
            case MARK:
                // Fallthrough
            case UNMARK:
                handleMarkOfTask(parsedInput, userList, tasks, storage);
                break;
            case TODO:
                handleToDo(userCommand, userList, storage, tasks);
                break;
            case DEADLINE:
                handleDeadline(userCommand, userList, storage, tasks);
                break;
            case EVENT:
                handleEvent(userCommand, userList, storage, tasks);
                break;
            case DELETE:
                handleDelete(parsedInput, userList, tasks, storage);
                break;
            case FIND:
                handleFind(parsedInput, tasks);
                break;
            default:
                throw new BillException("Not a recognised command, please try again");
            }
        } catch (BillException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Loads data in, but silence print messages, as leveraging custom functions which have print statements
     *
     * @param storage All helper methods associated with storing tasks
     * @param userList Current accessible state of mutable list.
     * @param tasks All helper methods associated with userList, such as getters.
     */
    public void silenceLoadingData(Storage storage, ArrayList<Task> userList, TaskList tasks) {
        PrintStream originalOutput = System.out;
        // temp output to hide print messages, so can leverage previously built methods which have print statements
        OutputStream silence = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                //do nothing
            }
        };
        try {
            System.setOut(new PrintStream(silence));
            storage.loadStorage(this, userList, tasks);
        } catch (IOException ex) {
            System.setOut(originalOutput);
            System.out.println(ex.getMessage());
            return;
        } catch (BillException ex) {
            System.setOut(originalOutput);
            System.out.println("There is a formatting issue with the bill.txt file, it is related to:");
            System.out.println(ex.getMessage());
            return;
        }
        System.setOut(originalOutput);
    }
}
