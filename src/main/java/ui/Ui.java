package ui;

import task.Task;
import task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public String displayWelcome() {
        return "Hey there! I'm Abdul Buddy\nWhat do ya need help with?";
    }

    public String displayGoodbye() {
        return "Bye! See ya soon!";
    }

    public String displayUpdateSuccess() {
        return "Task is updated!";
    }

    public String displayAddTask(Task task, TaskList list) {
        return String.format(
                "Gotcha! I've added this task: \n" +
                        "         [%s][%s] %s\n" +
                        "Now, you have %d tasks in the list!\n",
                task.getTaskType(), task.getStatusIcon(), task, list.getTasks().size()
        );
    }

    public String displayDeleteTask(Task task, TaskList list) {
        return String.format(
                "Noted. I've removed this task:\n" +
                        "      [%s][%s] %s\n" +
                        "Now you have %d tasks in the list.\n",
                task.getTaskType(), task.getStatusIcon(), task, list.getTasks().size()
        );
    }

    public String displayAlreadyMarked() {
        return "Uhh, its already been marked buddy!";
    }

    public String displayUnavailableItem() {
        return "Uhh, that item doesnt exist";
    }

    public String displayAlreadyUnmarked() {
        return "Uhh, its already been unmarked buddy!";
    }

    public String displayUnmarkedTask(int index, TaskList list) {
        return String.format(
                "Alright buddy, let's give that task another shot!\n" +
                        "    [%s][%s] %s\n",
                list.getTasks().get(index).getTaskType(),
                list.getTasks().get(index).getStatusIcon(),
                list.getTasks().get(index).toString()
        );
    }

    public String displayMarkedTask(int index, TaskList list) {
        return String.format(
                "Nice one buddy! Marked this as done...\n" +
                        "    [%s][%s] %s\n",
                list.getTasks().get(index).getTaskType(),
                list.getTasks().get(index).getStatusIcon(),
                list.getTasks().get(index).toString()
        );
    }

    public String displayError(String message) {
        return "OOPS! " + message;
    }

    public String displayTasks(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();

        if (tasks.isEmpty()) {
            result.append("List is empty!!\n");
        } else {
            result.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                result.append(String.format("%d. [%s][%s] %s\n",
                        i + 1,
                        tasks.get(i).getTaskType(),
                        tasks.get(i).getStatusIcon(),
                        tasks.get(i).toString()
                ));
            }
        }

        return result.toString();
    }

    public String displaySearchedTasks(ArrayList<Task> tasks, String search) {
        StringBuilder result = new StringBuilder();

        if (tasks.isEmpty()) {
            result.append("List is empty!!\n");
        } else {
            result.append("Here are the matching tasks in your list:\n");
            int tracker = 0;
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).toString().contains(search)) {
                    result.append(String.format("%d. [%s][%s] %s\n",
                            tracker + 1,
                            tasks.get(i).getTaskType(),
                            tasks.get(i).getStatusIcon(),
                            tasks.get(i).toString()
                    ));
                    tracker++;
                }
            }

            if (tracker == 0) {
                result.append("No matching tasks found.\n");
            }
        }

        return result.toString();
    }


}
