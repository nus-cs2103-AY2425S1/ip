public class Blob {
    private static boolean active = true;
    public static void main(String[] args) {
        while (active) {
            System.out.println("______________________________________________");
            System.out.println("Hello! I'm Blob");
            System.out.println("What can I do for you?");
            active = !active;
        }
        System.out.println("______________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("______________________________________________");
    }
}
