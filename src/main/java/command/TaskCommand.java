package command;
import task.Task;
import task.TaskList;
import utilities.Parser;
import utilities.Storage;

public class TaskCommand extends Command {
    private String dialog;
    private TaskList taskList;

    public TaskCommand(String dialog, TaskList taskList) {
        this.dialog = dialog;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        taskList.add(Task.of(dialog));
        new Storage("./data/duke.txt").save(taskList);

        String message = "Got it. I've added this task: \n" 
                + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        System.out.println(Parser.addHorizontalLinesAndIndentation(message));
    }
}