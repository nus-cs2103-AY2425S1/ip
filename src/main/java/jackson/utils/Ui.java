package jackson.utils;

import java.util.ArrayList;

import jackson.tasks.Task;

/**
 * Class to handle UI interaction.
 * Includes printing most exceptions and displaying task lists, etc
 */
public class Ui {
    public Ui() { }

    /**
     * Used to print welcome at the start of the chatbot program.
     * Cosmetic
     */
    public void printWelcome() {
        System.out.println("Oi! I'm Jackson!\nWhat you want me do today ah?");
    }

    /**
     * Prints a marker symbol before taking user input.
     * Cosmetic
     */
    public void printMarker() {
        System.out.print("> ");
    }

    /**
     * Prints command list during an unrecognised command.
     * Exception
     */
    public void printCommands() {
        System.out.println("Harh? What you talking about?");
        System.out.println("Try entering a recognized command:");
        System.out.println("""
                        1. todo [task-name]
                        2. deadline [task-name] /by [due-date]
                        3. event [task-name] /from [start-date] /to [end-date]
                        4. mark [index]
                        5. unmark [index]
                        6. list
                        7. find
                        8. bye""");
    }

    /**
     * Prints format of command based on which command user incorrectly enters.
     * @param category Command category that is entered incorrectly
     */
    public void printFormatGuide(String category) {
        System.out.println("Wrong format leh...");
        System.out.println("Try formatting your command as such:");

        // category is the type of command that the user incorrectly enters the format of
        // print the correct command format based on the user input
        switch (category) {
        case "todo":
            System.out.println("todo [task-name]");
            break;
        case "deadline":
            System.out.println("deadline [task-name] /by [due-date]");
            System.out.println("All dates must be in DD-MM-YYYY HH:MM format (HH:MM is optional)");
            break;
        case "event":
            System.out.println("event [task-name] /from [start-date] /to [end-date]");
            System.out.println("All dates must be in DD-MM-YYYY HH:MM format (HH:MM is optional)");
            break;
        case "list":
            System.out.println("list");
            break;
        case "mark":
            System.out.println("mark [index]");
            break;
        case "unmark":
            System.out.println("unmark [index]");
            break;
        case "delete":
            System.out.println("delete [index]");
            break;
        case "find":
            System.out.println("find [keywords]");
            break;
        case "bye":
            System.out.println("bye");
            break;
        default:
            System.out.println("Unknown error...");
            break;
        }
    }

    /**
     * Prints valid indices when invalid index is entered.
     * @param taskList {@code OutOfListException} exception encountered
     */
    public void printIndexGuide(TaskList taskList) {
        int size = taskList.getSize();
        System.out.printf("Alamak, you got %d items on the list only leh...\n", size);

        // different responses based on taskList size
        if (size == 0) {
            System.out.println("You've got no items in the list! Add some stuff first!");
        } else if (size == 1) {
            System.out.println("Enter 1 to mark/unmark/delete the task in the list!");
        } else {
            System.out.printf("Enter a number between 1 and %d when marking/unmarking/deleting tasks!\n", size);
        }
    }

    /**
     * Prints generic error message when other Exceptions occur.
     * @param e {@code Exception} encountered during runtime
     */
    public void printUnknownError(Exception e) {
        System.out.println("Oops! Something went wrong!");
        System.out.println(e.toString());
    }

    /**
     * Prints chatbot response when adding task to list.
     * @param task {@code Task} object to be added to taskList
     * @param taskList {@code TaskList} object task is to be added to
     */
    public void printAddList(Task task, TaskList taskList) {
        System.out.println("Ya la, adding this task to your list!");
        System.out.printf("\t%s\n", task);
        System.out.printf("You now got %s tasks in your list leh\n", taskList.getSize());
    }

    /**
     * Prints chatbot response when deleting task from list.
     * @param task {@code Task} object to be deleted from taskList
     * @param taskList {@code TaskList} object task is to be deleted from
     */
    public void printDeleteList(Task task, TaskList taskList) {
        System.out.println("Deleting now hor!");
        System.out.printf("\t%s\n", task);
        System.out.printf("You now got %s tasks in your list leh\n", taskList.getSize());
    }

    /**
     * Prints chatbot response when finding tasks containing keyword.
     * @param tasks {@code ArrayList} of tasks that contain keyword
     * @param keyword String keyword
     */
    public void printFindList(ArrayList<Task> tasks, String keyword) {
        System.out.println("Ok ok, find for you already...");
        if (tasks.isEmpty()) {
            System.out.printf("Don't have tasks that match '%s' leh....\n", keyword);
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s\n", i, tasks.get(i));
            }
        }
    }

    /**
     * Prints chatbot response when marking task in list.
     * @param task {@code Task} object to be marked
     */
    public void printMark(Task task) {
        System.out.println("Solid lah, marked already");
        System.out.printf("\t%s\n", task);
    }

    /**
     * Prints chatbot response when unmarking task in list.
     * @param task {@code Task} object to be unmarked
     */
    public void printUnmark(Task task) {
        System.out.println("Walao, ok la I unmark already...");
        System.out.printf("\t%s\n", task);
    }

    /**
     * Prints tasks stored in the task list.
     * @param listString {@code String} representation of tasks in the list
     */
    public void printList(String listString) {
        System.out.println(listString);
    }
}
