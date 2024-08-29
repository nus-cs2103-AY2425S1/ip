package donna;

import donna.task.Task;

import java.util.List;

public class Ui {
    static private void printDashedLine() {
        System.out.println("____________________________________________________________________");
    }
    static private void printDonnaLogo() {
        System.out.println(" ____      ");
        System.out.println("|  _ \\  ___  _ __  _ __   __ _ ");
        System.out.println("| | | |/ _ \\| '_ \\| '_ \\ / _` |");
        System.out.println("| |_| | (_) | | | | | | | (_| |");
        System.out.println("|____/ \\___/|_| |_|_| |_|\\__,_|");
        System.out.println();
    }

    public void printGreeting(boolean dataWasLoaded) { // T/F depending on whether the arrayList is empty
        printDashedLine();
        printDonnaLogo();
        System.out.println("Hello! I'm Donna");
        if (dataWasLoaded) {
            System.out.println("We have had a chat before! Let's resume :)");
        } else {
            System.out.println("What can I do for you?");
        }
        printDashedLine();
    }

    public void printGoodbyeMessage() {
        printDashedLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDashedLine();
    }

    public void printTaskAddedMessage(Task task, int taskCount) {
        printDashedLine();
        System.out.println("Got it. I've added this task: " );
        System.out.println("    " + task);
        if (taskCount == 1) {
            System.out.println("This is the first task in the list. ");
        } else {
            System.out.println("You now have " + taskCount + " tasks in the list. ");
        }
        printDashedLine();
    }

    public void printTaskDeletedMessage(Task task, int taskCount) {
        printDashedLine();
        System.out.println("Alright. The following task has been deleted: ");
        System.out.println("    " + task);
        if (taskCount != 1){
            System.out.println("You now have " + taskCount + " tasks in the list.");
        } else {
            System.out.println("You now have 1 task left in the list.");
        }
        printDashedLine();
    }

    public void printTaskMarkedMessage(Task task, boolean isMarked) {
        printDashedLine();
        if (isMarked) {
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            System.out.println("OK, I have marked this task as not done yet: ");
        }
        System.out.println("    " + task);
        printDashedLine();
    }

    public void printTaskList(TaskList tasks) {
        printDashedLine();
        if (tasks.isEmpty()) {
            System.out.println("No tasks added to the list yet."+ "\n"
                    + "use todo / deadline / event to add tasks to the list!");

        } else {
            System.out.println("There are " + tasks.size() + " task(s) in the list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        printDashedLine();
    }

    public void printErrorMessage(String message) {
        printDashedLine();
        System.out.println(message);
        printDashedLine();
    }

    public void printFindResults(List<Task> tasks) {
        printDashedLine();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found matching the search criteria :(");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        printDashedLine();
    }

}
