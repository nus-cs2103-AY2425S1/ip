/**
 * The Command class processes user input commands and determines the type of command
 * based on the input string. It also provides methods to check the validity of the command
 * and to extract the task details from the command.
 */
public class Command {
    private String command;

    /**
     * Constructs a Command object with the specified command string.
     * @param command the user's input command as a string
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Checks if the command is a consent command ('Y' or 'N').
     * @throws IncorrectCommandException if the command is not 'Y' or 'N'
     */
    public void isConsent() throws IncorrectCommandException{
        if (!(this.command.equalsIgnoreCase("Y") || this.command.equalsIgnoreCase("N"))) {
            throw new IncorrectCommandException("Hate me just say! Answer my question RRRRAHHHH!!!");
        }
    }

    /**
     * Checks if the command is a 'bye' command, indicating the user wants to exit.
     * @return true if the command is 'bye', false otherwise
     */
    public boolean isLeavingCommand() {
        return this.command.equals("bye");
    }

    /**
     * Checks if the command is a 'list' command, indicating the user wants to list all tasks.
     * @return true if the command is 'list', false otherwise
     */
    public boolean isListCommand() {
        return command.equals("list");

    }

    /**
     * Checks if the command is a 'mark' command to mark a task as completed.
     * Validates the task number.
     * @param size the total number of tasks in the list
     * @return true if the command is 'mark', false otherwise
     * @throws IncorrectCommandException if the task number is out of bounds
     */
    public boolean isCompletedCommand(Integer size) throws IncorrectCommandException {
        String[] words = command.split("\\s+");
        if (words[0].equals("mark")) {
            int taskNumber = Integer.valueOf(words[1]);
            if (taskNumber < size || taskNumber > size) {
                throw new IncorrectCommandException("do you not know how to count????");
            }
        }
        return words[0].equals("mark");
    }

    /**
     * Checks if the command is an 'unmark' command to mark a task as incomplete.
     * Validates the task number.
     * @param size the total number of tasks in the list
     * @return true if the command is 'unmark', false otherwise
     * @throws IncorrectCommandException if the task number is out of bounds
     */
    public boolean isIncompleteCommand(Integer size) throws IncorrectCommandException {
        String[] words = command.split("\\s+");
        if (words[0].equals("unmark")) {
            int taskNumber = Integer.valueOf(words[1]);
            if (taskNumber < size || taskNumber > size) {
                throw new IncorrectCommandException("do you not know how to count????");
            }
        }
        return words[0].equals("unmark");
    }


    /**
     * Checks if the command is a 'delete' command to remove a task.
     * Validates the task number.
     * @param size the total number of tasks in the list
     * @return true if the command is 'delete', false otherwise
     * @throws IncorrectCommandException if the task number is out of bounds
     */
    public boolean isDeleteCommand(Integer size) throws IncorrectCommandException{
        String[] words = command.split("\\s+");
        if (words[0].equals("delete")) {
            int taskNumber = Integer.valueOf(words[1]);
            if (taskNumber < size || taskNumber > size) {
                throw new IncorrectCommandException("do you not know how to count????");
            }
        }
        return words[0].equals("delete");
    }

    /**
     * Checks if the command is a 'deadline' task command and validates its format.
     * @return true if the command is a 'deadline' task, false otherwise
     * @throws IncorrectCommandException if the deadline task format is incorrect
     */
    public boolean isDeadlineTask() throws IncorrectCommandException{
        String[] words = command.split("\\s+");
        if (words[0].equals("deadline")) {
            String[] parts = command.split("/");
            if (parts.length != 2) {
                throw new IncorrectCommandException("Please enter your commands correctly for Derek (deadline (task) /by (date))");
            }
            return true;

        } else {
            return false;
        }
    }

    /**
     * Checks if the command is a 'todo' task command and validates its format.
     * @return true if the command is a 'todo' task, false otherwise
     * @throws IncorrectCommandException if the 'todo' task format is incorrect
     */
    public boolean isToDoTask() throws IncorrectCommandException{
        String[] words = command.split("\\s+");
        if (words[0].equals("todo")) {
            if (words.length == 1) {
                throw new IncorrectCommandException("Please enter task after the command. Derek needs to everything or he feels left out...");
            }
        }
        return words[0].equals("todo");
    }

    /**
     * Checks if the command is an 'event' task command and validates its format.
     * @return true if the command is an 'event' task, false otherwise
     * @throws IncorrectCommandException if the event task format is incorrect
     */
    public boolean isEventTask() throws IncorrectCommandException {
        String[] words = command.split("\\s+");
        if (words[0].equals("event")) {
            String[] parts = command.split("/");
            if (parts.length != 3) {
                throw new IncorrectCommandException("Please enter your commands correctly for Derek (event (task) /from (time) /to (time)");
            }
            return true;

        } else {
            return false;
        }
    }

    /**
     * Extracts the task description from the command.
     * @return the task description as a string
     */
    public String getTask(){
        int firstWord = command.indexOf(" ");
        return command.substring(firstWord + 1);

    }

}
