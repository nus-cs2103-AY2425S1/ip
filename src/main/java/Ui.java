public class Ui {
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
                   |   |     |\\_________\\|_______|\\|__| \\|__|    \\|__|  \\|__|\\|__| \\|__|\\|_______\\|_______|   |   |\s
                   |   |     \\|_________|                                                                     |   |\s
                   |   |                                                                                      |   |\s
                   |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___|\s
                  (_____)                                                                                    (_____)""";
    private final static String helpText = """
                    1. todo <task>                                Adds tasks without any date/time attached to list.
                    2. deadline <task> /by <date>                 Adds tasks that need to be done before a specific date/time to list.
                    3. event <event> /from <date> /to <date>      Adds tasks that start at a specific date/time and ends at a specific date/time to list.
                    4. list                                       List all tasks.
                    5. mark <index>                               Mark task as done.
                    6. unmark <index>                             Mark task as undone.
                    7. delete <index>                             Deletes task.
                    8. bye                                        Ends the chatbot.
                    """;

    private final static String line = "____________________________________________________________\n";

    public void showWelcome(){
        System.out.println("Hello from\n" + logo);
        System.out.println("\nWhat can I do for you?");
    }

    public void showLine(){
        System.out.println(line);
    }
    public void showError(Exception e){
        System.err.println("Sentinel just experienced an error! " + e.getMessage());
    }
    public void showGoodbye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void showHelp(){
        System.out.println(helpText);
    }
    public void showEventCommandGuidelines(){
        System.out.println("Please state the start and end date using /from and /to respectively (eg: event project meeting /from Mon 2pm /to 4pm)");
    }
    public void showDeadlineCommandGuidelines(){
        System.out.println("Please state the deadline using /by (eg: deadline return book /by Sunday)");
    }
    public void showNoItemExists(){
        System.out.println("No such item in the list!");
    }
    public void showUnrecognisedCommand(){
        System.out.println("Unrecognised command. Type \"help\" to list all commands.");
    }
    public void showTaskMark(Task t){
        System.out.println("Alright! I've marked this task as " + (t.isDone() ? "done" : "undone") + ":");
        System.out.println("\t" + t.getStatusIcon() + " " + t);
    }
    public void showList(SentinelList lst){
        System.out.println("Here " + (lst.sizeOne() ? "is" : "are") + " the " + (lst.sizeOne() ? "task" : "tasks") + " in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + lst.getListedString(i));
        }
    }
    public void showRemovedandRemaining(SentinelList list, Task removed){
        System.out.println("I have deleted the following task:\n\t" + removed.listedString() +
                "\n You have " + list.size() + " remaining " + (list.sizeOne() ? "task" : "tasks") + ".");
    }

    public void showAlreadyMarkedDone(Task t){
        System.out.println(t + " has already been marked as done.");
    }
    public void showAlreadyMarkedUndone(Task t){
        System.out.println(t + " has already been marked as undone.");
    }

    public void showAddedTask(Task t){
        System.out.println("Got it. I've added this task: " + t);
        System.out.println("\t" + t.listedString());

    }

}
