import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Blitz {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    public Blitz(String path, String divider, String tab) {
        this.ui = new Ui(divider, tab);
        this.storage = new Storage(path);
        this.tasklist = storage.readFromFile(this.ui);
    }

    public void run() {
        String[] greet = {"Hello! I'm Blitz.", "What can I do for you?"};
        String[] end = {"Bye. Hope to see you again soon!"};

        ui.printInDivider(greet);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String inp = sc.nextLine();

            try {
                Command c = Parser.parse(inp);
                if (c.isExit()) {
                    break;
                }
                c.execute(this.tasklist, this.ui, this.storage);
            } catch (BlitzException e) {
                ui.printError(e);
            }
        }

        ui.printInDivider(end);
    }

    private static ArrayList<Task> db = new ArrayList<>();

    public static void main(String[] args) {
        new Blitz("./Blitz.txt", "    -----------------------------------------------\n", "    ").run();
    }

    private static boolean regexChecker(String reg, String inp) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(inp);

        return matcher.find();
    }

    private static void commandTodo(String command) throws BlitzException {
//        Task temp = new Todo(command, "T", false);
//
//        db.add(temp);
//        writeOneToFile(temp);
//        printTaskAddedWithDivider("T", db.size(), temp);
    }

    private static void commandDeadline(String command) throws BlitzException {
//        if (!regexChecker(".+ \\/by (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9]", command)) {
//            throw new BlitzInvalidParameterRegexException("deadline [Task name] /by [Deadline]");
//        }
//
//        String[] param = command.split(" /by ");
//
//        if (param[0].contains("/by") || param[1].contains("/by")) {
//            throw new BlitzInvalidParameterRepeatedFlagException("/by", "deadline [Task name] /by [yyyy-mm-dd hhmm]");
//        }
//
//        if (param[1].isBlank()) {
//            throw new BlitzInvalidParameterMissingContentException("/by", "deadline [Task name] /by [yyyy-mm-dd hhmm]");
//        } else if (param[0].isBlank()) {
//            throw new BlitzInvalidParameterMissingContentException("[Task name]", "deadline [Task name] /by [yyyy-mm-dd hhmm]");
//        }
//
//        Task temp = new Deadline(param[0], "D", Task.stringToLocaldatetime(param[1]), false);
//
//        db.add(temp);
//        writeOneToFile(temp);
//        printTaskAddedWithDivider("D", db.size(), temp);
    }

    private static void commandEvent(String command) throws BlitzException {
//        if (!regexChecker(".+ \\/from (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9] \\/to (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9]", command)) {
//            throw new BlitzInvalidParameterRegexException("event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
//        }
//
//        String[] param1 = command.split(" /from ");
//        String[] param2 = param1[1].split(" /to ");
//
//        if (param1[0].contains("/from") || param1[1].contains("/from")) {
//            throw new BlitzInvalidParameterRepeatedFlagException("/from", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
//        } else if (param2[0].contains("/to") || param2[1].contains("/to")) {
//            throw new BlitzInvalidParameterRepeatedFlagException("/to", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
//        } else if (param1[0].isBlank()) {
//            throw new BlitzInvalidParameterMissingContentException("[Task name]", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
//        } else if (param2[0].isBlank()) {
//            throw new BlitzInvalidParameterMissingContentException("/from", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
//        } else if (param2[1].isBlank()) {
//            throw new BlitzInvalidParameterMissingContentException("/to", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
//        }
//
//        Task temp = new Event(param1[0], "E", Task.stringToLocaldatetime(param2[0]), Task.stringToLocaldatetime(param2[1]), false);
//
//        db.add(temp);
//        writeOneToFile(temp);
//        printTaskAddedWithDivider("E", db.size(), temp);
    }

    private static void commandDelete(String command) throws BlitzException {
//        String[] param = command.split(" ");
//        if (param.length > 1) {
//            throw new BlitzInvalidParameterMoreThanOneException("Delete [Integer]");
//        }
//
//        try {
//            int ind = Integer.parseInt(param[0]) - 1;
//
//            if (db.isEmpty()) {
//                throw new BlitzEmptyTaskListException();
//            }
//
//            Task task = db.remove(ind);
//            writeAllToFile();
//
//            String toPrint = TAB + "Noted. I've removed this task:\n" +
//                    TAB + "  [" + task.getType() + "]" + "[" + (task.getStatus() ? "X" : " ") + "] " + task + "\n";
//            printInDivider(toPrint);
//        } catch (IndexOutOfBoundsException e) {
//            throw new BlitzIndexOutOfBoundsException();
//        } catch (NumberFormatException e) {
//            throw new BlitzNumberFormatException();
//        }
    }
}
