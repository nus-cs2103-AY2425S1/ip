import java.util.Scanner;

public class Ekud {
    public static final String LINE_SEPARATOR =
            "_____________________________________________________________";
    public static final String END_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMNAD = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final int MAX_LIST_SIZE = 100;

    private final Task[] tasks = new Task[MAX_LIST_SIZE];
    private int currListSize = 0;
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

    public void addToList(String item) {
        // assume no more than LIST_MAX_SIZE items are added
        tasks[currListSize] = new Task(item);
        currListSize++;
        echo("added: " + item);
        echo(LINE_SEPARATOR);
    }

    public void echoList() {
        echo("Look at all these tasks:");
        for (int i = 0; i < currListSize; i++) {
            echo(String.format("%d. %s", i+1, tasks[i]));
        }
        echo(LINE_SEPARATOR);
    }

    public void markList(int listIndex) {
        // assumes valid index is given
        echo("Wowie!! You've completed your task!");
        echo("I shall mark it as complete in celebration!");
        tasks[listIndex].markAsDone();
        echo("  " + tasks[listIndex].toString());
        echo(LINE_SEPARATOR);
    }

    public void unmarkList(int listIndex) {
        // assumes valid index is given
        echo("Oh ho ho, did you forget something?");
        echo("It's OK, I will take note of your incompetence...");
        tasks[listIndex].markAsUndone();
        echo("  " + tasks[listIndex].toString());
        echo(LINE_SEPARATOR);
    }

    public static void main(String[] args) {
        Ekud ekud = new Ekud();
        Scanner sc = new Scanner(System.in);
        String command;

        ekud.greet();
        while (ekud.isRunning()) {
            // get user command
            // assumes user puts correct format
            System.out.println();
            command = sc.next();
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
                ekud.markList(sc.nextInt()-1);
                break;
            case UNMARK_COMMAND:
                ekud.unmarkList(sc.nextInt()-1);
                break;
            default:
                ekud.addToList(command + sc.nextLine());
            }
        }
        ekud.sayGoodbye();
    }
}
