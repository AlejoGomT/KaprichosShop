package shop.develop.kaprichosshop;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ShopApplication extends Application {
    private double cursorX;
    private double cursorY;
    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("kprichos-view.fxml"));

        fxmlLoader.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                cursorX = event.getSceneX();
                cursorY = event.getSceneY();
            }
        });
        fxmlLoader.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - cursorX);
                stage.setY(event.getScreenY() - cursorY);
            }
        });

        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(fxmlLoader);
        stage.setTitle("KprichosShop");
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}