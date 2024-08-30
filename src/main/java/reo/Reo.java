package reo;

public class Reo {
    private TaskList tasklist;
    private Ui ui;
    private Storage storage;

    public Reo() {
        TaskList taskList;
    }
//    public static void main(String[] args) {
//        new Reo().run();
//    }
//
//    public void run() {
//        String currInput;
//        Scanner scanner = new Scanner(System.in);
//        currInput = "placeholder";
//        ui.welcome();
//        while (!currInput.toLowerCase().equals("bye")) {
//            currInput = scanner.nextLine().trim();
//            Parser p = new Parser(currInput, tasklist, ui, storage);
//            p.parse();
//        }
//        scanner.close();
//    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Reo heard: " + input;
    }
}
