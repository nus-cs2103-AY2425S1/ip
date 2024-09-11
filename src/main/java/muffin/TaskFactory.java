package muffin;

/**
 * A factory class responsible for creating different types of tasks (Todo, Deadline, Event)
 * based on the provided input array. This class encapsulates the logic of interpreting
 * task details and setting their completion status.
 */
public class TaskFactory {

    /**
     * Creates a Task object based on the provided array of parts.
     * The first element of the array determines the type of task to create:
     *
     * @param parts An array of Strings representing the task details.
     * @return A Task object.
     * @throws MuffinException If the task type is unknown or the parts array is insufficient.
     */
    public static Task createTask(String[] parts) throws MuffinException {
        switch (parts[0]) {
        case "T":
            Todo t = new Todo(parts[2]);
            markStatus(parts[1], t);
            return t;

        case "D":
            Deadline d = new Deadline(parts[2], parts[3]);
            markStatus(parts[1], d);
            return d;

        case "E":
            Event e = new Event(parts[2], parts[3], parts[4]);
            markStatus(parts[1], e);
            return e;

        default:
            throw new MuffinException("Unknown task type: " + parts[0]);
        }
    }

    /**
     * Marks the task as done or not based on the provided status.
     *
     * @param input A string representing the status of the task. "1" means the task is done, and "0" means it's not.
     * @param task The task to update the status of.
     */
    public static void markStatus(String input, Task task) {
        if (input.equals("1")) {
            task.isDone = true;
        }
    }
}
