public class Parser {
    public int splitCommandAndTaskNumber(String command) {
        String taskNum = command.split(" ", 2)[1];
        return Integer.parseInt(taskNum);
    }
}
