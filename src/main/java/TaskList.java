import java.util.ArrayList;
import java.util.Collections;
public class TaskList {
    private ArrayList<Task> taskList;
    private final String lineBreak = "-------------------------------------";
    public TaskList(){
        this.taskList = new ArrayList<>();
    }
    public Task getTask(int index) {
        return this.taskList.get(index);
    }
    public void listAllTask() {
        System.out.println(lineBreak + "\nHere are the tasks in your list:");
        for(int i = 0; i < this.taskList.size(); i ++) {
            System.out.println((i + 1) + "." + this.getTask(i).toString());
        }
        System.out.println(lineBreak);
    }

    public void deleteTask(int index) throws EchoException {
        try {
            int numOfTask = this.taskList.size() - 1;
            if (index > numOfTask) {
                throw new EchoException(lineBreak + "\nThere is not enough task. " +
                        "\nPlease add more task or change another index\n" + lineBreak);
            }

            System.out.println(lineBreak + "\nNoted. I've removed this task:\n" +
                    this.getTask(index).toString());
            System.out.println("Now you have " + numOfTask + " tasks in the list.\n" + lineBreak);

            this.taskList.remove(index);
            Collections.rotate(this.taskList.subList(index, this.taskList.size()), -1);
            this.taskList.trimToSize();

        } catch (EchoException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addTask(Task userTask) throws EchoException {
        try {
            String description = userTask.getDescription();
            String[] replyArray = description.split(" ");
            String typeOfTask = replyArray[0];
            int numOfTask = this.taskList.size() + 1;

            if (replyArray.length == 1) {
                throw new EchoException(lineBreak + "\nSorry! Please include a " +
                        "description of what to do.\n" + lineBreak);
            }

            if (typeOfTask.equals("todo")) {

                String taskDescription = description.replaceFirst("todo ", "");
                ToDos toDoTask = new ToDos(taskDescription);
                this.taskList.add(toDoTask);

                System.out.println(lineBreak + "\nGot it. I've added this task:\n" + toDoTask.toString());
                System.out.println("Now you have " + numOfTask + " tasks in the list.\n" + lineBreak);

            } else if (typeOfTask.equals("deadline")) {
                String[] deadlineArray = description.split(" /");

                if (deadlineArray.length == 1) {
                    throw new EchoException(lineBreak + "\nSorry! Please include a " +
                            "deadline for the task.\n" + lineBreak);
                }

                String deadlineDescription = deadlineArray[0].replaceFirst("deadline ", "");
                String deadlineDate = deadlineArray[1];
                Deadlines deadlineTask = new Deadlines(deadlineDescription, deadlineDate);
                this.taskList.add(deadlineTask);

                System.out.println(lineBreak + "\nGot it. I've added this task:\n" + deadlineTask.toString());
                System.out.println("Now you have " + numOfTask + " tasks in the list.\n" + lineBreak);

            } else if (typeOfTask.equals("event")) {
                String[] eventArray = description.split(" /");

                if (eventArray.length < 3) {
                    throw new EchoException(lineBreak + "\nSorry! Please include a start and " +
                            "end time for the event.\n" + lineBreak);
                }

                String eventDescription = eventArray[0].replaceFirst("event ", "");
                String eventStart = eventArray[1];
                String eventEnd = eventArray[2];
                Events eventTask = new Events(eventDescription, eventStart, eventEnd);
                this.taskList.add(eventTask);

                System.out.println(lineBreak + "\nGot it. I've added this task:\n" + eventTask.toString());
                System.out.println("Now you have " + numOfTask + " tasks in the list.\n" + lineBreak);

            }
        } catch (EchoException e) {
            System.err.println(e.getMessage());
        }
    }
}
