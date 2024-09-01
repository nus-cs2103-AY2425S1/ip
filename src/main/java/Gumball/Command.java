package Gumball;

import java.io.IOException;

public abstract class Command {

    /**
     *
     * @param list The taskList which may be changed or where information will be extracted.
     * @param ui Class which contains ui functions.
     * @param fileManager The location where the information on the list is stored.
     * @throws InputErrorException
     * @throws IOException
     */
    public abstract void execute(TaskList list, UI ui, FileManager fileManager) throws InputErrorException, IOException;

}
