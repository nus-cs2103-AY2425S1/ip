package ynch.ui;

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

    
    void printFind(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Meow! I can't find anything matching your search :(");
        } else {
            System.out.println("Meow! Here are the matching tasks in your list: \n" + tasks.list());
        }
    }

    /**
     * Prints a greeting message.
     */
    void greet() {
        System.out.println("Meow! How can I help you today?");
    }
    /**
     * Prints an exit message.
     */
    void exit() {
        System.out.println("Bye. Hope to see you again soon meow!");
    }

}
