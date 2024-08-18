public class Greet extends Command {
  
  public Greet(String logo) {
    super("Hello from\n" + logo);
  }

  @Override
  public void execute() {
    System.out.println(this);
  }
}
