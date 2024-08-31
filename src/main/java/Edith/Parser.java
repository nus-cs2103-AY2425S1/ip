package Edith;

import Edith.command.Command;
import Edith.command.ExitCommand;
import Edith.command.ListCommand;
import Edith.command.AddCommand;
import Edith.command.ListOnDateCommand;
import Edith.command.MarkCommand;
import Edith.command.UnmarkCommand;
import Edith.command.DeleteCommand;

public class Parser {
    public Command parse(String userInput) {
        if (userInput.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (userInput.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("list ")) {
            String date = userInput.substring(5).trim();
            return new ListOnDateCommand(date);
        } else if (userInput.startsWith("mark ")) {
            int index = Integer.parseInt(userInput.substring(5).trim()) - 1;
            return new MarkCommand(index);
        } else if (userInput.startsWith("unmark ")) {
            int index = Integer.parseInt(userInput.substring(7).trim()) - 1;
            return new UnmarkCommand(index);
        } else if (userInput.startsWith("delete ")) {
            int index = Integer.parseInt(userInput.substring(7).trim()) - 1;
            return new DeleteCommand(index);
        } else if (userInput.startsWith("todo ") || userInput.startsWith("deadline ") || userInput.startsWith("event ")) {
            return new AddCommand(userInput);
        } else {
            throw new EdithException("Sorry but that is not an instruction I can execute.");
        }
    }
}
