/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eunarbdayangco.younghomesapp.Databases;



import com.example.eunarbdayangco.younghomesapp.Controller.UserController;
import com.example.eunarbdayangco.younghomesapp.Model.Student;

import java.util.ArrayList;


/**
 *
 * @author Eunar B. Dayangco
 */
public class StudentDB extends Database<Student> {

   
    private final int fromID = 1000000;
    private final int toID = Integer.MAX_VALUE;
    private final String TABLEFIELDS = "studentID,schoolID,fullname,address,"
            + "contactNumber,level,image,form138,birthcertificate,active,graduated,userID";

    public StudentDB() {
        super();
    }

    public StudentDB(Student student) {
        super();
        this.setObject(student);
    }

    
    @Override
    public Database insertData() throws Exception {
       

        return this;
        
    }
    
 
    @Override
    public Database updateData() throws Exception {
        

        return this;
    }

    @Override
    public Database deleteData() throws Exception {
        
        String statement = "DELETE FROM student WHERE studentID=?";
        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setInt(1,  this.getObject().getStudentID());
        this.setSuccess( this.getPs().executeUpdate() == 1);
        this.getPs().close();
        return this;
    }

    
    @Override
    public ArrayList<Student> getAllDatas(String condition, Object... parameters) throws Exception {
        
        ArrayList<Student> students  = new ArrayList<>();
        String statement = "SELECT * FROM student "+ condition;
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
        
            Student foundstudent = new Student();
            foundstudent.setStudentID(this.getRs().getInt("studentID"));
            foundstudent.setSchoolID(this.getRs().getString("schoolID"));
            foundstudent.setAddedDate(this.getRs().getTimestamp("addedDate"));
            foundstudent.setFullname(this.getRs().getString("fullname"));
            foundstudent.setAddress(this.getRs().getString("address"));
            foundstudent.setContactNumber(this.getRs().getString("contactNumber"));
            foundstudent.setLevel(this.getRs().getString("level"));
            foundstudent.setForm138(this.getRs().getBoolean("form138"));
            foundstudent.setBirthCertificate(this.getRs().getBoolean("birthcertificate"));
            foundstudent.setGraduated(this.getRs().getBoolean("graduated"));
            foundstudent.setActive(this.getRs().getBoolean("active"));
            foundstudent.setUser(new UserController().getUser(this.getRs().getInt("userID")));
            students.add(foundstudent);
            
        }
         
        this.getPs().close();
        this.getRs().close();
  
        return students;
        
        
    }
    

    //this will return only studentID,schoolID and fullname of student
    public ArrayList<Student> findStudents(String condition,Object...parameters) throws Exception{
        ArrayList<Student> students  = new ArrayList<>();
        String statement = "SELECT studentID,schoolID,fullname FROM student "+ condition;
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
        
            Student foundstudent = new Student();
            foundstudent.setStudentID(this.getRs().getInt("studentID"));
            foundstudent.setSchoolID(this.getRs().getString("schoolID"));
            foundstudent.setFullname(this.getRs().getString("fullname"));
            students.add(foundstudent);
            
        }
         
        this.getPs().close();
        this.getRs().close();
  
        return students;
        
    }

   
    
}
