package ui;

public class Ui {

    public static String[] helpCommands = {
            "todo (description) - creates a new todo", "deadline (description) /by (end time or date) - creates a deadline",
            "event (description) /from (start time or date) /to (end time or date) - creates an event",
            "list - shows you all your tasks",
            "mark (task number) / unmark (task number) - marks or unmarks your tasks as done",
            "delete (task number) - removes a task",
            "find (search query) - finds tasks with matching descriptions",
            "reminders - shows you upcoming tasks in the next 3 days"
    };


    public static String printLine() {
        return "____________________________________________________________";
    }


    public static String greetUser() {
        return "Hello! I'm Molly.\n" +
                "I am a virtual assistant designed to help you plan your tasks.\n" +
                "What can I do for you? For a guide, enter the '/help' command\n";
    }


    public static String sayBye() {
        return "Bye. Hope to see you again soon!\n";
    }

    public static String printHelpCommands() {
        StringBuilder helpCommandsString = new StringBuilder("These are Molly's help commands.\n");
        for (int i = 0; i < helpCommands.length; i++) {
            helpCommandsString.append((i + 1)).append(". ").append(helpCommands[i]).append("\n");
        }
        return helpCommandsString.toString();

    }

}
