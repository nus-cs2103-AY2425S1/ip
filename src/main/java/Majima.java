import java.util.Scanner;

public class Majima {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String linegap = "____________________________________________________________";
        System.out.println(linegap);
        System.out.println("KIIIIIRYU-CHAN! It's ya old pal, Majima!");
        System.out.println("What can I do fer ya?");
        System.out.println(linegap);
        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("Bye")) {
                System.out.println(linegap);
                System.out.println("Bye bye! Don't keep me waiting fer too long now, ya hear?");
                System.out.println(linegap);
                break;
            }

            System.out.println(linegap);
            System.out.println(input);
            System.out.println(linegap);
        }

        scanner.close();
    }
}
