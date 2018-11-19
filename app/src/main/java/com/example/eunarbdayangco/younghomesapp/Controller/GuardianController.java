package com.example.eunarbdayangco.younghomesapp.Controller;



import com.example.eunarbdayangco.younghomesapp.Databases.Database;
import com.example.eunarbdayangco.younghomesapp.Databases.GuardianDB;
import com.example.eunarbdayangco.younghomesapp.Model.Guardian;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuardianController {

    private Database<Guardian> database;

    public GuardianController() {

        this.database = new GuardianDB();
    }



    public ArrayList<Guardian> getGuardians(){
        try {
            return this.database.getAllDatas("");
        } catch (Exception ex){
            return new ArrayList<>();
        }

    }

    public ArrayList<Guardian> getGuardians(int studentID){

        ArrayList<Guardian> guardians = new ArrayList<>();

        try {
            guardians =  this.database.getAllDatas("WHERE studentID=?",studentID);
        } catch (Exception ex) {
            Logger.getLogger(GuardianController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return guardians;
    }








}
