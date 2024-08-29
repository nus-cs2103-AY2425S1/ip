package yoda.commands;

import yoda.TaskList;
import yoda.exceptions.InvalidInputException;
import yoda.tasks.Task;

public class DeleteCommand extends Command {
    private TaskList taskList;
    private String input;

    public DeleteCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    public void run() throws InvalidInputException {
        if (!checkForValidInt(input)) {
            throw new InvalidInputException("Delete... which one?");
        }
        String[] splitInput = input.split(" ", 2);
        int index = Integer.parseInt(splitInput[1]);
        Task currentTask = taskList.get(index - 1);
        taskList.delete(index - 1);
        System.out.println("Deleted this, I have.");
        System.out.printf("%s\n", currentTask);
        System.out.println(String.format("Now you have %d tasks in the list\n", taskList.getLength()));
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
