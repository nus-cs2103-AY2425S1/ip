package Command;

import Storage.Storage;
import TaskList.*;


import java.time.format.DateTimeParseException;

import static Ui.Ui.printMessage;
import static Ui.Ui.printMessages;

public class AddCommand extends Command{
    private Storage storage;
    private TaskList list;
    private String input;

    public AddCommand (Storage storage, TaskList list, String input) {
        this.list = list;
        this.input = input;
        this.storage = storage;
    }

    @Override
    public void execute() {
        try {
            Task task = Task.of(input);
            list.add(task);
            printMessages(new String[]{"Got it. I've added this task:",task.toString(),
                    String.format("Now you have %s tasks in the list",list.size())});
            storage.updateFile(list);
        } catch (MissingInformationException e) {
            printMessage(e.getMessage());
        } catch (InvalidInputException e) {
            printMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            printMessages("Sorry~ I can not recognize the time", "Please enter the time in the " +
                    "YYYY-MM-DD format");
        }
    }
}
