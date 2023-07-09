/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectClasses;

/**
 *
 * @author Muhammed
 */
public class productStorage {
    public int ID;
    private String wID;
    private int count;

    public productStorage(String wID, int count) {
        this.wID = wID;
        this.count = count;
    }

    public String getWID() {
        return wID;
    }

    public void setWID(String wID) {
        this.wID = wID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
}
