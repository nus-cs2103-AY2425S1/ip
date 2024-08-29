public class Ui {
    public void printLogo() {
        String logo =
                " **          **\n" +
                "*  *        *  *\n" +
                "*   *      *   *\n" +
                "*    *    *    *\n" +
                "*     *  *     *\n" +
                " *     **     *\n" +
                "  *    **    *\n" +
                "   *   **   *\n" +
                "    *  **  *\n" +
                "     ******\n" +
                "      ****\n" +
                "     * ** *\n" +
                "    *  **  *\n" +
                "    ***  ***\n";

        System.out.println(logo);
    }

    public void printHelloMsg() {
        System.out.println("I'm Tecna!\nHow can I help you?");
    }

    public void printGoodbyeMsg() {
        System.out.println("Pleased to help you! See you again ^_^");
    }

    public void printSectionLine() {
        System.out.println("----------------------------------------------");
    }

    public void printMarkMsg(Task markedTask) {
        System.out.println("Nice job! I've mark this as done. You deserve a short break <3");
        System.out.println(markedTask);
    }

    public void printUnmarkMsg(Task unmarkedTask) {
        System.out.println("I've mark this as undone. Keep going, my friend!");
        System.out.println(unmarkedTask);
    }

    public void printInvalidCmdError() {
        System.out.println("Oops! Your request sounds strange for me. Please enter a valid request ^^");
    }

    public void printError(String error) {
        System.out.println(error);
    }
}
