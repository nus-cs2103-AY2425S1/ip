package commands;

import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;
import task.Task;
import task.TaskList;

public class AddTaskCommand extends Command {

    private String userInput;

    public AddTaskCommand(String userInput) {
        this.userInput = userInput;
    }


    @Override
    public void execute(TaskList taskList) {
        try {
            Task newTask = Task.createTask(userInput);
            taskList.addTask(newTask);
            System.out.println("----------------\n"
                    + "Alrighty! The following task has been added:\n "
                    + newTask + "\n"
                    + "Oh my goodness you have " + taskList.getSize() + " tasks remaining\n"
                    + "----------------\n");
        } catch (NoTaskDescriptionException e) {
            System.out.println("Wah, no description then I record what?");
        } catch (InvalidTaskException e) {
            System.out.println("THIS IS NOT A VALID TASK LAH");
        }
    }
}
