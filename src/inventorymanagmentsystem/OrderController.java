package inventorymanagmentsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Muhammed
 */
public class OrderController implements Initializable {

    @FXML
    private Button close;

    @FXML
    private TextField count;

    @FXML
    private StackPane main_form;

    @FXML
    private Button order;

    @FXML
    private TextField productname;

    @FXML
    private TextField totalPrice;

    public static product selectedproduct;
    private Date dNow = new Date();
    private SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat ft1 = new SimpleDateFormat("hh:mm:ss");
    private int wID = logindata.wearhouseID;
    private int pID;
    private int tCount;
    private Connection connect = database.connectDb();
    private PreparedStatement prepare;
    private ResultSet result;

    public void order() {
        String sql = "INSERT INTO Sales (saledate, saletime, productID, WearhouseID, userID, Count, TotalPrice) "
                + "VALUES ('2023-07-03', '03:21:00', '4', '5', '1', '9', '00')";
        try {
            Alert alert;
            prepare = connect.prepareStatement(sql);
            prepare.setDate(1, new java.sql.Date(ft.parse(ft.format(dNow)).getTime()));
            prepare.setTime(2, new java.sql.Time(ft1.parse(ft1.format(dNow)).getTime()));
            prepare.setInt(3, selectedproduct.getID());
            prepare.setInt(4, logindata.wearhouseID);
            prepare.setString(5, logindata.username);
            prepare.setInt(6, Integer.parseInt(count.getText()));
            prepare.setFloat(7, Float.parseFloat(totalPrice.getText()));
            int rowsAffected = prepare.executeUpdate();
            if (rowsAffected > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Place  Order");
                alert.setHeaderText(null);
                alert.setContentText("Order placed successfully");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Place Order");
                alert.setHeaderText(null);
                alert.setContentText("unknowen Error , order did not placed");
                alert.showAndWait();
            }
        } catch (Exception e) {

        }
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public void close() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productname.setText(selectedproduct.getName());
        totalPrice.setText(Float.toString(tCount * selectedproduct.getPrice()));
    }

}
