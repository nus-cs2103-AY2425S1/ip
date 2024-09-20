package dawn;

import static dawn.TaskList.*;

public class ParserStub {
    private String command;
    private String input;
    private enum Command {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        BYE,
        LIST,
        TODAY
    }

    public ParserStub(String command) throws DawnException {
        if (command.equals("mark")) {
            this.command = command;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    protected static void mark(String cmd, String index) throws DawnException { // mark the tasks accordingly
        int ind;
        try {
            ind = Integer.parseInt(index);
            if (ind < 0 || ind >= numOfTasks()) {
                throw new DawnException("Task specified does not exist!\n");
            }
        } catch (NumberFormatException e) {
            throw new DawnException("Please specify the index of the task to be marked!\n");
        }

        if (cmd.equals("unmark")) {
            markAsNotDone(ind);
        } else {
            markAsDone(ind);
        }
    }
}
