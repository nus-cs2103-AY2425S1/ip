public class Molly {
    public static String name = "Molly";

    public Molly() {

    }

    public static void greetUser() {
        System.out.println("Hello! I'm " + Molly.name);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    public static void main(String[] args) {
        Molly.greetUser();
        Molly.sayBye();
    }
}
