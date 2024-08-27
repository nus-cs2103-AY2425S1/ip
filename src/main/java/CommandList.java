public class CommandList extends Command {
    public CommandList(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        //readFromFile();
        if (list.isEmpty()) {
            throw new BlitzEmptyTaskListException();
        }

        String[] toPrint = new String[list.size() + 1];
        toPrint[0] = "Here are the tasks in your list:";

        for (int i = 0; i < list.size(); i++) {
            Task curr = list.getTask(i);
            toPrint[i + 1] = "    " + (i + 1) + ".[" + curr.getType() + "]" + "[" + (curr.getStatus() ? "X" : " ") + "] " + curr;
        }

        ui.printInDivider(toPrint);
    }
}
