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
    @FXML
    public TextField textFieldCopy;


    private Double leftArg, rightArg;
    private boolean isLeftArgInputet = false;
    private String operat = null;
    private boolean calc = false;
    private boolean numer = false;

    private void appendText(String str){
        if (textField.getText().length() <= 15){
            textField.appendText(str);
        }
    }

    public void clickNamber(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (!calc) {
            numer = true;
            appendText(button.getText());
        } else {
            textField.setText("");
            calc = false;
            numer = true;
            appendText(button.getText());
        }

    }

    public void clearTextField(ActionEvent actionEvent) {
        textField.setText("");
        textFieldCopy.setText("");
        isLeftArgInputet = false;
      }

//    public void keyChar(KeyEvent keyEvent) {
//        if (keyEvent.getCode().isDigitKey()) {
//            appendText(keyEvent.getText());
//        }
//    }

    public void clickoperation(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String stringOperation = button.getText();
        if (!isLeftArgInputet && numer) {
            if (!textField.getText().isEmpty()) {
                leftArg = Double.parseDouble(textField.getText());
                isLeftArgInputet = true;
                calc = true;
                textField.setText("");
                textFieldCopy.appendText(leftArg+stringOperation);
                operat = stringOperation;
            }
        } else if (numer){
            calculate(actionEvent);
            operat = stringOperation;
            textFieldCopy.appendText(stringOperation);
        }
        numer = false;
    }

    public void calculate(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String stringOperation = button.getText();
//        if (stringOperation.equals("=")){
//            operat = "=";
//        }

        if (!isLeftArgInputet && numer && !stringOperation.equals("=")) {
            if (!textField.getText().isEmpty()) {
                leftArg = Double.parseDouble(textField.getText());
                isLeftArgInputet = true;
                calc = true;
                textFieldCopy.setText(leftArg + stringOperation);
                operat = stringOperation;
            }

        }else
            if (isLeftArgInputet && numer) {
                if (!textField.getText().isEmpty()) {
                    rightArg = Double.parseDouble(textField.getText());
                    Double result = null;
                    switch (operat) {
                        case "+":
                            result = leftArg + rightArg;
                            break;
                        case "-":
                            result = leftArg - rightArg;
                            break;
                        case "*":
                            result = leftArg * rightArg;
                            break;
                        case "/":
                            result = leftArg / rightArg;
                    }
                    if (stringOperation.equals("=") && result != null) {
                        textFieldCopy.appendText(rightArg + " = " + result);
                        isLeftArgInputet = false;
                        calc = false;
                        operat = null;
                        leftArg = null;
                        textField.setText("");
                    } else {
                        textFieldCopy.appendText(String.valueOf(rightArg+stringOperation));
                        leftArg = result;
                        calc = true;
                        operat = stringOperation;
                        textField.setText(String.valueOf(result));
                    }
                    numer = false;
                }
            }

    }
}
