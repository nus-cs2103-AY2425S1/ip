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
    String printAdd(Task task, int size) {
        return "Meow! I've added this task: \n" + task +
        "\n Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints a message when a task is deleted from the task list.
     *
     * @param task the deleted task
     * @param size the current size of the task list
     */
    String printDelete(Task task, int size) {
        return "Meow! I've removed this task: \n" + task +
        "\n Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param task the marked task
     */
    String printMark(Task task) {
        return "Meow! I've marked this task as done: \n" + task;
    }

    /**
     * Prints a message when a task is unmarked as not done.
     *
     * @param task the unmarked task
     */
    String printUnmark(Task task) {
        return "Meow! I've marked this task as not done yet: \n" + task;
    }

    
    String printFind(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Meow! I can't find anything matching your search :(";
        } else {
            return "Meow! Here are the matching tasks in your list: \n" + tasks.list();
        }
    }

    /**
     * Prints a greeting message.
     */
    String greet() {
        return "Meow! How can I help you today?";
    }
    /**
     * Prints an exit message.
     */
    String exit() {
        return "Bye. Hope to see you again soon meow!";
    }

}
