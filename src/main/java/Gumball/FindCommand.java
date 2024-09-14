package Gumball;

import java.io.IOException;

public class FindCommand extends Command{
    String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns String representing list of tasks which contain the keyword provided
     * in the constructor.
     * @param list The taskList where information will be extracted.
     * @param ui Class which contains ui functions.
     * @param fileManager The location where the information on the list is stored.
     * @return
     * @throws InputErrorException
     * @throws IOException
     */
    @Override
    public String execute(TaskList list, UI ui, FileManager fileManager) throws InputErrorException, IOException {
        UI.print(list.find(keyword));
        return list.find(keyword);
    }
}
