package bobby;

import java.util.ArrayList;

public class Ui {
    static int lengthOfLine = 35;


    public void horizontalLine(int x) {
        for (int i = 0; i < x; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public void showGreeting() {
        horizontalLine(lengthOfLine);
        System.out.println("Good day Sire/Madam! I'm Bobby");
        System.out.println("How may I be of assistance today?");
        horizontalLine(lengthOfLine);
    }

    public void showBye() {
        horizontalLine(lengthOfLine);
        System.out.println("Farewell, have a pleasant journey ahead!");
        horizontalLine(lengthOfLine);
    }

    public void showTaskCreated(Task t, ArrayList<Task> taskList) {
        horizontalLine(lengthOfLine);
        System.out.println("Alright! I have added a new task.");
        System.out.printf("You have a total of %d tasks now.%n", taskList.size());
        horizontalLine(lengthOfLine);
    }

    public void showMarked() {
        horizontalLine(lengthOfLine);
        System.out.println("Sure thing! I will check off this task as done.");
        horizontalLine(lengthOfLine);
    }

    public void showUnmarked() {
        horizontalLine(lengthOfLine);
        System.out.println("Roger that, I will uncheck the task.");
        horizontalLine(lengthOfLine);
    }

    public void showTaskDeleted(Task t, int numberOfTasksLeft) {
        horizontalLine(lengthOfLine);
        System.out.printf("As you wish, I will remove this task: %s%n", t.toString());
        System.out.printf("Now you have %d tasks left.%n", numberOfTasksLeft);
        horizontalLine(lengthOfLine);
    }

    public void showTaskList(ArrayList<Task> taskList) {
        int numberOfTasks = taskList.size();
        if (taskList.isEmpty()) {
            horizontalLine(lengthOfLine);
            System.out.println("Sire/Madam, you have no tasks at hand right now.");
            horizontalLine(lengthOfLine);
        } else {
            horizontalLine(lengthOfLine);
            for (int i = 0; i < numberOfTasks; i++) {
                int number = i + 1;
                System.out.println(number + ". " + taskList.get(i));
            }
            horizontalLine(lengthOfLine);
        }
    }
}
