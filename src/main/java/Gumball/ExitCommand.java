package Gumball;

import java.io.IOException;

public class ExitCommand extends Command{
    @Override
    public String execute(TaskList list, UI ui, FileManager fileManager) throws InputErrorException, IOException {
        String str = "Bye. Hope to see you again soon!";
        return(str);
    }
}
