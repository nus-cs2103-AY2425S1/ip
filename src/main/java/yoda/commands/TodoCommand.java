package yoda.commands;

import yoda.TaskList;
import yoda.exceptions.InvalidInputException;
import yoda.tasks.Task;
import yoda.tasks.ToDo;

public class TodoCommand extends Command {
    private TaskList taskList;
    private String input;

    public TodoCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    public void run() throws InvalidInputException {
        if (!checkValidToDo()) {
            throw new InvalidInputException("A todo must have a description, no...?");
        }
        String[] splitInput = input.split(" ", 2);
        String task = splitInput[1];
        ToDo newTask = new ToDo(task);
        taskList.add(newTask);
        System.out.println("Added task:\n" + newTask + "\n"
                + String.format("Now you have %d tasks in the list", taskList.getLength()));
    }

    public boolean checkValidToDo() {
        String[] splitInput = input.split(" ", 2);
        return splitInput.length == 2;
    }

}
