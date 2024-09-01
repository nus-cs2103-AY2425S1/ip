package Gumball;

import java.io.IOException;

public class AddCommand extends Command {
    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes intended command
     *
     * @param list
     * @param ui
     * @param fileManager
     */
    @Override
    public void execute(TaskList list, UI ui, FileManager fileManager)
            throws InputErrorException, IOException {
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

    public void addToList(Task task, TaskList list, UI ui) throws InputErrorException {
        UI.print("Got it. I've added this task:\n" + list.add(task) +
                String.format("\nNow you have %d tasks in the list.", list.getN()));
    }

}
