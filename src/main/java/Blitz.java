import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Blitz {
    private static ArrayList<Task> db = new ArrayList<>();
    private enum commandList {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT;

        private static boolean contains(String command) {
            for (commandList c : commandList.values()) {
                if (c.name().equals(command.toUpperCase())) {
                    return true;
                }
            }

            return false;
        }
    }
    private static final String TAB = "    ";
    private static final String DIVIDER = TAB + "-----------------------------------------------\n";

    public static void main(String[] args) {
        String greet = TAB + "Hello! I'm Blitz.\n" +
                TAB + "What can I do for you?\n";
        String end = TAB + "Bye. Hope to see you again soon!\n";

        printInDivider(greet);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String inp = sc.nextLine();

            if (inp.equals("bye")) {
                break;
            }

            if (inp.equals("list")) {
                try {
                    commandList();
                } catch (BlitzException e) {
                    printError(e);
                }
            } else {
                String[] cont = inp.split(" ", 2);
                String command = cont[0];

                try {
                    if (!checkCommandExist(command)) {
                        throw new BlitzCommandDoesNotExistException();
                    }

                    if (cont.length == 1 || cont[1].isBlank()) {
                        throw new BlitzNoParameterException();
                    }

                    switch (command) {
                        case "mark", "unmark" -> commandMarkAndUnmark(command.equals("mark"), cont[1]);
                        case "todo" -> commandTodo(cont[1]);
                        case "deadline" -> commandDeadline(cont[1]);
                        case "event" -> commandEvent(cont[1]);
                        default -> throw new BlitzCommandDoesNotExistException();
                    }
                } catch (BlitzException e) {
                    printError(e);
                }
            }
        }

        printInDivider(end);
    }

    private static void printInDivider(String cont) {
        System.out.print(DIVIDER + cont + DIVIDER);
    }

    private static void printTaskAddedWithDivider(String type, int size, Task task) {
        String toPrint = TAB + "Got it. I've added this task:\n" +
                TAB + "  [" + type + "][ ] " + task + "\n" +
                TAB + "Now you have " + size + " tasks in the list.\n";
        printInDivider(toPrint);
    }

    private static void printError(BlitzException err) {
        String toPrint = TAB + err + "\n";
        printInDivider(toPrint);
    }

    private static boolean regexChecker(String reg, String inp) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(inp);

        return matcher.find();
    }

    private static boolean checkCommandExist(String command) {
        return commandList.contains(command);
    }

    private static void commandList() throws BlitzException {
        if (db.isEmpty()) {
            throw new BlitzEmptyTaskListException();
        }

        System.out.println(DIVIDER +
                TAB + "Here are the tasks in your list:");
        for (int i = 0; i < db.size(); i++) {
            Task curr = db.get(i);
            System.out.println(TAB + (i + 1) + ".[" + curr.getType() + "]" + "[" + (curr.getStatus() ? "X" : " ") + "] " + curr);
        }
        System.out.print(DIVIDER);
    }

    private static void commandMarkAndUnmark(boolean isMark, String command) throws BlitzException {
        String[] param = command.split(" ");
        if (param.length > 1) {
            throw new BlitzInvalidParameterMoreThanOneException((isMark ? "mark" : "unmark") + " [Integer]");
        }

        try {
            int ind = Integer.parseInt(param[0]) - 1;

            if (db.isEmpty()) {
                throw new BlitzEmptyTaskListException();
            }

            Task task = db.get(ind);

            String toPrint;
            if (isMark) {
                task.markDone();
                toPrint = TAB + "Nice! I've marked this task as done:\n" +
                        TAB + "  [" + task.getType() + "]" + "[X] " + task + "\n";
            } else {
                task.unmarkDone();
                toPrint = TAB + "Ok, I've marked this task as not done yet:\n" +
                        TAB + "  [" + task.getType() + "]" + "[ ] " + task + "\n";
            }

            printInDivider(toPrint);
        } catch (IndexOutOfBoundsException e) {
            throw new BlitzIndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            throw new BlitzNumberFormatException();
        }
    }

    private static void commandTodo(String command) {
        Task temp = new Todo(command, "T", false);

        db.add(temp);
        printTaskAddedWithDivider("T", db.size(), temp);
    }

    private static void commandDeadline(String command) throws BlitzException {
        if (!regexChecker(".+ /by .+", command)) {
            throw new BlitzInvalidParameterRegexException("deadline [Task name] /by [Deadline]");
        }

        String[] param = command.split(" /by ");

        if (param[0].contains("/by") || param[1].contains("/by")) {
            throw new BlitzInvalidParameterRepeatedFlagException("/by", "deadline [Task name] /by [Deadline]");
        }

        if (param[1].isBlank()) {
            throw new BlitzInvalidParameterMissingContentException("/by", "deadline [Task name] /by [Deadline]");
        } else if (param[0].isBlank()) {
            throw new BlitzInvalidParameterMissingContentException("[Task name]", "deadline [Task name] /by [Deadline]");
        }

        Task temp = new Deadline(param[0], "D", param[1], false);

        db.add(temp);
        printTaskAddedWithDivider("D", db.size(), temp);
    }

    private static void commandEvent(String command) throws BlitzException {
        if (!regexChecker(".+ /from .+ /to .+", command)) {
            throw new BlitzInvalidParameterRegexException("event [Task name] /from [Start date or time] /to [End date or time]");
        }

        String[] param1 = command.split(" /from ");
        String[] param2 = param1[1].split(" /to ");

        if (param1[0].contains("/from") || param1[1].contains("/from")) {
            throw new BlitzInvalidParameterRepeatedFlagException("/from", "event [Task name] /from [Start date or time] /to [End date or time]");
        } else if (param2[0].contains("/to") || param2[1].contains("/to")) {
            throw new BlitzInvalidParameterRepeatedFlagException("/to", "event [Task name] /from [Start date or time] /to [End date or time]");
        } else if (param1[0].isBlank()) {
            throw new BlitzInvalidParameterMissingContentException("[Task name]", "event [Task name] /from [Start date or time] /to [End date or time]");
        } else if (param2[0].isBlank()) {
            throw new BlitzInvalidParameterMissingContentException("/from", "event [Task name] /from [Start date or time] /to [End date or time]");
        } else if (param2[1].isBlank()) {
            throw new BlitzInvalidParameterMissingContentException("/to", "event [Task name] /from [Start date or time] /to [End date or time]");
        }

        Task temp = new Event(param1[0], "E", param2[0], param2[1], false);

        db.add(temp);
        printTaskAddedWithDivider("E", db.size(), temp);
    }
}
