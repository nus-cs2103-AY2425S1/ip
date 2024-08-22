public class Pixel {
    public static String LINE = "------------------------------------";
    public static void greeting() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Pixel!");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
    public static void main(String[] args) {
        greeting();
        exit();
    }
}
