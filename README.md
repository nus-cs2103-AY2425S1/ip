# Stelle

> When there is a chance to make a choice, make one that you know you won't regret... - Stelle (Honkai: Star Rail)

Stelle (Chat bot simulation) frees your mind of having to remember things you need to do. It is:
- text-based
- easy to learn
- BLAZING FAST to use :fire: :fire: :fire:

All you need to do to use Stelle is:
1. Download it from [here](https://github.com/LeeZeHao/ip/releases)
2. Double click the .jar file.
3. Add your tasks.
4. Enjoy improved task management!

And it's <b>FREE</b>!

Features:
- [X] Manage general tasks
- [X] Manage deadlines
- [ ] Reminders (Coming soon!)

---

If you are a Java programmer, you can use it for practicing Java as well!

Here is the `main` method in `stelle.ui.Main`: 
```java
   @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setStelle(stelle); // inject the Duke instance
            stage.setTitle("Stelle (simulated)");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```