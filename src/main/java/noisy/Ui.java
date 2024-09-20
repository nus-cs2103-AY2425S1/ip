package noisy;


import java.util.ArrayList;


public class Ui {

    public String printWelcome() {
        String welcomeMessage = "____________________________________________________________\n"
                + " Hello! I'm noisy.noisy\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";

        return welcomeMessage;
    }

    public String printGoodbye() {
        String goodbyeMessage = "____________________________________________________________\n"
                + " Bye human :(\n"
                + "____________________________________________________________";
        return goodbyeMessage;
    }

    public String printList(TaskList taskList) {
        String echoMessage = "____________________________________________________________\n" +
                "Here are the tasks in your list\n";
        for (int i = 0; i < taskList.getListSize(); i++) {
            int taskIndex = i + 1;
            echoMessage += taskIndex + ". " + taskList.getTask(i) + "\n";
        }
        return echoMessage;
    }

    public String printMark(int i, TaskList taskList) {
        taskList.markDoneFromList(i - 1);
        String markString = "____________________________________________________________\n" +
                "Nice! I've marked this task as done:\n" +
                taskList.getTask(i - 1) + "\n" +
                "____________________________________________________________\n";
        return markString;
    }

    public String printDelete(int i, TaskList taskList) {
        Task deletedTask = taskList.getTask(i - 1);
        taskList.deleteFromList(i - 1);
        String deleteMessage = "____________________________________________________________\n" +
                " Noted. I've removed this task:\n" +
                deletedTask + "\n" +
                "Now you have " + taskList.getListSize() + " tasks in the list\n" +
                "____________________________________________________________\n";

        return deleteMessage;
    }

    public String printAdd(Task task, TaskList taskList) {
        String taskAdd = "____________________________________________________________\n" +
                " Got it. I've added this task:\n" +
                task + "\n" +
                "Now you have " + taskList.getListSize() + " tasks in the list.\n" +
                "____________________________________________________________";
        return taskAdd;
    }

    public String printFind(TaskList taskList, String keyword) {
        ArrayList<Task> foundTasks = taskList.findTasks(keyword);
        StringBuilder result = new StringBuilder();

        if (foundTasks.isEmpty()) {
            result.append("No tasks found with the keyword: ").append(keyword);
        } else {
            result.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                result.append(i + 1).append(". ").append(foundTasks.get(i)).append("\n");
            }
        }

        return result.toString();
    }

}
