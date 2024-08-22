public class Makima {

    public static final String LINE_SEPERATOR = "__________________\n";

    public void greeting() {
        System.out.println("Yahallo! I'm your friendly chatbot, Makima!");
        System.out.println("What can I do for you? *_*");
        System.out.println(LINE_SEPERATOR);
    }

    public void farewell() {
        System.out.println("Baibai. Hope to see you soon! ^_^");
    }

    public static void main(String[] args) {
        new Makima();
    }

    public Makima() {
        greeting();
        farewell();
    }

}
