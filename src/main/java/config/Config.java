package config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



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
	public static final String intro = "Meow. I'm Susan!\n" + INDENTATION + "What can I do for you?";
	public static final String outro = "Meow. Hope to see you again soon! This program will turn off automatically in 5 seconds...";
	public static final ArrayList<CommandDescriptor> cmds = new ArrayList<>(Arrays.asList(
		new CommandDescriptor("todo <description>", "Adds tasks without any date/time attached to it e.g., visit new theme park"),
		new CommandDescriptor("deadline <description> /by <deadline>", "adds tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm"),
		new CommandDescriptor("event <description> /from <from> /to <to>", "adds tasks that start at a specific date/time and ends at a specific date/time e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019"),
		new CommandDescriptor("list", "Lists down your current tasks."),
		new CommandDescriptor("mark <taskNumber>", "Checks the task with taskNumber as done."),
		new CommandDescriptor("unmark <taskNumber>", "Checks the task with taskNumber as undone."),
		new CommandDescriptor("bye", "Exits the program."),
		new CommandDescriptor("delete <taskNumber>", "Deletes the task with taskNumber."),
		new CommandDescriptor("deleteMany <id1> <id2> ... <idn>", "Deletes all the tasks with IDs from the list of IDs.")
	));

	public static boolean handleHelloMessage(String message) {
		// Create regex to catch 'hello' in multiple languages
		String regex = "\\b(hello|hola|bonjour|hallo|ciao|olá|namaste|salve|konnichiwa|nǐ hǎo|annyeonghaseyo|merhaba|szia|salām|privet|ahoj|god dag|sawubona|shalom)\\b";
		// Compile the pattern with case-insensitive flag
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		// Create matcher for the test string
		Matcher matcher = pattern.matcher(message);

		return matcher.find();
	}

	public static String makeCommandMessage(List<CommandDescriptor> cmds) {
		StringBuilder sb = new StringBuilder();
		int num = 1;
		for (CommandDescriptor cmd : cmds) {
			sb.append(INDENTATION).append(num).append(". ").append(cmd.syntax).append(": ").append(cmd.description).append("\n");
			num++;	
		}

		return sb.toString();
	}

	public static final String CSV_FILE_PATH = "data.csv";

	public static final String commands = makeCommandMessage(cmds); 
}	




