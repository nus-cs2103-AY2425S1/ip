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

  /**
   * Prints the items in the hist
   */
  public void prettyPrint() {
    int no = 1;
    System.out.println(Utility.INDENTED_LINE);
    for (String cmd : this.commands) {
      System.out.println(String.format("%s%d: %s", Utility.INDENT, no++, cmd));
    }
    System.out.println(Utility.INDENTED_LINE);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    int itemNo = 1;

    for (String cmd : this.commands) {
      sb.append(String.format("%d. %s\n", itemNo++, cmd));
    }
    return sb.toString();
  }
}
