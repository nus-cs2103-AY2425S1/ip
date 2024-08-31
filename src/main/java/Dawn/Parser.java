package Dawn;

import static Dawn.Storage.saveTasks;
import static Dawn.TaskList.*;

public class Parser {
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

    public Parser(String command, String input) throws DawnException {
        this.command = command;
        this.input = input;
        Command cmd;
        try {
            cmd = Command.valueOf(command.toUpperCase()); // convert the command input to a corresponding enum constant
            respond(cmd);
        } catch (IllegalArgumentException e) {
            throw new DawnException("Am I supposed to know what that means? Try something else\n");
        }
    }

    private void respond(Command cmd) { //provide responses to the user input
        try {
            switch (cmd) {
            case BYE:
                System.out.println("Byeeee~ nice chatting with you! See you next time, Dawn 🌙 out");
                saveTasks("./data/dawn.txt");
                String divider = "--".repeat(30);
                System.out.println(divider);
                return;
            case LIST:
                list();
                break;
            case MARK:
            case UNMARK:
                mark(command, input);
                break;
            case DELETE:
                delete(input);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                addTask(String.valueOf(cmd), input);
                break;
            case TODAY:
                doByToday();
                break;
            }
        } catch (DawnException e) {
            System.out.println(e);
        }
    }

    private static void mark(String cmd, String index) throws DawnException { // mark the tasks accordingly
        int ind;
        try {
            ind = Integer.parseInt(index);
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
