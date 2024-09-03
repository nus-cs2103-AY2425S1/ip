package sunny;

import java.util.List;

/**
 * Lists out all tasks on the user interface
 */
public class FindCommand extends Command {
    private String s = "";
    @Override
    public String runCommand(List<Task> ls, String m) {
        for (int i = 1; i <= ls.size(); i++) {
            if (ls.get(i - 1).toString().contains(m.trim())) {
                s = s + String.format("     %h. %s \n", i, ls.get(i - 1));
            }
        }
        return line + "\n" + s + "\n" + line;
    }
}
