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
        if (input.startsWith("todo")) {
            String temp = addToList(new ToDo(input), list, ui);
            fileManager.updateFile(list);
            return temp;
        } else if (input.startsWith("deadline")) {
            String temp = addToList(new Deadline(input), list, ui);
            fileManager.updateFile(list);
            return temp;
        } else if (input.startsWith("event")) {
            String temp = addToList(new Event(input), list, ui);
            fileManager.updateFile(list);
            return temp;
        }
        return null;
    }

    /**
     *
     * @param task The task which will be added to the list.
     * @param list The list which the task will be added to.
     * @param ui A class which contains ui functions.
     * @throws InputErrorException
     */
    public String addToList(Task task, TaskList list, UI ui) throws InputErrorException {
        String str = "Got it. I've added this task:\n" + list.add(task) +
                String.format("\nNow you have %d tasks in the list.", list.getN());
        UI.print(str);
        return str;
    }

}
