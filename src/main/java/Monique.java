//imports for user input
import java.io.*;

public class Monique {
    public static void main(String[] args) throws IOException{
        String HORIZONTAL_LINE = "_____________________________________________";
        System.out.println(HORIZONTAL_LINE + "\n");
        System.out.println("Hello, I am Monique,\nWhat can I do for you today?");
        System.out.println(HORIZONTAL_LINE + "\n");
        boolean active = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (active) {
            System.out.println("user: ");
            String userInput = br.readLine();
            if (userInput.equalsIgnoreCase("bye")){
                active = false;
                System.out.println("Monique: Goodbye! Have a great day!");
            } else {
                System.out.println(userInput);
            }
        }

        System.out.println("Ciao!");
    }
}
