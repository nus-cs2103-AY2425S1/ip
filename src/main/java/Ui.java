import java.util.ArrayList;

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
    public void displayTasks(ArrayList<Task> arrayList) {

        if (!arrayList.isEmpty()) {
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.println(i + 1 + ". " + arrayList.get(i).toString() + "\n");
            }
        } else {
            System.out.println("Hoshi doesn't have anything stored! Please add a task first");
        }

    }

    public void displayTaskAdded(String input) {
        System.out.println("added: " + input);
    }

    public void displayTaskDeleted(String description) {
        System.out.println("OK, Hoshi has removed ( " + description + " )! \n");
    }

    public void displayError(String message) {
        System.out.println(message);
    }

    public void displayBye() {
        System.out.println("""
                    Bye. Hope to see you again soon!\s
                    ____________________________________________________________
                    """);
    }

}
