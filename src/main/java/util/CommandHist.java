package util;

import java.util.List;
import java.util.ArrayList;

public class CommandHist {
  private List<String> commands;

  public CommandHist() {
    this.commands = new ArrayList<>(100);
  }

  /**
   * Method to add into the running list of commands entered.
   * 
   * @param cmd
   * @return
   */
  public boolean addCmd(String cmd) {
    return this.commands.add(cmd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    int itemNo = 1;

    for (String cmd : this.commands) {
      sb.append(String.format("%d. %s\n", itemNo, cmd));
    }

    return sb.toString();
  }
}
