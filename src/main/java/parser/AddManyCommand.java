package parser;

import exceptions.TheOrangeRatchetCatException;
import tasklist.TaskList;
import tasks.Task;
import java.util.List;
import java.util.Scanner;

public class AddManyCommand implements Command {
    @Override
    public String execute(String input, List<Task> tasks, Scanner scanner) {
        return TaskList.addMany(input, tasks, scanner);
    }
}
