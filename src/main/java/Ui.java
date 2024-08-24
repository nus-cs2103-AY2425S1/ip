public class Ui {

	private static final String END_LINE = "_______________________________________________________________________";
	private static final String GREETING = "Hello! I'm Wiggly\n" +
	                                       "What can I do for you?";
	private static final String TASK_HEADER = "Here are the tasks in your list:";
	private static final String EXIT = "Bye. Hope to see you again soon!";


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

	public void printTaskList(TaskList taskList) {
		String str = TASK_HEADER + "\n" +
		            taskList.toString();
		printWrappedString(str);
	}

	public void printExit() {
		printWrappedString(EXIT);
	}

}
