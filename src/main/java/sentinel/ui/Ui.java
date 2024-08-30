package sentinel.ui;

import java.util.Scanner;
import sentinel.Sentinel;
import sentinel.task.Task;
import sentinel.utils.SentinelList;

public class Ui {
    private final Scanner sc;
    private final static String logo =
            """
                   _____                                                                                      _____\s
                  ( ___ )                                                                                    ( ___ )
                   |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   |\s
                   |   |                                                                                      |   |\s
                   |   |   ________  _______   ________   _________  ___  ________   _______   ___            |   |\s
                   |   |  |\\   ____\\|\\  ___ \\ |\\   ___  \\|\\___   ___\\\\  \\|\\   ___  \\|\\  ___ \\ |\\  \\           |   |\s
                   |   |  \\ \\  \\___|\\ \\   __/|\\ \\  \\\\ \\  \\|___ \\  \\_\\ \\  \\ \\  \\\\ \\  \\ \\   __/|\\ \\  \\          |   |\s
                   |   |   \\ \\_____  \\ \\  \\_|/_\\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|/_\\ \\  \\         |   |\s
                   |   |    \\|____|\\  \\ \\  \\_|\\ \\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\ \\  \\____    |   |\s
                   |   |      ____\\_\\  \\ \\_______\\ \\__\\  \\__\\   \\ \\__\\ \\ \\__\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\  |   |\s
                   |   |     |\\_________\\|_______|\\|__| \\|__|    \\|__|  \\|__|\\|__| \\|_______|\\|_______|   |   |\s
                   |   |     \\|_________|                                                                     |   |\s
                   |   |                                                                                      |   |\s
                   |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___|\s
                  (_____)                                                                                    (_____)""";

    private final static String helpText = """
                    1. todo <sentinel.task>                                Adds tasks without any date/time attached to list.
                    2. deadline <sentinel.task> /by <date>                 Adds tasks that need to be done before a specific date/time to list.
                    3. event <event> /from <date> /to <date>      Adds tasks that start at a specific date/time and ends at a specific date/time to list.
                    4. list                                       List all tasks.
                    5. mark <index>                               Mark sentinel.task as done.
                    6. unmark <index>                             Mark sentinel.task as undone.
                    7. delete <index>                             Deletes sentinel.task.
                    8. bye                                        Ends the chatbot.
                    """;

    private final static String line = "____________________________________________________________\n";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println("\nWhat can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println(line);
    }
    public String readLine() {
        return sc.nextLine().trim();
    }

    public void showError(Exception e) {
        System.err.println("sentinel.Sentinel just experienced an error! " + e.getMessage());
        showLine();
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showHelp() {
        System.out.println(helpText);
    }

    public void showEventCommandGuidelines() {
        System.out.println("Please state the start and end date using /from <date> and /to <date> respectively (e.g., event project meeting /from 30 Aug 2024 2pm /to 30 Aug 2024 4pm)");
    }
    public void showEventDateOrder(){
        System.out.println("Start Date cannot be after End Date. Please try again.");
    }

    public void showDeadlineCommandGuidelines() {
        System.out.println("Please state the deadline using /by <date> (e.g., deadline return book /by 30 Aug 2024 5pm)");
    }
    public void showModifyGuidelines(){
        System.out.println("Please state a valid index you wish to mark/unmark/delete with the sentinel.command (e.g.: mark 1)");
    }
    public void showNoItemExists() {
        System.out.println("No such item in the list!");
    }

    public void showEmptyTaskNameError(Sentinel.CommandType commandType) {
        System.out.println(commandType.name().substring(0, 1).toUpperCase() + commandType.name().substring(1) + " name cannot be empty");
    }

    public void showUnrecognisedCommand() {
        System.out.println("Unrecognised sentinel.command. Type \"help\" to list all commands.");
    }

    public void showTaskMark(Task t) {
        System.out.println("Alright! I've marked this sentinel.task as " + (t.isDone() ? "done" : "undone") + ":");
        System.out.println("\t" + t.getStatusIcon() + " " + t);
    }

    public void showList(SentinelList lst) {
        System.out.println("Here " + (lst.sizeOne() ? "is" : "are") + " the " + (lst.sizeOne() ? "task" : "tasks") + " in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + lst.getListedString(i));
        }
    }

    public void showRemovedAndRemaining(SentinelList list, Task removed) {
        System.out.println("I have deleted the following sentinel.task:\n\t" + removed.listedString() +
                "\nYou have " + list.size() + " remaining " + (list.sizeOne() ? "task" : "tasks") + ".");
    }

    public void showAlreadyMarked(Task t) {
        if (t.isDone()) {
            System.out.println(t + " has already been marked as done.");
        } else {
            System.out.println(t + " has already been marked as undone.");
        }
    }

    public void showAddedTask(Task t) {
        System.out.println("Got it. I've added this sentinel.task: " + t);
        System.out.println("\t" + t.listedString());
    }
}
