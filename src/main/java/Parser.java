public class Parser {
    /**
     * Splits the command string to extract the task number
     *
     * @param command The command string containing the command and task number
     * @return The task number as an integer
     */
    public int splitCommandAndTaskNumber(String command) {
        String taskNum = command.split(" ", 2)[1];
        return Integer.parseInt(taskNum);
    }

    /**
     *
     * @param command The command string containing the command and task description
     * @return The task description as a String
     */
    public String splitCommandAndTaskDescription(String command) {

        return command.split(" ", 2)[1];
    }

    /**
     *
     * @param command The command string containing the command, task description, and deadline
     * @return The task deadline as a String
     */
    public String[] splitDeadlineCommand(String command) {

        return command.split("/by ", 2);
    }

    /**
     *
     * @param command The command string containing the command, task description,
     *                event start time, and event end time
     * @return The event start and end time as a String
     */
    public String[] splitEventCommand(String command) {

        return command.split(" /", 3);
    }
}
