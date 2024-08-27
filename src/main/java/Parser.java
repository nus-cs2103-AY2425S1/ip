public class Parser {

    public static CommandType getCommand(String[] commandDetails) {
        return CommandType.valueOf(commandDetails[0].toUpperCase());
    }

    public static String[] getCommandDetails(String inputLine) {
        return inputLine.split(" ");   // an array containing each word of the command
    }


    /**
     * Throws JanetException when,
     * 1. mark/unmark/delete X, where X cannot be parsed into an Integer.
     * 2. mark/unmark/delete X, where X can be parsed into an Integer but, is <= 0 or > number of tasks in list.
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @throws JanetException a custom exception class specific to Janet
     */
    public static void validateCommand(String[] commandDetails, int numOfTasksInList) throws JanetException {
        // when mark/unmark/delete X, where X is too big (out or bounds) OR <= 0 OR when the list is empty.
        if ((commandDetails[0].equals("mark") || commandDetails[0].equals("unmark") ||
                commandDetails[0].equals("delete")) && commandDetails.length > 1) {
            // when the command is mark/unmark X OR delete, where X is an invalid num (too big or <= 0)
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(commandDetails[1]);   // commandDetails[1] could be a string
            } catch (NumberFormatException e) {
                throw new JanetException("WHOOPS! Please provide an integer value task number!");
            }
            if (taskNumber <= 0) {
                // still need to handle case when taskNumber >= taskIndex + 1 (unable to access janet.getTaskIndex())
                throw new JanetException("WHOOPS! Your task number cannot be negative or 0!");
            } else if (taskNumber > numOfTasksInList) {
                throw new JanetException("WHOOPS! You don't have a task of this number!");
            }
        }
    }


    /**
     * Throws JanetException when,
     * 1. first word in command is not todo/event/deadline/mark/unmark/delete.
     * 2. mark/unmark/delete and the task number is not specified.
     * 3. todo/event/deadline and the description is not stated.
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @throws JanetException a custom exception class specific to Janet
     */
    public static void checkInaccurateCommand(String[] commandDetails) throws JanetException {
        // checks for inaccurate commands 1. rubbish, 2. without any task description, 3. no number for mark/unmark/delete.
        if (!(commandDetails[0].equals("todo") || commandDetails[0].equals("deadline") || commandDetails[0].equals("event")
                || commandDetails[0].equals("mark") || commandDetails[0].equals("unmark") || commandDetails[0].equals("delete") ||
                commandDetails[0].equals("bye") || commandDetails[0].equals("list"))) {
            // when the command is gibberish and NOT one of the commands (todo, deadline, event, mark, unmark, delete)
            throw new JanetException("WHOOPS! I'm only a chatbot, so I don't know what that means...");
        } else if (commandDetails.length == 1 && !(commandDetails[0].equals("bye") || commandDetails[0].equals("list"))) {
            if (commandDetails[0].equals("mark") || commandDetails[0].equals("unmark") || commandDetails[0].equals("delete")) {
                // when the command is either (mark, unmark, delete), BUT there is no task specified
                throw new JanetException("WHOOPS! I don't know which task you are referring to...");
            } else {
                // when the command is either (todo, deadline, todo), BUT there is no task description
                throw new JanetException("WHOOPS! You can't leave out the task's description!");
            }
        }
    }

}
