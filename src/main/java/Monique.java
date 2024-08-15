//imports for user input
import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Monique {
    //Create array to store tasks
    private static String[] taskList = new String[100];
    //Create counter to store the number of items in taskList;
    private static int numItems =0;

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
            }
            else if (userInput.equalsIgnoreCase("list")) {
                System.out.println(HORIZONTAL_LINE);
                IntStream.range(0,numItems)
                        .forEach(i -> {
                            System.out.println((i+1) + "." + taskList[i]);
                        });
                System.out.println(HORIZONTAL_LINE);
            } else {
                //add to taskList
                taskList[numItems] = userInput;
                numItems++;
                System.out.println("added: " + userInput);
            }
        }

        System.out.println("Ciao!");
    }
}
