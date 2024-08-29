package yoda.commands;

import yoda.TaskList;
import yoda.exceptions.InvalidInputException;
import yoda.tasks.Task;

public class MarkCommand extends Command {
    private TaskList taskList;
    private String input;

    public MarkCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    public void run() throws InvalidInputException {
        if (!checkForValidInt(input)) {
            throw new InvalidInputException("Mark... which one?");
        }
        String[] splitInput = input.split(" ", 2);
        int index = Integer.parseInt(splitInput[1]);
        Task currentTask = taskList.get(index - 1);
        currentTask.markDone();
        System.out.println("Good job! Marked this as done, I have");
        System.out.printf("%s\n", taskList.get(index - 1));
    }

    public boolean checkForValidInt(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 2) {
            if (splitInput[1].matches("\\d+")) {
                return Integer.parseInt(splitInput[1]) >= taskList.getLength();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
