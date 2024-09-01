import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteCommand extends Command {
    private String Message;

    public DeleteCommand(String Message) {
        this.Message = Message;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {
        Pattern pattern = Pattern.compile(("delete") + " (\\d+)");
        Matcher matcher = pattern.matcher(Message);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(1));
            Task task = taskList.get(index - 1);
            Task.count--;
            ui.printDelete(task);
            taskList.remove(index - 1);
        }
    }
}
