package echobot;

import echobot.task.TaskList;

public class Parser {
    private final TaskList tasks;

    public Parser(TaskList taskList) {
        this.tasks = taskList;
    }

    public Command parse(String userInput) {
        return new Command(userInput.split(" ", 2), this.tasks); // Splits input into command and the rest
    }
}
