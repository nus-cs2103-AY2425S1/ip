import java.util.Scanner;

public class Llama {
    public static void main(String[] args) {
        String logo = "";
        String hr = "\t____________________________________________________________" ;
        Scanner sc = new Scanner(System.in);

        // Initializing message
        System.out.println(hr);
        System.out.println("\tHello! I'm Llama!\n" + logo);
        System.out.println("\tWhat can I do for you?");

        // Get user input
        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.println(hr);
            String input = sc.nextLine();
            System.out.println(hr);

            if (input.equals("bye")) {
                shouldContinue = false;
                sc.close();
            } else {
                System.out.println("\t" + input);
            }


        }

        // Exit message
        System.out.println("Baaaaaa byeeee. Come baaaaack soon!");

    }
}
