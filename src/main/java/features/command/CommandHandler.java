package features.command;

import features.task.DeadlineTask;
import features.task.EventTask;
import features.task.TaskManagement;
import features.task.TodoTask;
import features.task.Task;
import utils.Utils;
import config.Config;

import java.util.*;
import java.util.stream.Collectors;

public class CommandHandler {
    private TaskManagement tm;

    public CommandHandler(TaskManagement tm) {
        this.tm = tm;
    }

    public void handleCommand(Command cmd) {
		String command = cmd.getName();
        try {
			if (command.equals(CommandType.LIST.getType())) {
				handleList();
			} else if (command.startsWith(CommandType.MARK.getType()) 
					|| command.startsWith(CommandType.UNMARK.getType())) {
				handleMark(command);
			} else if (command.startsWith(CommandType.TODO.getType()) || 
						command.startsWith(CommandType.DEADLINE.getType()) || 
						command.startsWith(CommandType.EVENT.getType())) {
				handleAddTask(command);
			} else if (command.startsWith(CommandType.DELETE.getType())) {
				handleDeleteTask(command);
			} else {
				throw new Exception("Unknown message :(. Please see below for the list of available commands:\n\n" + Config.commands);
			}
		} catch (Exception ex) {
			Utils.printItem(ex.getMessage());
		}
    }

    private void handleList() {
        StringBuilder s = new StringBuilder();
        s.append("Here are the tasks in your list:");
        s.append("\n" + tm.getPrintTasks());
        Utils.printItem(s.toString());
    }

    private void handleMark(String command) throws Exception {
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
        tm.findTaskById(id).ifPresent(t -> res.append("\n" + Config.INDENTATION + "  " + t.toString()));
        Utils.printItem(res.toString());
    }

	private void handleDeleteTask(String command) throws Exception {
		String[] parts = command.split(" ");
		if (parts.length != 2) {
			throw new Exception("Invalid command. Usage: delete <id>");
		}

		int id = Integer.parseInt(parts[1]);
		Task t = tm.remove(id);
		printAfterEditList("Noted. I've removed this task:", t);
	}

    private void handleAddTask(String task) throws Exception {
		String[] parts = task.split(" ");
		String type = parts[0];
		
		Task t = new Task("");

		if (type.equals("todo")) {
			String taskDescription = Arrays.stream(parts)
										.skip(1)
										.collect(Collectors.joining(" "));
			if (taskDescription.equals("")) {	
				throw new Exception("Invalid command. Usage: todo <description>.");
			}
			t = new TodoTask(taskDescription);
			
		} else if (type.equals("deadline")) {
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

		} else if (type.equals("event")) {
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
		} else {
			// not possible to reach here
			throw new Exception("Invalid task type.");
		}

		tm.add(t);
	    printAfterEditList("Got it. I've added the following task:", t);
    }
	
	private void printAfterEditList(String message, Task t) {
		StringBuilder res = new StringBuilder();
        res.append(message);
		res.append("\n  " + Config.INDENTATION + t.toString());
		String taskString = tm.length == 1 ? "task" : "tasks";
		res.append("\n" + Config.INDENTATION + "Now you have " + tm.length + " " + taskString + " in the list.");

        Utils.printItem(res.toString());

	}
	
}

enum CommandType {
	TODO("todo"),
	DEADLINE("deadline"),
	EVENT("event"),
	LIST("list"),
	EXIT("bye"),
	MARK("mark"),
	UNMARK("unmark"),
	DELETE("delete");

	private final String cmd;

	CommandType(String cmd) {
		this.cmd = cmd;
	}

	public String getType() {
		return cmd;
	}
}
