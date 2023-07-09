package inventorymanagmentsystem;

import com.mysql.jdbc.Statement;
import objectClasses.productStorage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Muhammed
 */
public class ProductstorageinfoController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private TableColumn<productStorage, String> count;

    @FXML
    private TableView<productStorage> products;

    @FXML
    private TableColumn<productStorage, String> wname;
    
    private productStorage productStorage;
    public static product selectedproduct;

    private Connection connect = database.connectDb();
    private PreparedStatement prepare;
    private ResultSet result;
    ObservableList<productStorage> productStoragelist = FXCollections.observableArrayList();

    public void  showData() {
        String sql = "SELECT * FROM Storage WHERE ProductID = ?";
        try{
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, selectedproduct.getID());
            result = prepare.executeQuery();
            while(result.next()){
                String sql1 = "SELECT * FROM inventorys WHERE ID = ?";
                PreparedStatement prepare1;
                ResultSet result1;
                prepare1 = connect.prepareStatement(sql1);
                prepare1.setInt(1, result.getInt("WarehouseID"));
                result1 = prepare1.executeQuery();
                if(result1.next()){
                    productStorage = new productStorage(result1.getString("name"),result.getInt("Count"));
                    productStoragelist.add(productStorage);
                }
            }
        } catch(Exception e){}
        showstorage();

    }
    public void showstorage(){
        //showData();
        wname.setCellValueFactory(new PropertyValueFactory("wID"));
        count.setCellValueFactory(new PropertyValueFactory("count"));
        products.setItems(productStoragelist);
    }

    public void close() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        showData();
        showstorage();
    }

}
