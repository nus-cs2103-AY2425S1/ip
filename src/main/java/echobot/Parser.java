package echobot;

import echobot.task.TaskList;

/**
 * Parses user input to create a Command object.
 * The parser is responsible for splitting the input into command and arguments.
 */
public class Parser {
    private final TaskList tasks;

    /**
     * Constructs a Parser with the specified task list.
     *
     * @param taskList The list of tasks to be manipulated by commands.
     */
    public Parser(TaskList taskList) {
        this.tasks = taskList;
    }

    /**
     * Parses the given user input and returns a Command object.
     * The input is split into a command and its arguments.
     *
     * @param userInput The input provided by the user.
     * @return A Command object representing the parsed command and arguments.
     */
    public Command parse(String userInput) {
        return new Command(userInput.split(" ", 2), this.tasks); // Splits input into command and the rest
    }
}
