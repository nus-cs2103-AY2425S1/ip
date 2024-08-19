public class Janet {
    private static final String horizontalLine = "____________________________________________________________";
    private final Task[] listOfTasks;
    private int taskIndex;

    Janet() {
        this.listOfTasks = new Task[100];
        this.taskIndex = 0;
    }

    /**
     * Level 0 - greets the user
     * @return a String message to greet the user.
     */
    public String greet() {
        return horizontalLine + "\nHello! I'm Janet\n" + "What can I do for you?\n" + horizontalLine;
    }


    /**
     * Level 0 - say bye to user
     * @return a String message to say bye to the user and exit the program.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!\n" + horizontalLine;
    }


    /**
     * Level 1 - echoes the message
     * @param message a String message to capture what the user typed into the command line.
     * @return the exact same String provided by the user
     */
    public String echo(String message) {
        return horizontalLine + "\n" + message + "\n" + horizontalLine;
    }


    /**
     * Level 2 - Add, list
     * @param task a String representation of the task that is to be added into the listOfTasks.
     * @return a String message to indicate successful addition of task into listOfTasks.
     */
    public String addTaskToList(String task) {
        Task newTask = new Task(task);
        this.listOfTasks[this.taskIndex] = newTask;
        this.taskIndex++;
        return horizontalLine + "\n" + String.format("added: %s\n", task) + horizontalLine;
    }


    /**
     * @param task a Task object that is to be added into the listOfTasks
     * @return a String message to indicate successful addition of task into listOfTasks.
     */
    public String addTaskToList(Task task) {
        this.listOfTasks[this.taskIndex] = task;
        this.taskIndex++;
        return horizontalLine + "\n" + String.format("added: %s\n", task.getDescription()) + horizontalLine;
    }


    /**
     * Level 2 - Add, list
     * @return a String representation (in numbered list format) of the current tasks inside the listOfTasks
     */
    public String showList() {
        String currentList = horizontalLine + "\nHere are the tasks in your list:\n";
        if (taskIndex == 0) {
            // empty listOfTasks
            return currentList + "*** Current list is empty ***\n" + horizontalLine;
        }
        for (int i = 0; i < taskIndex; i++) {
            if (i == taskIndex - 1) {
                currentList += (i+1) + ". " + listOfTasks[i] + "\n" + horizontalLine;
                break;
            }
            currentList += (i+1) + ". " + listOfTasks[i] + "\n";
        }
        return currentList;
    }
}
