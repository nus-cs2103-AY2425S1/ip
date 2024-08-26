// class to deal with user interaction

import java.util.Scanner;

public class JunoUi {
    private String logo;
    private String greeting;
    private String farewell;
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

    public void displayWelcomeMessage() {
        System.out.println("___________________________________________________________________");
        System.out.println(this.logo);
        System.out.println("___________________________________________________________________");
        System.out.println(this.greeting);
        System.out.println("___________________________________________________________________");
    }

    public void displayFarewellMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(this.farewell);
        System.out.println("____________________________________________________________");
    }

    public String invalidUserInput() {
        return "Well, seems like you did not input anything! Please try again.";
    }

    // Could implement a help function to see all possible commands in the future
    public String invalidFunctionInput() {
        return "The input you provided is invalid! " +
                "(\uD83D\uDCA1 Tip: You can use the following commands \"add\", \"list\",\"mark\", \"unmark\".)";
    }

}
