package justbot.ui;

import justbot.exception.JustbotException;
import justbot.task.Task;
import justbot.task.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.print("Enter your command: ");
        return scanner.nextLine().trim();
    }

    public void getJustBotExceptionMessage(JustbotException e) {
        System.out.println("------------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("------------------------------------------");
    }

    public void invalidCommandMessage() {
        System.out.println("------------------------------------------");
        System.out.println("Hey man you provided me with an invalid command. Here is a list of my commands:");
        System.out.println("1. list");
        System.out.println("2. mark [task number]");
        System.out.println("3. unmark [task number]");
        System.out.println("4. delete [task number]");
        System.out.println("5. todo [task description]");
        System.out.println("6. deadline [task description] /by [dd/MM/yyyy HH:mm]");
        System.out.println("7. event [task description] /from [dd/MM/yyyy HH:mm] /to [dd/MM/yyyy HH:mm]");
        System.out.println("8. find [task description]");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
    }

    public void botIntro() {
        System.out.println("------------------------------------------");
        System.out.println("Hello I'm Justbot!");
        System.out.println("Here is a list of my commands:");
        System.out.println("1. list");
        System.out.println("2. mark [task number]");
        System.out.println("3. unmark [task number]");
        System.out.println("4. delete [task number]");
        System.out.println("5. todo [task description]");
        System.out.println("6. deadline [task description] /by [dd/MM/yyyy HH:mm]");
        System.out.println("7. event [task description] /from [dd/MM/yyyy HH:mm] /to [dd/MM/yyyy HH:mm]");
        System.out.println("8. find [task description]");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
    }

    public void listMessage(TaskList taskList) {
        System.out.println("------------------------------------------");
        System.out.println("Here are the tasks in your list:\n");
        for(int i =0; i < taskList.size(); i++) {
            int taskListCount = i + 1;
            Task currTask = taskList.get(i);
            System.out.print(taskListCount + ". " + currTask.toString() + "\n");
        }
        System.out.println("------------------------------------------");
    }

    public void findMessage(TaskList taskList, String keyword) {
        System.out.println("------------------------------------------");
        System.out.println("Hey man here are the matching tasks in your list:\n");
        int count = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask.getTaskDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(count + ". " + taskList.get(i));
                count++;
            }
        }

        if (count == 1) {
            System.out.println("No matching tasks found.");
        }
        System.out.println("------------------------------------------");
    }

    public void markMessage(TaskList taskList, int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task currTask = taskList.get(taskIndex);
        System.out.println("------------------------------------------");
        System.out.println("Nice! I have marked this task as done:\n" + currTask.toString());
        System.out.println("------------------------------------------");
    }

    public void unmarkMessage(TaskList taskList, int taskNumber){
        int taskIndex = taskNumber - 1;
        Task currTask = taskList.get(taskIndex);
        System.out.println("------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:\n" + currTask.toString());
        System.out.println("------------------------------------------");
    }

    public void addTaskMessage(TaskList taskList, Task task) {
        System.out.println("------------------------------------------");
        int numberOfTasks = taskList.size();
        System.out.println( "Got it. I've added this task:\n" + task.toString() + "\n" + "Now you have " + numberOfTasks + " tasks in your list.");
        System.out.println("------------------------------------------");
    }

    public void deleteTaskMessage(TaskList taskList, int taskNumber) {
        System.out.println("------------------------------------------");
        int taskIndex = taskNumber -1;
        Task currTask = taskList.get(taskIndex);
        int numberOfTasks = taskList.size() - 1;
        System.out.println( "Noted. I've removed this task:\n" + currTask.toString() + "\n" + "Now you have " + numberOfTasks + " tasks in your list.");
        System.out.println("------------------------------------------");
    }
    public void byeMessage() {
        System.out.println("------------------------------------------");
        System.out.println("Hey man, I'll miss you. See you soon!");
        System.out.println("------------------------------------------");
    }
}
