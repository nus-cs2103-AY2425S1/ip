public abstract class Task {
    protected String task;
    protected boolean isDone = false;


    /**
     * Check whether the user's input is a valid command. Sequentially task description,
     * start time(if have) and end time(if have)
     *
     * @param taskInfoArray user's input split by " "
     * @param type task type
     * @throws DukeException a new Exception class defined to represent exceptions specific to EchoBot.
     */
    public static void checkValidCommand(String[] taskInfoArray, TaskType type) throws DukeException{

        if (taskInfoArray.length <= 1 || taskInfoArray[1].isEmpty()) {
            String msg = "Oops! The description of a " + type + " cannot be empty.";
            throw new DukeException(msg);
        }

        String description = taskInfoArray[1];

        if (type == TaskType.DEADLINE) {
            if (!description.contains("/by")) {
                String msg = "Oops! The description of a deadline should contain a '/by' keywords";
                throw new DukeException(msg);
            }

            if (taskInfoArray[1].indexOf("/by") <= 1) {
                String msg = "Oops! Please give me more information about your task.";
                throw new DukeException(msg);
            }

            if (taskInfoArray[1].split("/by ").length <= 1) {
                String msg = "Oops! The end time of a deadline cannot be empty.";
                throw new DukeException(msg);
            }
        }

        if (type == TaskType.EVENT) {
            if (!taskInfoArray[1].contains("/from") || !taskInfoArray[1].contains("/to")) {
                String msg = "Oops! The description of an event should"
                        + " contain a '/from' and '/to' keywords";
                throw new DukeException(msg);
            }

            if (taskInfoArray[1].indexOf("/from") <= 1) {
                String msg = "Please give me more information about your task.";
                throw new DukeException(msg);
            }

            String timeInfo = taskInfoArray[1].split("/from")[1];

            if (timeInfo.indexOf("/to") <= 1) {
                String msg = "Oops! The start time of an event cannot be empty.";
                throw new DukeException(msg);
            }

            if (timeInfo.split("/to ").length <= 1) {
                String msg = "Oops! The end time of an event cannot be empty.";
                throw new DukeException(msg);
            }
        }
    }

    /**
     * Creat a different kinds of task according to the user's input
     *
     * @param taskInfo task information, including task type, specific tasks information and
     *                 important time information of the task
     * @return task created according to the task information user entered.
     */
    public static Task creatTask(String taskInfo) throws DukeException{
        String[] taskInfoArray = taskInfo.split(" ", 2);
        String type = taskInfoArray[0].toUpperCase();
        Task newTask;
        switch (TaskType.valueOf(type)) {
            case TODO:
                checkValidCommand(taskInfoArray, TaskType.TODO);
                String todoInfo = taskInfoArray[1];
                newTask = new ToDo(todoInfo);
                break;
            case DEADLINE:
                checkValidCommand(taskInfoArray, TaskType.DEADLINE);
                String ddlInfo = taskInfoArray[1].split(" /by ")[0];
                String deadline = taskInfoArray[1].split(" /by ")[1];
                newTask = new Deadline(ddlInfo, deadline);
                break;
            case EVENT:
                checkValidCommand(taskInfoArray, TaskType.EVENT);
                String eventInfo = taskInfoArray[1].split(" /from ")[0];
                String[] timeInfo = taskInfoArray[1].split(" /from ")[1].split(" /to ");
                String startTime = timeInfo[0];
                String endTime = timeInfo[1];
                newTask = new Event(eventInfo, startTime, endTime);
                break;
            default:
                String msg = "Oops! It seems you enter a invalid type.";
                String guide = "Please enter a valid task type: todo/deadline/event";
                throw new DukeException(msg + "\n" + guide);
        }
        System.out.println("Got it. I've added this task:");
        return newTask;
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
