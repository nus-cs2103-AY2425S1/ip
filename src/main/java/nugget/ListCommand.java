package nugget;

import nugget.Command;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NuggetException {
        int len = tasks.size();
        ui.line();
        for (int i = 0; i < len; i++) {
            String formattedMessage = String.format("%d.%s", i + 1, tasks.getTask(i));
            System.out.println(formattedMessage);
        }
        ui.line();
    }
}
