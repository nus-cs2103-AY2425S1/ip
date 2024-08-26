package nameless;

import nameless.task.Task;

public class Ui {
    private final String LINE = "______________________________________________________________";
    private final String NAME = "nameless";
    private final String GREETINGS = "Hello, I'm " + NAME + "\n" + "What can I do for you?";
    private final String GOODBYE = "Bye. Hope to see you again!";

    public void greetings () {
        System.out.println(LINE + "\n" + GREETINGS + "\n" + LINE);
    }

    public void goodbye () {
        System.out.println(LINE + "\n" + GOODBYE + "\n" + LINE);
    }

    public void showAddTask (TaskList tasks) {
        System.out.println(LINE + "\n" + "Got it. I've added this task:" +
                "\n     " + tasks.get(tasks.size() - 1).toString() + "\n" +
                "Now you have " + tasks.size() + " task left \n" + LINE);
    }

    public void showDeleteTask (TaskList tasks, int index) throws DukeException {
        Task task = tasks.get(index);
        tasks.deleteTask(index);
        System.out.println(LINE + "\n" + "Noted. I've removed this task:" +
                "\n     " + task.toString() + "\n" +
                "Now you have " + tasks.size() + " task left \n" + LINE);

    }

    public void showList(TaskList tasks) {
        System.out.println(LINE + "\n Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println(LINE);
    }

    public void showMarkTask (TaskList tasks, int index) {
        System.out.println(LINE);
        tasks.get(index).markTask();
        System.out.println(LINE);
    }

    public void showUnmarkTask (TaskList tasks, int index) {
        System.out.println(LINE);
        tasks.get(index).unMarkTask();
        System.out.println(LINE);
    }
}

