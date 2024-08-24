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
