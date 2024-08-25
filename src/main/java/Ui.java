import java.util.Scanner;

public class Ui {

	public static final String END_LINE = "_______________________________________________________________________";
	public static final String GREETING = "Hello! I'm Wiggly\n" +
	                                       "What can I do for you?";
	public static final String TASK_HEADER = "Here are the tasks in your list:";
	public static final String EXIT = "Bye. Hope to see you again soon!";

	private final Scanner sc = new Scanner(System.in);

	public Ui() {

	}

	public static String endline() {
		return END_LINE + "\n";
	}

	public void printWrappedString(String str) {
		System.out.println(END_LINE + "\n" + str +"\n" + END_LINE);
	}

	public void printGreeting() {
		printWrappedString(GREETING);
	}

	public void showError(String error) {
		printWrappedString(error);
	}

	public void printExit() {
		printWrappedString(EXIT);
	}

	public String readCommand() {
		return sc.nextLine();
	}

}
