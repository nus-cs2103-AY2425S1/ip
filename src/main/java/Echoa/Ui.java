package Echoa;

public class Ui {
    public static final String[] INSTRUCTION_LIST = {"todo", "deadline", "event", "mark", "unmark", "delete", "list", "bye"};

    public void greetUserStart() {
        System.out.println("Hello, I'm Echoa!");
        System.out.println("What can I do for you?\n");
    }

    public void greetUserEnd() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void printAddTaskMessage(TaskList taskList) {
        System.out.println("Task added!");
        System.out.println(taskList.getSpecificTask(taskList.getSize() - 1).toString());
    }

    public void printDeleteTaskMessage(TaskList taskList, int index) {
        System.out.println("Task deleted :/");
        System.out.println(taskList.getSpecificTask(index).toString() + "\n");
        System.out.println("Now you have " + (taskList.getSize()) + " task(s).\n");
    }

    public void printMarkTaskMessage(TaskList taskList, int index) {
        System.out.println("Task marked :)");
        System.out.println(taskList.getSpecificTask(index).toString() + "\n");
    }

    public void printUnmarkTaskMessage(TaskList taskList, int index) {
        System.out.println("Task unmarked :(");
        System.out.println(taskList.getSpecificTask(index).toString() + "\n");
    }

    /**
     * The method prints the list of tasks in the taskList.
     *
     * @param taskList taskList to be printed.
     */
    public void printListOfTasks(TaskList taskList) {
        for (int i = 0; i < taskList.getSize(); i++) {
            int label = i + 1;
            System.out.print(label + ". ");
            System.out.println(taskList.getSpecificTask(i).toString());
        }
        if (taskList.getSize() == 0) {
            System.out.println("No tasks in the requested list.");
        }
    }

    public void informNumberOfTasks(TaskList taskList) {
        System.out.println("Now you have " + (taskList.getSize()) + " task(s).\n");
    }

    public void askUserToTryAgain() {
        System.out.println("Please try again.");
    }

    public void printInvalidInstructionExceptionMessage(InvalidInstructionException e) {
        System.out.println(e.getMessage());
    }

    public void printInvalidContentException(InvalidTaskContentException e) {
        System.out.println(e.getMessage());
    }

    public void printInvalidToDoContentException(InvalidToDoContentException e) {
        System.out.println(e.getMessage());
    }

    public void printInvalidDeadlineContentException(InvalidDeadlineContentException e) {
        System.out.println(e.getMessage());
    }

    public void printInvalidEventContentException(InvalidEventContentException e) {
        System.out.println(e.getMessage());
    }

    public void printListOutOfBoundsException(ListOutOfBoundsException e) {
        System.out.println(e.getMessage());
    }


}
