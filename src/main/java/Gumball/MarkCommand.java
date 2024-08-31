package Gumball;

import java.io.IOException;

public class MarkCommand extends Command{
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, UI ui, FileManager fileManager) throws InputErrorException, IOException {
        list.mark(index);
        ui.print("Nice! I've marked this task as done:\n" + list.getSpecific(index));
        fileManager.updateFile(list);
    }
}
