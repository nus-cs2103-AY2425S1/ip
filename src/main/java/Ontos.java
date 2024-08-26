import java.util.Scanner;

import javax.sql.rowset.serial.SerialJavaObject;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Ontos {
    private Ui ui;
    private TaskList tasks;
    private SaveManager saveManager;

    public Ontos(String saveLocation) {
        Path projectRoot = Paths.get("").toAbsolutePath().getParent().getParent().getParent();
        this.ui = new Ui();
        this.saveManager = new SaveManager(projectRoot, saveLocation);
        saveManager.createSave();
        this.tasks = saveManager.loadSave();
    }

    public void run() {
        ui.showLine();
        ui.greet();
        ui.showLine();
        saveManager.loadSave();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                ui.showLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, saveManager);
                isExit = c.isExit;
            } catch (IllegalArgumentException e) {
                ui.badInput();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Ontos ontos = new Ontos("Ontos.txt");
        ontos.run();
    }
}
