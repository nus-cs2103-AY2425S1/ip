package sunny;

import java.util.List;

/**
 * Marks the corresponding task as done
 */
public class MarkCommand extends Command {
    private String s = "";
    @Override
    public String runCommand(List<Task> ls, String m) {
        int i2 = Integer.parseInt(m);
        ls.get(i2 - 1).setter(true);
        return "     Marked the task as done! \n     " + ls.get(i2 - 1);
    }
}
