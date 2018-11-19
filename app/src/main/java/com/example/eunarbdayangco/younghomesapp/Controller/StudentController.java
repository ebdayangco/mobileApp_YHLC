/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eunarbdayangco.younghomesapp.Controller;


import com.example.eunarbdayangco.younghomesapp.Databases.Database;
import com.example.eunarbdayangco.younghomesapp.Databases.StudentDB;
import com.example.eunarbdayangco.younghomesapp.Model.Student;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eunar B. Dayangco
 */
public class StudentController {
    
    private Database<Student> database;

    
    public StudentController() {

      
    }
    

    public Student getStudent(int studentID){
        
        Student found = new Student();
        this.database = new StudentDB();

        try {
            ArrayList<Student> students = this.database.getAllDatas(" WHERE studentID =?",studentID);
            
            if(!students.isEmpty()){
                found = this.database.getAllDatas(" WHERE studentID =?",studentID).get(0);
            }
            
           
        } catch (Exception ex) {

            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                this.database.getConn().close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return found;
    }
    


    
    
    
}
