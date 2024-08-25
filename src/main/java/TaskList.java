import java.util.ArrayList;
import java.util.Collections;
public class TaskList {

    /** ArrayList of the tasks added to TaskList by the user*/
    private ArrayList<Task> taskList;
    private final String LINE_BREAK = "-------------------------------------";

    /**
     * Constructor for TaskList
     */
    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the Task at a given index of taskList
     *
     * @param index of the task in taskList
     * @return Task
     * @throws EchoException If index is greater than the total number of tasks in taskList
     */
    public Task getTask(int index) {
        int numOfTask = this.taskList.size() - 1;
        if (index > numOfTask) {
            throw new EchoException(LINE_BREAK + "\nThere is not enough task. " +
                    "\nPlease add more task or change another index\n" + LINE_BREAK);
        }
        return this.taskList.get(index);
    }

    /**
     * Prints out the list of all task stored in taskList
     */
    public void listAllTask() {
        System.out.println(LINE_BREAK + "\nHere are the tasks in your list:");
        for(int i = 0; i < this.taskList.size(); i ++) {
            System.out.println((i + 1) + "." + this.getTask(i).toString());
        }
        System.out.println(LINE_BREAK);
    }

    /**
     * Deletes Task at a given index in taskList
     *
     * @param index of the task in taskList
     * @throws EchoException If index is greater than the total number of tasks in taskList
     */
    public void deleteTask(int index) throws EchoException {
        try {
            int numOfTask = this.taskList.size() - 1;

            if (index > numOfTask) {
                throw new EchoException(LINE_BREAK + "\nThere is not enough task. " +
                        "\nPlease add more task or change another index\n" + LINE_BREAK);
            }

            System.out.println(LINE_BREAK + "\nNoted. I've removed this task:\n" +
                    this.getTask(index).toString());
            System.out.println("Now you have " + numOfTask + " tasks in the list.\n" + LINE_BREAK);

            this.taskList.remove(index);
            Collections.rotate(this.taskList.subList(index, this.taskList.size()), -1);
            this.taskList.trimToSize();

        } catch (EchoException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Adds Task into taskList and print a display message
     *
     * @param userTask Task provided by user input
     */
    public void addTask(Task userTask) {
        this.taskList.add(userTask);
        int numOfTask = this.taskList.size();
        System.out.println(LINE_BREAK + "\nGot it. I've added this task:\n" + userTask.toString());
        System.out.println("Now you have " + numOfTask + " tasks in the list.\n" + LINE_BREAK);
        }
}
