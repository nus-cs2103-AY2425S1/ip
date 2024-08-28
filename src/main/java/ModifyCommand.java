import java.io.FileWriter;

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
            } else { // if (this.verb == CommandVerb.DELETE)
                taskAffected = taskList.delete(taskIndex); // ugh
                ui.showDelete(taskAffected, taskList);
            }
            storage.save(taskList);
        } catch (NullPointerException e) { // only happen in mark and unmark I think due to TaskList dynamic allocation specified
            System.out.println("There aren't so many tasks. Please if the task number is keyed in correctly. To see all tasks, type list");
        } catch (NumberFormatException e) {
            System.out.println("Please state the task number in INTEGER. Definitely not the task name");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There's an issue in the instruction format. Please check that it is <CommandVerb> <Integer> format");
        }
    }
    public boolean isExit() {
        return false;
    }
}
