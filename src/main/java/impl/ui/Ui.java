package impl.ui;

public class Ui {
    static String cat = """
                  |\\      _,,,---,,_
            ZZZzz /,`.-'`'    -.  ;-;;,_
                 |,4-  ) )-,_. ,\\ (  `'-'
                '---''(_/--'  `-'\\_)
            """;
    //    static String seperator = "_______________________________________________________";
    Parser parser;

    /**
     * Main UI of the program.
     * Prints out all the statements in console
     *
     * @param parser parser for Danny to handle input string received.
     */
    public Ui(Parser parser) {
        this.parser = parser;
//        System.out.println(seperator);
        System.out.println("Hello! I'm Danny!");
        System.out.println(cat);
//        System.out.println(seperator);
        System.out.println("What can I do for you?");
//        System.out.println(seperator);
    }

    public String running(String in) {
        assert !in.isEmpty();
//        System.out.println(seperator);
        String text = parser.handleInput(in);
//        System.out.println(seperator);
        return text;
    }

    public void end() {
        System.out.println("Bye. Hope to see you again soon!");
//        System.out.println(seperator);
    }
}
