public class TaskList {
    private Task[] taskList;
    private int taskNum;
    private static String horizontalLine = "\n-------------------------------------------------";

    public TaskList() {
        this.taskList = new Task[100];
        this.taskNum = 0;
    }

    public void addTask(String taskDescription) {
        taskList[taskNum] = new Task(taskDescription);
        taskNum += 1;
        System.out.println("Added: " + taskDescription + horizontalLine);
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
        String output = "";
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
