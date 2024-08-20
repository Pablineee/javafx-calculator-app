package com.calculator.calculator_v3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CalculatorController {
    @FXML
    private Label output;
    private static boolean operatorSelected = false;
    private static boolean containsDecimal = false;
    private static boolean isNewInput = true; // New flag to track if a new input should start
    private static int operator = 0; // 1 - Add; 2 - Subtract; 3 - Multiply; 4 - Divide

    @FXML
    private static String currentOutput = "";
    private static String num1 = "";
    private static String num2 = "";
    private static String result = "";

    @FXML
    protected void onBtn0Click(){
        handleNumberInput("0");
    }

    @FXML
    protected void onBtn1Click(){
        handleNumberInput("1");
    }

    @FXML
    protected void onBtn2Click(){
        handleNumberInput("2");
    }

    @FXML
    protected void onBtn3Click(){
        handleNumberInput("3");
    }

    @FXML
    protected void onBtn4Click(){
        handleNumberInput("4");
    }

    @FXML
    protected void onBtn5Click(){
        handleNumberInput("5");
    }

    @FXML
    protected void onBtn6Click(){
        handleNumberInput("6");
    }

    @FXML
    protected void onBtn7Click(){
        handleNumberInput("7");
    }

    @FXML
    protected void onBtn8Click(){
        handleNumberInput("8");
    }

    @FXML
    protected void onBtn9Click(){
        handleNumberInput("9");
    }

    // Consolidated method to handle number input
    private void handleNumberInput(String number) {
        if (isNewInput) {
            currentOutput = number;
            isNewInput = false;
        } else {
            currentOutput += number;
        }
        output.setText(currentOutput);
    }

    @FXML
    protected void onClearBtnClick(){
        operatorSelected = false;
        containsDecimal = false;
        isNewInput = true;
        currentOutput = "0";
        num1 = ""; num2 = ""; result = "";
        output.setText(currentOutput);
    }

    @FXML
    protected void onPlusMinusBtnClick(){
        if (!result.isEmpty()){
            if (Double.parseDouble(result) > 0){ // Number is positive
                try {
                    currentOutput = String.valueOf(Double.parseDouble(result) * -1.0);
                    result = currentOutput;
                    testResult();
                    output.setText(result);
                } catch (NumberFormatException e){
                    output.setText("Error");
                }
            } else { // Number is negative
                try {
                    currentOutput = String.valueOf(Double.parseDouble(result) * -1.0);
                    result = currentOutput;
                    testResult();
                    output.setText(result);
                } catch (NumberFormatException e){
                    output.setText("Error");
                }
            }

        } else {
            try {
                currentOutput = String.valueOf(Double.parseDouble(currentOutput) * -1.0);
                if (currentOutput.endsWith(".0")) {
                    currentOutput = currentOutput.substring(0, currentOutput.length() - 2);
                }
                output.setText(currentOutput);
            } catch (NumberFormatException e){
                output.setText("Error");
            }
        }
    }

    @FXML
    protected void onPercentBtnClick(){
        if (!result.isEmpty()){
            try {
                currentOutput = String.valueOf(Double.parseDouble(result) / 100);
                result = currentOutput;
                output.setText(result);
            } catch (NumberFormatException e){
                output.setText("Error");
            }
        } else {
            try {
                currentOutput = String.valueOf(Double.parseDouble(currentOutput) / 100);
                output.setText(currentOutput);
            } catch (NumberFormatException e){
                output.setText("Error");
            }
        }
    }

    @FXML
    protected void onDivideBtnClick(){
        handleOperator(4);
    }

    @FXML
    protected void onMultiplyBtnClick(){
        handleOperator(3);
    }

    @FXML
    protected void onSubtractBtnClick(){
        handleOperator(2);
    }

    @FXML
    protected void onAddBtnClick(){
        handleOperator(1);
    }

    // Consolidated method to handle operator selection
    private void handleOperator(int operator) {
        if (!operatorSelected) {
            this.operator = operator;
            operatorSelected = true;
            num1 = currentOutput;
            isNewInput = true;
            containsDecimal = false;
        } else {
            this.operator = operator;
            isNewInput = true;
            containsDecimal = false;
        }
    }

    @FXML
    protected void onEqualsBtnClick() {
        if (operatorSelected) {
            if (isNewInput) {
                currentOutput = num2; // Use num2 for continued operations
            } else {
                num2 = currentOutput;
            }

            result = calculate(num1, num2, operator);
            testResult();

            if (result.equals("Error")) {
                output.setText("Error");
                return;
            }

            output.setText(result);
            num1 = result;
            currentOutput = ""; // Prepare for next input
            isNewInput = true;
        }
    }

    // Utility method to handle the calculation
    private String calculate(String num1, String num2, int operator) {
        if (num1.isEmpty() || num2.isEmpty()) {
            return "Error";  // Handle empty input case
        }

        double result = 0;

        try {
            double number1 = Double.parseDouble(num1);
            double number2 = Double.parseDouble(num2);

            switch (operator) {
                case 1:
                    result = number1 + number2;
                    break;
                case 2:
                    result = number1 - number2;
                    break;
                case 3:
                    result = number1 * number2;
                    break;
                case 4:
                    if (number2 == 0) {
                        return "Error";
                    }
                    result = number1 / number2;
                    break;
            }
        } catch (NumberFormatException e) {
            return "Error"; // Handle parsing errors
        }

        return String.valueOf(result);
    }

    // Method to clean up the result by removing unnecessary ".0"
    @FXML
    protected void testResult() {
        if (result.endsWith(".0")) {
            result = result.substring(0, result.length() - 2);
        }
    }

    @FXML
    protected void onDecimalBtnClick(){
        if (!containsDecimal){
            if (isNewInput) {
                currentOutput = "0.";
                isNewInput = false;
            } else {
                currentOutput += ".";
            }
            containsDecimal = true;
            output.setText(currentOutput);
        }
    }
}

