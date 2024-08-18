public class Deadline extends Task{
    private String deadline;

    /**
     * Constructor to creat tasks that need to be done before a specific date/time
     *
     * @param task task information of the deadline task
     * @param deadline time when task need to be done
     */
    public Deadline(String task, String deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    /**
     * Method that shows the information of the task
     *
     * @return information the task in "[D][-] Task (by: 'deadline')" format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

}
