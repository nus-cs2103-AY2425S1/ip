public class Bob {
    private static final String SEPARATOR = "____________________________________________________________";

    private static void say(String text) {
        System.out.println(text + "\n" + SEPARATOR + "\n");
    }

    private static void greet() {
        System.out.println(SEPARATOR);
        say("Hey there! Bob at your service.  \n" +
                "Let’s roll up our sleeves and get to work!");
    }

    public static void main(String[] args) {
        String logo = """
         .----------------. .----------------. .----------------.
        | .--------------. | .--------------. | .--------------. |
        | |   ______     | | |     ____     | | |   ______     | |
        | |  |_   _ \\    | | |   .'    `.   | | |  |_   _ \\    | |
        | |    | |_) |   | | |  /  .--.  \\  | | |    | |_) |   | |
        | |    |  __'.   | | |  | |    | |  | | |    |  __'.   | |
        | |   _| |__) |  | | |  \\  `--'  /  | | |   _| |__) |  | |
        | |  |_______/   | | |   `.____.'   | | |  |_______/   | |
        | |              | | |              | | |              | |
        | '--------------' | '--------------' | '--------------' |
        '----------------' '----------------' '----------------'""";
        System.out.println(logo);
        greet();
    }
}
