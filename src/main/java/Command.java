public class Command {
    private String command;

    public Command(String command) {
        this.command = command;
    }

    public boolean isLeavingCommand() {
        return command.equals("bye");
    }

    public boolean isListCommand() {
        return command.equals("list");

    }

    public boolean isCompletedCommand() {
        String[] words = command.split("\\s+");
        return words[0].equals("mark");
    }

    public boolean isIncompleteCommand() {
        String[] words = command.split("\\s+");
        return words[0].equals("unmark");
    }

    public boolean isDeadlineTask() {
        String[] words = command.split("\\s+");
        return words[0].equals("deadline");
    }

    public boolean isToDoTask() {
        String[] words = command.split("\\s+");
        return words[0].equals("todo");
    }

    public boolean isEventTask() {
        String[] words = command.split("\\s+");
        return words[0].equals("event");
    }

    public String getTask() throws IncorrectCommandException{
        String[] words = command.split("\\s+");
        if (words.length == 1) {
            throw new IncorrectCommandException("Please add your task after the command. Derek needs to know everything... or he feels left out");
        }
        int firstWord = command.indexOf(" ");
        String result;
        result = command.substring(firstWord + 1);
        return result;

    }

}
