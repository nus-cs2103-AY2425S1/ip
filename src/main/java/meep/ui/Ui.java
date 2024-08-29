package meep.ui;

public class Ui {

    public void greeting() {
        System.out.println("""
                -----------------------------------------
                Hello! I'm Meep
                What can I do for you?
                -----------------------------------------
                """);
    }

    public void bye() {
        System.out.println("""
                -----------------------------------------
                Meep: Bye! Have a great day!
                -----------------------------------------
                """);
    }

    public void inputWaiting() {
        System.out.print("You: ");
    }

    public void doneTask(String task) {
        System.out.println("""
                -----------------------------------------
                Meep: Nice! I've marked this task as done:
                """ + task + """
                -----------------------------------------
                """);

    }

    public void undoneTask(String task) {
        System.out.println("""
                -----------------------------------------
                Meep: OK, I've marked this task as not done yet:
                """ + task + """
                -----------------------------------------
                """);
    }

    public void addTask(String task, int size) {
        System.out.println("""
                -----------------------------------------
                Meep: Got it. I've added this task:
                """ + task + "\n" +
                "Now you have " + size + " tasks in the list\n" +
                "-----------------------------------------"
        );
    }

    public void deleteTask(String task, int size) {
        System.out.println("""
                -----------------------------------------
                Meep: Noted. I've removed this task:
                """ + task + "\n" +
                "Now you have " + (size - 1) + " tasks in the list\n" +
                "-----------------------------------------"
        );
    }

    public void listTasks(String tasks) {
        System.out.println("""
                -----------------------------------------
                Meep: Here are the tasks in your list:
                """ + tasks +
                "-----------------------------------------"
        );
    }

    public void invalidCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, I don't understand that command. Please try again.
                -----------------------------------------
                """);
    }

    public void invalidTodoCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with your todo command.
                Todo command should be in the format: todo <description>
                -----------------------------------------
                """);
    }

    public void invalidDeadlineCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with your deadline command.
                Deadline command should be in the format: deadline <description> /by <date>
                -----------------------------------------
                """);
    }

    public void invalidEventCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with your event command.
                Event command should be in the format: event <description> /from <date> /to <date>
                -----------------------------------------
                """);
    }

    public void invalidMarkCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with your mark command.
                Mark command should be in the format: mark <index>
                The index should be a number within the range of the list
                -----------------------------------------
                """);
    }

    public void invalidUnmarkCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with your unmark command.
                Unmark command should be in the format: unmark <index>
                The index should be a number within the range of the list
                -----------------------------------------
                """);
    }

    public void invalidDeleteCommand() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with your delete command.
                Delete command should be in the format: delete <index>
                The index should be a number within the range of the list
                -----------------------------------------
                """);
    }

    public void invalidDateFormat() {
        System.out.println("""
                -----------------------------------------
                Meep: Sorry, there is something wrong with the date format.
                Please use the format: d/M/yyyy HHmm
                -----------------------------------------
                """);
    }

    public void errorLoadingTask() {
        System.out.println("""
                -----------------------------------------
                Meep: Error loading task: The saved task is in an invalid format.
                -----------------------------------------
                """);
    }

    public void error() {
        System.out.println("""
                -----------------------------------------
                Meep: An error occurred. Please try again.
                -----------------------------------------
                """);
    }
}
