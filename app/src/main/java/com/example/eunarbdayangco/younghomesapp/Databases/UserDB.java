/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eunarbdayangco.younghomesapp.Databases;




import com.example.eunarbdayangco.younghomesapp.Model.User;

import java.util.ArrayList;

/**
 *
 * @author Eunar B. Dayangco
 */
public class UserDB extends Database<User> {
    

    private final int FROMID = 1000;
    private final int TOID = Integer.MAX_VALUE;
    private final String TABLEFIELDS = "userID,addedDate,type,fullname,contactNumber,email,"
                + "username,password,active";

    public UserDB() {
        super();
    }

    
    public UserDB(User user) {
        super();
        setObject(user);
    }

    
    
    public void setUserID()throws Exception{
        int genID = 0;
        
        while(true){
            
            genID =  (int) (Math.random() * (this.TOID - this.FROMID)) + this.FROMID;
            
            
            if(!isIDexist(genID)){
                break;
            }
        }
        
       getObject().setUserID(genID);
    
    }
        
    public boolean isIDexist(int id) throws Exception{
            
        String statement = "SELECT * FROM user WHERE userID=?";

        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setInt(1, id);
        this.setRs(this.getPs().executeQuery());

        int countfound = 0;

        while(this.getRs().next()){
            countfound++;

        }

       return countfound == 1;
             
              
    }

    @Override
    public Database insertData() throws Exception {
        
        String values = "?,?,?,?,?,?,?,?,?";
        String statement = "INSERT INTO user("+this.TABLEFIELDS+")VALUES("+
                values+")";
        setUserID();
        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setInt(1, this.getObject().getUserID());
        this.getPs().setString(2, this.getObject().getAddedDate());
        this.getPs().setString(3, this.getObject().getUserType());
        this.getPs().setString(4, this.getObject().getFullname());
        this.getPs().setString(5, this.getObject().getContactNumber());
        this.getPs().setString(6, this.getObject().getEmail());
        this.getPs().setString(7, this.getObject().getUsername());
        this.getPs().setString(8, this.getObject().getPassword());
        this.getPs().setBoolean(9, this.getObject().isActive());
        this.setSuccess(this.getPs().executeUpdate() == 1);
        
        return this;
        
    }

    @Override
    public Database updateData() throws Exception {
        
        String cond = "type=?,fullname=?,contactNumber=?,email=?,username=?,password=?,active=?";
        
        
        String statement = "UPDATE user SET "+cond+" WHERE userID=?";
        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setString(1, this.getObject().getUserType());
        this.getPs().setString(2, this.getObject().getFullname());
        this.getPs().setString(3, this.getObject().getContactNumber());
        this.getPs().setString(4, this.getObject().getEmail());
        this.getPs().setString(5, this.getObject().getUsername());
        this.getPs().setString(6, this.getObject().getPassword());
        this.getPs().setBoolean(7, this.getObject().isActive());
        this.getPs().setInt(8, this.getObject().getUserID());
        
        this.setSuccess(this.getPs().executeUpdate() == 1);
         
         return this;
        
    }

    @Override
    public Database deleteData() throws Exception {
        
        String statement = "DELETE FROM user WHERE userID=?";
        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setInt(1,  this.getObject().getUserID());
        this.setSuccess( this.getPs().executeUpdate() == 1);
        
        return this;
    }
    
    @Override
    public ArrayList<User> getAllDatas(String condition, Object... parameters) throws Exception {
         ArrayList<User> users = new ArrayList<>();
        
       String statement = "SELECT * FROM user " + condition;
       
        this.setPs(this.getConn().prepareStatement(statement));
        
        int x = 1;
        for(Object parameter:parameters){
            
            if(parameter instanceof String){
              
                this.getPs().setString(x, parameter.toString());
                
            }else if(parameter instanceof Integer){
                this.getPs().setInt(x, Integer.parseInt(parameter.toString()));
            }else if(parameter instanceof Double){
                this.getPs().setDouble(x, Double.parseDouble(parameter.toString()));
            }else if(parameter instanceof Boolean){
                this.getPs().setBoolean(x, Boolean.parseBoolean(parameter.toString()));
            }
            x++;
        }
        
       this.setRs(this.getPs().executeQuery());
        while(this.getRs().next()){
            
            User foundUser = new User();
            foundUser.setUserID(this.getRs().getInt("userID"));
            foundUser.setAddedDate(this.getRs().getString("addedDate"));
            foundUser.setUserType(this.getRs().getString("type"));
            foundUser.setFullname(this.getRs().getString("fullname"));
            foundUser.setContactNumber(this.getRs().getString("contactNumber"));
            foundUser.setEmail(this.getRs().getString("email"));
            foundUser.setUsername(this.getRs().getString("username"));
            foundUser.setPassword(this.getRs().getString("password"));
            foundUser.setActive(this.getRs().getBoolean("active"));
            users.add(foundUser);
        
        }
        return users;
    }

  
    
}
