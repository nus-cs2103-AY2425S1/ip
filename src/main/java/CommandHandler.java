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
        } else {
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

    private void handleAddTask(String taskDescription) {
        tm.add(new Task(taskDescription));
        Utils.printItem("Added: " + taskDescription);
    }
}

