package com.example.eunarbdayangco.younghomesapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eunarbdayangco.younghomesapp.Controller.LoginController;
import com.example.eunarbdayangco.younghomesapp.Databases.Database;
import com.example.eunarbdayangco.younghomesapp.Databases.GuardianDB;
import com.example.eunarbdayangco.younghomesapp.Interfaces.ProgressInterface;
import com.example.eunarbdayangco.younghomesapp.Model.Guardian;
import com.example.eunarbdayangco.younghomesapp.Section.StationSection;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginActivity extends Activity implements View.OnClickListener {


    private Button btnLogin;
    private EditText txtUsername,txtPassword;
    private ProgressDialog progressDialog;
    private LoginController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.layout_login);
        initComponents();
        super.onCreate(savedInstanceState);
    }

    public void initComponents(){

        btnLogin = (Button)findViewById(R.id.buttonLogin);
        txtUsername = (EditText)findViewById(R.id.txtUsername);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        btnLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        this.controller = new LoginController();

        this.controller.loginProcess(this, new ProgressInterface() {
            @Override
            public void startProgress() {
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setTitle("Login User");
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }

            @Override
            public void endProgress() {

                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }

            @Override
            public void successResult() {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.animator.fromup, R.animator.todown);
            }

            @Override
            public void failedResult(Exception ex) {
                if(ex !=null){

                    Toast.makeText(LoginActivity.this,"Got Error",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this,"Invalid Account",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public String getLoginUsername(){

        return txtUsername.getText().toString();
    }

    public String getLoginPassword(){

        return txtPassword.getText().toString();
    }



}
