public class Parser {
    public boolean isExit(String command) {
        return command.equalsIgnoreCase("bye");
    }

    public boolean isList(String command) {
        return command.equalsIgnoreCase("list");
    }

    public boolean isMark(String command) {
        if (command.length() >= 4) {
            return command.substring(0, 4).equalsIgnoreCase("mark");
        }
        return false; // If the command is shorter than 4 characters, it cannot be "mark"
    }

    public int getTaskNumber(String command) throws JarException {
        String[] parts = command.split(" ");
        if (parts.length == 2) {
            try {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber < 0) {
                    throw new JarException("Task number must be greater than 0.");
                }
                return taskNumber;
            } catch (NumberFormatException e) {
                throw new JarException("Invalid task number format.");
            }
        } else {
            throw new JarException("Invalid task command format.");
        }
    }

    public boolean isUnmarked(String command) {
        if (command.length() >= 6) {
            return command.substring(0, 6).equalsIgnoreCase("unmark");
        }
        return false; // If the command is shorter than 6 characters, it cannot be "unmark"
    }

    public Task parseTask(String command) throws JarException {
        try {
            if (command.startsWith("todo")) {
                String description = command.substring(5).trim();
                return new ToDo(description);
            } else if (command.startsWith("deadline")) {
                String[] parts = command.substring(9).split("/by");
                String description = parts[0].trim();
                String by = parts[1].trim();
                return new Deadline(description, by);
            } else if (command.startsWith("event")) {
                String[] parts = command.substring(6).split("/from");
                String description = parts[0].trim();
                String[] timeParts = parts[1].split("/to");
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();
                return new Event(description, from, to);
            } else {
                throw new JarException("what u talking about?????"); //Unknown command
            }
        } catch (Exception e) {
            throw new JarException("what u talking about?????");
        }
    }

    public boolean handleCommand(String command, TaskList taskList, Ui ui) throws JarException {
        if (isExit(command)) {
            ui.showGoodbye();
            return false;  // Stop bot
        } else if (isList(command)) {
            ui.showTaskList(taskList.listTasks());
        } else if (isMark(command)) {
            int number = getTaskNumber(command);
            Task task = taskList.getTask(number);
            if (task != null) {
                taskList.markTaskAsDone(number);
                ui.showTaskMarked(task);
            } else {
                throw new JarException("Invalid task number.");
            }
        } else if (isUnmarked(command)) {
            int number = getTaskNumber(command);
            Task task = taskList.getTask(number);
            if (task != null) {
                taskList.markTaskAsUndone(number);
                ui.showTaskUnmarked(task);
            } else {
                throw new JarException("Invalid task number.");
            }
        } else {
            Task task = parseTask(command);
            taskList.addTask(task);
            ui.showTaskAdded(task.toString());
            ui.showTaskCount(taskList.getTaskCount());
        }
        return true;  // Continue bot
    }
}
