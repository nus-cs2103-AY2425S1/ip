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
     * Add an task to the list and get the response message.
     * @param name the name of the task to add
     * @return the response message
     */
    public String add(String name) {
        BasicTask task = new BasicTask(name);
        this.tasks.add(task);
        return " added: " + name;
    }

    /**
     * Mark a task as completed or not completed and get the response message.
     * @param isCompleted whether the task is completed or not completed.
     * @param itemNum the item number of the task
     * @return the response message
     */
    public String mark(boolean isCompleted, int itemNum) throws InvalidInputException {
        if (itemNum > this.tasks.size()) {
            throw new InvalidInputException("Task number " + itemNum + " not found.");
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
        StringBuilder listMsg = new StringBuilder();
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