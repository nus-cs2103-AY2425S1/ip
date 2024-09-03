package sunny;

import java.util.List;

public class UnmarkCommand extends Command{
    private String s = "";
    @Override
    public String runCommand(List<Task> ls, String m) {
        int i2 = Integer.parseInt(m);
        ls.get(i2 - 1).setter(false);
        return "     Task undone \n     " + ls.get(i2 - 1);
    }
}
