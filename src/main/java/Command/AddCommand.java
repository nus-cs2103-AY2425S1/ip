package Command;

import TaskList.*;


import java.time.format.DateTimeParseException;

import static Ui.Ui.printMessage;
import static Ui.Ui.printMessages;

public class AddCommand extends Command{
    private TaskList list;
    private String input;

    public AddCommand (TaskList list, String input) {
        this.list = list;
        this.input = input;
    }

    @Override
    public void execute() {
        try {
            Task task = Task.of(input);
            list.add(task);
            printMessages(new String[]{"Got it. I've added this task:",task.toString(),
                    String.format("Now you have %s tasks in the list",list.size())});
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
