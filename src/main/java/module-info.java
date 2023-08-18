module shop.develop.kaprichosshop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens shop.develop.kaprichosshop to javafx.fxml;
    exports shop.develop.kaprichosshop;
}