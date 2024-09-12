package features.command;

import features.task.DeadlineTask;
import features.task.EventTask;
import features.task.TaskManagement;
import features.task.TodoTask;
import features.task.Task;
import utils.Utils;
import config.Config;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Handles user commands related to task management.
 */
public class CommandHandler {
	private final TaskManagement tm;

	/**
	 * Constructs a CommandHandler with the specified TaskManagement instance.
	 *
	 * @param tm the TaskManagement instance used to manage tasks
	 */
	public CommandHandler(TaskManagement tm) {
		this.tm = tm;
	}

	/**
	 * Processes the given command and executes the appropriate action.
	 *
	 * @param cmd the command to be processed
	 */
	public String handleCommand(Command cmd) {
		String command = cmd.getName();
		String res;
		try {
			if (command.equals(CommandType.LIST.getType())) {
				res = handleList();
			} else if (command.startsWith(CommandType.MARK.getType())
					|| command.startsWith(CommandType.UNMARK.getType())) {
				res = handleMark(command);
			} else if (command.startsWith(CommandType.TODO.getType()) ||
					command.startsWith(CommandType.DEADLINE.getType()) ||
					command.startsWith(CommandType.EVENT.getType())) {
				res = handleAddTask(command);
			} else if (command.startsWith(CommandType.DELETE.getType())) {
				res = handleDeleteTask(command);
			} else if (command.startsWith(CommandType.FIND.getType())) {
				res = handleFind(command);
			} else {
				throw new Exception("Unknown message :(. Please see below for the list of available commands:\n\n" + Config.commands);
			}
		} catch (Exception ex) {
			res = ex.getMessage();
		}

		return res;

	}

	/**
	 * Handles the 'list' command by printing all tasks.
	 */
	private String handleList() {
		StringBuilder s = new StringBuilder();
		s.append("Here are the tasks in your list:");
		s.append("\n").append(tm.getPrintTasks());
		Utils.printItem(s.toString());
		return s.toString();
	}

	/**
	 * Handles the 'mark' or 'unmark' command by marking or unmarking a task.
	 *
	 * @param command the command string containing the action and task ID
	 * @throws Exception if the command is invalid or the task ID is not found
	 */
	private String handleMark(String command) throws Exception {
		String[] parts = command.split(" ");
		if (parts.length != 2) {
			throw new Exception("Invalid command. Usage: mark/unmark <id>");
		}

		String action = parts[0];
		int id = Integer.parseInt(parts[1]);
		tm.handleItem(action, id);

		StringBuilder res = new StringBuilder();
		if (action.equals("mark")) {
			res.append("Nice! I've marked this task as done:");
		} else if (action.equals("unmark")) {
			res.append("OK, I've marked this task as not done yet:");
		}
		tm.findTaskById(id).ifPresent(t -> res.append("\n" + Config.INDENTATION + "  ").append(t));
		Utils.printItem(res.toString());
		return res.toString();
	}

	/**
	 * Handles the 'delete' command by removing a task.
	 *
	 * @param command the command string containing the task ID
	 * @throws Exception if the command is invalid or the task ID is not found
	 */
	private String handleDeleteTask(String command) throws Exception {
		String[] parts = command.split(" ");
		if (parts.length != 2) {
			throw new Exception("Invalid command. Usage: delete <id>");
		}

		int id = Integer.parseInt(parts[1]);
		Task t = tm.remove(id);
		return printAfterEditList("Noted. I've removed this task:", t);
	}

	/**
	 * Handles the 'find' command to search for tasks containing a specific query in their name.
	 * If the query is found in the task name, it adds the task to the result list and prints
	 * the matching tasks. Throws an exception if the command syntax is incorrect.
	 *
	 * @param command The full command string input by the user, including the find command and query.
	 * @throws Exception If the command is improperly formatted or lacks a search query.
	 */
	private String handleFind(String command) throws Exception {
		String[] parts = command.split(" ");
		if (parts.length <= 1) {
			throw new Exception("Invalid command. Usage: find <query>.");
		}

		List<Task> result = new ArrayList<>();
		String query = parts[1];
		for (Task t : tm.getAllTasks()) {
			if (t.getName().contains(query)) {
				result.add(t);
			}
		}

		String res = tm.getPrintTasks(result);
		return "Matching tasks in your list: \n" + res;
	}

	/**
	 * Handles the 'todo', 'deadline', or 'event' command by adding a new task.
	 *
	 * @param task the command string containing the task details
	 * @throws Exception if the command is invalid or the task details are incomplete
	 */
	private String handleAddTask(String task) throws Exception {
		String[] parts = task.split(" ");
		String type = parts[0];

		Task t;

		switch (type) {
			case "todo" -> {
				String taskDescription = Arrays.stream(parts)
						.skip(1)
						.collect(Collectors.joining(" "));
				if (taskDescription.equals("")) {
					throw new Exception("Invalid command. Usage: todo <description>.");
				}
				t = new TodoTask(taskDescription);

			}
			case "deadline" -> {
				int index = Arrays.asList(parts).indexOf("/by");

				String taskDescription = Arrays.stream(parts)
						.skip(1)
						.limit(index - 1)
						.collect(Collectors.joining(" "));

				String deadline = Arrays.stream(parts)
						.skip(index + 1)
						.collect(Collectors.joining(" "));

				if (taskDescription.equals("") || deadline.equals("")) {
					throw new Exception("Invalid command. Usage: deadline <description> /by <deadline>.");
				}

				t = new DeadlineTask(taskDescription, deadline);
			}
			case "event" -> {
				int indexFrom = Arrays.asList(parts).indexOf("/from");
				int indexTo = Arrays.asList(parts).indexOf("/to");

				String taskDescription = Arrays.stream(parts)
						.skip(1)
						.limit(indexFrom - 1)
						.collect(Collectors.joining(" "));

				String from = Arrays.stream(parts)
						.skip(indexFrom + 1)
						.limit(indexTo - indexFrom - 1)
						.collect(Collectors.joining(" "));

				String to = Arrays.stream(parts)
						.skip(indexTo + 1)
						.collect(Collectors.joining(" "));

				if (taskDescription.equals("") || from.equals("") || to.equals("")) {
					throw new Exception("Invalid command. Usage: event <description> /from <start> /to <end>.");
				}

				t = new EventTask(taskDescription, from, to);
			}
			default ->
				// not possible to reach here
					throw new Exception("Invalid task type.");
		}

		tm.add(t);
		return printAfterEditList("Got it. I've added the following task:", t);
	}

	/**
	 * Prints a message after a task is added or removed, and shows the updated list of tasks.
	 *
	 * @param message the message to display
	 * @param t the task that was added or removed
	 */
	private String printAfterEditList(String message, Task t) {
		StringBuilder res = new StringBuilder();
		res.append(message);
		res.append("\n  " + Config.INDENTATION).append(t.toString());
		String taskString = tm.length == 1 ? "task" : "tasks";
		res.append("\n" + Config.INDENTATION + "Now you have ").append(tm.length).append(" ").append(taskString).append(" in the list.");

		Utils.printItem(res.toString());

		return res.toString();
	}
}

/**
 * Gets the command type as a string.
 *
 */
enum CommandType {
	TODO("todo"),
	DEADLINE("deadline"),
	EVENT("event"),
	LIST("list"),
	MARK("mark"),
	UNMARK("unmark"),
	DELETE("delete"),
	FIND("find");

	private final String cmd;

	CommandType(String cmd) {
		this.cmd = cmd;
	}

	public String getType() {
		return cmd;
	}
}