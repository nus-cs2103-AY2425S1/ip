package Gumball;

import java.io.IOException;

public class FindCommand extends Command{
    String keyword;
    public FindCommand(String input) {
        this.keyword = input.substring(5);
    }

    /**
     * Returns String representing list of tasks which contain the keyword provided
     * in the constructor.
     * @param list The taskList where information will be extracted.
     * @param fileManager The location where the information on the list is stored.
     * @return
     * @throws InputErrorException
     * @throws IOException
     */
    @Override
    public String execute(TaskList list, FileManager fileManager) throws InputErrorException, IOException {
        return list.find(keyword);
    }
}
