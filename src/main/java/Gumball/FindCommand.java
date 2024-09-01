package Gumball;

import java.io.IOException;

public class FindCommand extends Command{
    String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList list, UI ui, FileManager fileManager) throws InputErrorException, IOException {
        UI.print(list.find(keyword));
    }
}
