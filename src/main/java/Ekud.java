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
    public static final int MAX_LIST_SIZE = 100;

    private final TaskList tasks = new TaskList(MAX_LIST_SIZE);
    private boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void echo(String message) {
        // prints string with a tab
        System.out.println("\t" + message);
    }

    public void greet() {
        echo(LINE_SEPARATOR);
        echo("Heyo! My name is EKuD!! You can call me Eku-chan :)");
        echo("How may I be of assistance");
        echo(LINE_SEPARATOR);
    }

    public void sayGoodbye() {
        echo("I hope you enjoyed your stay!");
        echo("See you next time!");
        echo(LINE_SEPARATOR);
    }

    public void addToList(Task task) {
        // assume no more than LIST_MAX_SIZE items are added
        tasks.addTask(task);
        echo("added: " + task);
        echo("And another one... " + tasks.getIncompleteCount() + " tasks to complete.");
        echo(LINE_SEPARATOR);
    }

    public void echoList() {
        echo("Look at all these tasks:");
        int i = 1;
        for (Task task: tasks) {
            echo(String.format("%d. %s", i, task));
            i++;
        }
        echo(LINE_SEPARATOR);
    }

    public void markList(int listIndex) {
        // assumes valid index is given
        echo("Wowie!! You've completed your task!");
        echo("I shall mark it as complete in celebration!");
        tasks.markComplete(listIndex);
        echo("   " + tasks.getTask(listIndex));
        echo("Woohoo!! Only " + tasks.getIncompleteCount() + " more to go!");
        echo(LINE_SEPARATOR);
    }

    public void unmarkList(int listIndex) {
        // assumes valid index is given
        echo("Oh ho ho, did you forget something?");
        echo("It's OK, I will take note of your incompetence...");
        tasks.markIncomplete(listIndex);
        echo("   " + tasks.getTask(listIndex));
        echo("OOPSIES! We back to " + tasks.getIncompleteCount() + " tasks left to complete.");
        echo(LINE_SEPARATOR);
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
            ekud.echo(LINE_SEPARATOR);

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
                ekud.echo(input);
                ekud.echo(LINE_SEPARATOR);
            }
        }
        ekud.sayGoodbye();
    }
}
