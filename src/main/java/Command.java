import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws HueException, IOException;

    public boolean isExit() {
        return false;
    }
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}

class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String fullCommand) throws HueException {
        String[] parts = fullCommand.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HueException("The descriptipn of a todo cannot be empty.");
        }
        this.description = parts[1].trim();
    }
    @Override
    public void execute (TaskList tasks, UI ui, Storage storage) throws IOException{
        Task newTask = new Todo(description);

        tasks.add(newTask);

        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        storage.saveTasks(tasks);

    }
}

class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String fullCommand) throws HueException {
        try {
            String[] parts = fullCommand.split("/by");
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new HueException("The description and deadline of a task cannot be empty.");
            }
            this.description = parts[0].substring(9).trim();
            this.by = parts[1].trim();
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new HueException("Please provide both a description and a deadline for the task");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        Task newTask = new Deadline(description, by);
        tasks.add(newTask);

        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        storage.saveTasks(tasks);  // Save the updated task list
    }
}

class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String fullCommand) throws HueException {
        try {
            String[] parts = fullCommand.split("/from|/to");
            if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                throw new HueException("The description, start time and end time cannot be empty.");
            }
            this.description = parts[0].substring(6).trim();
            this.from = parts[1].trim();
            this.to = parts[2].trim();
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new HueException("Pleae provide a description, start time and end time for the event.");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        Task newTask = new Event(description, from, to);
        tasks.add(newTask);

        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        storage.saveTasks(tasks);
    }


}

class MarkCommand extends Command {
    public final int taskIndex;

    public MarkCommand(String fullCommand) throws HueException {
        try {
            this.taskIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new HueException("Please give a valid task number to mark.");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws HueException, IOException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.markDone();

            ui.showLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);

            storage.saveTasks(tasks);
        } else {
            throw new HueException("Task number is out of range.");
        }
    }
}

class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(String fullCommand) throws HueException {
        try {
            this.taskIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new HueException("Please give a valid task number to mark.");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException, HueException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.unmarkDone();

            ui.showLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);

            storage.saveTasks(tasks);
        } else {
            throw new HueException("Task number is out of range.");
        }
    }

}

class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(String fullCommand) throws HueException {
        try {
            this.taskIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new HueException("Please provide a valid task number to delete");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException, HueException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task removedTask = tasks.remove(taskIndex);
            ui.showLine();
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");

            storage.saveTasks(tasks);
        } else {
            throw new HueException("Task number is out of range.");
        }
    }
}
