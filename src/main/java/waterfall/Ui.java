package waterfall;

import waterfall.task.Task;
import waterfall.task.TaskList;

import java.util.Scanner;

public class Ui {
    String chatBotName = "Waterfall";
    int indentSpace = 4;

    public void showWelcomeMessage() {
        String welcomeMessage = ("Hualalalalala I'm " + chatBotName + "\n" + "What can I do for you?\n")
                .indent(indentSpace + 1);
        System.out.println(welcomeMessage);
    }

    public void showByeMessage() {
        String byeMessage = ("Shhhhhhhhhhhh. Hope to see you again soon!\n").indent(indentSpace);
        System.out.println(byeMessage);
    }

    public void showLoadingError() {
        System.out.println("Oops! Something went wrong in loading the database!".indent(indentSpace));
    }

    public void showLine() {
        System.out.println(" ".repeat(indentSpace) + "____________________________________________________________\n");
    }

    public String readCommand() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

    public void showError(String message) {
        System.out.println(" ".repeat(indentSpace + 1) + "Oops Water falls: " + message);
    }

    public void showAddMessage(Task task) {
        System.out.println(" ".repeat(indentSpace + 1) + "Successfully added a task to the waterfallll:");
        System.out.println(" ".repeat(indentSpace + 1) + task.toString());
    }

    public void showMarkMessage(Task task) {
        System.out.println(" ".repeat(indentSpace + 1) + "Huluhuluhulu, I've marked this task as done: ");
        System.out.println(" ".repeat(indentSpace + 1) + task.toString());
    }

    public void showUnmarkMessage(Task task) {
        System.out.println(" ".repeat(indentSpace + 1) + "Hohohohohoho, I've marked this task as not done: ");
        System.out.println(" ".repeat(indentSpace + 1) + task.toString());
    }

    public void showDeleteMessage(Task task) {
        System.out.println(" ".repeat(indentSpace + 1)
                + "Hehehehehehe, I've removed this task from the waterfall: ");
        System.out.println(" ".repeat(indentSpace + 1) + task.toString());
    }

    public void showTaskListMessage(TaskList taskList) {
        System.out.println(" ".repeat(indentSpace) + "Here's the tasks in your waterfall hualalala");
        taskList.printDetail(indentSpace + 1);
    }
}
