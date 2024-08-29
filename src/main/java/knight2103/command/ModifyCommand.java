package knight2103.command;

import knight2103.tasks.*;
import knight2103.Ui;
import knight2103.files.Storage;

public class ModifyCommand extends Command {
    ModifyCommand(CommandVerb verb, String description) {
        super(verb, description);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task taskAffected;
            int taskIndex = Integer.parseInt(this.predicate) - 1;
            if (this.verb == CommandVerb.MARK) {
                taskAffected = taskList.mark(taskIndex);
                ui.showMark(taskAffected);
            } else if (this.verb == CommandVerb.UNMARK) {
                taskAffected = taskList.unmark(taskIndex);
                ui.showUnmark(taskAffected);
            } else { // if (this.verb == knight2103.command.CommandVerb.DELETE)
                taskAffected = taskList.delete(taskIndex); // ugh
                ui.showDelete(taskAffected, taskList);
            }
            storage.save(taskList);
        } catch (NumberFormatException e) {
            System.out.println("Please state the task number in INTEGER. Definitely not the task name");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There's an issue in the instruction format. " + "Please check that it is " +
                    "<knight2103.command.CommandVerb> <Integer> format");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
