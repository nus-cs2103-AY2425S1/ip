package momo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import momo.command.CommandType;
import momo.exception.MomoException;
import momo.task.TaskList;

import java.util.Scanner;

import javafx.scene.layout.VBox;


/**
 * Handles Ui related functionality in the chatbot program, handling some user interactions
 */
public class Ui {
    private VBox dialogContainer;
    private TextField userInput;
    private Momo momo;

    public Ui(VBox dialogContainer, TextField userInput) {
        this.userInput = userInput;
        this.dialogContainer = dialogContainer;
    }

    public static void showHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private final static Scanner sc = new Scanner(System.in);

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/userIcon.png"));
    private final Image momoImage = new Image(this.getClass().getResourceAsStream("/images/momoIcon.png"));


    public void showGreeting() {
        String input = "Greetings... I'm Momo.\n What can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getMomoDialog(input, momoImage)
        );

    }

    public void setMomo(Momo momo) {
        this.momo = momo;
    }


    /**
     * Scans and returns user input string
     *
     * @return Returns a String containing user input
     */
    public String getUserInput() {
        String input = userInput.getText().trim();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();

        try {
            CommandType command = Parser.parseInput(input);
            assert command != null;
            momo.processCommand(input, command);
        } catch (MomoException e) {
            printDialogue(e.getMessage());
        }



        return input;
    }

    public void printDialogue(String text) {
        assert !text.isEmpty() : "Dialogue box should not be empty";

        dialogContainer.getChildren().addAll(
                DialogBox.getMomoDialog(text, momoImage)
        );
    }

    public void printList(TaskList list) {

        StringBuilder listToPrint = new StringBuilder();

        for (int i = 0; i < list.getCount(); i++) {
            listToPrint.append(i + 1).append(". ").append(list.getTask(i)).append("\n");
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getMomoDialog(listToPrint.toString(), momoImage)
        );

    }

    public void printTaskListCount(TaskList list) {
        System.out.printf("Now there are %d tasks in your list\n", list.getCount());
        showHorizontalLine();

    }


    /**
     * Shows an ominous farewell message and terminates the program
     */
    public void showFarewell() {
        String logo =
                        "⣿⣿⣿⡉⢀⣾⣿⡟⣩⣭⣭⡈⠙⢿⣿⣿⣿⣿⣿⡿⣻⣿⣿⣿⣿⣿⣿⣿⡇⠄\n" +
                        "⣿⣿⡗⠄⣼⣿⣿⢸⡿⠉⠉⢻⡆⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢠⠄\n" +
                        "⣿⡻⠁⢠⣿⣿⣿⣦⡛⠢⠴⠛⠁⣸⣿⣿⣿⣿⡿⠛⢉⣉⣉⡙⢻⣿⣿⣗⠄⠄\n" +
                        "⠷⠁⠄⢰⣿⣿⣿⣷⣬⣭⣼⣷⣿⣿⣿⣿⣿⡏⢀⣾⠟⠛⢿⣿⣄⣿⣿⡏⠄⠄\n" +
                        "⠄⠄⠄⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠄⠳⢀⣀⡼⢟⣼⣿⡟⠄⠄⠄\n" +
                        "⠄⠄⠄⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⣒⣲⣶⣾⣿⣿⠏⠄⠄⠄⢠\n" +
                        "⠄⠄⠄⠸⣿⣽⣿⣿⣿⣿⣉⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠄⠄⠄⢠⣷\n" +
                        "⠄⠄⠄⠄⢻⣷⢻⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⠄⠄⠄⠄⢀⣾⣿\n" +
                        "⠄⠄⠄⠄⠄⢻⣧⡙⢿⣿⣿⣿⣿⣿⡿⣿⣿⣿⠿⠛⠁⠄⠄⠄⠄⠄⢠⣿⣿⣿\n" +
                        "⠄⠄⠄⡀⠄⠈⣿⣿⣶⣭⣭⣭⣿⣾⡿⠟⠋⠁⠄⠄⠄⠄⠄⠄⠄⢠⣿⣿⣿⣿\n" +
                        "⠄⠄⠎⠄⠄⣨⣿⣿⣿⣿⣿⣿⠋⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⣀⡲⣿⣿⣿⣿";

        String text = "Farewell... for now. I'll be waiting for your return, taking refuge in your shadows. Rest" +
                " well.... wħɨłɇ ɏøᵾ sŧɨłł ȼȺn\n";

        dialogContainer.getChildren().addAll(
                DialogBox.getMomoDialog(text, momoImage)
        );



    }


}











