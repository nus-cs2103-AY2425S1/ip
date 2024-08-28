package monique.command;

import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

import java.util.stream.IntStream;

public class ListCommand extends Command {

    public ListCommand(){
        super();
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        IntStream.range(0, tasks.getNumItems())
                .forEach(i -> {
                    System.out.println((i + 1) + "." + tasks.getTask(i));
                });
        if (tasks.getNumItems() == 0) {
            ui.emptyListMessage();
        }
    }

    @Override
    public boolean isActive(){
        return true;
    }
}
