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
}
