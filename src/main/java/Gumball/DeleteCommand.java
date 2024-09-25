package Gumball;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    /**
     *
     * @param input The index that determines which task will be deleted.
     */
    public DeleteCommand(String input) throws TaskException {
        this.index = getNumFromString(input);
    }

    /**
     *
     * @param list The taskList which contains task which will be deleted.
     * @param fileManager The location where the information on the list is stored.
     * @throws InputErrorException
     * @throws IOException
     */
    @Override
    public String execute(TaskList list, FileManager fileManager)
            throws InputErrorException, IOException {
        String description = list.delete(index);
        String msg = stringFormatter(description, list);
        fileManager.updateFile(list);
        return msg;
    }

    private String stringFormatter(String desc, TaskList list) {
        String temp = "Nice! I've deleted this task:\n" + desc
                + String.format("\nNow you have %d tasks in the list.", list.getNumOfTasks());
        return temp;

    }
    private static int getNumFromString(String input) throws TaskException {
        try {
            return Integer.parseInt(input.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            throw new TaskException("Input after delete has to be an integer.");
        }
    }

}
