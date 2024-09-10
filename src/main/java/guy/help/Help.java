package guy.help;

/**
 * The class helping the user with understanding the functions of ThatOneGuy.
 * It provides each command's syntax and a description of its purpose.
 */
public class Help {
    /**
     * Provides help for all commands.
     * Description is not provided for simplicity.
     */
    public static void helpAll() {
        System.out.println("You didn't give me a specific command to help with...");
        System.out.println("Fine. Behold the wall of text.");
        System.out.println("Help: help <command>");
        System.out.println("List: list");
        System.out.println("ToDo: todo <taskName>");
        System.out.println("Deadline: deadline <taskName> /by <deadline>");
        System.out.println("Event: event <taskName> /from <startDate> /to <endDate>");
        System.out.println("Find: find <keyword>");
        System.out.println("Delete: delete <id>");
        System.out.println("Mark: mark <id>");
        System.out.println("Unmark: unmark <id>");
        System.out.println("Bye: bye");
    }
    /**
     * Provides help for the "help" command.
     */
    public static void helpHelp() {
        System.out.println("This pulls up the help page.");
        System.out.println("You use it like this: help <command>.");
        System.out.println("But if you're dumb enough to not give a command, I'll provide the whole list.");
    }
    /**
     * Provides help for the "list" command.
     */
    public static void helpList() {
        System.out.println("This makes me show you your tasks.");
        System.out.println("Just don't be a dingus and use this when you had no tasks to begin with.");
        System.out.println("I HATE THAT.");
        System.out.println("Doesn't need any parameters. Just enter it alone.");
    }
    /**
     * Provides help for the "todo" command.
     */
    public static void helpToDo() {
        System.out.println("This adds a task to your list. But just by name.");
        System.out.println("You use it like this: todo <taskName>.");
    }
    /**
     * Provides help for the "deadline" command.
     */
    public static void helpDeadline() {
        System.out.println("This adds a task to your list. With a deadline.");
        System.out.println("You use it like this: deadline <taskName> /by <deadline>.");
        System.out.println("If you don't use the syntax yyyy-mm-dd HH:MM for the deadline, you're on your own.");
    }
    /**
     * Provides help for the "event" command.
     */
    public static void helpEvent() {
        System.out.println("This adds an event to your list. With the two dates between which it occurs.");
        System.out.println("You use it like this: event <taskName> /from <startDate> /to <endDate>.");
        System.out.println("If you don't use the syntax yyyy-mm-dd HH:MM for either date, you're on your own.");
    }
    /**
     * Provides help for the "find" command.
     */
    public static void helpFind() {
        System.out.println("With this command, provide a keyword and I'll look through your tasks");
        System.out.println("for those that match the keyword.");
        System.out.println("You use it like this: find <keyword>.");
        System.out.println("But if you're dumb enough to give an invalid keyword, I cannot help you.");
    }
    /**
     * Provides help for the "delete" command.
     */
    public static void helpDelete() {
        System.out.println("This deletes a task with an ID matching the given ID.");
        System.out.println("You use it like this: delete <id>.");
        System.out.println("Just don't give me an invalid ID. Otherwise, you're a dumbass.");
    }
    /**
     * Provides help for the "mark" command.
     */
    public static void helpMark() {
        System.out.println("This marks the task with the given ID as complete.");
        System.out.println("You use it like this: mark <id>.");
        System.out.println("Just don't give me an invalid ID. Otherwise, you're a dumbass.");
    }
    /**
     * Provides help for the "unmark" command.
     */
    public static void helpUnmark() {
        System.out.println("This marks the task with the given ID as incomplete.");
        System.out.println("You use it like this: unmark <id>.");
        System.out.println("Just don't give me an invalid ID. Otherwise, you're a dumbass.");
    }
    /**
     * Provides help for the "bye" command.
     */
    public static void helpBye() {
        System.out.println("This asks me to stop running. I'd genuinely prefer that.");
        System.out.println("Doesn't need any parameters. Just enter it alone.");
    }
}
