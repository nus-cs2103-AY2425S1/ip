package Gumball;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, UI ui, FileManager fileManager)
            throws InputErrorException, IOException {
        String temp = list.delete(index);
        UI.print("Nice! I've deleted this task:\n" + temp
                + String.format("\nNow you have %d tasks in the list.", list.getN()));
        fileManager.updateFile(list);
    }
}
