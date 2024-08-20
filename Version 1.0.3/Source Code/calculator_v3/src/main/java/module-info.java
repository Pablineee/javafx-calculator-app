module com.calculator.calculator_v3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.calculator.calculator_v3 to javafx.fxml;
    exports com.calculator.calculator_v3;
}