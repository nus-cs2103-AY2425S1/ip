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
        TODAY,
        FIND
    }
    private Parser() {
    }

    protected static String parse(String fullCommand) throws DawnException {
        String[] words = fullCommand.split(" ");
        String cmd = words[0].toUpperCase();
        Command command;
        String detailedInstruction = String.join(" ", java.util.Arrays.copyOfRange(words, 1, words.length));
        try {
            command = Command.valueOf(cmd); // convert the command input to a corresponding enum constant
            switch (command) {
            case BYE:
                String logo = "૮꒰ ˶• ༝ •˶꒱ა ♡";
                saveTasks("./data/dawn.txt");
                return "Bye! Nice chatting with you :)\n See you next time, Bunny out \n" + logo;
            case LIST:
                return list();
            case MARK:
            case UNMARK:
                return mark(cmd, detailedInstruction);
            case DELETE:
                return delete(detailedInstruction);
            case TODO:
            case DEADLINE:
            case EVENT:
                return addTask(cmd, detailedInstruction);
            case TODAY:
                return doByToday();
            case FIND:
                return findTask(detailedInstruction);
            }
        } catch (IllegalArgumentException e) {
            throw new DawnException("I do not undestand what you mean.. please try something else!\n");
        }
        return cmd;
    }

    protected static String mark(String cmd, String index) throws DawnException { // mark the tasks accordingly
        int ind;
        try {
            ind = Integer.parseInt(index);
            if (ind <= 0 || ind > numOfTasks()) {
                throw new DawnException("Task specified does not exist!\n");
            }
        } catch (NumberFormatException e) {
            throw new DawnException("Please specify the index of the task to be marked!\n");
        }

        if (cmd.equals("UNMARK")) {
            return markAsNotDone(ind);
        } else {
            return markAsDone(ind);
        }
    }

    public static String findTask(String input) throws DawnException {
        if (input.isBlank()) {
            throw new DawnException("Please specify what tasks you are looking for!");
        }

        StringBuilder s = new StringBuilder();
        s.append("Finding the matching tasks in your list...\n");

        boolean hasMatch = false;
        int counter = 1;
        for (int i = 0; i < numOfTasks(); i++) {
            if (getTask(i).isAMatch(input)) {
                hasMatch = true;
                s.append(counter + ". " + getTask(i) + "\n");
                counter++;
            }
        }
        if (!hasMatch) {
            s.append("There are no matching tasks in your task list!");
        }
        return String.valueOf(s);
    }
}
