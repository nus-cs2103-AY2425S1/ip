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

    public boolean isDeleteCommand() {
        String[] words = command.split("\\s+");
        return words[0].equals("delete");
    }
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

    public boolean isToDoTask() throws IncorrectCommandException{
        String[] words = command.split("\\s+");
        if (words.length == 1) {
            throw new IncorrectCommandException("Please enter task after the command. Derek needs to everything or he feels left out...");
        }
        return words[0].equals("todo");
    }

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

    public String getTask(){
        int firstWord = command.indexOf(" ");
        String task = command.substring(firstWord + 1);
        return task;

    }

}
