package com.meow;

/**
 * Class which handles all UI to the CLI
 */
public class Ui {
    private static String LOGO =
        "     ___           ___           ___           ___\n"
        + "    /  /\\         /  /\\         /  /\\         /  /\\\n"
        + "   /  /::|       /  /::\\       /  /::\\       /  /:/_\n"
        + "  /  /:|:|      /  /:/\\:\\     /  /:/\\:\\     /  /:/ /\\\n"
        + " /  /:/|:|__   /  /::\\ \\:\\   /  /:/  \\:\\   /  /:/ /:/_\n"
        + "/__/:/_|::::\\ /__/:/\\:\\ \\:\\ /__/:/ \\__\\:\\ /__/:/ /:/ /\\\n"
        + "\\__\\/  /~~/:/ \\  \\:\\ \\:\\_\\/ \\  \\:\\ /  /:/ \\  \\:\\/:/ /:/\n"
        + "      /  /:/   \\  \\:\\ \\:\\    \\  \\:\\  /:/   \\  \\::/ /:/\n"
        + "     /  /:/     \\  \\:\\_\\/     \\  \\:\\/:/     \\  \\:\\/:/\n"
        + "    /__/:/       \\  \\:\\        \\  \\::/       \\  \\::/\n"
        + "    \\__\\/         \\__\\/         \\__\\/         \\__\\/";
    private static String STARTING_MESSAGE = LOGO
        + "\n    _____________________________________________________________________"
        + "\n    Welcome to the Meow Chatbot!!! I love meowing hue hue"
        + "\n    What can I do for you Meow?"
        + "\n    _____________________________________________________________________";



    /**
     * Prints the task outcome message
     * @param  msg
     * @return void
     */
    public void showTaskOutcomeMessage(String msg) {
        System.out.println("    " + "_____________________________________________________________________");
        System.out.println("     " + msg);
        System.out.println("    " + "_____________________________________________________________________\n");
    }

    /*
     * Prints the exit message
     */
    public void printExitMessage() {
        System.out.println("    " + "_____________________________________________________________________");
        System.out.println("     " + "Fine. Leave me just like everybody does meow.");
        System.out.println("    " + "_____________________________________________________________________\n");
    }

    /*
     * Prints the starting message
     * @return void
     */
    public void printStartMessage() {
        System.out.println(STARTING_MESSAGE);
    }

    /*
     * Prints the meowception error message
     * @param String code
     * @return void
     */
    public void showMeowceptionError(String msg) {
        //Meowception meowception = new Meowception(code);
        System.out.println("    " + "_____________________________________________________________________");
        System.out.println("     " + msg);
        System.out.println("    " + "_____________________________________________________________________\n");
    }

    public String getStartingMessage() {
        return "What can I do to help? Meow!";
    }

}
