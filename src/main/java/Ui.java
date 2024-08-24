import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
	private static final String LINE = "___________________________________________";
	private static final String NAME = "Hana";
	private Scanner scanner;

	public Ui() {
		scanner = new Scanner(System.in);
	}

	public String readCommand() {
		return scanner.nextLine();
	}

	public void printLine() {
		System.out.println(LINE);
	}

	public void printGreetings() {
		printLine();
		System.out.println(" Hello! I'm " + NAME);
		System.out.println(" What can I do for you?");
		printLine();
	}

	public void printBye() {
		printLine();
		System.out.println(" Bye. Hope to see you again soon!");
		printLine();
	}

	public void printError(String message) {
		printLine();
		System.out.println(message);
		printLine();
	}

	public void printAdd(Task task, int taskCount) {
		printLine();
		System.out.println("Got it. I've added this task:");
		System.out.println("    " + task);
		System.out.println("Now you have " + taskCount + " tasks in the list.");
		printLine();
	}

	public void printMarked(Task task, boolean isDone) {
		printLine();
		if (isDone) {
			System.out.println("Nice! I've marked this task as done:");
		} else {
			System.out.println("OK, I've marked this task as not done yet:");
		}
		System.out.println("  " + task);
		printLine();
	}

	public void printDeleted(Task task, int taskCount) {
		printLine();
		System.out.println("Noted. I've removed this task:");
		System.out.println("    " + task);
		System.out.println("Now you have " + taskCount + " tasks in the list.");
		printLine();
	}

	public void printTasks(ArrayList<Task> tasks) {
		printLine();
		for (int i = 0; i < tasks.size(); i++) {
			System.out.println((i + 1) + ". " + tasks.get(i));
		}
		printLine();
	}

	public void printCommands() {
		printLine();
		System.out.println("""
							Here are some examples of what you can do:
							1. List all tasks: list
							2. Mark a task as done: mark [task number]
							3. Unmark a task: unmark [task number]
							4. Add a todo: todo [description]
							5. Add a deadline: deadline [description] /by [d/M/yyyy HHmm]
							6. Add an event: event [description] /from [d/M/yyyy HHmm] /to [d/M/yyyy HHmm]
							7. Delete a task: delete [task number]
							8. Find By Date: findByDate [d/M/yyyy]""");
		printLine();
	}

	public void printMessage(String message) {
		System.out.println(message);
	}
}