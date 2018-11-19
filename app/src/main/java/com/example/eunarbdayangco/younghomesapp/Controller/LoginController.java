package com.example.eunarbdayangco.younghomesapp.Controller;

import android.widget.Toast;

import com.example.eunarbdayangco.younghomesapp.Databases.Database;
import com.example.eunarbdayangco.younghomesapp.Databases.GuardianDB;
import com.example.eunarbdayangco.younghomesapp.Interfaces.ProgressInterface;
import com.example.eunarbdayangco.younghomesapp.LoginActivity;
import com.example.eunarbdayangco.younghomesapp.Model.Guardian;
import com.example.eunarbdayangco.younghomesapp.Section.StationSection;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    private Database<Guardian> database;

    public LoginController() {
    }

    public void loginProcess(LoginActivity loginActivity,ProgressInterface progressInterface){
        this.database = new GuardianDB();
        progressInterface.startProgress();

        try {
            ArrayList<Guardian> guardians =
                    this.database.getAllDatas(" WHERE username=? AND password=?",
                            loginActivity.getLoginUsername() ,loginActivity.getLoginPassword());

            if(guardians.isEmpty()){
                progressInterface.failedResult(null);

            }else{

                StationSection.setUserGuardian(guardians.get(0));

                progressInterface.successResult();
            }

        } catch (Exception e) {
            progressInterface.failedResult(e);
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();

        }

        progressInterface.endProgress();


    }
}
