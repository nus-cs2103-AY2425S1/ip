import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(Storage storage) {
        this.tasks = storage.load();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTaskMessage(ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addTodoTask(String[] split) throws SlothingWafflerException {
        if (split.length < 2 || split[1].isBlank()) {
            throw new SlothingWafflerException("HEY!! The description of a Todo Task cannot be empty!");
        }
        tasks.add(new Todo(split[1]));
        addTaskMessage(tasks);
    }

    public void addEventTask(String[] split) throws SlothingWafflerException {
        if (split.length < 2 || split[1].isBlank()) {
            throw new SlothingWafflerException("HEY!! The description of an Event Task cannot be empty.");
        }
        String[] desc = split[1].split(" /from | /to ");
        if (desc.length < 3) {
            throw new SlothingWafflerException("HEY!! An event must have a description, start time, and end time.");
        }
        tasks.add(new Event(desc[0], desc[1], desc[2]));
        addTaskMessage(tasks);
    }

    public void addDeadlineTask(String[] split) throws SlothingWafflerException {
        if (split.length < 2 || split[1].isBlank()) {
            throw new SlothingWafflerException("HEY!! The description of a Deadline Task cannot be empty.");
        }
        String[] desc = split[1].split(" /by ", 2);
        if (desc.length < 2) {
            throw new SlothingWafflerException("HEY!! The Deadline Task must have a description AND a due date.");
        }
        tasks.add(new Deadline(desc[0], desc[1]));
        addTaskMessage(tasks);
    }

    public void displayTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public void markTask(int taskNum) {
        tasks.get(taskNum).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskNum).toString());
    }

    public void deleteTask(int taskNum) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(taskNum));
        tasks.remove(taskNum);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
