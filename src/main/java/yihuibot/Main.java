package yihuibot;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import yihuibot.exception.executable.ExecutableException;
import yihuibot.exception.parse.ParseException;
import yihuibot.exception.taskformat.IncorrectTaskFormatException;
import yihuibot.executable.Executable;
import yihuibot.executable.TaskModifier;
import yihuibot.storage.Storage;
import yihuibot.task.TaskList;
import yihuibot.ui.Ui;

/**
 * Your friendly todolist bot.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Main extends Application {
    // The name of this bot
    private static final String NAME = "YihuiBot";

    // Format for date time of task
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * Display the GUI for YihuiBot.
     */
    @Override
    public void start(Stage stage) {
        Label greetings = new Label("Hello! This is YihuiBot!");
        Scene scene = new Scene(greetings);
        stage.setScene(scene);
        stage.show();
    }
} 
