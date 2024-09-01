package Gumball;

import java.io.IOException;

public class MarkCommand extends Command{
    private int index;

    /**
     *
     * @param index The index that determines which task will be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     *
     * @param list The taskList which contains the task that will be marked.
     * @param ui Class which contains ui functions.
     * @param fileManager The location where the information on the list is stored.
     * @throws InputErrorException
     * @throws IOException
     */
    @Override
    public void execute(TaskList list, UI ui, FileManager fileManager) throws InputErrorException, IOException {
        list.mark(index);
        ui.print("Nice! I've marked this task as done:\n" + list.getSpecific(index));
        fileManager.updateFile(list);
    }
}
