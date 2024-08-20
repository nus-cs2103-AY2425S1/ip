import java.util.Scanner;

public class Twilight {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I am Twilight your personal assistant\nWhat can I do for you?" );
        String s = input.nextLine();
        while (!s.equals("bye")) {
            System.out.println(s);
            s = input.nextLine();
        }
        System.out.println("See you");
    }
}
