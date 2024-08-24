import java.util.Scanner;

public class Alisa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String divider = "------------------------------------------------------------------------------------";
        String exitCommand = "bye";
        String input = "";

        System.out.println("Hey, Alisa here! What do you need help with?");
        System.out.println("BTW Say the word bye in any part of your sentence to get out of this conversation");
        System.out.println(divider);
        input = sc.nextLine();
        while (!input.contains(exitCommand)) {
            System.out.println(divider);
            System.out.println(input);
            System.out.println(divider);
            input = sc.nextLine();
        }
        System.out.println(divider);
        System.out.println("Since you technically said bye, see ya next time!");
        System.out.println(divider);
        sc.close();
    }
}







