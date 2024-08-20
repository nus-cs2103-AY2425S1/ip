import java.util.Scanner;

public class Broski {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "_________________________________________";
        String exit = "Bye. See ya around!";
        System.out.println(line);
        System.out.println("Wassup! I'm Broski!");
        System.out.println("What can I do for you bro?");
        System.out.println(line);
        String reply = scanner.nextLine();
        System.out.println(line);
        while (!reply.equals("bye")) {
            System.out.println(reply);
            System.out.println(line);
            reply = scanner.nextLine();
            System.out.println(line);
        }
        System.out.println(exit);
        System.out.println(line);
    }
}
