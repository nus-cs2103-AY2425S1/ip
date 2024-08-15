//imports for user input
import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.Set;

public class Monique {
    //Create array to store tasks
    private static Task[] taskList = new Task[100];
    //Create counter to store the number of items in taskList;
    private static int numItems =0;
    private static final Set<String> commands = Set.of("list", "mark", "unmark", "bye");
    private static final Set<String> taskTypes = Set.of("todo", "deadline", "event");

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
            //get first word to see if it is a command
            String firstWord = userInput.split(" ")[0];

            if (commands.contains(firstWord)) {
                switch(firstWord) {
                    case "bye": {
                        active = false;
                        System.out.println("Monique: Goodbye! Have a great day!");
                        break;
                    }
                    case "list": {
                        System.out.println(HORIZONTAL_LINE);
                        IntStream.range(0, numItems)
                                .forEach(i -> {
                                    System.out.println((i + 1) + "." + taskList[i]);
                                });
                        System.out.println(HORIZONTAL_LINE);
                        break;
                    }
                    case "mark": {
                        //minus one bc 0-based indexing
                        int itemNum = (Integer.parseInt(userInput.split(" ")[1])) - 1;
                        taskList[itemNum] = taskList[itemNum].mark();
                        System.out.println("Nice lah.. Great job on doing work! I've marked it: ");
                        System.out.println((itemNum + 1) + "." + taskList[itemNum].toString());
                        break;
                    }
                    case "unmark": {
                        int itemNum = (Integer.parseInt(userInput.split(" ")[1])) - 1;
                        taskList[itemNum] = taskList[itemNum].unmark();
                        System.out.println("ok... I've unmarked:");
                        System.out.println((itemNum + 1) + "." + taskList[itemNum].toString());
                        break;
                    }
                }
            } else if (taskTypes.contains(firstWord)) {
                //add to taskList
                switch(firstWord){
                    case "todo": {
                        String[] words = userInput.split(" ");
                        String description = String.join(" ", Arrays.copyOfRange(words,1,words.length));
                        taskList[numItems] = new ToDo(description);
                        System.out.println("added: " + taskList[numItems]);
                        numItems++;
                        break;
                    }
                    case "deadline": {
                        String[] parts = userInput.split("/by");
                        String by = parts.length > 1 ? parts[1].trim() : "";
                        String[] commandAndDescription = parts[0].trim().split(" ", 2);
                        String description = commandAndDescription.length > 1 ? commandAndDescription[1] : "";
                        taskList[numItems] = new Deadline(description,false,by);
                        System.out.println("Got it, I've added this deadline " + taskList[numItems]);
                        numItems++;
                        break;
                    }
                    case "event": {
                        String[] fromSplit = userInput.split("/from");
                        String[] toSplit = fromSplit[1].split("/to");
                        String[] commandAndDescription = fromSplit[0].trim().split(" ", 2);
                        String description = commandAndDescription.length > 1 ? commandAndDescription[1] : "";
                        String fromDate = toSplit[0].trim();
                        String toDate = toSplit.length > 1 ? toSplit[1].trim() : "";
                        taskList[numItems] = new Event(description,false, fromDate,toDate);
                        System.out.println("Got it, I've added this event " + taskList[numItems]);
                        numItems++;
                        break;
                    }
                }
            } else {
                System.out.println("Eh cannot like this lah bro.. you never even follow command type");
            }

        }

        System.out.println("Ciao!");
    }
}
