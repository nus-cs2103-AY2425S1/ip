import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Blitz {
    private static ArrayList<Task> db = new ArrayList<>();
    private static final String PATH = "./Blitz.txt";
    private enum commandList {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

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

        readFromFile();
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
                    case "mark", "unmark":
                        commandMarkAndUnmark(command.equals("mark"), cont[1]);
                        break;
                    case "todo":
                        commandTodo(cont[1]);
                        break;
                    case "deadline":
                        commandDeadline(cont[1]);
                        break;
                    case "event":
                        commandEvent(cont[1]);
                        break;
                    case "delete":
                        commandDelete(cont[1]);
                        break;
                    default:
                        throw new BlitzCommandDoesNotExistException();
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

    private static LocalDateTime stringToLocaldatetime(String str) {
        int year = Integer.parseInt(str.substring(0, 4));
        int month = Integer.parseInt(str.substring(5, 7));
        int day = Integer.parseInt(str.substring(8, 10));
        int hour = Integer.parseInt(str.substring(11, 13));
        int min = Integer.parseInt(str.substring(13, 15));

        return LocalDateTime.of(year, month, day, hour, min);
    }

    private static boolean checkCommandExist(String command) {
        return commandList.contains(command);
    }

    private static void writeOneToFile(Task task) throws BlitzException {
        try {
            FileWriter fw = new FileWriter(PATH, true);
            fw.write(task.getType().equals("Empty") ? "" : task.taskToString());
            fw.close();
        } catch (IOException e) {
            throw new BlitzIOException("Failed to write to database");
        }
    }

    private static void writeAllToFile() throws BlitzException {
        try {
            FileWriter fw = new FileWriter(PATH);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            throw new BlitzIOException("Failed to write to database");
        }

        if (!db.isEmpty()) {
            for (Task task : db) {
                writeOneToFile(task);
            }
        }
    }

    private static void readFromFile() {
        File f = new File(PATH);
        ArrayList<Task> list = new ArrayList<>();

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                list.add(stringToTask(s.nextLine()));
            }
        } catch (FileNotFoundException e) {
            try {
                writeAllToFile();
            } catch (BlitzException e2) {
                printError(e2);
            }
        } catch (BlitzException e) {
            printError(e);
        }

        db = list;
    }

    private static Task stringToTask(String str) throws BlitzException {
        String[] params = str.split("::");
        String type = params[0];

        switch (type) {
        case "T":
            if (params.length != 3) {
                throw new BlitzIOException("Failed to read from database");
            }
            return new Todo(params[2], "T", Boolean.parseBoolean(params[1]));
        case "D":
            if (params.length != 4) {
                throw new BlitzIOException("Failed to read from database");
            }
            return new Deadline(params[2], "D", stringToLocaldatetime(params[3]), Boolean.parseBoolean(params[1]));
        case "E":
            if (params.length != 5) {
                throw new BlitzIOException("Failed to read from database");
            }
            return new Event(params[2], "E", params[3], params[4], Boolean.parseBoolean(params[1]));
        default:
            throw new BlitzIOException("Failed to read from database");
        }
    }

    private static void commandList() throws BlitzException {
        readFromFile();

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

            writeAllToFile();
            printInDivider(toPrint);
        } catch (IndexOutOfBoundsException e) {
            throw new BlitzIndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            throw new BlitzNumberFormatException();
        }
    }

    private static void commandTodo(String command) throws BlitzException {
        Task temp = new Todo(command, "T", false);

        db.add(temp);
        writeOneToFile(temp);
        printTaskAddedWithDivider("T", db.size(), temp);
    }

    private static void commandDeadline(String command) throws BlitzException {
        if (!regexChecker(".+ \\/by (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9]", command)) {
            throw new BlitzInvalidParameterRegexException("deadline [Task name] /by [Deadline]");
        }

        String[] param = command.split(" /by ");

        if (param[0].contains("/by") || param[1].contains("/by")) {
            throw new BlitzInvalidParameterRepeatedFlagException("/by", "deadline [Task name] /by [yyyy-mm-dd hhmm]");
        }

        if (param[1].isBlank()) {
            throw new BlitzInvalidParameterMissingContentException("/by", "deadline [Task name] /by [yyyy-mm-dd hhmm]");
        } else if (param[0].isBlank()) {
            throw new BlitzInvalidParameterMissingContentException("[Task name]", "deadline [Task name] /by [yyyy-mm-dd hhmm]");
        }

        Task temp = new Deadline(param[0], "D", stringToLocaldatetime(param[1]), false);

        db.add(temp);
        writeOneToFile(temp);
        printTaskAddedWithDivider("D", db.size(), temp);
    }

    private static void commandEvent(String command) throws BlitzException {
        if (!regexChecker(".+ \\/from (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9] \\/to (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9]", command)) {
            throw new BlitzInvalidParameterRegexException("event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
        }

        String[] param1 = command.split(" /from ");
        String[] param2 = param1[1].split(" /to ");

        if (param1[0].contains("/from") || param1[1].contains("/from")) {
            throw new BlitzInvalidParameterRepeatedFlagException("/from", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
        } else if (param2[0].contains("/to") || param2[1].contains("/to")) {
            throw new BlitzInvalidParameterRepeatedFlagException("/to", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
        } else if (param1[0].isBlank()) {
            throw new BlitzInvalidParameterMissingContentException("[Task name]", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
        } else if (param2[0].isBlank()) {
            throw new BlitzInvalidParameterMissingContentException("/from", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
        } else if (param2[1].isBlank()) {
            throw new BlitzInvalidParameterMissingContentException("/to", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
        }

        Task temp = new Event(param1[0], "E", param2[0], param2[1], false);

        db.add(temp);
        writeOneToFile(temp);
        printTaskAddedWithDivider("E", db.size(), temp);
    }

    private static void commandDelete(String command) throws BlitzException {
        String[] param = command.split(" ");
        if (param.length > 1) {
            throw new BlitzInvalidParameterMoreThanOneException("Delete [Integer]");
        }

        try {
            int ind = Integer.parseInt(param[0]) - 1;

            if (db.isEmpty()) {
                throw new BlitzEmptyTaskListException();
            }

            Task task = db.remove(ind);
            writeAllToFile();

            String toPrint = TAB + "Noted. I've removed this task:\n" +
                    TAB + "  [" + task.getType() + "]" + "[" + (task.getStatus() ? "X" : " ") + "] " + task + "\n";
            printInDivider(toPrint);
        } catch (IndexOutOfBoundsException e) {
            throw new BlitzIndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            throw new BlitzNumberFormatException();
        }
    }
}
