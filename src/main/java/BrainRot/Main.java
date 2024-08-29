package BrainRot;

/**
 * The BrainRot.Main class serves as the entry point for the application.
 * It initializes the BrainRot.BrainRot instance with the file path where tasks are stored
 * and then starts the main loop to process user commands.
 */
public class Main {

    /**
     * The main method is the entry point of the Java application.
     * It constructs a BrainRot.BrainRot instance with the specified file path for task storage
     * and then runs the application.
     *
     * @param args Command-line arguments passed to the program (not used).
     */
    public static void main(String[] args) {
        // Get the file path for storing tasks in the user's home directory
        String filePath = System.getProperty("user.home") + "/ip/data/brainRot.txt";

        // Initialize BrainRot.BrainRot with the specified file path
        BrainRot brainRot = new BrainRot(filePath);

        // Start the main loop to process user commands
        brainRot.run();
    }
}
