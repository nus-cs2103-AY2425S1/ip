import java.util.Scanner;

public class Llama {
    public static void main(String[] args) {
        String logo = "";
        String hr = "____________________________________________________________" ;
        Scanner sc = new Scanner(System.in);

        // Initializing message
        System.out.println(hr);
        System.out.println("Hello! I'm Llama!\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println(hr);

        // Get user input
        boolean shouldContinue = true;
        while (shouldContinue) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                shouldContinue = false;
                sc.close();
            }
        }

        // Exit message
        System.out.println(hr);
        System.out.println("Baaaaaa byeeee. Come baaaaack soon!");

    }
}
