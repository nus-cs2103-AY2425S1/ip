public class Ui {
    public void displayEnd() {
        System.out.println("""
            ____________________________________________________________
             Bye. Hope to see you again soon!
            ____________________________________________________________

            """
        );
    }

    public void displayStart() {
        System.out.println("""
            ____________________________________________________________
             Hello! I'm DemureBot
             What can I do for you?
            ____________________________________________________________

            """
        );
    }

    public void displayMarkTask(Task task) {
        System.out.println("____________________________________________________________\n" +
                " Nice! I've marked this task as done:\n   " +
                task + "\n" +
                "____________________________________________________________\n" +
                "\n"
        );
    }

    public void displayUnmarkTask(Task task) {
        System.out.println("____________________________________________________________\n" +
                " OK, I've marked this task as not done yet:\n   " +
                task + "\n" +
                "____________________________________________________________\n" +
                "\n"
        );
    }

    public void displayDeleteTask(Task task, int size) {
        System.out.println("____________________________________________________________\n" +
                " Noted. I've removed this task:\n   " +
                task + "\n" +
                "Now you have " + size + " tasks in the list.\n" +
                "____________________________________________________________\n" +
                "\n"
        );
    }

    public void displayAddTask(Task task, int size) {
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n  " +
                task + "\n" +
                "Now you have " + size + " tasks in the list.\n" +
                "____________________________________________________________\n" +
                "\n"
        );
    }
}
