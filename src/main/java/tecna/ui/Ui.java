package tecna.ui;

import tecna.collection.TaskList;
import tecna.task.Task;

public class Ui {
    public String printLogo() {
        String logo =
                " **          **\n" +
                "*  *        *  *\n" +
                "*   *      *   *\n" +
                "*    *    *    *\n" +
                "*     *  *     *\n" +
                " *     **     *\n" +
                "  *    **    *\n" +
                "   *   **   *\n" +
                "    *  **  *\n" +
                "     ******\n" +
                "      ****\n" +
                "     * ** *\n" +
                "    *  **  *\n" +
                "    ***  ***\n";

        System.out.println(logo);
        return logo;
    }

    public String printHelloMsg() {
        System.out.println("I'm Tecna!\nHow can I help you?");
        printSectionLine();
        return "I'm Tecna!\nHow can I help you?";
    }

    public String printGoodbyeMsg() {
        String msg = "Pleased to help you! See you again ^_^";
        System.out.println(msg);
        printSectionLine();
        return msg;
    }

    public String printItems(TaskList tasks) {
        return tasks.listItems();
    }

    public void printSectionLine() {
        System.out.println("----------------------------------------------");
    }

    public String printMarkMsg(Task markedTask) {
        StringBuilder sb = new StringBuilder("Nice job! I've mark this as done. You deserve a short break <3\n");
        sb.append(markedTask);
        String msg = sb.toString();
        System.out.println(msg);
        printSectionLine();
        return msg;
    }

    public String printUnmarkMsg(Task unmarkedTask) {
        StringBuilder sb = new StringBuilder("I've mark this as undone. Keep going, my friend!\n");
        sb.append(unmarkedTask);
        String msg = sb.toString();
        System.out.println(msg);
        return msg;
    }

    public String printDeleteItemMsg(TaskList tasks, int index) {
        return tasks.deleteItem(index);
    }

    public String printFindTasksMsg(TaskList tasks, String keyword) {
        return tasks.findTasks(keyword);
    }

    public String printAddItemMsg(TaskList tasks, Task item) {
        return tasks.addItem(item);
    }

    public String printInvalidCmdError() {
        String msg = "Oops! Your request sounds strange for me. Please enter a valid request ^^";
        System.out.println(msg);
        return msg;
    }

    public String printError(String error) {
        StringBuilder sb = new StringBuilder("Oops! " + error);
        String msg = sb.toString();
        System.out.println(msg);
        return msg;
    }
}
