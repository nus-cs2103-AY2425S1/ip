public class Commands {

    public static void botIntro(String botName) {
        System.out.println("------------------------------------------");
        System.out.println("Hello I'm " + botName);
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
    }

    public static void returnTaskList(Task[] tasks, int tasksIndex) {
        System.out.println("------------------------------------------");
        System.out.println("Here are the tasks in your list: \n");
        for(int i =0; i < tasksIndex; i++) {
            int taskListCount = i + 1;
            Task currTask = tasks[i];
            System.out.print(taskListCount + ". " + "[" + currTask.getStatusIcon() + "] " + tasks[i].getTaskDescription() + "\n");
        }
        System.out.println("------------------------------------------");
    }

    public static void addTask(Task[] tasksArr, int currIndex, Task task) {
        System.out.println("------------------------------------------");
        tasksArr[currIndex] = task;
        String taskDescription = task.getTaskDescription();
        System.out.println( "added: " + taskDescription);
        System.out.println("------------------------------------------");
    }

    public static void markTask(Task[] tasksArr, int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task currTask = tasksArr[taskIndex];
        currTask.setIsDone(true);
        System.out.println("------------------------------------------");
        System.out.println("Nice! I have marked this task as done: \n" + "[" + currTask.getStatusIcon() + "] " + currTask.getTaskDescription());
        System.out.println("------------------------------------------");
    }

    public static void unmarkTask(Task[] tasksArr, int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task currTask = tasksArr[taskIndex];
        currTask.setIsDone(false);
        System.out.println("------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet: \n" + "[" + currTask.getStatusIcon() + "] " + currTask.getTaskDescription());
        System.out.println("------------------------------------------");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");
    }

}