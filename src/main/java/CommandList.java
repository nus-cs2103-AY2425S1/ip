public class CommandList {

    public static Command addTodo(String description) {
        return (tasks, ui, storage) -> {
            Task newTask = new Todo(description);
            tasks.add(newTask);
            ui.say(String.format(" Task added successfully!\n   %s\n Now you have %d tasks in the list",
                    newTask, tasks.count()));
            return false;
        };
    }

    public static Command addDeadline(String description, String deadline) {
        return (tasks, ui, storage) -> {
            Task newTask = new Deadline(description, DateTimeParser.parseDateTime(deadline));
            tasks.add(newTask);
            ui.say(String.format(" Task added successfully!\n   %s\n Now you have %d tasks in the list",
                    newTask, tasks.count()));
            return false;
        };
    }

    public static Command addEvent(String description, String start, String end) {
        return (tasks, ui, storage) -> {
            Task newTask = new Event(description,
                    DateTimeParser.parseDateTime(start),
                    DateTimeParser.parseDateTime(end));
            tasks.add(newTask);
            ui.say(String.format(" Task added successfully!\n   %s\n Now you have %d tasks in the list",
                    newTask, tasks.count()));
            return false;
        };
    }

    public static Command deleteTask(int taskNumber) {
        return (tasks, ui, storage) -> {
            try {
                Task deletedTask = tasks.remove(taskNumber);
                ui.say(String.format(" Task deleted successfully!\n   %s\n"
                        + "Now you have %d tasks in the list", deletedTask, tasks.count()));
            } catch (IndexOutOfBoundsException e) {
                String errorMessage = String.format(" There is no such task!\n "
                        + "You currently have %d tasks in your list", tasks.count());
                ui.say(errorMessage);
            }
            return false;
        };
    }

    public static Command listTasks() {
        return (tasks, ui, storage) -> {
            ui.say(tasks.toString());
            return false;
        };
    }

    public static Command markTask(int taskNumber) {
        return (tasks, ui, storage) -> {
            try {
                ui.say("Nice! I have marked this task as done:\n   "
                        + tasks.mark(taskNumber));
            } catch (IndexOutOfBoundsException e) {
                String errorMessage = String.format(" There is no such task!\n "
                        + "You currently have %d tasks in your list", tasks.count());
                ui.say(errorMessage);
            }
            return false;
        };
    }

    public static Command unmarkTask(int taskNumber) {
        return (tasks, ui, storage) -> {
            try {
                ui.say("OK, I've marked this task as not done yet:\n   "
                        + tasks.unmark(taskNumber));
            } catch (IndexOutOfBoundsException e) {
                String errorMessage = String.format(" There is no such task!\n "
                        + "You currently have %d tasks in your list", tasks.count());
                ui.say(errorMessage);
            }
            return false;
        };
    }

    public static Command bye() {
        return (tasks, ui, storage) -> true;
    }
}
