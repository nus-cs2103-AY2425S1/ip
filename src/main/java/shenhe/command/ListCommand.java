package shenhe.command;
import shenhe.exception.InvalidListEnquiry;
import shenhe.TaskList;
import shenhe.Ui;
import shenhe.Storage;


public class ListCommand extends Command {
    private String userInput;
    public ListCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidListEnquiry {
        if (!userInput.trim().equals("list")) {
            throw new InvalidListEnquiry();
        } else {
            ui.showTasksMessage();
            int totalNumberOfTasks = tasks.getSize();
            for (int i = 0; i < totalNumberOfTasks; i++) {
                System.out.println(i + 1 + "." + tasks.getTask(i).toString());
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
