import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        String blankline = "____________________________________________________________ \s";
        String skeleton = """
                ____________________________________________________________ \s
                 Hello! I'm Bob \s
                 What can I do for you? \s
                ____________________________________________________________ \s
                """;
        System.out.println(skeleton);
        
        Scanner scanner = new Scanner(System.in);
        
        boolean exit = false;
        while (!exit) {
            System.out.print("Text: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exit = true;
            } else {
                System.out.println("Bob: " + input + "\n" + blankline);
            }
        }
        System.out.println(blankline + "\n" + "Bye. Hope to see you again soon!");

    }
}
