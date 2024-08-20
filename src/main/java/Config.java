import java.util.*;

public class Config {
    public static final String INDENTATION = "    "; 
	public static final String logo = "      ___           ___           ___           ___           ___     \n" +
		"     /\\  \\         /\\__\\         /\\  \\         /\\  \\         /\\__\\    \n" +
		"    /::\\  \\       /:/  /        /::\\  \\       /::\\  \\       /::|  |   \n" +
		"   /:/\\ \\  \\     /:/  /        /:/\\ \\  \\     /:/\\:\\  \\     /:|:|  |   \n" +
		"  _\\:\\~\\ \\  \\   /:/  /  ___   _\\:\\~\\ \\  \\   /::\\~\\:\\  \\   /:/|:|  |__ \n" +
		" /\\ \\:\\ \\ \\__\\ /:/__/  /\\__\\ /\\ \\:\\ \\ \\__\\ /:/\\:\\ \\:\\__\\ /:/ |:| /\\__\\\n" +
		" \\:\\ \\:\\ \\/__/ \\:\\  \\ /:/  / \\:\\ \\:\\ \\/__/ \\/__\\:\\/:/  / \\/__|:|/:/  /\n" +
		"  \\:\\ \\:\\__\\    \\:\\  /:/  /   \\:\\ \\:\\__\\        \\::/  /      |:/:/  / \n" +
		"   \\:\\/:/  /     \\:\\/:/  /     \\:\\/:/  /        /:/  /       |::/  /  \n" +
		"    \\::/  /       \\::/  /       \\::/  /        /:/  /        /:/  /   \n" +
		"     \\/__/         \\/__/         \\/__/         \\/__/         \\/__/    \n";
	public static final String intro = "Meow. I'm Susan!\n" + INDENTATION + "What can I do for you?";
	public static final String outro = "Meow. Hope to see you again soon!";
	public static final ArrayList<Command> cmds = new ArrayList<>(Arrays.asList(
		new Command("todo <description>", "Adds tasks without any date/time attached to it e.g., visit new theme park"),
		new Command("deadline <description> /by <deadline>", "adds tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm"),
		new Command("event <description> /from <from> /to <to>", "adds tasks that start at a specific date/time and ends at a specific date/time e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019"),
		new Command("list", "Lists down your current tasks."),
		new Command("mark <taskNumber>", "Checks the task with taskNumber as done."),
		new Command("unmark <taskNumber>", "Checks the task with taskNumber as undone."),
		new Command("bye", "Exits the program."),
		new Command("delete <taskNumber>", "Deletes the task with taskNumber.")
	));

	public static String makeCommandMessage(List<Command> cmds) {
		StringBuilder sb = new StringBuilder();
		int num = 1;
		for (Command cmd : cmds) {
			sb.append(INDENTATION + num + ". " + cmd.syntax + ": " + cmd.description + "\n");
			num++;	
		}

		return sb.toString();
	
	}

	public static final String commands = makeCommandMessage(cmds); 
}	

class Command {
	public String syntax;
	public String description;

	public Command(String syntax, String description) {
		this.syntax = syntax;
		this.description = description;
	}
}


