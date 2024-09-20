package Gumball;

import java.io.IOException;

public class MarkCommand extends Command {
    private int index;

    /**
     *
     * @param input The input that determines which task will be marked.
     */
    public MarkCommand(String input) throws TaskException {
            this.index = getNumFromString(input);
    }

    /**
     *
     * @param list The taskList which contains the task that will be marked.
     * @param fileManager The location where the information on the list is stored.
     * @throws InputErrorException
     * @throws IOException
     */
    @Override
    public String execute(TaskList list, FileManager fileManager)
            throws InputErrorException, IOException {
        list.mark(index);
        String str = "Nice! I've marked this task as done:\n" + list.getTaskString(index);
        fileManager.updateFile(list);
        return str;
    }

    private static int getNumFromString(String input) throws TaskException {
        try {
            return Integer.parseInt(input.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            throw new TaskException("Input after mark has to be an integer.");
        }
    }
}
