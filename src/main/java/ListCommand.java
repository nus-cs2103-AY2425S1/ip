public class ListCommand extends Command {

    public ListCommand() {}

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        output.append("Here are the tasks in your list:\n");
        for (int i=0; i<tasklist.size(); i++) {
            output.append(i + 1).append(".").append(tasklist.get(i).toString()).append("\n");
        }

        ui.printString(output.toString());
    }

    public boolean isExit() {
        return false;
    }
}
