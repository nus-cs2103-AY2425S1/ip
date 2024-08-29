package elster;

import elster.tasks.Task;

public class Ui {
    public void printList(TaskList list) {
        printLine();
        System.out.println(list.printString());
        printLine();
    }

    protected static void printLine() {
        System.out.println("    ____________________________________________________________________________");
    }

    public void goodbyeMessage() {
        printLine();
        System.out.println("    See you next time.");
        printLine();
    }

    public void nonsenseErrorMessage() {
        printLine();
        System.out.println("    Could you, like, write a sensible command please? \n");
        printLine();
    }

    public void saveFileErrorMessage() {
        printLine();
        System.out.println("    there hath been a failure in saving your work");
        printLine();
    }

    public void loadFileErrorMessage() {
        printLine();
        System.out.println("    there hath been a failure in loading your work\n"
                + "    your list starts empty");
        printLine();
    }

    public void welcomeMessage() {
        String logo = "___________.__            __\n"
                + "\\_   _____/|  |   _______/  |_  ___________\n"
                + " |    __)_ |  |  /  ___/\\   __\\/ __ \\_  __ \\\n"
                + " |        \\|  |__\\___ \\  |  | \\  ___/|  | \\/\n"
                + "/_______  /|____/____  > |__|  \\___  >__|\n"
                + "        \\/           \\/            \\/";
        System.out.println(logo);
        printLine();
        System.out.println("    Hello, \"greetings\" from your friendly neighbourhood chatbot Elster..");
        System.out.println("    How can I help you today :|");
        printLine();
    }

    public void taskDoneMessage(Task task) {
        printLine();
        System.out.println("    Yes boss, marked the task as done.");
        System.out.println("      " + task.toString());
        printLine();
    }

    public void taskUndoneMessage(Task task) {
        printLine();
        System.out.println("    Interesting choice, I've marked the task as not done.");
        System.out.println("      " + task.toString());
        printLine();
    }

    public void indexOutOfBoundsErrorMessage() {
        printLine();
        System.out.println("    Ain't no such task in the middle of these woods");
        printLine();
    }

    public void alreadyDoneErrorMessage() {
        printLine();
        System.out.println("    So uh, the task is already done");
        printLine();
    }

    public void alreadyUndoneErrorMessage() {
        printLine();
        System.out.println("    So uh, the task already is not done");
        printLine();
    }

    public void deleteTaskMessage(TaskList taskList, Task task) {
        printLine();
        System.out.println("    Your bidding has been done, removed:");
        System.out.println("      " + task.toString());

        if (taskList.getSize() == 1) {
            System.out.println("    thou now hath " + taskList.getSize() + " task to complete");
        } else if (taskList.isEmpty()) {
            System.out.println("    thou hath no tasks to be completed");
        } else {
            System.out.println("    thou now hath " + taskList.getSize() + " tasks to complete");
        }
        printLine();
    }

    public void addTaskMessage(TaskList taskList, Task task) {
        printLine();
        System.out.println("    The task hath been added");
        System.out.println("      " + task);

        if (taskList.getSize() == 1) {
            System.out.println("    thou now hath " + taskList.getSize() + " task to complete");
        } else {
            System.out.println("    thou now hath " + taskList.getSize() + " tasks to complete");
        }
        printLine();
        System.out.println();
    }
}
