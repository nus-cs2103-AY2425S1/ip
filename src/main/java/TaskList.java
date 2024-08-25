public class TaskList {
    private Task[] taskList;
    private int taskNum;
    private static String horizontalLine = "\n-------------------------------------------------";

    public TaskList() {
        this.taskList = new Task[100];
        this.taskNum = 0;
    }

    public void addTask(String taskDescription) {
        // here handle the different task types
        if (taskDescription.startsWith("todo ")) {
            String taskName = taskDescription.substring(5);
            taskList[taskNum] = new TodoTask(taskName);
        } else if (taskDescription.startsWith("deadline ")) {
            int byIndex = taskDescription.indexOf("/by");
            String taskName = taskDescription.substring(9, byIndex);
            String deadline = taskDescription.substring(byIndex + 4);
            taskList[taskNum] = new DeadlineTask(taskName, deadline);
        } else if (taskDescription.startsWith("event ")) {
            int fromIndex = taskDescription.indexOf("/from");
            int toIndex = taskDescription.indexOf("/to");
            String taskName = taskDescription.substring(6, fromIndex);
            String startTime = taskDescription.substring(fromIndex + 6, toIndex);
            String endTime = taskDescription.substring(toIndex + 4);
            taskList[taskNum] = new EventTask(taskName, startTime, endTime);
        } else {
            System.out.println("Please use keywords: todo, deadline or event");
            return;
        }
        System.out.println("Nimbus added this: \n" + taskList[taskNum].toString() + horizontalLine);
        taskNum += 1;
    }

    public int getTaskNum() {
        return this.taskNum;
    }

    public void completeTask(int index) {
        // check if task is already completed
        if (index >= taskNum) {
            System.out.println("There is no task " + (index + 1));
        } else if (taskList[index].isCompleted()) {
            System.out.println("Already Marked");
        } else if (index < taskNum) {
            taskList[index].complete();
            System.out.println("Nimbus shall mark this as done:\n" +
                    "    " + taskList[index].toString() + horizontalLine);
        }
    }

    public void incompleteTask(int index) {
        // check if task is already incomplete
        if (index >= taskNum) {
            System.out.println("There is no task " + (index + 1));
        } else if (!taskList[index].isCompleted()) {
            System.out.println("Already Unmarked");
        } else if (index < taskNum) {
            taskList[index].incomplete();
            System.out.println("Nimbus shall mark this as not done:\n" +
                    "    " + taskList[index].toString() + horizontalLine);
        }
    }

    @Override
    public String toString() {
        String output = "Nimbus says this is your list: \n";
        for (int i = 0; i < taskNum; i++) {
            if (i == taskNum - 1) {
                output += (String.valueOf(i + 1) + ". " + taskList[i]);
            } else {
                output += (String.valueOf(i + 1) + ". " + taskList[i] + "\n");
            }
        }
        output += horizontalLine;
        System.out.println(output);
        return output;
    }
}
