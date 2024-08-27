public class UI {
    // Handles System Interactions
    public static void updateUserOnAddition(String name, int size) {
        System.out.println("I've added: " + name);
        System.out.println(String.format("Now you have %d in the list!", size));
    }

    public static void updateUserOnDeletion(Task t) {
        System.out.println("Noted! I've removed this task:");
        System.out.println(t.toString());
    }

    public static void updateUserOnUncompletion(Task t) {
        System.out.println("OK! I've marked the task as not done yet: ");
        System.out.println(t.toString());
    }

    public static void updateUserOnCompletion(Task t) {
        System.out.println("Good job! I've marked this task as done: ");
        System.out.println(t.toString());
    }

    public static void updateUserOnError(Exception e) {
        System.out.println("Some error has occured, please try again.");
        System.out.println(e.getMessage());
    }

    public static void greetUserOnEntry() {
        System.out.println("Hello! I'm Peter!\nWhat can I do for you?\n");
    }
    
    public static void updateUserOnExit() {
        System.out.println("\nBye, hope to see you again soon!");
    }
}
