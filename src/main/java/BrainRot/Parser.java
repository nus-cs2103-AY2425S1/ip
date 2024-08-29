package BrainRot;

public class Parser {

    public static String[] parse(String userInput) {
        String[] result = new String[2];

        if (userInput.equals("list")) {
            result[0] = "list";
        } else if (userInput.equals("bye")) {
            result[0] = "bye";
        } else if (userInput.startsWith("mark")) {
            result[0] = "mark";
            result[1] = userInput.substring(5).trim();  // Get the index
        } else if (userInput.startsWith("unmark")) {
            result[0] = "unmark";
            result[1] = userInput.substring(7).trim();  // Get the index
        } else if (userInput.startsWith("delete")) {
            result[0] = "delete";
            result[1] = userInput.substring(7).trim();  // Get the index
        } else {
            result[0] = "add";
            result[1] = userInput;  // Add task with the entire input as details
        }

        return result;
    }
}