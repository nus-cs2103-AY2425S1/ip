import ouiouibaguette.OuiOuiBaguette;

/**
 * The entry point of the OuiOuiBaguette application.
 * Initializes and runs the OuiOuiBaguette bot.
 */
class Main {

    /**
     * The main method that starts the OuiOuiBaguette application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        OuiOuiBaguette bot = new OuiOuiBaguette("data");
        bot.run();
    }
}
