import java.time.LocalDate;
import java.util.Scanner;

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

    private void respond(Command cmd) throws DawnException { //provide responses to the user input
        switch (cmd) {
        case BYE:
            System.out.println("Byeeee~ nice chatting with you! See you next time, Dawn 🌙 out");
            Storage.saveTasks("./data/dawn.txt");
            return;
        case LIST:
            TaskList.list();
            break;
        case MARK: case UNMARK:
            mark(command, input);
            break;
        case DELETE:
            TaskList.delete(input);
            break;
        case TODO: case DEADLINE: case EVENT:
            TaskList.addTask(String.valueOf(cmd), input);
            break;
        case TODAY:
            TaskList.doByToday();
            break;
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
            TaskList.markAsNotDone(ind);
        } else {
            TaskList.markAsDone(ind);
        }
    }
}
