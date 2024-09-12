package Gumball;

import java.io.IOException;

public class MarkCommand extends Command {
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
    public String execute(TaskList list, UI ui, FileManager fileManager)
            throws InputErrorException, IOException {
        list.mark(index);
        String str = "Nice! I've marked this task as done:\n" + list.getSpecific(index);
        ui.print(str);
        fileManager.updateFile(list);
        return str;
    }
}
