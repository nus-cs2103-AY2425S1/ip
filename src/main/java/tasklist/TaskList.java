package tasklist;

import java.util.ArrayList;
import task.*;
import ui.Ui;

public class TaskList {

    private ArrayList<Task> botMemory;

    public TaskList(ArrayList<Task> botMemory) {
        this.botMemory = botMemory;
    }

    /**
     * Returns all the items in the ArrayList as a task list.
     * @return String
     */
    public String listToString() {
        StringBuilder outputString = new StringBuilder();
        if (this.botMemory.isEmpty()) {
            outputString.append("No items in your list");
        } else {
            outputString.append("Here are the tasks in your list: \n");
            for (int i = 0; i < this.botMemory.size(); i++) {
                outputString.append((i + 1)).append(". ").append(this.botMemory.get(i).toString()).append("\n");
            }
        }
        System.out.println(outputString.toString());
        return outputString.toString().trim();
    }

    public ArrayList<Task> getBotMemory() {
        return this.botMemory;
    }

    public void findTerm(String searchTerm) {
        ArrayList<Task> resultArray = new ArrayList<>();
        if (this.botMemory.isEmpty()) {
            Ui.printLine();
            System.out.println("There are no items in the list at the moment, enter '/help' to find out how to enter them.");
            Ui.printLine();
        } else {
            for (int i = 0; i < this.botMemory.size(); i++) {
                if (this.botMemory.get(i).description.contains(searchTerm)) {
                    resultArray.add(this.botMemory.get(i));
                }
            }
        }

        if (resultArray.size() > 0) {
            Ui.printLine();
            System.out.println("Here are the matching tasks in your list: ");
            for (int j = 0; j < resultArray.size(); j++) {
                System.out.println((j + 1) + ". " + resultArray.get(j).toString());
            }
            Ui.printLine();
        } else {
            System.out.println("No matching tasks found.");
        }


    }

    /**
     * Toggles the boolean value corresponding to whether the task is finished or not.
     * @param taskNumber
     */

    public void toggleTaskDone(int taskNumber) {
        botMemory.get(taskNumber).toggleTaskDone();
        Ui.printLine();
    }

    /**
     * Removes tasks from the ArrayList and informs the user.
     * @param taskToRemove
     */
    public void removeTask(int taskToRemove) {
        Ui.printLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(" " + botMemory.get(taskToRemove).toString());
        botMemory.remove(taskToRemove);
        System.out.println("Now you have " + botMemory.size() + " tasks in the list.");
        Ui.printLine();
    }

    /**
     * Adds tasks to the ArrayList and informs the user.
     * @param newTask
     */
    public void addTask(Task newTask) {
        botMemory.add(newTask);
        Ui.printLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println(" " + newTask.toString());
        System.out.println("Now you have " + botMemory.size() + " task(s) in the list.");
        System.out.println();
        this.listToString();
        Ui.printLine();

    }


}
