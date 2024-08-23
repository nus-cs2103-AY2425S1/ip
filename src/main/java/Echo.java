public class Echo {

    private String input;

    public Echo(String input) {
        this.input = input;
    }

    public void output() {

        System.out.println("____________________________________________________________\n"
                + input + "\n" +
                "____________________________________________________________");
    }
}
