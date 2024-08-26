import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class TrackBot {
    
    public static void main(String[] args) {
        TrackBotStorage storage;
        TrackList trackList = null;
        String filePath = "src/main/java/data/TrackBot.txt";
        try {
            storage = new TrackBotStorage(filePath);
            trackList = new TrackList(new File(filePath), storage);
        } catch (IOException e) {
            System.out.println("File not found");
        }
        if (trackList == null) {
            System.out.println("Failed to initialize TrackList.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("************************************************************");
        System.out.println("Hello from TrackBot!\n" + "How may I assist you?");

        // Solution below inspired by https://www.javatpoint.com/chatbot-application-in-java
        while (true) {
            String userInput = scanner.nextLine();

            try {
                if (userInput.equalsIgnoreCase("list")) {
                    trackList.listToString();
                } else if (userInput.equalsIgnoreCase("how are you?")) {
                    System.out.println("I'm good, thank you. How about you?");
                } else if (userInput.equalsIgnoreCase("bye")) {
                    break;
                } else if (userInput.toLowerCase().startsWith("mark")) {
                    String input = userInput.substring(4).trim();
                    if (input.isEmpty()) {
                        throw TrackBotException.invalidFormat("mark", "mark <task number>");
                    } else {
                        try {
                            int num = Integer.parseInt(input) - 1;
                            trackList.markTask(num);
                        } catch (NumberFormatException e) {
                            throw TrackBotException.invalidFormat("mark", "mark <task number>");
                        }
                    }
                } else if (userInput.toLowerCase().startsWith("unmark")) {
                    String input = userInput.substring(6).trim();
                    if (input.isEmpty()) {
                        throw TrackBotException.invalidFormat("unmark", "unmark <task number>");
                    } else {
                        try {
                            int num = Integer.parseInt(input) - 1;
                            trackList.unmarkTask(num);
                        } catch (NumberFormatException e) {
                            throw TrackBotException.invalidFormat("unmark", "unmark <task number>");
                        }
                    }
                } else if (userInput.toLowerCase().startsWith("todo")) {
                    String taskDescription = userInput.substring(4).trim();
                    if (taskDescription.isEmpty()) {
                        throw TrackBotException.invalidFormat("todo", "todo <task description>");
                    }
                    ToDo todo = new ToDo(taskDescription);
                    trackList.addToList(todo);
                } else if (userInput.toLowerCase().startsWith("deadline")) {
                    String[] parts = userInput.substring(8).trim().split(" /by ", 2);
                    if (parts.length < 2) {
                        throw TrackBotException.invalidFormat("deadline", "deadline <task description> /by <date/time>");
                    }
                    Deadline deadline = new Deadline(parts[0], parts[1]);
                    trackList.addToList(deadline);
                } else if (userInput.toLowerCase().startsWith("event")) {
                    String[] parts = userInput.substring(5).trim().split(" /from | /to ", 3);
                    if (parts.length < 3) {
                        throw TrackBotException.invalidFormat("event", "event <description> /from <start> /to <end>");
                    }
                    Event event = new Event(parts[0], parts[1], parts[2]);
                    trackList.addToList(event);
                } else if (userInput.toLowerCase().startsWith("delete")) {
                    String input = userInput.substring(6).trim();
                    if (input.isEmpty()) {
                        throw TrackBotException.invalidFormat("delete", "delete <task number>");
                    } else {
                        try {
                            int num = Integer.parseInt(input) - 1;
                            trackList.deleteFromList(num);
                        } catch (NumberFormatException e) {
                            throw TrackBotException.invalidFormat("delete", "delete <task number>");
                        }
                    }
                } else {
                    throw new TrackBotException("Sorry, I did not understand that command.");
                }
            } catch (TrackBotException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        // close scanner and exit with message
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("************************************************************");
    }
}
