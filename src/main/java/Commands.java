import java.util.ArrayList;

public class Commands {

    public static void botIntro(String botName) {
        System.out.println("------------------------------------------");
        System.out.println("Hello I'm " + botName);
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
    }

    public static void returnTaskList(ArrayList<Task> tasks) {
        System.out.println("------------------------------------------");
        System.out.println("Here are the tasks in your list:\n");
        for(int i =0; i < tasks.size(); i++) {
            int taskListCount = i + 1;
            Task currTask = tasks.get(i);
            System.out.print(taskListCount + ". " + currTask.toString() + "\n");
        }
        System.out.println("------------------------------------------");
    }

    public static void addTask(ArrayList<Task> tasksArr, Task task) {
        System.out.println("------------------------------------------");
        tasksArr.add(task);
        int numberOfTasks = tasksArr.size();
        System.out.println( "Got it. I've added this task:\n" + task.toString() + "\n" + "Now you have " + numberOfTasks + " tasks in your list.");
        System.out.println("------------------------------------------");
    }
    public static void markTask(ArrayList<Task> tasksArr, int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task currTask = tasksArr.get(taskIndex);
        currTask.setIsDone(true);
        System.out.println("------------------------------------------");
        System.out.println("Nice! I have marked this task as done: \n" + currTask.toString());
        System.out.println("------------------------------------------");
    }

    public static void unmarkTask(ArrayList<Task> tasksArr, int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task currTask = tasksArr.get(taskIndex);
        currTask.setIsDone(false);
        System.out.println("------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet: \n" + currTask.toString());
        System.out.println("------------------------------------------");
    }

    public static void deleteTask(ArrayList<Task> tasksArr, int taskNumber) {
        System.out.println("------------------------------------------");
        int taskIndex = taskNumber -1;
        Task currTask = tasksArr.get(taskIndex);
        tasksArr.remove(taskIndex);
        int numberOfTasks = tasksArr.size();
        System.out.println( "Noted. I've removed this task:\n" + currTask.toString() + "\n" + "Now you have " + numberOfTasks + " tasks in your list.");
        System.out.println("------------------------------------------");
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");
    }
}
