package Bellroy;

import java.util.Scanner;
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    public void printWelcomeMessage() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Bellroy\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    public void printByeMessage() {
        System.out.println("____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void taskAddedMessage(Task task, int size) {
        System.out.printf("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "     %s\n" +
                "     Now you have %d tasks in the list.\n" +
                "    ____________________________________________________________\n",task, size);
    }

    public void printTaskList(TaskList taskList) {
        System.out.println("____________________________________________________________");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    public void markedDone(Task task) {
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done:\n" +
                "     " + task.toString() + "\n" +
                "    ____________________________________________________________\n");
    }

    public void markedUndone(Task task) {
        System.out.println("    ____________________________________________________________\n" +
                "     OK, I've marked this task as not done yet:\n" +
                "     " + task.toString() + "\n" +
                "    ____________________________________________________________\n");
    }

    public void taskDeleted(Task task, int size) {
        System.out.printf("    ____________________________________________________________\n" +
                "     Got it. I've removed this task:\n" +
                "       " + task + "\n" +
                "     Now you have %d tasks in the list.\n" +
                "    ____________________________________________________________\n", size);
    }

}
