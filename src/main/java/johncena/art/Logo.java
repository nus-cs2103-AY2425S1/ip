
package johncena.art;


/**
 * The {@code Logo} class provides a static method to retrieve the ASCII art logo
 * for the John Cena Task Manager application.
 */
public class Logo {


    /**
     * The ASCII art logo for the John Cena Task Manager application.
     */
    private static final String logo = "\n" +
            "                    $$\\                        $$$$$$\\                                \n" +
            "                    $$ |                      $$  __$$\\                               \n" +
            "      $$\\  $$$$$$\\  $$$$$$$\\  $$$$$$$\\        $$ /  \\__| $$$$$$\\  $$$$$$$\\   $$$$$$\\  \n" +
            "      \\__|$$  __$$\\ $$  __$$\\ $$  __$$\\       $$ |      $$  __$$\\ $$  __$$\\  \\____$$\\ \n" +
            "      $$\\ $$ /  $$ |$$ |  $$ |$$ |  $$ |      $$ |      $$$$$$$$ |$$ |  $$ | $$$$$$$ |\n" +
            "      $$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |      $$ |  $$\\ $$   ____|$$ |  $$ |$$  __$$ |\n" +
            "      $$ |\\$$$$$$  |$$ |  $$ |$$ |  $$ |      \\$$$$$$  |\\$$$$$$$\\ $$ |  $$ |\\$$$$$$$ |\n" +
            "      $$ | \\______/ \\__|  \\__|\\__|  \\__|       \\______/  \\_______|\\__|  \\__| \\_______|\n" +
            "$$\\   $$ |                                                                            \n" +
            "\\$$$$$$  |                                                                            \n" +
            " \\______/                                                                             \n";

    /**
     * Returns the ASCII art logo for the John Cena Task Manager application.
     *
     * @return the ASCII art logo
     */
    public static String getLogo() {
        return logo;
    }
}
