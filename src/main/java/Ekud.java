public class Ekud {
    public static final String LINE_SEPARATOR =
            "_____________________________________________________________";

    public static void greet() {
        String greeting = "Heyo! My name is EKuD!! You can call me Eku-chan"
                + "\nHow may I be of assistance";
        System.out.println(LINE_SEPARATOR);
        System.out.println(greeting);
    }

    public static void sayGoodbye() {
        String goodbye = "I hope you enjoyed your stay!\nSee you next time!";
        System.out.println(LINE_SEPARATOR);
        System.out.println(goodbye);
        System.out.println(LINE_SEPARATOR);
    }

    public static void main(String[] args) {
        greet();
        sayGoodbye();
    }
}
