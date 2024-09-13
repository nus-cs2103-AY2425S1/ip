package BonnieGUI;

import Exceptions.*;
public class Bonnie {
    public static void main(String[] args) {
        System.out.println("Hello!");
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws DeadlineFormatException, EmptyTodoException, UnknownCommandException {
        String response = GUIParser.parseInput(input);
        return response;
    }

    public String getInitialisationResponse(String input) {
        return String.format("Nice to meet you %s, my name is Bonnie! Let's be friends forever!", input);
    }
}