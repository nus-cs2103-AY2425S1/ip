package Gumball;

import java.io.IOException;

public class DeleteCommand extends Command{
    private int index;

    /**
     *
     * @param index The index that determines which task will be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     *
     * @param list The taskList which contains task which will be deleted.
     * @param ui Class which contains ui functions.
     * @param fileManager The location where the information on the list is stored.
     * @throws InputErrorException
     * @throws IOException
     */
    @Override
    public void execute(TaskList list, UI ui, FileManager fileManager) throws InputErrorException, IOException {
        String temp = list.delete(index);
        ui.print("Nice! I've deleted this task:\n" + temp
                + String.format("\nNow you have %d tasks in the list.",list.getN()));
        fileManager.updateFile(list);
    }
}
