package command;

public class Exit extends Command {
  public Exit() {
    super("Bye. Hope to see you again soon!\n");
  }

  @Override
  public void execute() {
    System.out.println(this);
  } 
}
