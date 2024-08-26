import java.io.*;
import task.*;

public class AddTask extends Command {
    public AddTask(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    @Override
    public void execute(String line) {
        Task.TaskType taskType = Task.TaskType.valueOf(line.split(" ")[0].toUpperCase());        
        Task task;
        try {
            switch (taskType) {
                case TODO:
                    task = new ToDo(line);
                    break;
                case DEADLINE:
                    task = new Deadline(line);
                    break;
                case EVENT:
                    task = new Event(line);
                    break;
                default:
                    // not possible
                    throw new InvalidTaskException();
            }
            taskList.addTask(task);
            String[] lines = new String[]{
                "Got it. I've added this task:",
                task.toString()
            };
            ui.say(lines);
        } catch (InvalidTaskException exception) {
            ui.warn(String.format("%s Usage: Alice <description>", exception));
        } catch (IOException e) {
            ui.warn("Unable to save task.");
        }
    }
}