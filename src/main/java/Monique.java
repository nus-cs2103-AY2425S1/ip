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
    private static final Set<String> commands = Set.of("list", "mark", "unmark", "bye", "/commands");
    private static final Set<String> taskTypes = Set.of("todo", "deadline", "event");

    public static void main(String[] args) throws IOException, MarkException, ParseException, UnknownCommandException {
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
            boolean hasSecondWord = userInput.split(" ").length >1;

            try {
                if (!commands.contains(firstWord) && !taskTypes.contains(firstWord)) {
                    throw new UnknownCommandException();
                }
            } catch (UnknownCommandException unknowne){
                unknowne.advice();
            }


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
                        try {
                            if (!hasSecondWord) {
                                throw new ParseException();
                            }
                            int itemNum = (Integer.parseInt(userInput.split(" ")[1])) - 1;
                            if (itemNum > numItems - 1 || itemNum <0 ) {
                                throw new MarkException();
                            }
                            taskList[itemNum] = taskList[itemNum].mark();
                            System.out.println("Nice lah.. Great job on doing work! I've marked it: ");
                            System.out.println((itemNum + 1) + "." + taskList[itemNum].toString());
                            break;
                        } catch (MarkException marke) {
                            marke.advice();
                        } catch (ParseException pe) {
                            pe.advice();
                        } finally {
                            //ensures breaking out of the loop?
                            break;
                        }
                    }
                    case "unmark": {
                        try {
                            if (!hasSecondWord) {
                                throw new ParseException();
                            }
                            int itemNum = (Integer.parseInt(userInput.split(" ")[1])) - 1;
                            if (itemNum > numItems-1 || itemNum<0){
                                throw new MarkException();
                            }
                            taskList[itemNum] = taskList[itemNum].unmark();
                            System.out.println("ok... I've unmarked:");
                            System.out.println((itemNum + 1) + "." + taskList[itemNum].toString());
                            break;
                        } catch (MarkException me) {
                            me.advice();
                        } catch (ParseException pe) {
                            pe.advice();
                        } finally {
                            break;
                        }
                    }
                    case "/commands": {
                            System.out.println(GuideText.GUIDE);
                    }
                }
            } else if (taskTypes.contains(firstWord)) {
                //add to taskList
                switch(firstWord){
                    case "todo": {
                        try {
                            String[] words = userInput.split(" ");
                            if (words.length <=1){
                                throw new ParseException();
                            }
                            String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                            taskList[numItems] = new ToDo(description);
                            System.out.println("added: " + taskList[numItems]);
                            numItems++;
                        } catch (ParseException pe) {
                            pe.advice();
                        } finally {
                            break;
                        }
                    }
                    case "deadline": {
                        try {
                            String[] parts = userInput.split("/by");
                            if (parts.length <= 1) {
                                throw new ParseException();
                            }
                            String by = parts[1].trim();
                            String[] commandAndDescription = parts[0].trim().split(" ", 2);
                            String description = commandAndDescription.length > 1 ? commandAndDescription[1] : "";
                            taskList[numItems] = new Deadline(description, false, by);
                            System.out.println("Got it, I've added this deadline " + taskList[numItems]);
                            numItems++;
                        } catch (ParseException pe) {
                            pe.advice();
                        } finally {
                            break;
                        }
                    }
                    case "event": {
                        try {
                            String[] fromSplit = userInput.split("/from");
                            if (fromSplit.length!= 2){
                                throw new ParseException();
                            }
                            String[] toSplit = fromSplit[1].split("/to");
                            if (toSplit.length != 2) {
                                throw new ParseException();
                            }

                            String[] commandAndDescription = fromSplit[0].trim().split(" ", 2);
                            String description = commandAndDescription.length > 1 ? commandAndDescription[1] : "";
                            String fromDate = toSplit[0].trim();
                            String toDate = toSplit[1].trim();
                            taskList[numItems] = new Event(description, false, fromDate, toDate);
                            System.out.println("Got it, I've added this event " + taskList[numItems]);
                            numItems++;
                        } catch (ParseException pe) {
                            pe.advice();
                        } finally {
                            break;
                        }
                    }
                }
            }

        }

        System.out.println("Ciao!");
    }
}
