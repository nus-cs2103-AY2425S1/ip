public class MarkTaskCommand extends Command{

    private final boolean isComplete;
    private final int taskToMark;

    public MarkTaskCommand(int taskToMark, Boolean isComplete) {
        this.taskToMark = taskToMark;
        this.isComplete = isComplete;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        if (isComplete) {
            try {
                if (tasklist.markTask(taskToMark)) {
                    storage.updateTxt(taskToMark, isComplete);
                    ui.printText("Nice! I've marked this task as done:\n" + tasklist.getTaskStr(taskToMark));
                } else {
                    ui.printText("Hey! You've done that one already!\n" + tasklist.getTaskStr(taskToMark));
                }
            } catch (IndexOutOfBoundsException e) {
                if (taskToMark < 0) {
                    throw new TayooException("Expected task number > 0");
                } else if (taskToMark > 100) {
                    throw new TayooException("Expected 0 < Task number < 100");
                } else {
                    throw new TayooException("Task number not found");
                }
            } catch (NumberFormatException e) {
                throw new TayooException("Integer expected");
            }
        } else {
            try {
                if (tasklist.unmarkTask(taskToMark)) {
                    storage.updateTxt(taskToMark, isComplete);
                    ui.printText("OK, I've marked this task as not done yet:\n" + tasklist.getTaskStr(taskToMark));
                } else {
                    ui.printText("Hey! You haven't even done that one yet!\n" + tasklist.getTaskStr(taskToMark));
                }
            } catch (IndexOutOfBoundsException e) {
                if (taskToMark <= 0) {
                    throw new TayooException("Expected task number > 0");
                } else if (taskToMark > 100) {
                    throw new TayooException("Expected 0 < Task number < 100");
                } else {
                    throw new TayooException("Task number not found");
                }
            } catch (NumberFormatException e) {
                throw new TayooException("Integer expected");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
