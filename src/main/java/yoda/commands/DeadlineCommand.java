package yoda.commands;

import yoda.TaskList;
import yoda.exceptions.InvalidInputException;
import yoda.tasks.Deadline;
import yoda.tasks.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private TaskList taskList;
    private String input;

    public DeadlineCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    public void run() throws InvalidInputException {
        if (!checkValidDeadline(input)) {
            throw new InvalidInputException("A deadline must have a description and due by time, no...?");
        }
        String[] splitInput = input.split(" ", 2);
        String task = splitInput[1];
        String[] splitTask = task.split(" /by ", 2);
        Deadline newTask = null;
        try {
            LocalDate by = LocalDate.parse(splitTask[1]);
            newTask = new Deadline(splitTask[0], by);
            taskList.add(newTask);
            System.out.println("Added task:\n" + newTask + "\n"
                    + String.format("Now you have %d tasks in the list", taskList.getLength()));
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Deadline must be in format yyyy-mm-dd");
        }
    }

    public boolean checkValidDeadline(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 2) {
            String[] splitTask = splitInput[1].split("/by ", 2);
            return splitTask.length == 2;
        } else {
            return false;
        }
    }
}

