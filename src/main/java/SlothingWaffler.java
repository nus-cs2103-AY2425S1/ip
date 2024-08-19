import java.util.Scanner;

public class SlothingWaffler {
    public static void main(String[] args) {
        System.out.println("""
                Hello! I'm the Slothing Waffler!
                Let's stop slothing and get cracking!""");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.strip().equals("bye")) {
                System.out.println("See you next time! Remember to get a waffle!");
                break;
            }
            System.out.println(input);
        }
        scanner.close();
    }
}
