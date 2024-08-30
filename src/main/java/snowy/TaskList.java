package snowy;

import java.util.ArrayList;

/**
 * Represents a list of Tasks with the methods the add, remove and modify the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

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
    public TaskList(){

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
                try {
                    Task newDateline = new Deadline(name, dueDate);
                    tasks.add(newDateline);
                    if (status.equals("1")) {
                        newDateline.markComplete();
                    }
                } catch (SnowyException ignored) {

                }


                break;

            case "E":
                status = dataArray[1];
                name = dataArray[2];
                String fromDate = dataArray[3];
                String toDate = dataArray[4];
                try {
                    Task newEvent = new Event(name, fromDate, toDate);
                    tasks.add(newEvent);
                    if (status.equals("1")) {
                        newEvent.markComplete();
                    }
                } catch (SnowyException ignored) {

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
    public  void addToDo(String description) throws SnowyException{
        if (description.isEmpty()) {
            throw new SnowyException("Invalid input for Todo");
        }
        Task newTask = new ToDo(description);
        tasks.add(newTask);
        System.out.println("New todo task added:\n" + newTask);
    }


    /**
     * Adds a deadline task with the given description.
     * @param description the description of the deadline task.
     * @throws SnowyException if the description is empty or invalid.
     */
    public void addDeadline(String description) throws SnowyException{
        if (description.isEmpty()) {
            throw new SnowyException("Invalid input for Deadline");
        }

        int byIndex = description.indexOf("/by ");

        if (byIndex == -1) {
            throw new SnowyException("Invalid input for Deadline");
        }
        String deadlineName = description.substring(0, byIndex).trim();
        String date = description.substring(byIndex + 4);

        if (deadlineName.isEmpty()) {
            throw new SnowyException("Invalid input for Deadline");
        }

        if (date.isEmpty()) {
            throw new SnowyException("Invalid input for Deadline");
        }
        Task newTask = new Deadline(deadlineName, date);
        tasks.add(newTask);
        System.out.println("New Deadline task added:\n" + newTask);
    }


    /**
     * Adds an Event Task with the given description.
     * @param description the description of the event.
     * @throws SnowyException if the description is empty or invalid.
     */
    public void addEvent(String description) throws SnowyException{
        if (description.isEmpty()) {
            throw new SnowyException("Invalid input for Event");
        }
        int fromIndex = description.indexOf("/from ");
        int toIndex = description.indexOf("/to ");

        if (toIndex == -1 || fromIndex == -1) {
            throw new SnowyException("Invalid input for Event");
        }

        String eventName = description.substring(0, fromIndex);
        String fromDate = description.substring(fromIndex + 6, toIndex).trim();
        String toDate = description.substring(toIndex + 4);
        if (eventName.isEmpty()) {
            throw new SnowyException("Invalid input for Event");
        }
        if (fromDate.isEmpty()) {
            throw new SnowyException("Invalid input for Event");
        }
        if (toDate.isEmpty()) {
            throw new SnowyException("Invalid input for Event");
        }
        Task newTask = new Event(eventName, fromDate, toDate);
        tasks.add(newTask);
        System.out.println("New Event task added:\n " + newTask);
    }

    /**
     * Deletes the task with the current index.
     * @param index the index of the task to be deleted.
     * @return the Task that was deleted.
     * @throws SnowyException if the given index is invalid.
     */
    public Task deleteTask(int index) throws SnowyException {
        try {
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new SnowyException("Invalid index input");
        }
    }


    /**
     * Marks the task with the given index as complete.
     * @param index the index of the task.
     * @return the Task after it is marked as complete.
     * @throws SnowyException if the given index is invalid.
     */
    public Task markTask(int index) throws SnowyException {
        try {
            tasks.get(index - 1).markComplete();
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new SnowyException("Invalid index input");
        }
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

}
