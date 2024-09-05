package Echoa;

public class Ui {
    public static final String[] INSTRUCTION_LIST = {"todo", "deadline", "event", "mark", "unmark", "delete", "list", "bye"};

    public String greetUserStart() {
        return "Hello, I'm Echoa!\n" +
               "What can I do for you?\n";
    }


    public String greetUserEnd() {
        return "Bye. Hope to see you again soon!\n";
    }

    public String printAddTaskMessage(TaskList taskList) {
        return "Task added!\n" +
                taskList.getSpecificTask(taskList.getSize() - 1).toString() +
                "\n";
    }

    public String printDeleteTaskMessage(TaskList taskList, int index) {
        return "Task deleted :/\n" +
                taskList.getSpecificTask(index).toString() + "\n" +
                "Now you have " + (taskList.getSize()) + " task(s).\n";
    }

    public String printMarkTaskMessage(TaskList taskList, int index) {
        return "Task marked :)\n" +
                taskList.getSpecificTask(index).toString() + "\n";
    }

    public String printUnmarkTaskMessage(TaskList taskList, int index) {
        return "Task unmarked :(\n" +
                taskList.getSpecificTask(index).toString() + "\n";
    }

    /**
     * The method prints the list of tasks in the taskList.
     *
     * @param taskList taskList to be printed.
     */
    public String printListOfTasks(TaskList taskList) {
        String message = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            int label = i + 1;
            message += label + ". ";
            message += taskList.getSpecificTask(i).toString() + "\n";
        }
        if (taskList.getSize() == 0) {
            message = "No tasks in the requested list.";
        }

        return message += "\n";
    }

    public String informNumberOfTasks(TaskList taskList) {
        return "Now you have " + (taskList.getSize()) + " task(s).\n";
    }

    public String printExceptionMessage(Exception e) {
        return e.getMessage() + "\n" + "Please try again.\n";
    }
}
