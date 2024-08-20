import java.util.*;
import java.util.stream.Collectors;

public class CommandHandler {
    private TaskManagement tm;

    public CommandHandler(TaskManagement tm) {
        this.tm = tm;
    }

    public void handleCommand(String command) {
        if (command.equals("list")) {
            handleList();
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            handleMark(command);
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            handleAddTask(command);
        }
    }

    private void handleList() {
        StringBuilder s = new StringBuilder();
        s.append("Here are the tasks in your list:");
        s.append("\n" + tm.getPrintTasks());
        Utils.printItem(s.toString());
    }

    private void handleMark(String command) {
        String[] parts = command.split(" ");
        if (parts.length != 2) {
            Utils.printItem("Invalid command. Usage: mark/unmark [id]");
            return;
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

    private void handleAddTask(String task) {
		String[] parts = task.split(" ");
		String type = parts[0];
		
		Task t = new Task("");

		if (type.equals("todo")) {
			String taskDescription = Arrays.stream(parts)
										.skip(1)
										.collect(Collectors.joining(" "));
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

			t = new EventTask(taskDescription, from, to);	
		} else {
			return;
		}

		tm.add(t);
		StringBuilder res = new StringBuilder();
		res.append("Got it. I've added this task:");
		res.append("\n  " + Config.INDENTATION + t.toString());
		String taskString = tm.length == 1 ? "task" : "tasks";
		res.append("\n" + Config.INDENTATION + "Now you have " + tm.length + " " + taskString + " in the list.");

		Utils.printItem(res.toString());
    }
}	
