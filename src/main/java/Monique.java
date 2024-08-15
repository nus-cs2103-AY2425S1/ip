//imports for user input
import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Monique {
    //Create array to store tasks
    private static Task[] taskList = new Task[100];
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
            } else if (userInput.startsWith("mark")) {
                //minus one bc 0-based indexing
                int itemNum = (Integer.parseInt(userInput.split(" ")[1])) - 1;
                taskList[itemNum] =taskList[itemNum].mark();
                System.out.println("Nice lah.. Great job on doing work! I've marked it: ");
                System.out.println((itemNum+1) + "." + taskList[itemNum].toString());

            } else if (userInput.startsWith("unmark")) {
                int itemNum = (Integer.parseInt(userInput.split(" ")[1])) - 1;
                taskList[itemNum] =taskList[itemNum].unmark();
                System.out.println("ok... I've unmarked:");
                System.out.println((itemNum+1) + "." + taskList[itemNum].toString());
            }
            else {
                //add to taskList
                taskList[numItems] = new ToDo(userInput);
                numItems++;
                System.out.println("added: " + userInput);
            }
        }

        System.out.println("Ciao!");
    }
}
