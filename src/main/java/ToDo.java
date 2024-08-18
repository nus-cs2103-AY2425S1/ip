public class ToDo extends Task{

    /**
     * Constructor to creat a task to complete
     *
     * @param task without any date/time attached to it
     */
    public ToDo(String task) {
        this.task = task;
    }

    /**
     * Method that shows the information of the task
     *
     * @return information the task in "[T][-] Task" format
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
