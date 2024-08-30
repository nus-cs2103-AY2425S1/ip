package bill;

import java.io.IOException;

import java.util.ArrayList;

/**
 * The Parser class offers helper functions to help parse user input based on the commands of the user and when reading from bill.txt.
 */
public class Parser {

    /**
     * Initializes Ui.
     */
    public Parser() {

    }

    public int handleMarkOfTaskParser(String[] parsedInput, ArrayList<Task> userList) throws BillException {
        // data validation
        if (parsedInput.length < 2) {
            throw new BillException("Please provide a second argument when marking or unmarking a task");
        }

        // ensure task number is within the range of the task list
        if (Integer.parseInt(parsedInput[1]) > userList.size() || Integer.parseInt(parsedInput[1]) < 1) {
            throw new BillException("There is no task of that number in the current list");
        }
        return Integer.parseInt(parsedInput[1]) - 1;
    }

    public String handleToDoParser(String userCommand, ArrayList<Task> userList, Storage storage, TaskList tasks)
                throws BillException, IOException {
        // data validation
        String[] parsedInput = userCommand.split(" ");
        if (parsedInput.length < 2) {
            throw new BillException("Please provide a second argument for the todo, such as a description");
        }
        return userCommand.replaceFirst("todo", "").trim();
    }

    public String[] handleDeadlineParser(String userCommand) throws BillException, IOException {
        // data validation
        String[] parsedInput = userCommand.split(" ");
        if (parsedInput.length < 4) {
            throw new BillException("4 Arguments needed minimum for deadline command, following the format:"
                        + " deadline <task> /by <date>, where <> suggest user input");
        }
        if (!userCommand.contains(" /by ")) {
            throw new BillException("Missing /by with spaces around it, ensure to follow the format:"
                        + " deadline <task> /by <date> where <> suggest user input");
        }

        // data parsing
        // remove deadline, trim white spaces and delimit by /by
            String[] trimmedUserCommand = userCommand.replaceFirst("deadline", "")
                        .trim().split(" /by ");
            String deadlineDescription = trimmedUserCommand[0];
            String deadlineBy = trimmedUserCommand[1];
            return new String[]{deadlineDescription, deadlineBy};
    }

    public String[] handleEventParser(String userCommand) throws BillException, IOException {
        // data validation
        String[] parsedInput = userCommand.split(" ");
        if (parsedInput.length < 6) {
            throw new BillException("6 Arguments needed minimum for deadline command, following the format:"
                        + " event <task> /from <date1> /to <date2>, where <> suggest user input");
        }
        if (!userCommand.contains(" /from ")) {
            throw new BillException("Missing /from with spaces around it, ensure to follow the format:"
                        + " event <task> /from <date1> /to <date2>, where <> suggest user input");
        }
        if (!userCommand.contains(" /to ")) {
            throw new BillException("Missing /to with spaces around it, ensure to follow the format:"
                        + " event <task> /from <date1> /to <date2>, where <> suggest user input");
        }

        // data parsing
        // remove event, trim white spaces and delimit by /from and /to
        String[] trimmedUserCommand = userCommand.replaceFirst("event", "")
                    .trim().split(" /from ");
        String eventDescription = trimmedUserCommand[0];
        String[] furtherTrimmedUserCommand = trimmedUserCommand[1].trim().split(" /to ");
        String eventFrom = furtherTrimmedUserCommand[0];
        String eventTo = furtherTrimmedUserCommand[1];

       return new String[]{eventDescription, eventFrom, eventTo};
    }

    public int handleDeleteParser(String[] parsedInput, ArrayList<Task> userList) throws BillException, IOException {
        // data validation
        if (parsedInput.length < 2) {
            throw new BillException("Please provide a second argument when deleting a task");
        }
        if (parsedInput.length > 2) {
            throw new BillException("Please provide only one additional argument after the delete word,"
                        + " ensure to follow the format: delete <number>, where <> is your input");
        }
        // ensure task number is within the range of the task list
        if (Integer.parseInt(parsedInput[1]) > userList.size() || Integer.parseInt(parsedInput[1]) < 1) {

            throw new BillException("There is no task of that number in the current list, unable to delete,"
                        + " please try again with a valid number");
        }

        return Integer.parseInt(parsedInput[1]) - 1;
    }

    public String[] handleRouteParser(String userCommand) {
        return userCommand.split(" ");
    }

    public String handleFindParser(String[] parsedInput) throws BillException {
        // data validation
        if (parsedInput.length < 2) {
            throw new BillException("Please provide a second argument for the find command, such as a keyword,"
                        + " the format for find is: find <>, where <> is a single keyword");
        }
        if (parsedInput.length > 2) {
            throw new BillException("Please ensure the following format for the find command:"
                        + " find <>, where <> is a single keyword");
        }
        return parsedInput[1];
    }
}
