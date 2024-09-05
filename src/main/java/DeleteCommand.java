import java.io.IOException;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(String input) {
        idx = Integer.parseInt(input.split(" ")[1]);
        int ptr = convertStrToInteger(temp);
        Task element = tasks.get(ptr - 1);
        tasks.remove(ptr - 1);
        System.out.println("✿ Shnoop ✿: I know a place, where the grass is really greener. "
                + "I'll send this task there\n" + "Goodbye " + element + "!");
        return "task_removed";
    }

        return "task_not_removed";
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task temp = tasks.get(idx);
        tasks.delete(idx);
        System.out.println("✿ Shnoop ✿: I know a place, where the grass is really greener. "
                + "I'll send this task there\n" + "Goodbye " + temp + "!");
    }

}
}
