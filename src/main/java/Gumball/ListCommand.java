package Gumball;

import java.io.IOException;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList list, UI ui, FileManager fileManager)
            throws InputErrorException, IOException {
        ui.print(list.get());
    }
}
