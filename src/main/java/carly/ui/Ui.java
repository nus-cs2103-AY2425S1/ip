package carly.ui;

/** Deals with interactions with the user.*/
public class Ui {
    public static final String INDENTATION = "    ";

    public Ui() {}

    /** Displays a welcome message to the user with the current username. */
    public String welcomeMsg() {
        return "Hello there! 😊\nI'm Carly, your friendly chatbot.\nHow can I assist you today?";
    }

    /** Displays a farewell message to the user with the current username. */
    public String byeMsg() {
        return("Goodbye for now! 🌟\nIt was great chatting with you. I'll be here whenever you need me. Have a fantastic day!");
    }

}

