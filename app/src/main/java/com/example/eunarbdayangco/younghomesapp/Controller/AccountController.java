package com.example.eunarbdayangco.younghomesapp.Controller;

import com.example.eunarbdayangco.younghomesapp.ChangePasswordDialog;
import com.example.eunarbdayangco.younghomesapp.Databases.Database;
import com.example.eunarbdayangco.younghomesapp.Databases.GuardianDB;
import com.example.eunarbdayangco.younghomesapp.Fragment_mainaccount;
import com.example.eunarbdayangco.younghomesapp.Model.Guardian;
import com.example.eunarbdayangco.younghomesapp.Section.StationSection;

import java.sql.SQLException;

public class AccountController {

    private Database<Guardian> database;

    public AccountController() {
    }

    public void updateProcess(Fragment_mainaccount account){

        this.database = new GuardianDB(account.getUpdatedGuardian());
        try {

            this.database.updateData();

            if(this.database.isSuccess()){
                StationSection.setUserGuardian(account.getUpdatedGuardian());
                account.displayMessage("Successfully Updated!");
            }else{

                account.displayMessage("Failed to Update!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{

            try {
                this.database.getConn().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void updateProcess(ChangePasswordDialog dialog){

        this.database = new GuardianDB(dialog.getUpdatedGuardian());
        try {

            this.database.updateData();

            if(this.database.isSuccess()){
                StationSection.setUserGuardian(dialog.getUpdatedGuardian());
                dialog.displayMessage("Successfully Updated!");
            }else{

                dialog.displayMessage("Failed to Update!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{

            try {
                this.database.getConn().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
