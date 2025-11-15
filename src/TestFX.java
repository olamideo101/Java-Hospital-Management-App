import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TestFX extends Application {

    @Override
    public void start(Stage stage) {
        Label lbl = new Label("JavaFX is working!");
        Scene scene = new Scene(lbl, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Test FX");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
