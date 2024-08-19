public class Welcome extends MendelAction{
    public void speak() {
        String message = "Hello! I'm Mendel \n" +
                "What can I do for you?";
        System.out.println(new FormatText(message).wrapLines());
    }
}
