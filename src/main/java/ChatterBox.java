//
//import java.util.Scanner;
//
//import command.Parser;
//import command.Ui;
//import storage.Storage;
//import task.TaskList;
//
///**
// * Represents the main class for the ChatterBox application.
// * This class initializes the application, handles user input, and manages tasks.
// */
//public class ChatterBox {
//    private final TaskList taskList;
//    private final Storage storage;
//    private final Ui ui;
//    private final Parser parser;
//
//
//    /**
//     * Constructs a ChatterBox instance with the specified file path for storage.
//     *
//     * @param filePath The path to the file where tasks will be saved and loaded.
//     */
//    public ChatterBox(String filePath) {
//        storage = new Storage(filePath);
//        ui = new Ui();
//        taskList = new TaskList();
//        parser = new Parser();
//        storage.saveTasks(taskList.getTasks());
//    }
//
//    /**
//     * Starts the ChatterBox application, greeting the user and entering the main input loop.
//     * The loop continues until the user types "bye".
//     */
//    public void run() {
//        ui.greetUser();
//        Scanner sc = new Scanner(System.in);
//        boolean isRunning = true;
//
//        while (isRunning) {
//            String input = sc.nextLine().trim();
//            if (input.equals("bye")) {
//                parser.parseExecute(input, taskList, storage, ui);
//                isRunning = false;
//            } else {
//                parser.parseExecute(input, taskList, storage, ui);
//            }
//        }
//    }
//
//    /**
//     * Main method to start the ChatterBox application.
//     *
//     * @param args Command line arguments (not used).
//     */
//    public static void main(String[] args) {
//        String filePath = "./data/chatterbox.txt";
//        ChatterBox chatterBox = new ChatterBox(filePath);
//        chatterBox.run();
//    }
//}