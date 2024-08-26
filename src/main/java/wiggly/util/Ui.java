package wiggly.util;

import java.util.Scanner;

/**
 * A class representation of the user interface for Wiggly
 */
public class Ui {

	public static final String END_LINE = "_______________________________________________________________________";
	public static final String GREETING = "Hello! I'm Wiggly\n" +
	                                       "What can I do for you?";
	public static final String TASK_HEADER = "Here are the tasks in your list:";
	public static final String EXIT = "Bye. Hope to see you again soon!";

	private final Scanner sc = new Scanner(System.in);

	public Ui() {

	}

	/**
	 * Prints out {@code END_LINE} first, followed by the specified string, and another {@code END_LINE}
	 * @param str The string to be wrapped by {@code END_LINE}
	 */
	public void printWrappedString(String str) {
		System.out.println(END_LINE + "\n" + str +"\n" + END_LINE);
	}

	/**
	 * Prints out {@code GREETING} wrapped with {@code END_LINE}
	 */
	public void printGreeting() {
		printWrappedString(GREETING);
	}

	/**
	 * Prints out the specified string in an error message format
	 * @param error The error message to format
	 */
	public void showError(String error) {
		printWrappedString(error);
	}

	/**
	 * Prints out {@code EXIT} wrapped with {@code END_LINE}
	 */
	public void printExit() {
		printWrappedString(EXIT);
	}

	/**
	 * Waits for the user input until next line, then returns the entire line
	 * @return The raw user input until the next line termination
	 */
	public String readCommand() {
		return sc.nextLine();
	}

}
