package config;

import java.util.*;
import features.command.CommandDescriptor;

public class Config {
    public static final String INDENTATION = "    "; 
	public static final String logo = """
			      ___           ___           ___           ___           ___    \s
			     /\\  \\         /\\__\\         /\\  \\         /\\  \\         /\\__\\   \s
			    /::\\  \\       /:/  /        /::\\  \\       /::\\  \\       /::|  |  \s
			   /:/\\ \\  \\     /:/  /        /:/\\ \\  \\     /:/\\:\\  \\     /:|:|  |  \s
			  _\\:\\~\\ \\  \\   /:/  /  ___   _\\:\\~\\ \\  \\   /::\\~\\:\\  \\   /:/|:|  |__\s
			 /\\ \\:\\ \\ \\__\\ /:/__/  /\\__\\ /\\ \\:\\ \\ \\__\\ /:/\\:\\ \\:\\__\\ /:/ |:| /\\__\\
			 \\:\\ \\:\\ \\/__/ \\:\\  \\ /:/  / \\:\\ \\:\\ \\/__/ \\/__\\:\\/:/  / \\/__|:|/:/  /
			  \\:\\ \\:\\__\\    \\:\\  /:/  /   \\:\\ \\:\\__\\        \\::/  /      |:/:/  /\s
			   \\:\\/:/  /     \\:\\/:/  /     \\:\\/:/  /        /:/  /       |::/  / \s
			    \\::/  /       \\::/  /       \\::/  /        /:/  /        /:/  /  \s
			     \\/__/         \\/__/         \\/__/         \\/__/         \\/__/   \s
			""";
	public static final String intro = "Meow. I'm features.Susan!\n" + INDENTATION + "What can I do for you?";
	public static final String outro = "Meow. Hope to see you again soon!";
	public static final ArrayList<CommandDescriptor> cmds = new ArrayList<>(Arrays.asList(
		new CommandDescriptor("todo <description>", "Adds tasks without any date/time attached to it e.g., visit new theme park"),
		new CommandDescriptor("deadline <description> /by <deadline>", "adds tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm"),
		new CommandDescriptor("event <description> /from <from> /to <to>", "adds tasks that start at a specific date/time and ends at a specific date/time e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019"),
		new CommandDescriptor("list", "Lists down your current tasks."),
		new CommandDescriptor("mark <taskNumber>", "Checks the task with taskNumber as done."),
		new CommandDescriptor("unmark <taskNumber>", "Checks the task with taskNumber as undone."),
		new CommandDescriptor("bye", "Exits the program."),
		new CommandDescriptor("delete <taskNumber>", "Deletes the task with taskNumber.")
	));

	public static String makeCommandMessage(List<CommandDescriptor> cmds) {
		StringBuilder sb = new StringBuilder();
		int num = 1;
		for (CommandDescriptor cmd : cmds) {
			sb.append(INDENTATION).append(num).append(". ").append(cmd.syntax).append(": ").append(cmd.description).append("\n");
			num++;	
		}

		return sb.toString();
	}

	public static final String CSV_FILE_PATH = "src/main/java/data/data.csv";

	public static final String commands = makeCommandMessage(cmds); 
}	




