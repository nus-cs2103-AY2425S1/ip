public class Edith {
    private static final String lineBreak = "\n_______________________________________________________________\n";
    private static final String greeting = "Hello! I am EDITH, your personal chatbot companion:)"
            + "\nHow may I assist you?";
    private static final String exit = "It has been my pleasure helping you. Hope to see you again soon!";

    public Edith() {
    }

    public static void main(String[] args) {
        String string0 = lineBreak + greeting + lineBreak;
        String lastString = exit + lineBreak;

        System.out.println(string0 + lastString);
    }
}
