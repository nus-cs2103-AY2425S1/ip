public class Ekud {
    public static final String LINE_SEPARATOR =
            "\t_____________________________________________________________";

    public static void greet() {
        String greeting = "\tHeyo! My name is EKuD!! You can call me Eku-chan :)"
                + "\n\tHow may I be of assistance!";
        System.out.println(LINE_SEPARATOR);
        System.out.println(greeting);
    }

    public static void sayGoodbye() {
        String goodbye = "\tI hope you enjoyed your stay!\n\tSee you next time!";
        System.out.println(LINE_SEPARATOR);
        System.out.println(goodbye);
        System.out.println(LINE_SEPARATOR);
    }

    public static void main(String[] args) {
        greet();
        sayGoodbye();
    }
}
