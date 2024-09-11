package genesis;

import commandparser.CommandParser;
import storage.Storage;
import taskmanager.TaskManager;
import ui.Ui;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a generic task.
 */







/**
 * Manages a list of tasks.
 */

/**
 * Handles loading tasks from and writing tasks to a file.
 */






/**
 * The main class for running the Genesis task management application.
 */
public class Genesis {
    public TaskManager taskManager;
    public Storage storage;
    public CommandParser parser;
    public Ui ui;

    public Genesis() {
        this.taskManager = new TaskManager();

        this.storage = new Storage(taskManager);
        this.parser = new CommandParser(taskManager, storage);
        this.ui = new Ui(taskManager, parser);
        storage.loadTasks(parser, ui);
    }




    /**
     * The entry point of the Genesis application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm Genesis!\nWhat can I do for you?\n");

    }
}

