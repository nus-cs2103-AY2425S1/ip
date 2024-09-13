package parser;

import java.util.List;
import java.util.Scanner;

import tasks.Task;

/**
 * Command interface where each command needs to have an execute method
 */
public interface Command {
    String execute(String input, List<Task> items, Scanner scanner);
}
