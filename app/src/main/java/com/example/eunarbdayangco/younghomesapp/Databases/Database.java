package com.example.eunarbdayangco.younghomesapp.Databases;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Database<Type> {

    private String dbDriver,dbUrl,dbUsername,dbPassword,dbName,dbHost;
    private Connection conn;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;
    private boolean success;
    private Type object;
    private ArrayList<Type> objects;
    private Context context;

    public Database() {
        init();
        connect();
    }

    public void init(){

        this.dbDriver = "com.mysql.jdbc.Driver";
        this.dbUrl = "jdbc:mysql://192.168.43.107/yhlc_payment_monitoring_system";
        this.dbName = "yhlc_payment_monitoring_system";
        this.dbUsername = "user";
        this.dbPassword = "oSXQC4vekuw7XuyS";
        this.dbHost = "%";
        this.conn = null;
        this.st = null;
        this.rs = null;

    }

    @SuppressLint("NewApi")
    public Connection connect(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.
                Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {


            Class.forName(this.dbDriver);

          this.conn = DriverManager.getConnection( "jdbc:mysql://192.168.43.107/yhlc_payment_monitoring_system",
                    "user","oSXQC4vekuw7XuyS");
            this.st = this.conn.createStatement();



        }catch(Exception ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

        }

        return this.conn;

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Type getObject() {
        return object;
    }

    public void setObject(Type object) {
        this.object = object;
    }

    public ArrayList<Type> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<Type> objects) {
        this.objects = objects;
    }

    public abstract Database insertData()throws Exception;
    public abstract Database updateData()throws Exception;
    public abstract Database deleteData()throws Exception;
    public abstract ArrayList<Type> getAllDatas(String condition,Object...parameters)throws Exception;
}
