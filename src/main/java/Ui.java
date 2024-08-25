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

    /**
     * Displays default prompt with options for user to select from
     */
    public void displayPrompt() {
        System.out.println("- Add ToDo/Deadline/Event \n" +
                            "- List \n" +
                            "- Mark/Unmark \n" +
                            "- Delete \n" +
                            "- Bye \n " +
                "____________________________________________________________");
    }

    /**
     * Displays text requesting user to specify which task to mark
     */
    public void displayTaskToMark() {
        System.out.println("Please specify the task number to mark! \n");
    }

    /**
     * Displays text indicating text has been marked as done
     */
    public void displayTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done: \n");
        System.out.println(task.toString() + "\n");
    }

    /**
     * Displays text requesting user to specify which task to delete
     */
    public void displayTaskToDelete() {
        System.out.println("Please specify the task number to delete! \n");
    }

    /**
     * Displays text indicating which task was deleted
     */
    public void displayTaskDeleted(Task task) {
        System.out.println("OK, Hoshi has removed ( " + task.getDesc() + " )! \n");
    }

    /**
     * Displays text requesting user to specify which task to add
     */
    public void displayTaskAdd() {
        System.out.println("Please specify the task to add! E.g. Add {task to be added} \n");
    }

    /**
     * Displays text requesting user to specify to do to add
     */
    public void displayTodoTask() {
        System.out.println("Understood! What is your ToDo? ");
    }

    /**
     * Displays text requesting user to specify deadline to add
     */
    public void displayDeadlineTask() {
        System.out.println("Understood! What is your Deadline? ");
    }

    /**
     * Displays text requesting user to specify when deadline is due
     */
    public void displayDeadlineDue() {
        System.out.println("When would you like your Deadline to be due by? ");
    }

    /**
     * Displays text requesting user to specify event to add
     */
    public void displayEventTask() {
        System.out.println("Understood! What is your Event? ");
    }

    /**
     * Displays text requesting user to specify when event starts
     */
    public void displayEventStart() {
        System.out.println("When would you like your Event to start? ");
    }

    /**
     * Displays text requesting user to specify when event ends
     */
    public void displayEventEnd() {
        System.out.println("When would you like your Event to end? ");
    }

    /**
     * Displays text indicating task has been added
     */
    public void displayTaskAdded(String input) {
        System.out.println("added: " + input);
    }

    /**
     * Displays text indicating an error has occurred
     */
    public void displayError(String message) {
        System.out.println(message);
    }

    /**
     * Displays text indicating a loading error has occurred
     */
    public void displayLoadingError(String message) {
        System.out.println("Hoshi can't load data! " + message);
    }

    /**
     * Displays text indicating a saving error has occurred
     */
    public void displaySavingError(String message) {
        System.out.println("Hoshi can't save data! " + message);
    }

    /**
     * Displays text indicating the program has terminated
     */
    public void displayBye() {
        System.out.println("""
                    Bye. Hope to see you again soon!\s
                    ____________________________________________________________
                    """);
    }

}
