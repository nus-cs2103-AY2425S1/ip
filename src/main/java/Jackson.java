import java.util.Scanner;

public class Jackson {
    public static void main(String[] args) {
        String name = "Jackson";
        String response = "";
        Scanner sc = new Scanner(System.in);

        System.out.printf("Hello! My name is %s!\nWhat can I do for you today?\n> ", name);
        response = sc.nextLine();

        while (!response.equals("bye")) {
            System.out.println(response);
            System.out.print("> ");
            response = sc.nextLine();
        }

        System.out.println("Goodbye! See you soon!");
    }
}
