/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eunarbdayangco.younghomesapp.Controller;



import com.example.eunarbdayangco.younghomesapp.Databases.Database;
import com.example.eunarbdayangco.younghomesapp.Databases.UserDB;
import com.example.eunarbdayangco.younghomesapp.Model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Eunar B. Dayangco
 */
public class UserController {
    
    private Database<User> database;
    private String message;


    public UserController() {

        this.database = new UserDB();
    }
    public UserController(User user) {
         
        this.database = new UserDB(user);

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    public User getUser(int id){
       
        this.database = new UserDB();
         User foundUser = new User();
        
        try {
            ArrayList<User> users= this.database.getAllDatas(" WHERE userID=?", id);
            
            if(!users.isEmpty()){
                foundUser = users.get(0);
            }
     
        } catch (Exception ex) {

           Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                try {
                    this.database.getConn().close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        
         return foundUser;
    }
    


    
    
}
