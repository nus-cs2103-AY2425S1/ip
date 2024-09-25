package Gumball;

import java.io.IOException;

public abstract class Command {

    /**
     *
     * @param list The taskList which may be changed or where information will be extracted.
     * @param fileManager The location where the information on the list is stored.
     * @throws InputErrorException
     * @throws IOException
     */
    public abstract String execute(TaskList list, FileManager fileManager)
            throws InputErrorException, IOException;

}
