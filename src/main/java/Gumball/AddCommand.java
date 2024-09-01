package Gumball;

import java.io.IOException;

public class AddCommand extends Command{
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
    public void execute(TaskList list, UI ui, FileManager fileManager) throws InputErrorException, IOException {
        if (input.startsWith("todo")) {
            addToList(new ToDo(input), list, ui);
            fileManager.updateFile(list);
        } else if (input.startsWith("deadline")) {
            addToList(new Deadline(input), list, ui);
            fileManager.updateFile(list);
        } else if (input.startsWith("event")) {
            addToList(new Event(input), list, ui);
            fileManager.updateFile(list);
        }
    }

    /**
     *
     * @param task The task which will be added to the list.
     * @param list The list which the task will be added to.
     * @param ui A class which contains ui functions.
     * @throws InputErrorException
     */
    public void addToList(Task task, TaskList list, UI ui) throws InputErrorException {
        ui.print("Got it. I've added this task:\n" + list.add(task) +
                String.format("\nNow you have %d tasks in the list.",list.getN()));
    }

}
