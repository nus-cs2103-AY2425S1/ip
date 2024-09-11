package snowy;

import java.util.ArrayList;

/**
 * Represents a list of Tasks with the methods the add, remove and modify the tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Creates a TaskList with tasks strings that are previously saved in a file.
     * @param lines the ArrayList with each element containing the task as a string.
     */
    public TaskList(ArrayList<String> lines) {
        for (String line : lines) {
            initializeTask(line);
        }
    }

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {

    }

    private void initializeTask(String description) {
        String status;
        String name;

        String[] dataArray = description.split("[|]");
        String type = dataArray[0];

        switch (type) {
        case "T":
            status = dataArray[1];
            name = dataArray[2];
            Task newToDo = new ToDo(name);
            tasks.add(newToDo);
            if (status.equals("1")) {
                newToDo.markComplete();
            }
            break;

        case "D":
            status = dataArray[1];
            name = dataArray[2];
            String dueDate = dataArray[3];
            Task newDateline = new Deadline(name, dueDate);
            tasks.add(newDateline);
            if (status.equals("1")) {
                newDateline.markComplete();
            }
            break;

        case "E":
            status = dataArray[1];
            name = dataArray[2];
            String fromDate = dataArray[3];
            String toDate = dataArray[4];
            Task newEvent = new Event(name, fromDate, toDate);
            tasks.add(newEvent);
            if (status.equals("1")) {
                newEvent.markComplete();
            }
            break;

        default:
            System.out.println("Error: task type not found");
        }
    }

    /**
     * Adds a ToDo task with the given description.
     * @param description the description of the task.
     * @throws SnowyException if the description given is empty.
     */
    public Task addToDo(String description) throws SnowyException {
        if (description.isEmpty()) {
            throw new SnowyException("Invalid input for Todo");
        }
        Task newTask = new ToDo(description);
        tasks.add(newTask);
        return newTask;

    }


    /**
     * Adds a deadline task with the given description.
     * @param description the description of the deadline task.
     * @throws SnowyException if the description is empty or invalid.
     */
    public Task addDeadline(String description) throws SnowyException {
        int byIndex = description.indexOf("/by ");

        if (byIndex == -1) {
            throw new SnowyException("Invalid input for Deadline");
        }

        String deadlineName = description.substring(0, byIndex).trim();
        String date = description.substring(byIndex + 4).trim();

        boolean isInvalidInput = deadlineName.isEmpty() || date.isEmpty();

        if (isInvalidInput) {
            throw new SnowyException("Invalid input for Deadline");
        }

        Task newTask = new Deadline(deadlineName, date);
        tasks.add(newTask);
        return newTask;
    }


    /**
     * Adds an Event Task with the given description.
     * @param description the description of the event.
     * @throws SnowyException if the description is empty or invalid.
     */
    public Task addEvent(String description) throws SnowyException {
        int fromIndex = description.indexOf("/from ");
        int toIndex = description.indexOf("/to ");

        if (toIndex == -1 || fromIndex == -1) {
            throw new SnowyException("Invalid input for Event");
        }

        String eventName = description.substring(0, fromIndex);
        String fromDate = description.substring(fromIndex + 6, toIndex).trim();
        String toDate = description.substring(toIndex + 4);

        boolean isInvalidInput = eventName.isEmpty() || fromDate.isEmpty() || toDate.isEmpty();

        if (isInvalidInput) {
            throw new SnowyException("Invalid input for Event");
        }

        Task newTask = new Event(eventName, fromDate, toDate);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Deletes the task with the current index.
     * @param index the index of the task to be deleted.
     * @return the Task that was deleted.
     * @throws SnowyException if the given index is invalid.
     */
    public Task deleteTask(int index) throws SnowyException {
        if (index < 1 || index > tasks.size()) {
            throw new SnowyException("Invalid index input");
        }
        return tasks.remove(index - 1);
    }


    /**
     * Marks the task with the given index as complete.
     * @param index the index of the task.
     * @return the Task after it is marked as complete.
     * @throws SnowyException if the given index is invalid.
     */
    public Task markTask(int index) throws SnowyException {
        if (index < 1 || index > tasks.size()) {
            throw new SnowyException("Invalid index input");
        }
        tasks.get(index - 1).markComplete();
        return tasks.get(index - 1);
    }


    /**
     * Marks the task with the given index as incomplete.
     * @param index the index of the task.
     * @return the Task after it is marked as incomplete.
     * @throws SnowyException if the given index is invalid.
     */
    public Task unmarkTask(int index) throws SnowyException {
        try {
            tasks.get(index - 1).markIncomplete();
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new SnowyException("Invalid index input");
        }
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String temp = String.format("%d. %s\n", i + 1, tasks.get(i));
            text.append(temp);
        }
        return text.toString();
    }


    /**
     * Converts all the tasks into the save string and separating each string to a new line.
     * @return The overall String to be saved.
     */
    public String toSaveString() {
        StringBuilder text = new StringBuilder();
        for (Task task : tasks) {
            text.append(task.toFileStorage()).append("\n");
        }
        return text.toString();
    }


    /**
     * Find all task which description matches the specifed string before returning them in an arrayList.
     * @param description The String to be matched.
     * @return An ArrayList of tasks which matches the string
     */
    public ArrayList<Task> findTask(String description) {
        int i = 0;
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().contains(description)) {
                result.add(task);
            }
        }
        return result;
    }

}
