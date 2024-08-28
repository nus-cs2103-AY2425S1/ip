package com.appleaster.main;

import com.appleaster.command.Command;
import com.appleaster.command.CommandType;
import com.appleaster.exception.AppleasterException;
import com.appleaster.parser.Parser;
import com.appleaster.storage.Storage;
import com.appleaster.task.TaskList;
import com.appleaster.ui.Ui;

public class Appleaster {
  private final Storage storage;
  private TaskList tasks;
  private final Ui ui;

  public Appleaster(String filePath) {
    ui = new Ui();
    storage = new Storage(filePath);
    try {
      tasks = new TaskList(storage.load());
    } catch (AppleasterException e) {
      ui.showLoadingError();
      tasks = new TaskList();
    }
  }

  public void run() {
    ui.showWelcome();
    boolean isExit = false;
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        ui.showLine();
        Command c = Parser.parseCommand(fullCommand);
        isExit = executeCommand(c);
        ui.showLine();
      } catch (AppleasterException e) {
        ui.showError(e.getMessage());
      }
    }
  }

  private boolean executeCommand(Command c) throws AppleasterException {
    switch (c.getType()) {
      case LIST:
        tasks.listTasks();
        break;
      case MARK:
      case UNMARK:
        tasks.markTask(c.getTaskIndex(), c.getType() == CommandType.MARK);
        break;
      case TODO:
      case DEADLINE:
      case EVENT:
        tasks.addTask(c.getTask());
        break;
      case DELETE:
        tasks.deleteTask(c.getTaskIndex());
        break;
      case DATE:
        tasks.listTasksOnDate(c.getDate());
        break;
      case BYE:
        storage.save(tasks.getTasks());
        ui.showGoodbye();
        return true;
      default:
        throw new AppleasterException("Unknown command type");
    }
    return false;
  }

  public static void main(String[] args) {
    new Appleaster("data/tasks.txt").run();
  }
}