package com.meow;
public class Ui {
    static private String LOGO =  
    "     ___           ___           ___           ___\n" +     
    "    /  /\\         /  /\\         /  /\\         /  /\\\n" +   
    "   /  /::|       /  /::\\       /  /::\\       /  /:/_\n" +  
    "  /  /:|:|      /  /:/\\:\\     /  /:/\\:\\     /  /:/ /\\\n" +
    " /  /:/|:|__   /  /::\\ \\:\\   /  /:/  \\:\\   /  /:/ /:/_\n" +
    "/__/:/_|::::\\ /__/:/\\:\\ \\:\\ /__/:/ \\__\\:\\ /__/:/ /:/ /\\\n" +
    "\\__\\/  /~~/:/ \\  \\:\\ \\:\\_\\/ \\  \\:\\ /  /:/ \\  \\:\\/:/ /:/\n" +
    "      /  /:/   \\  \\:\\ \\:\\    \\  \\:\\  /:/   \\  \\::/ /:/\n" +
    "     /  /:/     \\  \\:\\_\\/     \\  \\:\\/:/     \\  \\:\\/:/\n" +
    "    /__/:/       \\  \\:\\        \\  \\::/       \\  \\::/\n" +
    "    \\__\\/         \\__\\/         \\__\\/         \\__\\/";
    static private String STARTING_MESSAGE = LOGO + 
    "\n    _____________________________________________________________________" +   
    "\n    Welcome to the Meow Chatbot!!! I love meowing hue hue"
    + "\n    What can I do for you Meow?"
    + "\n    _____________________________________________________________________";

    /*
     * Prints the task outcome message
     * @param String msg
     * @return void
     */
    public void showTaskOutcomeMessage(String msg) {
        System.out.println("    " + "_____________________________________________________________________");
        System.out.println("     " + msg);
        System.out.println("    " + "_____________________________________________________________________\n");
    }

    /*
     * Prints the exit message
     * @param None
     * @return void
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

    
}
