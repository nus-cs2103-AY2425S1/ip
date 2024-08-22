import java.util.HashMap;
import java.util.Scanner;

public class Ekud {
    public static final String LINE_SEPARATOR =
            "\t_____________________________________________________________";
    public static final String OUTPUT_PREFIX = "\t ";
    public static final String END_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMNAD = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    private final TaskList tasks = new TaskList();
    private boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public static void printLineSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    public void greet() {
        String greeting = "Hey! My name is EkuD!!\nYou can call me Eku-Chan!";
        printLineSeparator();
        FormatPrinter.printIndent(greeting, OUTPUT_PREFIX);
        printLineSeparator();
    }

    public void sayGoodbye() {
        String goodbye = "I hope you enjoyed your stay!\nSee you next time! NOT!!";
        FormatPrinter.printIndent(goodbye, OUTPUT_PREFIX);
        printLineSeparator();
    }

    public void addToList(Task task) {
        // assume no more than LIST_MAX_SIZE items are added
        tasks.addTask(task);
        String confirmation = String.format("added: %s\nAnd another one; %s tasks to complete...",
                task,
                tasks.getIncompleteCount());
        FormatPrinter.printIndent(confirmation, OUTPUT_PREFIX);
    }

    public void echoList() {
        System.out.println("\t Look at all these tasks:");
        int i = 1;
        for (Task task: tasks) {
            System.out.printf("\t %d. %s\n", i, task);
            i++;
        }
    }

    public void markList(int listIndex) {
        // assumes valid index is given
        tasks.markComplete(listIndex);
        String message = String.format("""
                        Wowie!! You've completed your task!
                        I shall mark it as complete in celebration!
                          %s
                        Woohoo!! Only %d more to go!""",
                tasks.getTask(listIndex),
                tasks.getIncompleteCount());
        FormatPrinter.printIndent(message, OUTPUT_PREFIX);
    }

    public void unmarkList(int listIndex) {
        // assumes valid index is given
        String message = String.format("""
                        Oh ho ho, did you perhaps forget something?
                        It's OK, I already noted down your incompetence...
                          %s
                        Tsk Tsk... Back to %d incomplete tasks you go!""",
                tasks.getTask(listIndex),
                tasks.getIncompleteCount());
        FormatPrinter.printIndent(message, OUTPUT_PREFIX);
    }

    public void warnUnknownCommand() {
        String warning = "Stop yapping buddy, I have no clue what ya saying!";
        FormatPrinter.printIndent(warning, OUTPUT_PREFIX);
    }

    public static HashMap<String, String> parseTokens(String[] tokens) {
        // parses scanner into a map of tokens into a map of keywords and values
        // each line of commands has at least 2 keywords: command & argument
        StringBuilder tokenBuilder = new StringBuilder();
        HashMap<String, String> tokenMap = new HashMap<>();
        // get command
        tokenMap.put("command", tokens[0]);
        // get params
        String currToken = "argument";
        for (int i = 1; i < tokens.length; i++) {
            // encounter optional token
            if (!tokens[i].isEmpty() && tokens[i].charAt(0) == '/') {
                tokenMap.put(currToken, tokenBuilder.toString());
                currToken = tokens[i];
                tokenBuilder.setLength(0); // reset builder
            } else {
                if (!tokenBuilder.isEmpty()) { // add space in between words
                    tokenBuilder.append(" ");
                }
                tokenBuilder.append(tokens[i]);
            }
        }
        tokenMap.put(currToken, tokenBuilder.toString());
        return tokenMap;
    }

    public static void main(String[] args) {
        Ekud ekud = new Ekud();
        Scanner sc = new Scanner(System.in);

        ekud.greet();
        while (ekud.isRunning()) {
            // get user command
            // assumes user puts correct format
            System.out.println();
            String input = sc.nextLine();
            String[] tokens = input.split("\\s"); // delimits input by space into array of String
            HashMap<String, String> tokenMap = parseTokens(tokens);
            String command = tokenMap.get("command");
            String argument = tokenMap.get("argument");
            printLineSeparator();

            // handle command
            try {
                switch (command) {
                case END_COMMAND:
                    ekud.setRunning(false);
                    break;
                case LIST_COMMAND:
                    ekud.echoList();
                    break;
                case MARK_COMMNAD:
                    ekud.markList(Integer.parseInt(argument) - 1);
                    break;
                case UNMARK_COMMAND:
                    ekud.unmarkList(Integer.parseInt(argument) - 1);
                    break;
                case TODO_COMMAND:
                    ekud.addToList(new TodoTask(argument));
                    break;
                case DEADLINE_COMMAND:
                    ekud.addToList(new DeadlineTask(argument, tokenMap.get("/by")));
                    break;
                case EVENT_COMMAND:
                    ekud.addToList(new EventTask(argument, tokenMap.get("/from"), tokenMap.get("/to")));
                    break;
                default:
                    ekud.warnUnknownCommand();
                }
            } catch (TaskArgumentMissingException tame) {
                FormatPrinter.printIndent(tame.getMessage(), OUTPUT_PREFIX);
            } finally {
                if (ekud.isRunning()) {
                    printLineSeparator();
                };
            }
        }
        ekud.sayGoodbye();
    }
}
