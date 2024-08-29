public class DeleteCommand extends Command{
    private int num;

    public DeleteCommand(int num) {
        this.num = num;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException {
        if (this.num > tasks.size() - 1) {
            throw new SnipeException("This list item does not exist!\n"
                    + tasks.listLength());
        } else {
            tasks.deleteTask(this.num);
            String message = "Noted. I've removed this task:\n"
                    + tasks.getTask(this.num).toString()
                    + tasks.listLength();
            ui.printWithLines(message);
        }
    }
}
