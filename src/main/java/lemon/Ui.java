package lemon;

import lemon.app.Launcher;

/**
 * Represents the user interface class for displaying information
 * @author He Yiheng
 */
public class Ui {
    private static final String INTRO_MSG = " Hello! I'm Lemon\n"
            + " What can I do for you?";
    private static final String END_MSG = " Bye. Hope to see you again soon!\n";
    private static final String BAR = "____________________________________________________________";
    private static final String EMPTY_LIST_MSG = " Sowwy, theres currently no tasks in your list.\n"
            + " Ill be happy to add some for you OwO!";
    private static final String LIST_MSG = " Here are the tasks in your list:";
    private static final String MARK_MSG = " Nice! I've marked this task as done:";
    private static final String UNMARK_MSG = " OK, I've marked this task as not done yet:";
    private static final String ADD_TASK_MSG = " Got it. I've added this task:";
    private static final String DELETE_TASK_MSG = " Noted. I've removed this task:";
    private static final String MATCHING_TASK_MSG = " Here are the matching tasks in your list:";
    private static final String NO_MATCHING_MSG = " Didnt manage to find any matching tasks :c";

    /**
     * Prints the intro message
     */
    public void printIntroMsg() {
        if (Launcher.IS_GUI) {
            Launcher.out.addOutput(INTRO_MSG);
            Launcher.out.print();
        } else {
            System.out.println(BAR);
            System.out.println(INTRO_MSG);
        }
    }

    /**
     * Prints the end message
     */
    public void printEndingMsg() {
        if (Launcher.IS_GUI) {
            Launcher.out.addOutput(END_MSG);
            Launcher.out.print();
        } else {
            System.out.println(END_MSG);
            System.out.println(BAR);
        }
    }

    /**
     * Prints the bar message
     */
    public void printBarMsg() {
        if (!Launcher.IS_GUI) {
            System.out.println(BAR);
        }
    }

    /**
     * Prints the message when the list is empty
     */
    public void printEmptyListMsg() {
        if (Launcher.IS_GUI) {
            Launcher.out.addOutput(EMPTY_LIST_MSG);
            Launcher.out.print();
        } else {
            System.out.println(EMPTY_LIST_MSG);
        }
    }

    /**
     * Prints the message for lists
     * @param listStr string that contains all the tasks
     */
    public void printListMsg(String listStr) {
        if (Launcher.IS_GUI) {
            Launcher.out.addOutputln(LIST_MSG);
            Launcher.out.addOutput(listStr);
            Launcher.out.print();
        } else {
            System.out.println(LIST_MSG);
            System.out.print(listStr);
        }
    }

    /**
     * Prints the message when marking a task
     */
    public void printMarkMsg(String task) {
        if (Launcher.IS_GUI) {
            Launcher.out.addOutputln(MARK_MSG);
            Launcher.out.addOutput("   " + task);
            Launcher.out.print();
        } else {
            System.out.println(MARK_MSG);
            System.out.println("   " + task);
        }
    }

    /**
     * Prints the message when unmarking a task
     */
    public void printUnmarkMsg(String task) {
        if (Launcher.IS_GUI) {
            Launcher.out.addOutputln(UNMARK_MSG);
            Launcher.out.addOutput("   " + task);
            Launcher.out.print();
        } else {
            System.out.println(UNMARK_MSG);
            System.out.println("   " + task);
        }
    }

    /**
     * Prints the message after adding a task
     * @param addedTask string of the task to be added
     * @param numTasks total number of task after adding
     */
    public void printAddTaskMsg(String addedTask, int numTasks) {
        if (Launcher.IS_GUI) {
            Launcher.out.addOutputln(ADD_TASK_MSG);
            Launcher.out.addOutputln("   " + addedTask);
            Launcher.out.addOutput(" Now you have " + numTasks + " tasks in the list.");
            Launcher.out.print();
        } else {
            System.out.println(ADD_TASK_MSG);
            System.out.println("   " + addedTask);
            System.out.println(" Now you have " + numTasks + " tasks in the list.");
        }
    }

    /**
     * Prints the message after deleting a task
     * @param deletedTask string of the task that is deleted
     * @param numTasks total number of task after delete
     */
    public void printDeleteTaskMsg(String deletedTask, int numTasks) {
        if (Launcher.IS_GUI) {
            Launcher.out.addOutputln(DELETE_TASK_MSG);
            Launcher.out.addOutputln("   " + deletedTask);
            Launcher.out.addOutput(" Now you have " + numTasks + " tasks in the list.");
            Launcher.out.print();
        } else {
            System.out.println(DELETE_TASK_MSG);
            System.out.println("   " + deletedTask);
            System.out.println(" Now you have " + numTasks + " tasks in the list.");
        }
    }

    /**
     * Prints message with all tasks that have matching keyword
     * @param matchingTasksStr String representation of the found tasks
     */
    public void printMatchingTaskMsg(String matchingTasksStr) {
        if (Launcher.IS_GUI) {
            Launcher.out.addOutputln(MATCHING_TASK_MSG);
            Launcher.out.addOutput(matchingTasksStr);
            Launcher.out.print();
        } else {
            System.out.println(MATCHING_TASK_MSG);
            System.out.print(matchingTasksStr);
        }
    }

    /**
     * Prints message when there is no matching tasks found
     */
    public void printNoMatchingMsg() {
        if (Launcher.IS_GUI) {
            Launcher.out.addOutput(NO_MATCHING_MSG);
            Launcher.out.print();
        } else {
            System.out.println(NO_MATCHING_MSG);
        }
    }

    public void printInvalidCommand() {
        String invalidCommandStr = " OOPS!!! I'm sowwy, but I don't know what that means :-(\n\n"
                + " I can help you add tasks with \"todo\", \"deadline\", \"event\"\n"
                + " I can also keep track of all your tasks with \"list\"\n"
                + " If you wanna update certain tasks, use \"mark\" or \"unmark\" and then its number";
        if (Launcher.IS_GUI) {
            Launcher.out.addOutput(invalidCommandStr);
            Launcher.out.print();
        } else {
            System.out.println(invalidCommandStr);
        }
    }

    public void printNotInitialisedCorrectly() {
        String NotInitialisedStr = " Ouhiiee, my head hurrrtt, i dont think im initialized properly ;-;\n"
                + " Please check the database and try again...";
        if (Launcher.IS_GUI) {
            Launcher.out.addOutput(NotInitialisedStr);
            Launcher.out.print();
        } else {
            System.out.print(NotInitialisedStr);
        }
    }

    public void printException(Exception e) {
        if (Launcher.IS_GUI) {
            Launcher.out.addOutput(e.getMessage());
            Launcher.out.print();
        } else {
            System.out.println(e.getMessage());
        }
    }

    public void printException(String errorMsg) {
        if (Launcher.IS_GUI) {
            Launcher.out.addOutput(errorMsg);
            Launcher.out.print();
        } else {
            System.out.println(errorMsg);
        }
    }
    public void printUnexpectedException(Exception e, String errorMsg) {
        String UnexpectedErrStr = " " + errorMsg
                + "\n\n Unexpected error of type:\n"
                + " " + e.getClass().getName()
                + "\n o no, please contact a programmer to fix me!";
        if (Launcher.IS_GUI) {
            Launcher.out.addOutput(UnexpectedErrStr);
            Launcher.out.print();
        } else {
            System.out.println(UnexpectedErrStr);
        }
    }
}
