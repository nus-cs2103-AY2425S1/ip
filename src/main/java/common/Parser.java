package common;

import common.Command;
import commands.AddCommand;
import commands.EchoCommand;
import commands.ExitCommand;
import commands.ListCommand;

public class Parser {
    public Command parse(String input) {

        String normalizedInput = input.trim().toLowerCase();

        if (normalizedInput.equals("bye")) {
            return new ExitCommand();
        } else if (normalizedInput.equals("list")) {
            return new ListCommand();
        } else if (normalizedInput.startsWith("add ")) {
            return new AddCommand(input.substring(4));
        } else {
            return new EchoCommand(input);
        }
    }
}