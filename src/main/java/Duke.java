public class Duke {

    public static void lnDiv() {
        System.out.println("_________________________________________________________");
    }
    public static void main(String[] args) {


        String logo = " _   _       _                 _   \n"
                + "| \\ | | ___ | |_    ____ ____ | |_ \n"
                + "|  \\| |/ _ \\  __|  / _` | '_ \\| __|\n"
                + "| |\\  | (_) | |_  | (_| | |_) | |_ \n"
                + "|_| \\_|\\___/ \\__|  \\__, | .__/ \\__|\n"
                + "                   |___/|_|        \n";
        System.out.println(logo+ "hi, I'm Not-gpt,\ndo you really need me to do sth for you?");
        lnDiv();
        System.out.println("*the first word will always be read as the command*");
        lnDiv();
        Parser.parse();

    }
}
