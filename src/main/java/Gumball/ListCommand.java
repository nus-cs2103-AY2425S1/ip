package Gumball;

import java.io.IOException;

public class ListCommand extends Command{

    /**
     *
     * @param list The taskList which determines what list will be listed.
     * @param ui contains ui functions.
     * @param fileManager location where the information on the list is stored.
     * @throws InputErrorException
     * @throws IOException
     */
    @Override
    public void execute(TaskList list, UI ui, FileManager fileManager) throws InputErrorException, IOException {
        ui.print(list.get());
    }
}
