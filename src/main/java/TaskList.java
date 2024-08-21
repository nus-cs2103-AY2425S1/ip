import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList(){
        this.taskList = new ArrayList<>();
    }
    public Task getTask(int index) {
        return this.taskList.get(index);
    }
    public void listAllTask() {
        System.out.println("-------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < this.taskList.size(); i ++) {
            System.out.println((i + 1) + "." + this.getTask(i).toString());
        }
        System.out.println("-------------------------------------");
    }

    public void addTask(Task userTask) {
        String description = userTask.getDescription();
        String[] replyArray = description.split(" ");
        String typeOfTask = replyArray[0];
        int numOfTask = this.taskList.size() + 1;

        if (typeOfTask.equals("todo")) {
            String taskDescription = description.replaceFirst("todo ", "");
            ToDos toDoTask = new ToDos(taskDescription);
            this.taskList.add(toDoTask);

            System.out.println("-------------------------------------");
            System.out.println("Got it. I've added this task:\n" + toDoTask.toString());
            System.out.println("Now you have " + numOfTask + " tasks in the list.");
            System.out.println("-------------------------------------");

        } else if (typeOfTask.equals("deadline")) {
            String[] deadlineArray = description.split(" /");
            String deadlineDescription = deadlineArray[0].replaceFirst("deadline ", "");
            String deadlineDate = deadlineArray[1];
            Deadlines deadlineTask = new Deadlines(deadlineDescription, deadlineDate);
            this.taskList.add(deadlineTask);

            System.out.println("-------------------------------------");
            System.out.println("Got it. I've added this task:\n" + deadlineTask.toString());
            System.out.println("Now you have " + numOfTask + " tasks in the list.");
            System.out.println("-------------------------------------");

        } else if (typeOfTask.equals("event")) {
            String[] eventArray = description.split(" /");
            String eventDescription = eventArray[0].replaceFirst("event ", "");
            String eventStart = eventArray[1];
            String eventEnd = eventArray[2];
            Events eventTask = new Events(eventDescription, eventStart, eventEnd);
            this.taskList.add(eventTask);

            System.out.println("-------------------------------------");
            System.out.println("Got it. I've added this task:\n" + eventTask.toString());
            System.out.println("Now you have " + numOfTask + " tasks in the list.");
            System.out.println("-------------------------------------");

        } else {
            this.taskList.add(userTask);

            System.out.println("-------------------------------------");
            System.out.println("Got it. I've added this task:\n" + userTask.toString());
            System.out.println("Now you have " + numOfTask + " tasks in the list.");
            System.out.println("-------------------------------------");
        }

    }


}
