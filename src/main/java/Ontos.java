import java.util.Scanner;

import ontos.commands.Command;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import ontos.parser.Parser;
import ontos.storage.SaveManager;
import ontos.task.TaskList;
import ontos.ui.Ui;

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
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                ui.showLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, saveManager);
                isExit = c.isExit();
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
        Ontos ontos = new Ontos("Ontos");
        ontos.run();
    }
}
