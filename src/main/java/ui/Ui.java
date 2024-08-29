package ui;

public class Ui {

    public static String[] helpCommands = {
            "todo (description) - creates a new todo", "deadline (description) /by (end time or date) - creates a deadline",
            "event (description) /from (start time or date) /to (end time or date) - creates an event",
            "list - shows you all your tasks",
            "mark (task number) / unmark (task number) - marks or unmarks your tasks as done",
            "delete (task number) - removes a task"
    };

    /**
     * This static method prints a line for Molly's user interface
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * This is a public static method that makes Molly greet the user.
     */
    public static void greetUser() {
        Ui.printLine();
        System.out.println("Hello! I'm Molly.");
        System.out.println("I am a virtual assistant designed to help you plan your tasks.");
        System.out.println("What can I do for you? For a guide, enter the '/help' command");
    }


    /**
     * This is a public static method that makes Molly say bye.
     */
    public static void sayBye() {
        Ui.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        Ui.printLine();
    }

    public static void printHelpCommands() {
        System.out.println("These are Molly's help commands.");
        for (int i = 0; i < helpCommands.length; i++) {
            System.out.println((i + 1) + ". " + helpCommands[i]);
        }
        Ui.printLine();

    }

}
