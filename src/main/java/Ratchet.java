public class Ratchet {

    public static void main(String[] args) {
        lineBreak();
        greet();
        lineBreak();
        exit();
        lineBreak();
    }

    public static void lineBreak() {
        System.out.println("________________________________________________________");
    }

    public static void greet() {
        System.out.println("Hello! I'm Ratchet\nWhat can I do for you?" );
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
