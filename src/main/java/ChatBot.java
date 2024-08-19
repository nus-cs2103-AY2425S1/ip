import java.util.ArrayList;

public class ChatBot {
    /**
     * The list of tasks added.
     */
    ArrayList<Task> tasks;

    public ChatBot() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Get the greeting message.
     * @return the greeting message
     */
    public String greet() {
        return " Hello! I'm Bob\n"
                + " What can I do for you?";
    }

    /**
     * Get the exit message.
     * @return the exit message.
     */
    public String exit() {
        return " Bye. Hope to see you again soon!";
    }

    /**
     * Add a task to the list and get the response message.
     * @param task the task to add
     * @return the response message
     */
    private String add(Task task) {
        this.tasks.add(task);
        return String.format("""
                 Got it. I've added this task:
                    %s
                 Now you have %d tasks in the list.""",
                task.toString(), this.tasks.size());
    }

    /**
     * Add a new to-do task to the list and get the response message.
     * @param name the name of the to-do task
     * @return the response message
     */
    public String todo(String name) {
        ToDo task = new ToDo(name);
        return add(task);
    }

    /**
     * Add a new deadline task to the list and get the response message.
     * @param name the name of the deadline task
     * @param deadline the deadline of the task
     * @return the response message
     */
    public String deadline(String name, String deadline) {
        Deadline task = new Deadline(name, deadline);
        return add(task);
    }

    /**
     * Add a new event task to the list and get the response message.
     * @param name the name of the event task
     * @param start the start of the event
     * @param end the end of the event
     * @return the response message
     */
    public String event(String name, String start, String end) {
        Event task = new Event(name, start, end);
        return add(task);
    }

    /**
     * Mark a task as completed or not completed and get the response message.
     * @param isCompleted whether the task is completed or not completed.
     * @param itemNum the item number of the task
     * @return the response message
     */
    public String mark(boolean isCompleted, int itemNum) throws InvalidInputException {
        if (itemNum > this.tasks.size()) {
            throw new InvalidInputException("I'm sorry, I could not find task number " + itemNum + ".");
        }

        Task task = this.tasks.get(itemNum - 1);
        task.mark(isCompleted);
        if (isCompleted) {
            return " Nice! I've marked this task as done:\n   " + task;
        } else {
            return " OK, I've marked this task as not done yet:\n   " + task;
        }
    }

    /**
     * Get the list of items added.
     * @return the list message
     */
    public String list() {
        StringBuilder listMsg = new StringBuilder(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            listMsg
                    .append("\n ")
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.get(i));
        }
        return listMsg.toString();
    }
}