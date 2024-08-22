public class Duke {

    public static void lnDiv() {
        System.out.println("_______________________________________________");
    }
    public static void main(String[] args) {

        Parser p = new Parser();
        String logo = " _   _       _                 _   \n"
                + "| \\ | | ___ | |_    ____ ____ | |_ \n"
                + "|  \\| |/ _ \\  __|  / _` | '_ \\| __|\n"
                + "| |\\  | (_) | |_  | (_| | |_) | |_ \n"
                + "|_| \\_|\\___/ \\__|  \\__, | .__/ \\__|\n"
                + "                   |___/|_|        \n";
        System.out.println(logo+ "Hello I'm Not-gpt,");
        System.out.println("what can I do for you?");
        lnDiv();
        p.parse();

    }
}
