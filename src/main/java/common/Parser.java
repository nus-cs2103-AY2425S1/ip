package common;

import common.Command;
import commands.AddCommand;
import commands.EchoCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;

public class Parser {
    public Command parse(String input) {

        String normalizedInput = input.trim().toLowerCase();

        if (normalizedInput.equals("bye")) {
            return new ExitCommand();
        } else if (normalizedInput.equals("list")) {
            return new ListCommand();
        } else if (normalizedInput.startsWith("add ")) {
            return new AddCommand(input.substring(4));
        } else if (normalizedInput.startsWith("mark ")) {
            String task = input.substring(5).trim();
            return new MarkCommand(task);
        } else if (normalizedInput.startsWith("unmark ")) {
            String task = input.substring(7).trim();
            return new UnmarkCommand(task);
        } else {
            return new EchoCommand(input);
        }
    }
}