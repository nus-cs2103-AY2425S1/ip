public class Ui {
    private static String LOGO = """
                           ___
                          |* *|
                          |\\_/|
                  ---------------------
                  |                   |
                -----     SUMO      -----
                | | |               | | |
                -----      DE       -----
                  |                   |
                  ---------------------
                  |                   |
                  |      /-----\\      |
                  |_____/       \\_____|
                """;

    private static String GOODBYE = """
                ------------------------------------
                Goodbye! Sumo hope to see you again!
                ------------------------------------
                """;



    public void greet() {
        System.out.println("------------------------------------\n"
                + "    Hello, I am Sumo-DE\n"
                + LOGO
                + '\n'
                + " How can Sumo help you?\n"
                + "------------------------------------"
        );
    }

    public void unknownCommand (String commandString) {
        System.out.println("Sumo dunno your command \"" + commandString +"\" ! Check spelling of your first word.");
    }

    public void handleError (Exception e) {
        System.out.println(e.getMessage());
    }

    public void next() {
        System.out.println("""
                            ------------------------------------
                            Do you need anything else from SUMO?
                            ------------------------------------""");
    }

    public void bye() {
        System.out.println(GOODBYE);
    }
}
