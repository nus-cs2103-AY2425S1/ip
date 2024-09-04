package duke;

import java.util.Scanner;

public class Ui {
    Ui() {
    }

    public String showWelcome() {
        String greet = "Hello! I'm Bob\nWhat can I do for you?\n";
        return show(greet);
    }

    public String show(String show) {
        String line = "-----------------------------\n";
        return line + show + "\n" + line;
    }

    public String showAddTask(Task task, int length) {
        String add = "Got it. I've added this task:\n" + task.toString().trim()
                + "\nNow you have " + length + " tasks in the list.";
        return show(add);
    }

    public String showError(String show) {
        String line = "------------ERROR------------\n";
        return line + show + "\n" + line;
    }

    public String readCommand() {

        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }

    public String showAllTasks(TaskList tasks) {
        String result = "";
        if (tasks.size() == 0) {
            return "No tasks!";
        }
        for (int i = 0; i < tasks.size(); i++) {
            result += i + 1 + ". " + tasks.get(i).toString() + "\n";
        }
        return result;
    }
}
