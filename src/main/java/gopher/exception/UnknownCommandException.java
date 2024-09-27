package gopher.exception;

/**
 * Thrown if the given command is not recognized.
 */
public class UnknownCommandException extends Exception {
    private String command;

    public UnknownCommandException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        return "Sorry, but I can't recognize this command: "
                + this.command
                + "\nPlease try again...\n\n"
                + """
                    Currently I can understand the following commands:
                        1. todo - Create a ToDo Task
                        2. deadline - Create a Deadline Task
                        3. event - Create an Event Task
                        4. list -List out all the current tasks
                        5. mark - Mark tasks as done
                        6. unmark - Mark tasks as not done
                        7. find - Find tasks based on keywords
                        8. update - Update a task with provided information
                        9. delete - Delete tasks from the task list
                        10. bye - Exit the chatbot

                    Note that the command is case-insensitive,
                    as long as the input characters match,
                    I would be able to respond to the given command""";
    }
}
