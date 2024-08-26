package ip.derrick ;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An ArrayList of tasks currently in the list.
     */
    public ArrayList<Task> output() {
        return this.tasks;
    }

    /**
     * Prints out all the tasks in the list. If the list is empty,
     * it notifies the user that there are no tasks in the list.
     */
    public void list() {
        if (this.tasks.isEmpty()) {
            System.out.println("You have nothing in your list.");
        } else {
            System.out.println(("Here are the items in your list:"));
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Deletes a task from the list based on the position provided in the input string.
     *
     * @param input The input string containing the position of the task to be deleted.
     * @throws MissingPositionException If the position is missing or not an integer.
     * @throws MissingItemException If the position does not correspond to an existing task.
     * @throws EmptyListException If the task list is empty.
     */
    public void delete(String input) throws MissingPositionException, MissingItemException, EmptyListException {
        int position;
        try {
            position = Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingPositionException("You are missing a specific position. Please try again.");
        } catch (NumberFormatException e) {
            throw new MissingPositionException("Position must be an integer. Please try again.");
        }

        if (this.tasks.isEmpty()) {
            throw new EmptyListException("You are trying to delete from an empty list.");
        } else if (position <= 0 || position > this.tasks.size()) {
            throw new MissingItemException("Item does not exist in the list. Please try again.");
        }
        Task task = this.tasks.get(position - 1);
        this.tasks.remove(task);
        System.out.println("I have removed this task:");
        System.out.println(task);
        System.out.println("You have " + this.tasks.size() + " items in your list.");
    }

    /**
     * Adds a new todo task to the list based on the description provided in the input string.
     *
     * @param input The input string containing the description of the todo task.
     * @throws InvalidDescriptionException If the description for the todo task is missing or invalid.
     */
    public void addTodo(String input) throws InvalidDescriptionException {
        Todo todo;
        try {
            todo = new Todo(input.split(" ", 2)[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You have not added any description for the todo task. "
                    + "Please try again.");
        }
        this.tasks.add(todo);
        System.out.println("Got it. I have added this todo.");
        System.out.println(todo);
        System.out.println("You have " + this.tasks.size() + " items in your list.");

    }

    /**
     * Adds a new deadline task to the list based on the description and deadline time provided in the input string.
     *
     * @param input The input string containing the description and deadline time for the deadline task.
     * @throws InvalidDescriptionException If the description or deadline time is missing or invalid.
     */
    public void addDeadline(String input) throws InvalidDescriptionException {
        String time;
        String description;

        try {
            time = input.split("/by")[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You have not added any deadline for the todo task. "
                    + "Please try again.");
        }

        try {
            description = input.split("/by")[0].split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You have not added any description for the todo task. "
                    + "Please try again.");
        }

        Deadline deadline = new Deadline(description, time);
        this.tasks.add(deadline);
        System.out.println("Got it. I have added this deadline.");
        System.out.println(deadline);
        System.out.println("You have " + this.tasks.size() + " items in your list");
    }

    /**
     * Adds a new event task to the list based on the description, start time, and end time provided in the input string.
     *
     * @param input The input string containing the description, start time, and end time for the event task.
     * @throws InvalidDescriptionException If the description, start time, or end time is missing or invalid.
     */
    public void addEvent(String input) throws InvalidDescriptionException {
        String start;
        String end;
        String description;

        try {
            description = input.split("/from")[0].split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You are missing the description for the event. Please try again.");
        }

        try {
            start = input.split("/from")[1].split("/to")[0];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You are missing the start time for the event. Please try again.");
        }

        try {
            end = input.split("/from")[1].split("/to")[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You are missing the end time for the event. Please try again.");
        }

        Event event = new Event(description, start, end);
        this.tasks.add(event);
        System.out.println("Got it. I have added this event.");
        System.out.println(event);
        System.out.println("You have " + this.tasks.size() + " items in your list.");
    }

    /**
     * Marks a task as completed based on the position provided in the input string.
     *
     * @param input The input string containing the position of the task to be marked as completed.
     * @throws MissingPositionException If the position is missing or not an integer.
     * @throws MissingItemException If the position does not correspond to an existing task.
     */
    public void markItem(String input) throws MissingPositionException, MissingItemException {
        int position;
        try {
            position = Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingPositionException("You are missing a specific position. Please try again.");
        } catch (NumberFormatException e) {
            throw new MissingPositionException("Position must be an integer. Please try again.");
        }

        if (position <= 0 || position > this.tasks.size()) {
            throw new MissingItemException("Item does not exist in the list. Please try again.");
        }

        Task task = this.tasks.get(position - 1);
        task.setMark();
        System.out.println("I have marked this task as done!");
        System.out.println(task);
    }

    /**
     * Unmarks a task as incomplete based on the position provided in the input string.
     *
     * @param input The input string containing the position of the task to be unmarked.
     * @throws MissingPositionException If the position is missing or not an integer.
     * @throws MissingItemException If the position does not correspond to an existing task.
     */
    public void unmarkItem(String input) throws MissingPositionException, MissingItemException {
        int position;
        try {
            position = Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingPositionException("You are missing a specific position. Please try again.");
        } catch (NumberFormatException e) {
            throw new MissingPositionException("Position must be an integer. Please try again.");
        }

        if (position <= 0 || position > this.tasks.size()) {
            throw new MissingItemException("Item does not exist in the list. Please try again.");
        }

        Task task = this.tasks.get(position - 1);
        task.setUnmark();;
        System.out.println("I have marked this task as not done yet!");
        System.out.println(task);
    }

    public void findItem(String input) throws MissingPositionException {
        String keyword;
        try {
            String[] parts = input.split(" ", 2);
            if (parts.length < 2 || parts[1].isBlank()) {
                throw new MissingPositionException("You are missing the keyword to be searched. Please try again.");
            }
            keyword = parts[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingPositionException("You are missing the keyword to be searched. Please try again.");
        }

        ArrayList<Task> results = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword)) {
                results.add(task);
            }
        }

        if (results.isEmpty()) {
            System.out.println("Nothing matches your keyword.");
        } else {
            System.out.println("Here are the items that match your keyword:");
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        }
    }

}
