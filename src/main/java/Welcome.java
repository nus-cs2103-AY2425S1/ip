public class Welcome extends MendelAction{
    public void speak() {
        System.out.println(new FormatText(this.toString()).wrapLines());
    }

    @Override
    public String toString() {
        return "Hello! I'm Mendel \n" +
                "What can I do for you?";
    }
}
