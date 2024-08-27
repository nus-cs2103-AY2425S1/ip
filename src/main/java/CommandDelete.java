public class CommandDelete extends Command {
    private String param;

    public CommandDelete(String command, String param) {
        super(command);
        this.param = param;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        try {
            int ind = Integer.parseInt(param) - 1;

            if (list.isEmpty()) {
                throw new BlitzEmptyTaskListException();
            }

            Task task = list.deleteTask(ind);
            storage.writeAllToFile(list);

            String[] toPrint = {"Noted. I've removed this task:", "  [" + task.getType() + "]" + "[" + (task.getStatus() ? "X" : " ") + "] " + task};
            ui.printInDivider(toPrint);
        } catch (IndexOutOfBoundsException e) {
            throw new BlitzIndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            throw new BlitzNumberFormatException();
        }
    }
}
