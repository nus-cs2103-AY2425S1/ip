package espresso.ui;
import espresso.task.TaskList;
import espresso.task.Task;
import espresso.command.InvalidCommandException;

public class Ui {

    public String printLine() {
        return "________________________________________________\n";
    }

    public String printWelcome() {

        String res = printLine() + "   ____                            \n" +
                "  / __/__ ___  _______ ___ ___ ___ \n" +
                " / _/(_-</ _ \\/ __/ -_|_-<(_-</ _ \\\n" +
                "/___/___/ .__/_/  \\__/___/___/\\___/\n" +
                "       /_/                         "
                + "\n" + "Hello! I'm Espresso" + "\n" + "What can I do for you?" + "\n" + printLine();
        return res;
    }

    public String printGoodbye() {
        String res = printLine() + "It was nice talking to you!" + "Until next time...." +
                "\n" + printLine();
        return res;
    }

    public String printError(String message) {
        return printLine() +
                "Error: " + message + "\n" +
                printLine();
    }


    public String printTasks(TaskList taskList) throws InvalidCommandException {
        String res = "";
        res = printLine() + "Here are the tasks in your list: \n";
        for (int i = 0; i < taskList.size(); ++i) {
            res += i + 1 + "." + taskList.getTask(i) + "\n";
        }
        return res + "\n" + printLine();
    }

    public String printTaskAdded(Task task, String type) {
        String res = printLine() + "Added the following " + type + ":" + "\n"
                + task + "\n" +
                printLine();
        return res;
    }

    public String printTaskRemoved(Task task) {
        String res = printLine() +
                "Noted. I've removed this task:" + "\n" +
                task + "\n" + printLine();
        return res;
    }

    public String printTaskMarked(Task task) {
        String res = printLine() + "Nice. The following task has been marked as done:" + "\n" +
                task + "\n" +
                printLine();
        return res;
    }

    public String printTaskUnmarked(Task task) {
        String res = printLine() +
                "Nice. The following task has been marked as undone:" + "\n" +
                task + "\n" +
                printLine();
        return res;
    }

    public String printFoundTasks(TaskList tasks) {
        if (tasks.size() < 1) {
            return "No tasks found.";
        } else {
            String res = "Here are the matching tasks in your list:" + "\n" + tasks;
            return res;
        }
    }
}

