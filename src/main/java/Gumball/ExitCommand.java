package Gumball;

import java.io.IOException;

public class ExitCommand extends Command{
    /**
     *
     * @param list The taskList which may be changed or where information will be extracted.
     * @param fileManager The location where the information on the list is stored.
     * @return
     * @throws InputErrorException
     * @throws IOException
     */
    @Override
    public String execute(TaskList list, FileManager fileManager) throws InputErrorException, IOException {
        String str = UI.outro();
        return(str);
    }
}
