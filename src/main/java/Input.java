public class Input {
    String input;

    public Input(String input) {
        this.input = input;
    }

    public void end() {
        System.out.println("Bye! Come back to Hoodini soon!");
    }

    public boolean checker() {
        return input.equalsIgnoreCase("bye");
    }

    public void output() {
        if(input.equalsIgnoreCase("bye")) {
            end();

        } else {
            System.out.println(input);
        }
    }
}
