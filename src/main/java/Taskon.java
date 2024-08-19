public class Taskon {
    public static void main(String[] args) {
        greet();
        exit();
    }

    public static void greet() {
        String greeting = "Hello! I'm Taskon\nWhat can I do for you?\n";
        System.out.println(greeting);
    }

    public static void exit() {
        String exiting = "Bye. Hope to see you again soon!\n";
        System.out.println(exiting);
    }
}
