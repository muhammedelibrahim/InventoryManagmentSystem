/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectClasses;

import java.text.SimpleDateFormat;
import java.sql.Time;
import java.sql.Date;

/**
 *
 * @author Muhammed
 */
public class sales {

    private int ID;
    //private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    // private SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
    private Date date;
    private Time salesTime;
    private int productID;
    private int wearhosueID;
    private String UserID;
    private int count;
    private float totalprice;

    public sales(Date date1, Time time,int wearhosueID, int productID,  String UserID, int count, float totalprice) {

        this.productID = productID;
        this.wearhosueID = wearhosueID;
        this.UserID = UserID;
        this.count = count;
        this.totalprice = totalprice;
    }

    public sales(Date date, Time salesTime, int wearhosueID, String UserID, int count, float totalprice) {
        this.date = date;
        this.salesTime = salesTime;
        this.wearhosueID = wearhosueID;
        this.UserID = UserID;
        this.count = count;
        this.totalprice = totalprice;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getSalesTime() {
        return salesTime;
    }

    public void setSalesTime(Time salesTime) {
        this.salesTime = salesTime;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getWearhosueID() {
        return wearhosueID;
    }

    public void setWearhosueID(int wearhosueID) {
        this.wearhosueID = wearhosueID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

}
