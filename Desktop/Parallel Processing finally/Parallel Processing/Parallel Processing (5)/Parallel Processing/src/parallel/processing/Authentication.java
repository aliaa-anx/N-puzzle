/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parallel.processing;

import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author roaam
 */
public class Authentication {
    public static boolean login(String card_num, String card_pin){
        DBConnection.openConnection();
            String query = "SELECT * FROM account WHERE cardNumber = '" + card_num + "' AND"
                    + " cardPin = '" + card_pin + "'";
            ResultSet result = DBConnection.selectQuery(query);
            if(result == null)
                return false;
            else{
                return true;
            }
                

    }
    
    public static void logout(String accountID, String accountPass){
        
    }
}
