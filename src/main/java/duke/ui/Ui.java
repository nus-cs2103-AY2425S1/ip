import java.util.List;

public class Ui {
    public void showGreeting() {
        String greeting = "   *        *        *        __o    *       *\n"
                + "*      *       *        *    /_| _     *\n"
                + "  FF  *    FF      *        O'_)/ \\  *    *\n"
                + "  o')____  o')____    __*   V   \\  ) __  *\n"
                + "   \\ ___ )--\\ ___ )--( (    (___|__)/ /*     *\n"
                + " *  |   |    |   |  * \\ \\____| |___/ /  *\n"
                + "    |*  |    |   |     \\____________/       *\n";
        String festiveMessage =
                "Merry★* 。 • ˚ ˚ ˛ ˚ ˛ •\n" +
                        "•。★Christmas★ 。* 。\n" +
                        "° 。 ° ˛˚˛ * Π__。˚\n" +
                        "˚ ˛ •˛•˚ */__/~＼。˚ ˚ ˛\n" +
                        "˚ ˛ •˛• ˚ ｜ 田田 ｜門｜ ˚\n" +
                        "And a happy new year!";
        System.out.println(greeting + "Hello, I am Rudolf, Santa's trusty red-nose reindeer");
        System.out.println("Christmas is nearing, and I am here to help you with your Christmas preparations.");
        System.out.println("Here's how you can chat with me:");
        System.out.println("1. Add a ToDo: todo <description>");
        System.out.println("2. Add a Deadline: deadline <description> /by <date/time>");
        System.out.println("3. Add an Event: event <description> /from <start date/time> /to <end date/time>");
        System.out.println("4. List all tasks: list");
        System.out.println("5. Mark a task as done: mark <task number>");
        System.out.println("6. Unmark a task: unmark <task number>");
        System.out.println("7. Delete a task: delete <task number>");
        System.out.println("8. Exit: bye");
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error encountered while loading previous tasks.");
    }

    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Ho Ho Ho! No tasks in your list yet. Add some tasks to get started.");
        } else {
            System.out.println("Ho Ho Ho! Here are the tasks in your Christmas list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Gotcha. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list. Feliz Navidad!");
    }

    public void showTaskMarked(Task task) {
        System.out.println("Sleigh! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("Alright-o, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println("Aww okay. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list. Let it snow!");
    }

    public void showGoodbyeMessage() {
        String festiveMessage =
                "Merry★* 。 • ˚ ˚ ˛ ˚ ˛ •\n" +
                        "•。★Christmas★ 。* 。\n" +
                        "° 。 ° ˛˚˛ * Π__。˚\n" +
                        "˚ ˛ •˛•˚ */__/~＼。˚ ˚ ˛\n" +
                        "˚ ˛ •˛• ˚ ｜ 田田 ｜門｜ ˚\n" +
                        "And a happy new year!";
        System.out.println("Bye~ Hope to see you again!\n" + festiveMessage);
    }

    public void showUnknownCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showSuggestion(String message) {
        System.out.println("Sorry, I don't understand. Did you mean: " + message);
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public void showSaveError(String message) {
        System.out.println("Jingling bells! It seems like an error was encountered while saving tasks: " + message);
    }
}

