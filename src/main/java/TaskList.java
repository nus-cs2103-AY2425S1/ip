import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(Storage storage) {
        this.tasks = storage.load();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTodoTask(String[] split) throws SlothingWafflerException {
        if (split.length < 2 || split[1].isBlank()) {
            throw new SlothingWafflerException("HEY!! The description of a Todo Task cannot be empty!");
        }
        tasks.add(new Todo(split[1]));
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
    }

    public void displayTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public void markTask(int taskNum) {
        tasks.get(taskNum).markAsDone();
    }

    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }
}
