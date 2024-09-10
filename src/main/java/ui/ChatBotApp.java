package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The {@code ChatBotApp} class is the entry point for the Sigma Bot application.
 * It initializes the JavaFX application by loading the main FXML layout and setting up the primary stage.
 */
public class ChatBotApp extends Application {

	/**
	 * Starts the JavaFX application.
	 * This method is called when the application is launched and is responsible for setting up the UI.
	 *
	 * @param primaryStage The primary stage for the application, onto which the UI scene is set.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			// Load the FXML layout for the chatbot UI
			VBox root = FXMLLoader.load(getClass().getResource("/ui/ChatBotLayout.fxml"));

			// Create a new scene with the specified width and height
			Scene scene = new Scene(root, 400, 500);

			// Set the title of the application window
			primaryStage.setTitle("Sigma Bot");

			// Attach the scene to the stage and display the stage
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			// Print detailed error information if any exception occurs during the setup
			e.printStackTrace();
		}
	}

	/**
	 * The main method serves as the entry point to launch the JavaFX application.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		// Launch the JavaFX application
		launch(args);
	}
}
