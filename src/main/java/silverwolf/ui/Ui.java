package silverwolf.ui;

import java.util.Scanner;

import silverwolf.task.Task;
/**
 * The Ui class handles user interactions with the application.
 * It is responsible for displaying messages to the user and reading user input.
 */
public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a line separator to the console for visual separation of sections.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a newline character to the console for visual spacing.
     */
    public void newline() {
        System.out.print("\n");
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user when the application starts.
     * Includes a logo and a brief introduction.
     */
    public void showWelcome() {
        String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡴⠶⠒⠋⠉⠉⠉⠛⠓⠶⡶⠶⠒⠒⠲⠶⠶⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡴⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⢶⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠾⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠶⠦⢤⣀⡀⠉⠻⢦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠳⢦⣄⠙⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠟⠁⢀⡤⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⢿⡿⣷⡂⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠁⢀⡔⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢾⣷⡤⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠟⠀⢠⠏⠀⠀⠀⠀⡄⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⡀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠏⠀⢰⠃⠀⠀⠀⢠⣾⠁⠀⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡀⠀⠀⠀⠀⠀⠀⠀⢄⠀⠀⠀⠀⠀⠀⠀⠘⢷⡀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡟⠀⢠⡇⠀⠀⠀⣰⢿⠇⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡄⠀⠀⠀⠀⢧⠀⠀⠀⠀⠀⠀⠀⠈⣧⠀⠀⠀⠀⠀⠀⠀⠈⢿⡄⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠁⠀⣾⠀⠀⠀⢠⢏⡏⢀⡆⠀⢀⡴⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢱⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⢸⡀⠀⠀⠀⠀⠀⠀⠀⠈⣷⠀⠀⠀⠀\n"
                + "⣿⣿⣶⡤⣀⠀⠀⢀⡴⣺⣿⡀⠀⡇⠀⠀⠀⡟⢸⣇⡞⠀⣴⠏⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡆⠀⢸⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠈⡇⠀⠀⠀⠀⠀⠀⠀⠀⠘⣧⠀⠀⠀\n"
                + "⠻⣿⣎⠙⠳⢯⣟⡵⠛⠉⠛⢷⣼⣃⡀⠀⢸⠁⢸⡝⢠⡾⢥⣄⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⡄⢸⠀⢸⠃⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡀⠀⠀\n"
                + "⠀⠈⢿⣷⡀⠀⠀⠀⠀⢸⠀⠈⠻⣿⡄⠀⣾⠀⣿⢠⠏⠀⡄⠉⠻⢾⡆⠀⠀⠀⡄⠀⠀⠀⠀⠀⠀⣿⢳⡟⠀⣾⠀⠀⠀⡁⠀⠀⠀⠀⠀⠁⠀⡄⠀⠀⠀⠀⢶⠀⠀⠘⣇⠀⠀\n"
                + "⠀⠀⠀⠹⣿⣦⠀⠀⣠⣾⣶⣦⣄⡈⢣⡀⣿⠀⣷⣯⣤⣾⣿⣄⡀⠀⢿⡀⠀⠸⣅⠀⠀⠀⠀⠀⠀⢹⣼⠃⢠⡏⠀⠀⠀⡄⠀⠀⠀⠀⠀⡆⠀⡇⠀⠀⠀⠀⢸⣧⠀⠀⣿⠀⠀\n"
                + "⠀⠀⠀⠀⠈⢺⣷⣞⡿⣿⢀⠀⢸⠺⡅⠓⣿⠀⣿⣿⣿⣿⣿⣿⣿⣷⣜⢷⡀⠀⣿⣆⠀⠀⠀⠀⠀⢸⡟⠀⡾⣇⠀⠀⢀⡇⠀⠀⠀⠀⠀⡇⢸⠁⢀⣠⣤⡀⢸⡿⣇⠀⢸⡄⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠈⠉⠀⣿⢸⣆⠈⣇⣷⠀⢹⠀⡿⠋⠁⢰⠟⠀⣾⠙⢻⣷⡻⣦⣻⡙⢷⣄⠀⣀⠀⡼⠁⣼⠃⢹⡆⠀⣼⣧⠀⠀⠀⢀⣤⣇⣯⣴⡿⠛⣾⠁⢸⠃⣿⠀⢸⡇⠀\n"
                + "⠀⠀⠀⠀⠀⢠⣤⣖⣲⣿⠟⢈⣙⣻⢹⣇⠈⣇⣷⠀⠀⣿⠀⠁⢿⣀⣸⢻⠁⠈⠉⠁⠙⠛⠳⢽⡿⢁⣾⣁⣴⣿⣧⣾⣥⣿⠀⠀⢀⣞⡼⠫⢚⣉⡀⠀⠿⠶⠾⠿⠿⢶⣾⣷⣏\n"
                + "⠀⠀⠀⠀⠀⠈⠻⣿⣦⣄⣀⡿⠉⣿⠈⠹⣆⠹⣿⡄⠀⢹⡄⠒⠀⠉⠡⡿⠀⠀⠀⠀⠀⠀⢀⣾⣷⡿⠿⠿⠿⠿⣿⣿⣿⣿⠀⢀⠎⢸⡧⠉⢩⣽⠇⠀⠀⣀⣤⣴⣾⣿⢹⡇⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⢠⡿⠉⠙⠁⠀⡟⢧⣀⠙⢷⣹⣷⡀⠀⠛⠒⠦⠖⠚⠁⢀⣀⣘⣀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠄⣀⠙⡋⣇⣴⠋⢀⡾⠁⠀⣼⣛⣤⠶⠛⣽⡿⠋⠀⣿⣼⠁⠀\n"
                + "⠀⠀⠀⠀⠀⠀⢀⡾⠁⠀⠀⠀⡼⣱⢰⣿⣿⣟⣿⡛⠃⠀⠀⠀⠀⠀⠀⠀⠀⣏⠉⠈⠉⢹⠀⠀⠀⠀⠀⠐⠃⠜⠡⠞⠀⢩⡏⣠⠟⠀⣠⣾⠙⠋⠀⠀⣰⡏⣧⠀⠀⣿⠟⠀⠀\n"
                + "⠀⠀⠀⠀⠀⢀⡾⠁⠀⠀⢠⡞⣡⠇⠀⠛⠛⠹⣾⣿⡷⣤⣀⠀⠀⠀⠀⠀⠀⠸⡄⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣾⡵⠃⣠⣾⡯⣾⠘⠀⠀⣠⣿⡀⢿⠀⠀⠉⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⣼⠁⠀⢀⡴⣻⢠⡯⠖⠛⠉⠉⠛⠺⣿⣿⣆⠉⠛⠷⣶⣤⣤⣤⣀⣻⣄⣠⡇⢀⣀⣀⣀⣀⣠⣤⣤⠶⣻⣟⣫⣴⠟⣹⠀⢉⡟⠀⠀⣰⠟⠀⠙⢾⡆⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⢰⡇⠀⡴⠋⢀⣿⠟⢷⣄⣀⠀⠀⠀⠀⠈⠙⢿⣧⠀⠀⠘⡻⣶⢿⠹⠿⣿⣿⣿⢿⣟⣿⣿⡟⠁⠀⠈⠳⣏⠉⢸⣽⢠⠏⠀⣸⠃⣠⣾⠋⠀⠀⠀⠀⠙⠶⣄⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠘⣧⢰⣧⣀⣼⠃⢠⡟⠀⠀⠑⠲⠆⠀⠀⠀⢸⡟⡇⠀⢀⣧⠘⣿⣧⣀⡀⠀⢀⣿⣽⣿⣿⠁⠀⠀⢀⣠⣞⣿⣾⣿⣿⠀⢰⣯⡶⣿⡟⠀⠀⠀⠀⠀⠀⠀⠈⢷⡄⠀\n"
                + "⠀⠀⠀⠀⠀⠹⣦⠇⣼⠃⠀⠸⣦⠀⠀⠀⠀⠀⠀⠀⢀⣾⢧⡇⣠⡾⢹⠀⣿⡉⠁⠳⣿⣿⣽⣿⣿⣿⣿⣄⡴⣫⣿⣿⣿⣽⣿⡿⣗⡮⠥⣾⡿⠁⠀⢰⣤⣀⠀⠀⠀⠀⠀⠹⡆\n"
                + "⠀⠀⠀⠀⠀⠀⠈⢻⠇⠀⠀⠀⠘⢧⣀⡀⠀⠀⣀⣴⢟⡟⢸⣿⡟⢡⣯⠾⠃⠹⣄⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⡉⠉⠁⠀⠀⠀⣸⠃⠙⠳⣤⡀⠀⠀⠀⣿\n"
                + "⠀⠀⠀⠀⠀⠀⠀⢸⡄⠀⠀⠀⠀⢸⠻⣍⣩⠽⠋⣡⠏⠀⠸⣿⣇⠀⠈⠳⣶⡶⠈⢦⡀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⡂⠀⠀⠀⣴⠏⠀⠀⠀⣸⠳⡄⠀⢠⠟\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠈⠛⠶⣤⣤⣤⣬⣷⣦⣤⣠⠜⠃⠀⠀⠀⢈⣟⣷⠒⣿⠉⠀⠀⠀⠱⣜⣿⣿⣿⣿⣿⣿⣿⣿⣯⣿⣧⡳⣽⣿⣿⣿⣿⣦⡴⠟⠃⠀⠀⣀⡴⠋⠀⣿⣴⠟⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠷⠤⠤⠤⠤⠴⠟⠛⠛⠛⠛⠛⠶⠶⠾⠭⠿⠿⢿⣿⠿⠟⠻⠿⠿⠟⠋⠻⠟⠛⠛⠛⠉⠉⠁⠀⠀⠀⠀⠀⠙⠛⠛⠛⠉⠀⠀⠀";
        System.out.println(logo);
        System.out.println("____________________________________________________________\n"
                + " Hello! I'm Silver Wolf\n"
                + " What can I help you with?\n"
                + "____________________________________________________________\n");
    }

    /**
     * Displays an error message indicating that an error occurred during loading.
     */
    public void showLoadingError() {
        this.showLine();
        System.out.println("An error occured whilst loading data");
        this.showLine();
    }

    /**
     * Displays a custom error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        this.showLine();
        System.out.println(message);
        this.showLine();
    }

    /**
     * Displays an error message indicating that an error occurred when reading the file.
     */
    public void showFileError() {
        this.showLine();
        System.out.println("The file has errors");
        this.showLine();
    }
    /**
     * Displays a goodbye message to the user when the application exits.
     */
    public void showGoodbye() {
        showLine();
        System.out.println(" Till we meet again! May this journey lead us starward!");
        showLine();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showUnmarkTask(Task task) {
        this.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        this.showLine();
        this.newline();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkTask(Task task) {
        this.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        this.showLine();
        this.newline();
    }
}
