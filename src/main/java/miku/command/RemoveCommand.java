package miku.command;

import miku.exception.RemoveNullException;

import miku.utility.UI;
import miku.utility.TaskList;
import miku.utility.Storage;

public class RemoveCommand extends Command {
    int index;

    public RemoveCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            if (index < 1 || index > taskList.size()) {
                throw new RemoveNullException("In valid index");
            }
            taskList.deleteItem(index);
            storage.saveFromTaskList(taskList);
            ui.printSectionBreak();
        } catch (RemoveNullException e) {
            e.print();
        }

    }

    @Override
    public void setData() {

    }

//                try {
//        int index = Integer.parseInt(input.split(" ")[1]);
//        if (index < 1 || index > taskList.size()) {
//            throw new miku.exception.RemoveNullException(String.valueOf(index));
//        }
//        taskList.deleteItem(index);
//    } catch (miku.exception.RemoveNullException e) {
//        e.print();
//        ui.printSectionBreak();
//    }
}
