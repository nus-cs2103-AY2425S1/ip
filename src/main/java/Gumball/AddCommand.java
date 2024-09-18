package Gumball;

import java.io.IOException;

public class AddCommand extends Command {
    private String input;

    /**
     *
     * @param input The input that determines what task will be added.
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     *
     * @param list The taskList where the task will be stored.
     * @param fileManager The location where the information on the list is stored.
     * @throws InputErrorException
     * @throws IOException
     */
    @Override
    public String execute(TaskList list, FileManager fileManager)
            throws InputErrorException, IOException {
        Task task = null;
        task = checkTaskType(input);
        String output = addToList(task, list, fileManager);
        return output;

    }

    /**
     *
     * @param task The task which will be added to the list.
     * @param list The list which the task will be added to.
     * @throws InputErrorException
     */
    private String addToList(Task task, TaskList list, FileManager fileManager) throws InputErrorException, IOException {
        String str = "Got it. I've added this task:\n" + list.add(task) +
                String.format("\nNow you have %d tasks in the list.", list.getNumOfTasks());
        fileManager.updateFile(list);
        return str;
    }

    private Task checkTaskType(String input) throws TaskException {
        Task task = null;
        if (input.startsWith("todo")) {
            task = new ToDo(input);
        } else if (input.startsWith("deadline")) {
            task = new Deadline(input);
        } else if (input.startsWith("event")) {
            task = new Event(input);
        } else if (input.startsWith("fixed")) {
            task = new FixedDurationTask(input);
        }
        return task;
    }

}
