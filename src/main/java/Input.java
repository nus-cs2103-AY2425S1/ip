public class Input {

    private String input;


    public Input(String input) {
        this.input = input;

    }



    public boolean checker() {
        return input.equalsIgnoreCase("bye");
    }

    public boolean checker2() {
        return input.equalsIgnoreCase("list");
    }



    @Override
    public String toString() {
        return this.input;
    }
}
