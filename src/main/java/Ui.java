import java.util.ArrayList;

/**
 * Ui class that handles User Interactions for HoSHI
 */
public class Ui {

    /**
     * Displays the welcome message and logo of Hoshi to the user.
     */
    public void displayWelcome() {

        String logo = """
                 __    __    ______        _______. __    __   __ \s
                |  |  |  |  /  __  \\      /       ||  |  |  | |  |\s
                |  |__|  | |  |  |  |    |   (----`|  |__|  | |  |\s
                |   __   | |  |  |  |     \\   \\    |   __   | |  |\s
                |  |  |  | |  `--'  | .----)   |   |  |  |  | |  |\s
                |__|  |__|  \\______/  |_______/    |__|  |__| |__|\s
                                                                  \s
                """;
        System.out.println(logo);
        System.out.println("""
                ____________________________________________________________
                Hello! I'm Hoshi!
                What can I do for you?
                ____________________________________________________________
                """);

    }

    /**
     * Displays all tasks that the user has previously added to Hoshi
     */
    public void displayTasks(TaskList taskList) {

        if (!taskList.isEmpty()) {
            System.out.println(taskList);
        } else {
            System.out.println("Hoshi doesn't have anything stored! Please add a task first");
        }

    }

    public void displayPrompt() {
        System.out.println("- Add ToDo/Deadline/Event \n" +
                            "- List \n" +
                            "- Mark/Unmark \n" +
                            "- Delete \n" +
                            "- Bye \n " +
                "____________________________________________________________");
    }

    public void displayTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done: \n");
        System.out.println(task.toString() + "\n");
    }

    public void displayTaskToMark() {
        System.out.println("Please specify the task number to mark! \n");
    }

    public void displayTaskToDelete() {
        System.out.println("Please specify the task number to delete! \n");
    }

    public void displayTaskDeleted(Task task) {
        System.out.println("OK, Hoshi has removed ( " + task.getDesc() + " )! \n");
    }

    public void displayTaskAdd() {
        System.out.println("Please specify the task to add! E.g. Add {task to be added} \n");
    }

    public void displayTodoTask() {
        System.out.println("Understood! What is your ToDo? ");
    }

    public void displayDeadlineTask() {
        System.out.println("Understood! What is your Deadline? ");
    }

    public void displayDeadlineDue() {
        System.out.println("When would you like your Deadline to be due by? ");
    }

    public void displayEventTask() {
        System.out.println("Understood! What is your Event? ");
    }

    public void displayEventStart() {
        System.out.println("When would you like your Event to start? ");
    }

    public void displayEventEnd() {
        System.out.println("When would you like your Event to end? ");
    }

    public void displayTaskAdded(String input) {
        System.out.println("added: " + input);
    }

    public void displayError(String message) {
        System.out.println(message);
    }

    public void displayLoadingError(String message) {
        System.out.println("Hoshi can't load data! " + message);
    }

    public void displaySavingError(String message) {
        System.out.println("Hoshi can't save data! " + message);
    }

    public void displayBye() {
        System.out.println("""
                    Bye. Hope to see you again soon!\s
                    ____________________________________________________________
                    """);
    }

}
