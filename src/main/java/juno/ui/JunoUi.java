package juno.ui;
// class to deal with user interaction

/**
 * The {@code JunoUi} class handles user interaction for the Juno chat bot.
 * It provides methods for displaying welcome and farewell messages, as well as responses for invalid user inputs.
 */
public class JunoUi {
    private String logo;
    private String greeting;
    private String farewell;

    /**
     * Constructs a {@code JunoUi} instance and initialises the logo,
     * greeting message, and farewell message for the chatbot.
     */
    public JunoUi() {
        this.logo = """
                
            .----------------.  .----------------.  .-----------------. .----------------.\s
            | .--------------. || .--------------. || .--------------. || .--------------. |
            | |     _____    | || | _____  _____ | || | ____  _____  | || |     ____     | |
            | |    |_   _|   | || ||_   _||_   _|| || ||_   \\|_   _| | || |   .'    `.   | |
            | |      | |     | || |  | |    | |  | || |  |   \\ | |   | || |  /  .--.  \\  | |
            | |   _  | |     | || |  | '    ' |  | || |  | |\\ \\| |   | || |  | |    | |  | |
            | |  | |_' |     | || |   \\ `--' /   | || | _| |_\\   |_  | || |  \\  `--'  /  | |
            | |  `.___.'     | || |    `.__.'    | || ||_____|\\____| | || |   `.____.'   | |
            | |              | || |              | || |              | || |              | |
            | '--------------' || '--------------' || '--------------' || '--------------' |
             '----------------'  '----------------'  '----------------'  '----------------'\s
            """;
        this.greeting = "🌟 Welcome to the Future! I'm Juno, your digital assistant.\n" +
                "How can I assist you on your journey today?";
        this.farewell = "👋 Farewell for now! Looking forward to our next interaction.";
    }

    /**
     * Displays the welcome message to the console.
     */
    public void displayWelcomeMessage() {
        System.out.println("___________________________________________________________________");
        System.out.println(this.logo);
        System.out.println("___________________________________________________________________");
        System.out.println(this.greeting);
        System.out.println("___________________________________________________________________");
    }

    /**
     * Displays the farewell message to the console.
     */
    public void displayFarewellMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(this.farewell);
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns a string for empty user input.
     *
     * @return A string indicating that the input was empty.
     */
    public String invalidUserInput() {
        return "Well, seems like you did not input anything! Please try again.";
    }

    /**
     * Returns a string for invalid function input.
     *
     * @return A string indicating that the input was invalid.
     */
    // Could implement a help function to see all possible commands in the future
    public String invalidFunctionInput() {
        return "The input you provided is invalid! " +
                "(\uD83D\uDCA1 Tip: You can use the following commands \"add\", \"list\",\"mark\", \"unmark\".)";
    }
}
