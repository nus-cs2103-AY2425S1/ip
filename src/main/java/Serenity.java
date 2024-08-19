public class Serenity {
    public static void main(String[] args) {
        String horizontalLine = "__________________________________________";
        System.out.println(horizontalLine);
        greet();
        System.out.println(horizontalLine);
        exit();
        System.out.println(horizontalLine);
    }

    public static void greet() {
        System.out.println("Hi, I'm Serenity!\n" + "What can I do for you?");
    }

    public static void exit() {
        System.out.println("Goodbye. Hope to see you again soon!");
    }
}
