package bill;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The Parser class offers helper functions to help parse user input based on the commands of the user
 * and when reading from bill.txt.
 */
public class Parser {

    /**
     * Initializes Ui.
     */
    public Parser() {

    }

    /**
     * Gets index to targeted task to mark or unmark.
     *
     * @param parsedInput Input of user in array format seperated by blank spaces.
     * @param userList Current accessible state of mutable list.
     * @return Index of targeted task to mark or unmark.
     * @throws BillException If there is an error handling the parsing of the targeted task.
     */
    public int handleMarkOfTaskParser(String[] parsedInput, ArrayList<Task> userList) throws BillException {
        // data validation
        if (parsedInput.length < 2) {
            throw new BillException("Please provide a second argument when marking or unmarking a task");
        }

        // ensures task number is within the range of the task list
        if (Integer.parseInt(parsedInput[1]) > userList.size() || Integer.parseInt(parsedInput[1]) < 1) {
            throw new BillException("There is no task of that number in the current list");
        }
        return Integer.parseInt(parsedInput[1]) - 1;
    }

    /**
     * Gets the task description of todo commands.
     *
     * @param userCommand Input of user.
     * @param userList Current accessible state of mutable list.
     * @param storage All helper methods associated with storing tasks
     * @param tasks All helper methods associated with userList, such as getters.
     * @return Index of targeted task to mark or unmark.
     * @throws BillException If there is an error handling the parsing of the targeted task.
     * @throws IOException If there is an error reading from the bill.txt file.
     */
    public String handleToDoParser(String userCommand, ArrayList<Task> userList, Storage storage, TaskList tasks)
                throws BillException, IOException {
        // data validation
        String[] parsedInput = userCommand.split(" ");
        if (parsedInput.length < 2) {
            throw new BillException("Please provide a second argument for the todo, such as a description");
        }
        return userCommand.replaceFirst("todo", "").trim();
    }

    /**
     * Gets an array, with index 0 being the task and index 1 being the date for deadlines.
     *
     * @param userCommand Input of user.
     * @return array of task and date of deadline.
     * @throws BillException If there is an error handling the parsing of the targeted task.
     * @throws IOException If there is an error reading from the bill.txt file.
     */
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

        // parse data by remove deadline word, trim white spaces and delimit by /by
        String[] trimmedUserCommand = userCommand.replaceFirst("deadline", "")
                    .trim().split(" /by ");
        String deadlineDescription = trimmedUserCommand[0];
        String deadlineBy = trimmedUserCommand[1];
        return new String[]{deadlineDescription, deadlineBy};
    }

    /**
     * Gets an array, index 0 is task, index 1 is from date and index 2 is to date for events.
     *
     * @param userCommand Input of user.
     * @return array of task, from date and to date of event.
     * @throws BillException If there is an error handling the parsing of the targeted task.
     * @throws IOException If there is an error reading from the bill.txt file.
     */
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

        // parse data by remove event keyword, trim white spaces and delimit by /from and /to
        String[] trimmedUserCommand = userCommand.replaceFirst("event", "")
                    .trim().split(" /from ");
        String eventDescription = trimmedUserCommand[0];
        String[] furtherTrimmedUserCommand = trimmedUserCommand[1].trim().split(" /to ");
        String eventFrom = furtherTrimmedUserCommand[0];
        String eventTo = furtherTrimmedUserCommand[1];

        return new String[]{eventDescription, eventFrom, eventTo};
    }

    /**
     * Gets an index of the targeted task to delete.
     *
     * @param parsedInput Input of user in array format seperated by blank spaces.
     * @param userList Current accessible state of mutable list.
     * @return index of task the user wants to delete
     * @throws BillException If there is an error handling the parsing of the targeted task.
     * @throws IOException If there is an error reading from the bill.txt file.
     */
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

    /**
     * Gets an array of the user command delimited by white spaces.
     *
     * @param userCommand Input of user.
     * @return array of the user commands seperated by whitespaces
     */
    public String[] handleRouteParser(String userCommand) {
        return userCommand.split(" ");
    }

    /**
     * Gets a string that the user wants to match that is used in find tasks
     *
     * @param parsedInput Input of user in array format seperated by blank spaces.
     * @return the keyword the user wants to match from the list of items
     * @throws BillException If there is an error handling the parsing of the targeted task.
     */
    public String handleFindParser(String ... parsedInput) throws BillException {
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

    /**
     * Generates the user help guide
     *
     * @param parsedInput Input of user in array format seperated by blank spaces.
     * @return insructions for help manual
     * @throws BillException If there is an error handling the parsing of the targeted task.
     */
    public String handleHelpParser(String[] parsedInput) throws BillException {
        // data validation
        if (parsedInput.length != 1) {
            throw new BillException("Please ensure the help command has only one argument,"
                    + " it should follow the format: help, with no other arguments");
        }
        return "Howdy you seem a little lost, these are the following commands you could use \n\n"
                + "Below is a brief description of what each command can do to get the exact formatting"
                + "simply type one of these commands and ill show you how to format that command :D \n\n"
                + "-> todo this command allows you to add a task you intend to do to your very own list!\n\n"
                + "-> deadline this command allows you to add a deadline task you intend to handle with a date"
                + " to your very own list!\n\n"
                + "-> event this command allows you to add a event task you intend to attend to your very own list!"
                + "there is come flexibility with this command whereby from and to descriptions"
                + " can be strings and not dates\n\n"
                + "-> list this command allows you to view your very own list!\n\n"
                + "-> find this command allows you to filter your list by descriptions"
                + " to narrow down what your looking for! \n\n"
                + "-> mark this command allows you to check of a task to mark it as complete! Congrats :D \n\n"
                + "-> unmark this command allows you to unmark a task to indicate you still have not done it :/ \n\n"
                + "-> delete this command allows you to remove a task from your list!\n\n"
                + "-> help this command allows you to see an overview of all the commands you could possible use \n\n"
                + "-> bye this command will stop the chatbot and ill say bye :( \n\n";
    }
}
