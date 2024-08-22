public class Echo {
    String word;

    public Echo(String word) {
        this.word = word;
    }

    public void echoOut() {
        switch (word.toLowerCase()) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!\n"
                                +  "____________________________________________________________\n");
                break;
            default:
                System.out.println("\n____________________________________________________________\n"
                        + word + "\n____________________________________________________________\n");
                break;
        }
    }
}
