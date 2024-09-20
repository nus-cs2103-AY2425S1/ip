package voidcat.ui;

import voidcat.task.Task;

/**
 * Represents the user interface for Void Cat.
 * It handles the display of messages to the user, such as task-related messages,
 * greetings, and farewells.
 */
public class Ui {
    private static final String name = "Void Cat ฅ^•ﻌ•^ฅ\n";

    private static final String[] greetings = {
        "Hello! I'm your friendly cat helper, \n",
        "Purr... Hello, wanderer. I am \n",
        "Mew! Welcome human! I'm \n",
        "Greetings from the abyss, friend, for I am\n",
        "Meow! Happy to help, they call me \n"
    };

    private static final String[] assistGreetings = {
        "How can this void assist you today?",
        "At your service, human.",
        "What help does human need today?",
        "Need any help?",
        "What can I do for you?"
    };

    private static final String[] exits = {
        "Purr... Until next time, friend.",
        "Meow! I shall vanish into the shadows now.",
        "Farewell! May your path be clear.",
        "Mew! See you in the void again soon.",
        "The void calls, but I'll return. Goodbye!"
    };

    /**
     * Displays a welcome message with a random greeting and the application logo.
     *
     * @return A formatted welcome message with a greeting and logo.
     */
    public static String welcome() {
        return greetings[(int) (Math.random() * greetings.length)]
                + name
                + assistGreetings[(int) (Math.random() * assistGreetings.length)]
                + "\nType `help` to see a list of commands you can use.";
    }

    /**
     * Displays a random goodbye message from the given options.
     *
     * @return A random farewell message.
     */
    public static String goodbye() {
        return "/ᐠ ˵- ⩊ -˵マ" + exits[(int) (Math.random() * exits.length)];

    }

    /**
     * Displays all commands and their format to help as a guide.
     *
     * @return A message of the formats of all commands
     */
    public static String getHelpMessage() {
        return "Here are the available commands ^•ﻌ•^ฅ♡ :\n\n"
                + "1. list - Shows all tasks\n"
                + "2. todo <description> - Adds a ToDo task\n"
                + "3. deadline <description> /by <yyyy-mm-dd hhmm> - Adds a Deadline task\n"
                + "4. event <description> /from <yyyy-mm-dd hhmm> /to <yyyy-mm-dd hhmm> - Adds an Event task\n"
                + "5. mark <task_number> - Marks a task as done\n"
                + "6. unmark <task_number> - Unmarks a task\n"
                + "7. delete <task_number> - Deletes a task\n"
                + "8. find <keyword> - Finds tasks with the keyword\n"
                + "9. help - Displays this help page\n"
                + "10. bye - Exits the application";
    }

    /**
     * Displays a message when a task has been successfully deleted.
     *
     * @param removedTask The task that was removed.
     * @param size The current size of the task list.
     * @return A formatted message indicating the task has been removed.
     */
    public String showDeleteTaskMessage(Task removedTask, int size) {
        return "ฅ^._.^ฅ Noted. I've removed this task:\n\n" + removedTask
                + "\n\nNow you have " + size + " tasks in the list";
    }

    /**
     * Displays a message when a task has been successfully marked.
     *
     * @param markedTask The task that was marked.
     * @return A formatted message indicating the task has been marked.
     */
    public String showMarkTaskMessage(Task markedTask) {
        return "/ᐠ > ˕ <マ ₊˚⊹♡ Good job! I've marked this task as done:\n\n" + markedTask;
    }

    /**
     * Displays a message when a task has been successfully unmarked.
     *
     * @param unmarkedTask The task that was unmarked.
     * @return A formatted message indicating the task has been unmarked.
     */
    public String showUnmarkTaskMessage(Task unmarkedTask) {
        return "ฅ^._.^ฅ OK, I've marked this task as not done yet:\n\n" + unmarkedTask;
    }

    /**
     * Displays a message when a task has been successfully added.
     *
     * @param task The task that was added.
     * @param size The current size of the task list.
     * @return A formatted message indicating the task has been added.
     */
    public String showAddTaskMessage(Task task, int size) {
        return "/ᐠ ^ ˕ ^マ Got it. I've added this task:\n\n" + task
                + "\n\nNow you have " + size + " tasks in the list";
    }

}
