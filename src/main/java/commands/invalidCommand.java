package commands;
import storage.Storage;
import storage.TaskList;
import ui.UI;

public class invalidCommand implements Command {
    private String desc;

    public invalidCommand(String desc) {
        this.desc = desc;
    }

    public boolean execute(Storage storage, TaskList master, UI ui) {
        switch(desc) {
            case "mark":
            case "unmark":
                System.out.println("Friday > Input the task number to mark/unmark the task");
                break;
            case "add":
                System.out.println("Friday > Try doing add <task name>");
                break;
            case "remove":
                System.out.println("Friday > Input the task number (1 - " + master.getSize() + ") to remove the task");
                break;
            default:
                System.out.println("Friday > Hmm...you can't do that. Try add/remove <task> or \"help\" for more options");
                break;
        }
        UI.printLine();
        return false;
    }
}
