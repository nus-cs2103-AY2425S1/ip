/**
 * Represents the user interface for YNCH chatbot.
 */
class YnchUi {
    String name;

    /**
     * Constructs a YnchUi object with the default name "YNCH".
     */
    YnchUi() {
        this.name = "YNCH";
    }

    /**
     * Prints a message when a task is added to the task list.
     *
     * @param task the added task
     * @param size the current size of the task list
     */
    void printAdd(Task task, int size) {
        System.out.println("Meow! I've added this task: \n" + task + 
        "\n Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints a message when a task is deleted from the task list.
     *
     * @param task the deleted task
     * @param size the current size of the task list
     */
    void printDelete(Task task, int size) {
        System.out.println("Meow! I've removed this task: \n" + task + 
        "\n Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param task the marked task
     */
    void printMark(Task task) {
        System.out.println("Meow! I've marked this task as done: \n" + task);
    }

    /**
     * Prints a message when a task is unmarked as not done.
     *
     * @param task the unmarked task
     */
    void printUnmark(Task task) {
        System.out.println("Meow! I've marked this task as not done yet: \n" + task);
    }

    /**
     * Prints a greeting message.
     */
    void greet() {
        System.out.println("Meow! I'm YNCH. What can I do for you?");
    }
    
    /**
     * Prints an exit message.
     */
    void exit() {
        System.out.println("Bye. Hope to see you again soon meow!");
    }

}
