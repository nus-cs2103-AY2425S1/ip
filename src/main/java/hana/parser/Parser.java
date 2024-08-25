package hana.parser;

import hana.HanaException;
import hana.command.*;

public class Parser {

	public static Command parse(String input) throws HanaException {
		if (input.startsWith("mark")) {
			return new MarkCommand(input);
		} else if (input.startsWith("unmark")) {
			return new UnmarkCommand(input);
		} else if (input.startsWith("todo")) {
			return new ToDoCommand(input);
		} else if (input.startsWith("deadline")) {
			return new DeadlineCommand(input);
		} else if (input.startsWith("event")) {
			return new EventCommand(input);
		} else if (input.startsWith("delete")) {
			return new DeleteCommand(input);
		} else if (input.startsWith("bye")) {
			return new ByeCommand();
		} else if (input.startsWith("list")) {
			return new ListCommand();
		} else if (input.startsWith("findByDate")) {
			return new FindByDateCommand(input);
		} else if (input.startsWith("help")) {
			return new HelpCommand();
		} else if (input.startsWith("findByKey")) {
			return new FindCommand(input);
		} else {
			throw new HanaException("Unknown command! Use help to see list of available commands");
		}
	}
}
