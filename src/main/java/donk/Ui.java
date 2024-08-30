package donk;

import donk.task.Task;
import donk.task.TaskType;

public class Ui {

    public Ui() {

    }

    public void showLoadingError() {
        System.out.println("No save file found, starting new task list");
    }

    public void greet() {
        String greetMsg = " ____________________________________________________________\n" +
                " Hello! I'm donk.Donk, the super intelligent chatbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        System.out.println(greetMsg);
    }

    public void bye() {
        String byeMsg = "    Bye bro, catch 'ya later\n" +
                "____________________________________________________________\n";
        System.out.println(byeMsg);
    }

    /**
     * Prints error message and indicates correct input format
     * @param taskType
     */
    public void invalidFormat(TaskType taskType) {
        if (taskType == TaskType.DEADLINE) {
            System.out.println("invalid format, require /by <date-time>");
            return;
        }
        if (taskType == TaskType.EVENT) {
            System.out.println("    Invalid Format man. You need a /start and a /end");
            return;
        }
    }

    public void markedDone(Task task) {
        System.out.println("    Yo I've marked this thingy as done");
        System.out.println("    " + task.toString());

    }

    public void unmarkedDone(Task task) {
        System.out.println("    Aights now it's unmarked again");
        System.out.println("    " + task.toString());

    }

    public void delete (Task task, int size) {
        System.out.println("    Alright bro I deleted that for you");
        System.out.println("    deleted: " + task.toString());
        System.out.println("    You now have " + size + " tasks");
    }


}
