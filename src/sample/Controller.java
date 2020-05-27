package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Controller {

    @FXML
    public TextField textField;

    private void appendText(String str){
        if (textField.getText().length() <= 15){
            textField.appendText(str);
        }
    }

    public void clickNamber(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        appendText(button.getText());
    }

    public void clearTextField(ActionEvent actionEvent) {
        textField.setText("");
    }

    public void keyChar(KeyEvent keyEvent) {

        if (keyEvent.getCode().isDigitKey()) {
            appendText(keyEvent.getText());
        }
    }
}
