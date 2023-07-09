package inventorymanagmentsystem;

import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import objectClasses.sales;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author Muhammed
 */
public class DashboardController implements Initializable {
    
    @FXML
    private Button close;
    
    @FXML
    private Button home_button;
    
    @FXML
    private AnchorPane home_frame;
    
    @FXML
    private Button logout_button;
    
    @FXML
    private StackPane main_form;
    
    @FXML
    private Button minimize;
    
    @FXML
    private Button products_button;
    
    @FXML
    private Button products_button1;
    
    @FXML
    private AnchorPane products_frame;
    
    @FXML
    private Button sales_button;
    
    @FXML
    private AnchorPane sales_frame;
    
    @FXML
    private Button user_;
    
    @FXML
    private Button user_add;
    
    @FXML
    private Button user_delete;
    
    @FXML
    private Button user_search;
    
    @FXML
    private Button user_update;
    
    @FXML
    private TextField usercontorl_password;
    
    @FXML
    private TextField usercontorl_premission;
    
    @FXML
    private TextField usercontorl_username;
    
    @FXML
    private TextField usercontorl_wearhouseid;
    
    @FXML
    private Button usercontrol_button;
    
    @FXML
    private AnchorPane usercontrol_frame;
    
    @FXML
    private TableColumn<user, Integer> userltable_premission_col;
    
    @FXML
    private Label username;
    
    @FXML
    private TableView<user> users_table;
    
    @FXML
    private TableColumn<user, String> usertable_passowrd_col;
    
    @FXML
    private TableColumn<user, String> usertable_username_col;
    
    @FXML
    private TableColumn<user, Integer> usertable_wearhouseid_col;
    
    @FXML
    private Button wearhosue_button;
    
    @FXML
    private Button wearhosue_button1;
    
    @FXML
    private AnchorPane wearhouses_frame;
    
    @FXML
    private Button wearhouses_add;
    
    @FXML
    private TextField wearhouses_adress;
    
    @FXML
    private TableColumn<wearhouse, String> wearhouses_adress_col;
    
    @FXML
    private Button wearhouses_delete;
    
    @FXML
    private TextField wearhouses_name;
    
    @FXML
    private TableColumn<wearhouse, String> wearhouses_name_col;
    
    @FXML
    private Button wearhouses_search;
    
    @FXML
    private Button wearhouses_udpate;
    
    @FXML
    private TableView<wearhouse> wearhousestable;
    
    @FXML
    private Button product_add;
    
    @FXML
    private Button product_delete;
    
    @FXML
    private TextField product_name;
    
    @FXML
    private TextField product_price;
    
    @FXML
    private Button product_search;
    
    @FXML
    private Button product_update;
    
    @FXML
    private TableColumn<product, String> products_cat_col;
    
    @FXML
    private TableColumn<product, String> products_name_col;
    
    @FXML
    private TableColumn<product, String> products_price_col;
    
    @FXML
    private TableView<product> productstable;
    
    @FXML
    private ChoiceBox<String> product_cat_box;
    
    @FXML
    private TableColumn<sales, String> salescont;

    @FXML
    private TableColumn<sales, String> salesdate;

    @FXML
    private TableColumn<sales, String> salesproductid;

    @FXML
    private TableView<sales> salestable;

    @FXML
    private TableColumn<sales, String> salestime;

    @FXML
    private TableColumn<sales, String> salestotalprice;

    @FXML
    private TableColumn<sales, String> salesweharhouseid;

    @FXML
    private TableColumn<sales, String> salseuserid;
    
    private Connection connect = database.connectDb();
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private double x = 0;
    private double y = 0;
    
    private int selectedIndex;
    
    private user uuser;
    private wearhouse wwearhouse;
    private product pproduct;
    private sales sales;
    
    private ObservableList<user> users = FXCollections.observableArrayList();
    private ObservableList<wearhouse> wearhouses = FXCollections.observableArrayList();
    private ObservableList<product> products = FXCollections.observableArrayList();
    private ObservableList<sales> ssles = FXCollections.observableArrayList();
    
    public void close() {
        
        System.exit(0);
    }
    
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    
    public void logout() {
        
        try {
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            
            Optional<ButtonType> option = alert.showAndWait();
            
            if (option.get().equals(ButtonType.OK)) {

                //HIDE YOUR DASHBOARD FORM
                logout_button.getScene().getWindow().hide();

                //LINK YOUR LOGIN FORM 
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/Loginpanel.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                
                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });
                
                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                    
                    stage.setOpacity(.8);
                });
                
                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });
                
                stage.initStyle(StageStyle.TRANSPARENT);
                
                stage.setScene(scene);
                stage.show();
                
            } else {
                return;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void showusername() {
        username.setText(logindata.username);
    }
    
    public void switchframe(ActionEvent event) {
        if (event.getSource() == home_button) {
            home_frame.setVisible(true);
            sales_frame.setVisible(false);
            products_frame.setVisible(false);
            wearhouses_frame.setVisible(false);
            usercontrol_frame.setVisible(false);
            
        } else if (event.getSource() == sales_button) {
            
            home_frame.setVisible(false);
            sales_frame.setVisible(true);
            products_frame.setVisible(false);
            wearhouses_frame.setVisible(false);
            usercontrol_frame.setVisible(false);
            saleslist();
        } else if (event.getSource() == products_button) {
            home_frame.setVisible(false);
            sales_frame.setVisible(false);
            products_frame.setVisible(true);
            wearhouses_frame.setVisible(false);
            usercontrol_frame.setVisible(false);
            showproduts();
        } else if (event.getSource() == wearhosue_button) {
            home_frame.setVisible(false);
            sales_frame.setVisible(false);
            products_frame.setVisible(false);
            wearhouses_frame.setVisible(true);
            usercontrol_frame.setVisible(false);
            showwearhouses();
        } else if (event.getSource() == usercontrol_button) {
            home_frame.setVisible(false);
            sales_frame.setVisible(false);
            products_frame.setVisible(false);
            wearhouses_frame.setVisible(false);
            usercontrol_frame.setVisible(true);
            showusers();
        }
    }
    
    public void setProductCategorayChoiseboxItems() {
        String sql = "SELECT * FROM Category";
        List<String> items = new ArrayList<>();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                items.add(result.getString("Name"));
            }
        } catch (SQLException e) {
            
        }
        product_cat_box.setItems(FXCollections.observableArrayList(items));
    }

    //User Control Methodes, Start:
    public ObservableList<user> listUsers() {
        ObservableList<user> userslist = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users";
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                uuser = new user(
                        result.getString("username"),
                        result.getString("password"),
                        result.getInt("WarehouseID"),
                        result.getInt("permission"));
                userslist.add(uuser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userslist;
    }
    
    public void showusers() {
        users = listUsers();
        usertable_username_col.setCellValueFactory(new PropertyValueFactory("username"));
        usertable_passowrd_col.setCellValueFactory(new PropertyValueFactory("password"));
        userltable_premission_col.setCellValueFactory(new PropertyValueFactory("premission"));
        usertable_wearhouseid_col.setCellValueFactory(new PropertyValueFactory("wearhouseID"));
        users_table.setItems(users);
    }
    
    public void usertabletowselection() {
        users_table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Check for single click
                // Get the selected user from the table view
                user selectedUser = users_table.getSelectionModel().getSelectedItem();
                if (selectedUser != null) {
                    // Set the values from the selected user to the text fields
                    usercontorl_username.setText(selectedUser.getUsername());
                    usercontorl_password.setText(selectedUser.getPassword());
                    usercontorl_premission.setText(String.valueOf(selectedUser.getPremission()));
                    usercontorl_wearhouseid.setText(String.valueOf(selectedUser.getWearhouseID()));

                    // Set values for other text fields as needed
                }
            }
        });
    }
    
    public void addUser() {
        String usernamee = usercontorl_username.getText();
        String password = usercontorl_password.getText();
        int premission = Integer.parseInt(usercontorl_premission.getText());
        int warehouseID = Integer.parseInt(usercontorl_wearhouseid.getText());
        String sql = "INSERT INTO users (username, password, permission, WarehouseID) VALUES (?, ?, ?, ?)";
        try {
            Alert alert;
            if (usercontorl_username.getText().isEmpty()
                    || usercontorl_password.getText().isEmpty()
                    || usercontorl_premission.getText().isEmpty()
                    || usercontorl_wearhouseid.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, usernamee);
                prepare.setString(2, password);
                prepare.setInt(3, premission);
                prepare.setInt(4, warehouseID);
                
                int rowsAffected = prepare.executeUpdate();
                if (rowsAffected > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User Added");
                    alert.setHeaderText(null);
                    alert.setContentText("User added successfully");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to add user");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
        showusers();
        
    }
    
    public void deletUser() {
        String usernamee = usercontorl_username.getText();
        
        String sql = "DELETE FROM users WHERE username = ?";
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, usernamee);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to Delete User?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                int rowsAffected = prepare.executeUpdate();
                if (rowsAffected > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User Added");
                    alert.setHeaderText(null);
                    alert.setContentText("User deleted successfully");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("User not found");
                    alert.showAndWait();
                }
                
            } else {
                return;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
        
        usercontorl_username.clear();
        usercontorl_password.clear();
        usercontorl_premission.clear();
        usercontorl_wearhouseid.clear();
        
        showusers();
    }
    
    public void updateUser() {
        String usernamee = usercontorl_username.getText();
        String password = usercontorl_password.getText();
        int premission = Integer.parseInt(usercontorl_premission.getText());
        int warehouseID = Integer.parseInt(usercontorl_wearhouseid.getText());
        String sql = "UPDATE users SET password = ?, permission = ?, WarehouseID = ? WHERE username = ?";
        try {
            Alert alert;
            if (usercontorl_username.getText().isEmpty()
                    || usercontorl_password.getText().isEmpty()
                    || usercontorl_premission.getText().isEmpty()
                    || usercontorl_wearhouseid.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, password);
                prepare.setInt(2, premission);
                prepare.setInt(3, warehouseID);
                prepare.setString(4, usernamee);
                
                int rowsAffected = prepare.executeUpdate();
                if (rowsAffected > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User Update");
                    alert.setHeaderText(null);
                    alert.setContentText("User updated successfully");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("User not found");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
        showusers();
        
    }
    
    public void searchUser() {
        String susername = "%" + usercontorl_username.getText() + "%";
        String password = "%" + usercontorl_password.getText() + "%";
        int premission = Integer.parseInt(usercontorl_premission.getText());
        int warehouseID = Integer.parseInt(usercontorl_wearhouseid.getText());

        // Validate the input data, perform necessary checks
        // Construct the SQL query based on the entered search criteria
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM users WHERE 1=1");
        List<Object> queryParameters = new ArrayList<>();
        
        if (!usercontorl_username.getText().isEmpty()) {
            queryBuilder.append(" AND username LIKE ?");
            queryParameters.add(susername);
        }
        
        if (!usercontorl_password.getText().isEmpty()) {
            queryBuilder.append(" AND password LIKE ?");
            queryParameters.add(password);
        }
        
        if (!usercontorl_premission.getText().isEmpty()) {
            queryBuilder.append(" AND permission = ?");
            queryParameters.add(premission);
        }
        
        if (!usercontorl_wearhouseid.getText().isEmpty()) {
            queryBuilder.append(" AND WarehouseID = ?");
            queryParameters.add(warehouseID);
        }
        
        String sql = queryBuilder.toString();

        // Perform the search query
        try {
            prepare = connect.prepareStatement(sql);
            
            for (int i = 0; i < queryParameters.size(); i++) {
                prepare.setObject(i + 1, queryParameters.get(i));
            }
            
            result = prepare.executeQuery();
            
            ObservableList<user> searchResults = FXCollections.observableArrayList();
            while (result.next()) {
                user foundUser = new user(
                        result.getString("username"),
                        result.getString("password"),
                        result.getInt("WarehouseID"),
                        result.getInt("permission"));
                searchResults.add(foundUser);
            }

            // Set the search results in the table view
            users_table.setItems(searchResults);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
        
    }

    // User Control methodes, END;
    //Wearhouses Control methods, Start:
    public ObservableList<wearhouse> listWearhouses() {
        ObservableList<wearhouse> wearhouselist = FXCollections.observableArrayList();
        String sql = "SELECT * FROM inventorys";
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                wwearhouse = new wearhouse(
                        result.getString("name"),
                        result.getString("adress"));
                wearhouselist.add(wwearhouse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wearhouselist;
    }
    
    public void showwearhouses() {
        wearhouses = listWearhouses();
        wearhouses_name_col.setCellValueFactory(new PropertyValueFactory("name"));
        wearhouses_adress_col.setCellValueFactory(new PropertyValueFactory("adress"));
        wearhousestable.setItems(wearhouses);
    }
    
    public void wearhousetabletowselection() {
        wearhousestable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Check for single click
                // Get the selected user from the table view
                wearhouse selectedWearhouse = wearhousestable.getSelectionModel().getSelectedItem();
                if (selectedWearhouse != null) {
                    // Set the values from the selected user to the text fields
                    wearhouses_name.setText(selectedWearhouse.getName());
                    wearhouses_adress.setText(selectedWearhouse.getAdress());
                    selectedIndex = wearhousestable.getSelectionModel().getSelectedIndex();
                    System.out.println("Selected item index: " + selectedIndex);
                }
            }
        });
    }
    
    public void addWearhouse() {
        String namee = wearhouses_name.getText();
        String adresss = wearhouses_adress.getText();
        String sql = "INSERT INTO inventorys (name, adress) VALUES (?, ?)";
        try {
            Alert alert;
            if (wearhouses_name.getText().isEmpty() || wearhouses_adress.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, namee);
                prepare.setString(2, adresss);
                
                int rowsAffected = prepare.executeUpdate();
                if (rowsAffected > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Wearhouse Added");
                    alert.setHeaderText(null);
                    alert.setContentText("Product Added successfully");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to add Wearhouse");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
        showwearhouses();
    }
    
    public void deleteWearhouse() {
        String namee = wearhouses_name.getText();
        
        String sql = "DELETE FROM inventorys WHERE name = ?";
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, namee);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to Delete Wearhouse?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                int rowsAffected = prepare.executeUpdate();
                if (rowsAffected > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Wearhouse Deleted");
                    alert.setHeaderText(null);
                    alert.setContentText("Wearhouse deleted successfully");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wearhouse not found");
                    alert.showAndWait();
                }
                
            } else {
                return;
            }
            
        } catch (SQLException e) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
        
        wearhouses_name.clear();
        wearhouses_adress.clear();
        
        showwearhouses();
    }
    
    public void updateWearhouse() {
        String newAddress = wearhouses_adress.getText();
        String warehouseName = wearhouses_name.getText();
        String sql = "UPDATE inventorys SET adress = ? WHERE name = ?";
        try {
            Alert alert;
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, newAddress);
            prepare.setString(2, warehouseName);
            
            int rowsAffected = prepare.executeUpdate();
            if (rowsAffected > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Update");
                alert.setHeaderText(null);
                alert.setContentText("Warehouse address updated successfully");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Warehouse not found");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }

        // Refresh the warehouses table to reflect the update
        showwearhouses();
    }
    
    public void searhWearhouse() {
        
    }

    //Wearhouses Control methods, end;
    // Products:
    public ObservableList<product> listProducts() {
        /*ObservableList<product> productslist = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Products";
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                String sql1 = "SELECT Name FROM Category WHERE ID = ?";
                try (PreparedStatement prepare1 = connect.prepareStatement(sql1);) {
                    prepare1.setInt(1, result.getInt("CategoryID"));
                    ResultSet result1 = prepare1.executeQuery();
                    if (result1.next()) {
                        pproduct = new product(
                                result.getString("Name"),
                                result.getFloat("price"),
                                result1.getString("Name"));
                        productslist.add(pproduct);
                    }
                } catch (Exception e) {
                }
            }
        } catch (SQLException e) {
        }
        return productslist;*/
        ObservableList<product> productslist = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Products";
        try (PreparedStatement prepare = connect.prepareStatement(sql); ResultSet result = prepare.executeQuery()) {
            while (result.next()) {
                String sql1 = "SELECT * FROM Category WHERE ID = ?";
                
                try (PreparedStatement prepare1 = connect.prepareStatement(sql1)) {
                    prepare1.setInt(1, result.getInt("CategoryID"));
                    try (ResultSet result1 = prepare1.executeQuery()) {
                        
                        if (result1.next()) {
                            
                            product product = new product(
                                    result.getInt("ID"),
                                    result.getString("Name"),
                                    result.getFloat("price"),
                                    result1.getString("Name"));
                            productslist.add(product);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
        }
        return productslist;
    }
    
    public void showproduts() {
        products = listProducts();
        products_name_col.setCellValueFactory(new PropertyValueFactory("name"));
        products_cat_col.setCellValueFactory(new PropertyValueFactory("categoray"));
        products_price_col.setCellValueFactory(new PropertyValueFactory("price"));
        productstable.setItems(products);
    }
    
    public void producttableselection() {
        productstable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Check for single click
                // Get the selected user from the table view
                product selectedProduct = productstable.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    // Set the values from the selected user to the text fields
                    product_name.setText(selectedProduct.getName());
                    product_price.setText(Float.toString(selectedProduct.getPrice()));
                    product_cat_box.setValue(selectedProduct.getCategoray());
                }
            }
        });
    }
    
    public void addProduct() {
        String namee = product_name.getText();
        float price = Float.parseFloat(product_price.getText());
        String sql = "INSERT INTO Products (Name, price, CategoryID) VALUES (?, ?, ?)";
        try {
            Alert alert;
            if (product_price.getText().isEmpty() || product_name.getText().isEmpty() || product_cat_box.getSelectionModel().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                
                String sql1 = "SELECT ID FROM Category WHERE Name = ?";
                PreparedStatement prepare1 = connect.prepareStatement(sql1);
                prepare1.setString(1, product_cat_box.getValue());
                ResultSet result1 = prepare1.executeQuery();
                prepare = connect.prepareStatement(sql);
                /*prepare.setString(1, namee);
                prepare.setFloat(2, price);
                prepare.setInt(3,result1.getInt("ID"));
                prepare1.close();*/
                if (result1.next()) {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, namee);
                    prepare.setFloat(2, price);
                    prepare.setInt(3, result1.getInt("ID"));

                    // Rest of your code
                }
                int rowsAffected = prepare.executeUpdate();
                if (rowsAffected > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Product Added");
                    alert.setHeaderText(null);
                    alert.setContentText("Product Added successfully");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to add Prodct");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
        showproduts();
    }
    
    public void deleteProduct() {
        String namee = product_name.getText();
        
        String sql = "DELETE FROM Products WHERE Name = ?";
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, namee);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to Delete Product?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                int rowsAffected = prepare.executeUpdate();
                if (rowsAffected > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Product Deleted");
                    alert.setHeaderText(null);
                    alert.setContentText("Product deleted successfully");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Product not found");
                    alert.showAndWait();
                }
                
            } else {
                return;
            }
            
        } catch (SQLException e) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
        
        product_name.clear();
        product_price.clear();
        product_cat_box.getSelectionModel().clearSelection();
        showproduts();
    }
    
    public void updateProduct() {
        
        float newprice = Float.parseFloat(product_price.getText());
        String namee = product_name.getText();
        String sql = "UPDATE Products SET CategoryID = ?, price = ? Where Name = ?";
        try {
            Alert alert;
            String sql1 = "SELECT ID FROM Category WHERE Name = ?";
            PreparedStatement prepare1 = connect.prepareStatement(sql1);
            prepare1.setString(1, product_cat_box.getValue());
            ResultSet result1 = prepare1.executeQuery();
            prepare = connect.prepareStatement(sql);
            if (result1.next()) {
                prepare = connect.prepareStatement(sql);
                prepare.setFloat(1, newprice);
                prepare.setInt(2, result1.getInt("ID"));
                prepare.setString(3, namee);
                
            }
            
            int rowsAffected = prepare.executeUpdate();
            if (rowsAffected > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Product Update");
                alert.setHeaderText(null);
                alert.setContentText("Product  updated successfully");
                showproduts();
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Product not found");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
        
    }
    
    public void searchProduct() {
        
    }
    
    public void doubleclick() {
        productstable.setRowFactory(tv -> {
            TableRow<product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    product rowData = row.getItem();
                    showNewWindow(rowData);
                }
            });
            return row;
        });
    }
    
    private void showNewWindow(product rowData) {
        
        ProductstorageinfoController.selectedproduct = rowData;
        OrderController.selectedproduct = rowData;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/productstorageinfo.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });
            
            stage.initStyle(StageStyle.TRANSPARENT);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
        }
    }
    
    public void order() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Order.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });
            
            stage.initStyle(StageStyle.TRANSPARENT);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
        }
    }
    // end Products

    public ObservableList<sales> listofsales() {
        ObservableList<sales> salesslist = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Sales";
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                sales = new sales(result.getDate("saledate"),
                         result.getTime("saletime"),
                         result.getInt("productID"),
                         result.getInt("WearhouseID"),
                         result.getString("userID"),
                        result.getInt("Count"),
                        result.getFloat("TotalPrice"));
                salesslist.add(sales);
            }
        } catch (SQLException e) {
            
        }
        return salesslist;
    }
    
    public void saleslist() {
        ObservableList<sales> salesslist = listofsales();
        salesdate.setCellValueFactory(new PropertyValueFactory("date"));
        salestime.setCellValueFactory(new PropertyValueFactory("salesTime"));
        salesproductid.setCellValueFactory(new PropertyValueFactory("productID"));
        salesweharhouseid.setCellValueFactory(new PropertyValueFactory("wearhosueID"));
        salseuserid.setCellValueFactory(new PropertyValueFactory("UserID"));
        salescont.setCellValueFactory(new PropertyValueFactory("count"));
        salestotalprice.setCellValueFactory(new PropertyValueFactory("totalprice"));
        salestable.setItems(salesslist);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showusername();
         saleslist();
        usertabletowselection();
        wearhousetabletowselection();
        producttableselection();
        setProductCategorayChoiseboxItems();
        if (logindata.premission != 1) {
            usercontrol_button.setDisable(true);
            wearhosue_button.setDisable(true);
            product_add.setVisible(false);
            product_delete.setVisible(false);
            product_update.setVisible(false);
            
        }
        if (logindata.premission == 1) {
                    doubleclick();

        }
    }
    
}
