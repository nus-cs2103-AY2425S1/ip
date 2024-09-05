package vuewee.ui;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import vuewee.EndProgramException;
import vuewee.command.Command;
import vuewee.command.CommandType;
import vuewee.parser.CommandParser;
import vuewee.parser.IllegalCommandException;
import vuewee.task.Task;
import vuewee.task.TaskList;

/**
 * The TaskListGui class represents the user interface for Vuewee. It contains
 * methods to add, delete, display, and mark tasks as done or not done.
 */
public class TaskListGui extends TaskListUi {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Creates a new TaskListUI object with the specified task list. Used for
     * reading and writing tasks to a file and for testing purposes.
     *
     * @param taskList Existing task list to be used
     */
    public TaskListGui(TaskList taskList) {
        super(taskList);
    }

    /**
     * Creates a new TaskListUI object with the specified scanner.
     */
    public TaskListGui() {
        super();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added
     */
    @Override
    public void addTask(Task task) {
        super.addTask(task);

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + super.taskList.size() + " " + this.taskWord() + " in the list.");
    }

    /**
     * Delete a task from the list
     *
     * @param taskNumber The index of the task to be deleted (1-based)
     * @throws IndexOutOfBoundsException
     */
    @Override
    public Task deleteTask(int taskNumber) throws IndexOutOfBoundsException {
        Task deletedTask = super.deleteTask(taskNumber);

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deletedTask.toString());
        System.out.println("Now you have " + this.taskList.size() + " " + super.taskWord() + " in the list.");

        return deletedTask;
    }

    /**
     * Display all tasks in the list that match the keyword. Search is done by
     * another method that returns a new TaskList with matching tasks.
     *
     * @param tasks TaskList to search for matching tasks
     * @throws IllegalCommandException
     */
    @Override
    public void displayTasks(TaskList tasks) throws IllegalCommandException {
        if (tasks.size() == 0) {
            throw new IllegalCommandException("No tasks found.");
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("  " + (i + 1) + ". " + task.toString());
        }
    }

    /**
     * Mark a task as done or not done
     *
     * @param taskNumber The index of the task to be marked (1-based)
     * @param isDone     The new status of the task
     * @throws IllegalCommandException
     */
    @Override
    public void markTask(int taskNumber, boolean isDone) throws IllegalCommandException {
        try {
            super.markTask(taskNumber, isDone);
            if (isDone) {
                System.out.println("Nice! I've marked this task as done:");
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  " + this.taskList.get(taskNumber - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalCommandException("Invalid task number. There are " + this.taskList.size() + " " + this
                    .taskWord() + " in your list.");
        }
    }

    /**
     * Starts the Vuewee program and reads user input until the user types "bye".
     */
    @Override
    public void run() {
        this.taskList = this.storage.readTasks();
        Application.launch(Gui.class);

        if (this.taskList.size() > 0) {
            System.out.println("Loaded " + this.taskList.size() + " " + this.taskWord() + " into your task list.");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Vuewee\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        // Echo input from user until user types "bye"
        try {
            while (true) {
                String input = scanner.nextLine();
                try {
                    System.out.println("____________________________________________________________");

                    CommandParser parser = new CommandParser(input);
                    CommandType commandType = parser.getCommandType();
                    Command command = commandType.createCommand();
                    command.execute(this, taskList, parser);

                } catch (IndexOutOfBoundsException | IllegalCommandException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("____________________________________________________________");
                storage.storeTasks(this.taskList);
            }
        } catch (EndProgramException e) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
            scanner.close();
        }
    }

    @Override
    public void close() {
        this.storage.storeTasks(this.taskList);
        this.scanner.close();
    }

    public static class Gui extends Application {
        private ScrollPane scrollPane;
        private VBox dialogContainer;
        private TextField userInput;
        private Button sendButton;
        private Scene scene;

        private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

        @Override
        public void start(Stage stage) {
            // Setting up required components

            scrollPane = new ScrollPane();
            dialogContainer = new VBox();
            scrollPane.setContent(dialogContainer);

            userInput = new TextField();
            sendButton = new Button("Send");

            DialogBox dialogBox = new DialogBox("Hello!", userImage);
            dialogContainer.getChildren().addAll(dialogBox);

            AnchorPane mainLayout = new AnchorPane();
            mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

            scene = new Scene(mainLayout);

            stage.setScene(scene);
            stage.show();
            stage.setTitle("Duke");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);

            mainLayout.setPrefSize(400.0, 600.0);

            scrollPane.setPrefSize(385, 535);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

            scrollPane.setVvalue(1.0);
            scrollPane.setFitToWidth(true);

            dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

            userInput.setPrefWidth(325.0);

            sendButton.setPrefWidth(55.0);

            AnchorPane.setTopAnchor(scrollPane, 1.0);

            AnchorPane.setBottomAnchor(sendButton, 1.0);
            AnchorPane.setRightAnchor(sendButton, 1.0);

            AnchorPane.setLeftAnchor(userInput, 1.0);
            AnchorPane.setBottomAnchor(userInput, 1.0);
        }
    }
}
