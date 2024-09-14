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
     * @param ui Class which contains ui functions.
     * @param fileManager The location where the information on the list is stored.
     * @throws InputErrorException
     * @throws IOException
     */
    @Override
    public String execute(TaskList list, UI ui, FileManager fileManager)
            throws InputErrorException, IOException {
        Task task = null;
        if (input.startsWith("todo")) {
            task = new ToDo(input);
        } else if (input.startsWith("deadline")) {
            task = new Deadline(input);
        } else if (input.startsWith("event")) {
            task = new Event(input);
        }
        String output = addToList(task, list, ui, fileManager);
        return output;

    }

    /**
     *
     * @param task The task which will be added to the list.
     * @param list The list which the task will be added to.
     * @param ui A class which contains ui functions.
     * @throws InputErrorException
     */
    private String addToList(Task task, TaskList list, UI ui, FileManager fileManager) throws InputErrorException, IOException {
        String str = "Got it. I've added this task:\n" + list.add(task) +
                String.format("\nNow you have %d tasks in the list.", list.getN());
        UI.print(str);
        fileManager.updateFile(list);
        return str;
    }

}
