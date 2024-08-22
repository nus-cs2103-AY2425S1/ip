import java.util.ArrayList;
import java.util.Scanner;

public class Topaz {

    public static void main(String[] args) {
        greetUser();
        goodbyeUser();
    }

    private static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Topaz — I work in the IPC's Strategic Investment Department. Long time no see! How have you been? Clocking in a lot of overtime?");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void goodbyeUser() {
        System.out.println(" Ugh, why do they have to call me for such a trivial matter... Sorry, something just came up at work, I've got to get going!");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
