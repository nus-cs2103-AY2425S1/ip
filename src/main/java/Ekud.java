import java.util.HashMap;
import java.util.Scanner;

public class Ekud {
    public static final String LINE_SEPARATOR =
            "_____________________________________________________________";
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

    public void greet() {
        System.out.printf("""
                        \t%s
                        \t Hey! My name is EKuD!! You can call me Eku-chan!
                        \t How may I be of assistance?
                        \t%s%n""",
                LINE_SEPARATOR,
                LINE_SEPARATOR);
    }

    public void sayGoodbye() {
        System.out.printf("""
                        \t I hope you enjoyed your stay!
                        \t See you next time! NOT!!
                        \t%s""",
                LINE_SEPARATOR);
    }

    public void addToList(Task task) {
        // assume no more than LIST_MAX_SIZE items are added
        tasks.addTask(task);
        System.out.printf("""
                        \t added: %s
                        \t And another one; %s tasks to complete...
                        \t%s%n""",
                task,
                tasks.getIncompleteCount(),
                LINE_SEPARATOR);
    }

    public void echoList() {
        System.out.println("\t Look at all these tasks:");
        int i = 1;
        for (Task task: tasks) {
            System.out.printf("\t %d. %s\n", i, task);
            i++;
        }
        System.out.println("\t" + LINE_SEPARATOR);
    }

    public void markList(int listIndex) {
        // assumes valid index is given
        tasks.markComplete(listIndex);
        System.out.printf("""
                        \t Wowie!! You've completed your task!
                        \t I shall mark it as complete in celebration!
                        \t   %s
                        \t Woohoo!! Only %d more to go!
                        \t%s%n""",
                tasks.getTask(listIndex),
                tasks.getIncompleteCount(),
                LINE_SEPARATOR);
    }

    public void unmarkList(int listIndex) {
        // assumes valid index is given
        tasks.markIncomplete(listIndex);
        System.out.printf("""
                        \t Oh ho ho, did you perhaps forget something?
                        \t It's OK, I already noted down your incompetence...
                        \t   %s
                        \t Tsk Tsk... Back to %d incomplete tasks you go!
                        \t%s%n""",
                tasks.getTask(listIndex),
                tasks.getIncompleteCount(),
                LINE_SEPARATOR);
    }

    public void warnUnknownCommand() {
        System.out.printf("""
                            \t Stop yapping buddy, I have no clue what ya saying!
                            \t%s%n""",
                LINE_SEPARATOR);
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
            System.out.println("\t" + LINE_SEPARATOR);

            // handle command
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
        }
        ekud.sayGoodbye();
    }
}
