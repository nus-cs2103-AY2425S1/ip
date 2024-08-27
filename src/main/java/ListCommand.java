import java.util.StringJoiner;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList tasks) {
        StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            joiner.add(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        ui.say(joiner.toString());
    }
}
