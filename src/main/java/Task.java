public abstract class Task {
    protected String task;
    protected boolean isDone = false;

    /**
     * Creat a different kinds of task according to the user's input
     *
     * @param taskInfo task information, including task type, specific tasks information and
     *                 important time information of the task
     * @return task created according to the task information user entered.
     */
    public static Task creatTask(String taskInfo) {
        System.out.println("Got it. I've added this task:");
        String[] taskInfoArray = taskInfo.split(" ", 2);
        String type = taskInfoArray[0];
        switch (type) {
            case "todo":
                String todoInfo = taskInfoArray[1];
                return new ToDo(todoInfo);
            case "deadline":
                String ddlInfo = taskInfoArray[1].split(" /by ")[0];
                String deadline = taskInfoArray[1].split(" /by ")[1];
                return new Deadline(ddlInfo, deadline);
            case "event":
                String eventInfo = taskInfoArray[1].split(" /from ")[0];
                String[] timeInfo = taskInfoArray[1].split(" /from ")[1].split(" /to ");
                String startTime = timeInfo[0];
                String endTime = timeInfo[1];
                return new Event(eventInfo, startTime, endTime);
            default:
                System.out.println("Oops! It seems you enter a invalid type.");
                System.out.println("Please enter a valid task type: todo/deadline/event");
                return null;
        }
    }

    /**
     * Mark the task as completed
     */
    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    /**
     * Mark the task as incomplete
     */
    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }

    /**
     * Show the status of a task. Show whether the task is complete
     *
     * @return status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Method that shows the basic information of the task
     *
     * @return task specific information and complete status
     */
    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        return statusIcon + " " + this.task;
    }
}
