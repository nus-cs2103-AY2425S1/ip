package tecna.command;

import tecna.collection.TaskList;
import tecna.exception.WrongFormatException;
import tecna.storage.Storage;
import tecna.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            int index = parseMarkCommand(taskList);
            taskList.mark(index);
            return ui.printMarkMsg(taskList.getTask(index));
        } catch (WrongFormatException e) {
            return ui.printError(e.getMessage());
        }
    }

    public int parseMarkCommand(TaskList taskList) throws WrongFormatException {
        String[] input_words = message.split("\\s+");
        try {
            int index =  Integer.parseInt(input_words[1]) - 1;
            if (index < 1 || index > taskList.getSize()) {
                throw new WrongFormatException("mark", "Mark command should be in the format of \"mark [index of the task from 1 to " + taskList.getSize() +  "]\"");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new WrongFormatException("mark", "Mark command should be in the format of \"mark [index of the task from 1 to " + taskList.getSize() +  "]\"");
        }
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("");
        Ui ui = new Ui();
        MarkCommand command = new MarkCommand("mark 1");
        System.out.println(command.execute(taskList, storage, ui));
    }
}
