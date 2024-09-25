package Gumball;

import java.io.IOException;

public class ListCommand extends Command{

    /**
     *
     * @param list The taskList which determines what list will be listed.
     * @param fileManager location where the information on the list is stored.
     * @throws InputErrorException
     * @throws IOException
     */
    @Override
    public String execute(TaskList list, FileManager fileManager)
            throws InputErrorException, IOException {
        return list.getListAsString();
    }
}
