public class FormattedPrint {
    public static void greeting() {
        System.out.println("""
                -----------------------------------------
                Hello! I'm Meep
                What can I do for you?
                -----------------------------------------
                """);
    }

    public static void bye() {
        System.out.println("""
                -----------------------------------------
                Meep: Bye! Have a great day!
                -----------------------------------------
                """);
    }

    public static void doneTask(String task) {
        System.out.println("""
                -----------------------------------------
                Meep: Nice! I've marked this task as done:
                """ + task + """
                -----------------------------------------
                """);

    }

    public static void undoneTask(String task) {
        System.out.println("""
                -----------------------------------------
                Meep: OK, I've marked this task as not done yet:
                """ + task + """
                -----------------------------------------
                """);
    }

    public static void addTask(String task, int size) {
        System.out.println("""
                -----------------------------------------
                Meep: Got it. I've added this task:
                """ + task + "\n" +
                "Now you have " + size + " tasks in the list\n" +
                "-----------------------------------------"
        );
    }

    public static void listTasks(String tasks) {
        System.out.println("""
                -----------------------------------------
                Meep: Here are the tasks in your list:
                """ + tasks +
                "-----------------------------------------"
        );
    }
}
