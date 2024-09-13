package diomon.command;

import diomon.Storage;
import diomon.TaskList;
import diomon.ui.Ui;

public class UnmarkCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int i = Integer.parseInt(input);
            System.out.printf("[%s] has been unmarked\n" +
                    "Ya did a little oopies, just like your mom", i, tasks.get(i - 1));
            tasks.unmark( i- 1);
        } catch (NumberFormatException e) {
            System.out.println("Param given for unmarking a task is wrong, please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bound");
        }
    };
}
