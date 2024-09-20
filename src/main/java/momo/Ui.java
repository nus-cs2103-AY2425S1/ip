package momo;

import momo.task.TaskList;


/**
 * Handles Ui related functionality in the chatbot program, handling some user interactions
 */
public class Ui {

    public static void showHorizontalLine() {
        System.out.println("____________________________________________________________");
    }


    /**
     * Hihihih.
     * @param list
     * @return
     */
    public String printList(TaskList list) {

        if (list.getCount() == 0) {
            return "Your list is EMPTY \n Better start filling it up soon...";
        }

        StringBuilder listToPrint = new StringBuilder();

        for (int i = 0; i < list.getCount(); i++) {
            listToPrint.append(i + 1).append(". ").append(list.getTask(i)).append("\n");
        }

        return listToPrint.toString();

    }

    /**
     * Shows an ominous farewell message and terminates the program
     */
    public void showFarewell() {

        String text = "Farewell... for now. I'll be waiting for your return, taking refuge in your shadows. Rest"
                + " well.... wħɨłɇ ɏøᵾ sŧɨłł ȼȺn\n";

        System.exit(1);
    }


}











