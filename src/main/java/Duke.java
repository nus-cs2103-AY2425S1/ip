public class Duke {
    public static void main(String[] args) {
        // Note: those extra spaces before \n is done intentionally
        // so it is easier to see the logo and edit it subsequently
        // I know it is waste of space and unnecessary
        String logo = "           ___           \n"
                    + "          |* *|          \n"
                    + "          |\\_/|          \n"
                    + "  ---------------------  \n"
                    + "  |                   |  \n"
                    + "-----     SUMO      -----\n"
                    + "| | |               | | |\n"
                    + "-----      DE       -----\n"
                    + "  |                   |  \n"
                    + "  ---------------------  \n"
                    + "  |                   |  \n"
                    + "  |      /-----\\      |  \n"
                    + "  |_____/       \\_____|  \n";

        String goodbye = "------------------------------------\n"
                        + "Goodbye! Sumo hope to see you again!\n"
                        + "------------------------------------\n";


        System.out.println("    Hello, I am Sumo-DE \n"
                + logo
                + '\n'
                + " How can Sumo help you?"
        );

        System.out.println(goodbye);
    }
}
