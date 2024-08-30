package chacha;

import chacha.task.Task;

import java.time.LocalDate;

public class Ui {
    private final static String LINE = "     ____________________________________________________________ \n";
    private final static String INDENTATION = "     ";
    private final static String GREETING = "     ____________________________________________________________ \n"
            + "     Hello! I'm ChaCha the ChatBot. :) \n"
            + "     What can I do for you? \n"
            + "     ____________________________________________________________ \n";
    private final static String EXIT = "     ____________________________________________________________ \n"
            + "     Bye! Hope to talk to you again soon! \n"
            + "     ____________________________________________________________ \n";

    public Ui() {

    }

    public String printGreeting() {
        return GREETING;
    }

    public String printExit() {
        return EXIT;
    }

    public String printAdd(Task task, TaskList tasks) {
        return LINE
                + INDENTATION + "Got it. I've added this task: \n"
                + INDENTATION + task.printTask() + "\n"
                + INDENTATION + "Now you have " + tasks.getTotalNumber() + " tasks in the list. \n"
                + LINE;
    }

    public String printDelete(Task task, TaskList tasks) {
        return LINE
                + INDENTATION + "Okay! I've removed this task: \n"
                + INDENTATION + task.printTask() + "\n"
                + INDENTATION + "Now you have " + tasks.getTotalNumber() + " tasks in the list. \n"
                + LINE;
    }

    public String printMark(Task task) {
        return LINE
                + INDENTATION + "Nice! I've marked this task as done: \n"
                + INDENTATION + task.printTask() + "\n"
                + LINE;
    }

    public String printUnmark(Task task) {
        return LINE
                + INDENTATION + "Nice! I've marked this task as not done yet: \n"
                + INDENTATION + task.printTask() + "\n"
                + LINE;
    }

    public String printList(String[] arrOfTasks) {
        String result = LINE
                + INDENTATION + "Here are the tasks in your list: \n";
        for (int i = 0; i < arrOfTasks.length; i++) {
            result += INDENTATION + arrOfTasks[i] + "\n";
        }
        return result + LINE;
    }

    public String printStrings(String[] arrOfString) {
        String result = LINE;
        for (int i = 0; i < arrOfString.length; i++) {
            result += INDENTATION + arrOfString[i] + "\n";
        }
        return result + LINE;
    }

    public String printToDo(String description) {
        return "T | 0 | " + description + "\n";
    }

    public String printDeadline(String description, LocalDate date) {
        return "D | 0 | " + description + " | " + date.toString() + "\n";
    }

    public String printEvent(String description, LocalDate date, String startTime, String endTime) {
        return "E | 0 | " + description
                + " | " + date.toString() + " | " + startTime + "-" + endTime + "\n";
    }
}
