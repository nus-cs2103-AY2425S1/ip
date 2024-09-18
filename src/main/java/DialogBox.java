import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * A custom HBox component representing a dialog box used in the ChatterBox application.
 * This class provides a convenient way to display messages and images in a visually
 * appealing and structured format.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;


    /**
     * Constructs a new DialogBox with specified text, image and background
     *
     * @param s The text to be displayed in dalog box.
     * @param i The image to be displayed in the dialog box.
     * @param backgroundColor The background color of the dialog box.
     */
    public DialogBox(String s, Image i, Color backgroundColor, Font font) {
        this.text = new Label(s);
        this.displayPicture = new ImageView(i);

        text.setWrapText(true);
        text.setFont(font);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setSpacing(10);
        this.setPadding(new Insets(10));

        Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);

        this.setPadding(new Insets(10, 10, 10, 10));

        this.setAlignment(Pos.TOP_RIGHT);
        this.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));

        this.getChildren().addAll(text, displayPicture);

    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String s, Image i) {
        Color translucentGreen = Color.color(Color.OLIVE.getRed(),
                Color.OLIVE.getGreen(),
                Color.OLIVE.getBlue(),
                0.40);
        Font userFont = new Font("Times New Roman", 14);
        return new DialogBox(s, i, translucentGreen, userFont);
    }

    public static DialogBox getChatterBoxDialog(String s, Image i) {
        if (!s.contains("OOPS!!!")) {
            Color translucentOrchid = Color.color(Color.ORCHID.getRed(),
                    Color.ORCHID.getGreen(),
                    Color.ORCHID.getBlue(),
                    0.40);
            Font chatbotFont = new Font("Bodoni 72", 12);
            var db = new DialogBox(s, i, translucentOrchid, chatbotFont);
            db.flip();
            return db;
        } else {
            Color translucentRed = Color.color(1.0, 0.0, 0.0, 0.4);
            Font chatbotFont = new Font("Bodoni 72", 12);
            var db = new DialogBox(s, i, translucentRed, chatbotFont);
            db.flip();
            return db;
        }
    }
}