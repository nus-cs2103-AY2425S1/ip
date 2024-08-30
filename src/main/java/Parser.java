public class Parser {

    public boolean parseCommand(String command, TaskList tasks, Ui ui) {
        try {
            if (command.equalsIgnoreCase("list")) {
                if (tasks.isEmpty()) {
                    ui.showListEmpty();
                } else {
                    ui.showTasks(tasks.getList());
                }
            } else if (command.equalsIgnoreCase("Bye")) {
                return true;
            } else if (command.startsWith("mark")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                Task task = tasks.get(taskNumber - 1);
                task.markAsDone(true);
                ui.showTaskMarked(task);
            } else if (command.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                Task task = tasks.get(taskNumber - 1);
                task.markAsDone(false);
                ui.showTaskMarked(task);
            } else if (command.startsWith("delete")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                Task task = tasks.get(taskNumber - 1);
                tasks.remove(task);
                ui.showTaskRemoved(task, tasks.size());
            } else if (command.startsWith("todo ")) {
                String description = command.substring(5);
                if (description.isEmpty()) {
                    throw new PixyExceptions("OOPS!!! The description of a todo cannot be empty.");
                }
                Task todo = new ToDos(description);
                tasks.add(todo);
                ui.showTaskAdded(todo, tasks.size());
            } else if (command.startsWith("deadline ")) {
                String[] parts = command.substring(9).split(" /by");
                if (parts.length != 2) {
                    throw new PixyExceptions("OOPS!!! The description of a deadline is not in the correct format.");
                }
                Task deadline = new Deadlines(parts[0], parts[1]);
                tasks.add(deadline);
                ui.showTaskAdded(deadline, tasks.size());
            } else if (command.startsWith("event ")) {
                String[] parts = command.substring(6).split(" /from | /to");
                if (parts.length != 3) {
                    throw new PixyExceptions("OOPS!!! The description of an event is not in the correct format.");
                }
                Task event = new Event(parts[0], parts[1], parts[2]);
                tasks.add(event);
                ui.showTaskAdded(event, tasks.size());
            } else {
                throw new PixyExceptions("OOPS!!! I'm sorry, I don't understand the command.");
            }
        } catch (NumberFormatException e) {
            ui.showError("OOPS!!! Please provide a valid number for the task.");
        } catch (PixyExceptions e) {
            ui.showError(e.getMessage());
        }
        return false; // Continue running the application
    }
}
