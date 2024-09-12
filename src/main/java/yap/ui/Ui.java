package yap.ui;

import yap.task.TaskList;

/**
 * Class that implements the interaction with a user.
 */
public class Ui {
    private TaskList taskList;
    private Command command;

    /**
     * Constructor for the Ui class.
     *
     * @param taskList The tasklist object that is storing all tasks for the program.
     */
    public Ui(TaskList taskList) {
        this.taskList = taskList;
        this.command = new Command(this.taskList);
    }

    /**
     * Reacts to the user's input
     *
     * @param userInput A string containing the user's input
     * @return Returns 0 if user sends bye, 1 otherwise
     * @throws InputException Throws an input exception if the user inputs an invalid command
     */
    public int reactToUserInput(String userInput) throws InputException {
        if (userInput.equalsIgnoreCase("bye")) {
            System.out.println("Bye! It was really nice talking to you, see you soon :)");
            return 0;
        }

        // Mark functionality
        if (userInput.startsWith("mark")) {
            command.mark(userInput);
            return 1;
        }

        // Unmark functionality
        if (userInput.startsWith("unmark")) {
            command.unmark(userInput);
            return 1;
        }

        // Delete functionality
        if (userInput.startsWith("delete")) {
            command.delete(userInput);
            return 1;
        }

        // If user adds a todo task
        if (userInput.startsWith("todo")) {
            command.todo(userInput);
            return 1;
        }

        // If user adds a deadline task
        if (userInput.startsWith("deadline")) {
            command.deadline(userInput);
            return 1;
        }

        // If user adds an event task
        if (userInput.startsWith("event")) {
            command.event(userInput);
            return 1;
        }

        // List functionality
        if (userInput.startsWith("list")) {
            command.list();
            return 1;
        }

        if (userInput.startsWith("find")) {
            command.find(userInput);
            return 1;
        }

        throw new InputException();
    }
}
