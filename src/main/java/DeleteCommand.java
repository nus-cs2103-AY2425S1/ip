import java.util.ArrayList;

public class DeleteCommand  extends Command {
    private String userInput;
    private ArrayList<Task> tasks;
    private TaskList taskList;

    public DeleteCommand(String userInput, TaskList taskList) {
        super(userInput, taskList);
        this.userInput = userInput;
        this.tasks = taskList.getTaskList();
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        int x = Integer.parseInt(userInput.substring(7).trim());
        int index = x - 1;

        if (index >= tasks.size()) {
            System.out.println("There is no task " + (index + 1));
        } else {
            String temp = tasks.get(index).toString();
            Task task = tasks.get(index);
            taskList.delete(task);
            System.out.println("Nimbus has removed the task! \n" +
                    "    " + temp + "\n" + "You have " + tasks.size() + " tasks left!" + Ui.horizontalLine);
        }
    }
}
