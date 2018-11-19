package com.example.eunarbdayangco.younghomesapp.Databases;


import com.example.eunarbdayangco.younghomesapp.Controller.StudentController;
import com.example.eunarbdayangco.younghomesapp.Controller.UserController;
import com.example.eunarbdayangco.younghomesapp.Model.Guardian;


import java.util.ArrayList;

public class GuardianDB extends Database<Guardian> {

    private final int FROMID = 2000;
    private final int TOID = Integer.MAX_VALUE;
    private final String TABLEFIELDS = "guardianID,userID,studentID,fullname,relationship,"
            + "contactNumber,address,active,username,password";

    public GuardianDB() {
        super();
    }

    public GuardianDB(Guardian guardian) {

        super();
        this.setObject(guardian);
    }


    public void setGuardianID()throws Exception{

        int genID = 0;

        while(true){

            genID =  (int) (Math.random() * (this.TOID - this.FROMID)) + this.FROMID;


            if(!isIDexist(genID)){
                break;
            }
        }

        getObject().setGuardianID(genID);

    }

    public boolean isIDexist(int id) throws Exception{

        String statement = "SELECT * FROM guardian WHERE guardianID=?";

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

        String statement = "INSERT INTO guardian("+TABLEFIELDS+") VALUES(?,?,?,?,?,?,?,?,?,?)";
        setGuardianID();
        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setInt(1, this.getObject().getGuardianID());
        this.getPs().setInt(2, this.getObject().getUser().getUserID());
        this.getPs().setInt(3, this.getObject().getStudent().getStudentID());
        this.getPs().setString(4, this.getObject().getFullname());
        this.getPs().setString(5, this.getObject().getRelationship());
        this.getPs().setString(6, this.getObject().getContactNumber());
        this.getPs().setString(7, this.getObject().getAddress());
        this.getPs().setBoolean(8, this.getObject().isActive());
        this.getPs().setString(9, this.getObject().getGuardianID()+"");
        this.getPs().setString(10, this.getObject().getGuardianID()+"");
        this.setSuccess(this.getPs().executeUpdate() == 1);

        this.getPs().close();
        this.getConn().close();

        return this;
    }

    @Override
    public Database<Guardian> updateData() throws Exception {

        String cond = "fullname=?,relationship=?,contactNumber=?,address=?,active=?,username=?,password=?";
        String statement = "UPDATE guardian SET "+cond+" WHERE guardianID=?";
        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setString(1, this.getObject().getFullname());
        this.getPs().setString(2, this.getObject().getRelationship());
        this.getPs().setString(3, this.getObject().getContactNumber());
        this.getPs().setString(4, this.getObject().getAddress());
        this.getPs().setBoolean(5, this.getObject().isActive());
        this.getPs().setString(6,this.getObject().getUsername());
        this.getPs().setString(7,this.getObject().getPassword());
        this.getPs().setInt(8, this.getObject().getGuardianID());
        this.setSuccess(this.getPs().executeUpdate() == 1);


        this.getPs().close();
        this.getConn().close();
        return this;
    }

    @Override
    public Database<Guardian> deleteData() throws Exception {

        String statement = "DELETE FROM guardian WHERE guardianID=?";
        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setInt(1,  this.getObject().getGuardianID());
        this.setSuccess( this.getPs().executeUpdate() == 1);
        return this;
    }


    @Override
    public ArrayList<Guardian> getAllDatas(String condition, Object... parameters) throws Exception {
        ArrayList<Guardian> guardians = new ArrayList<>();

        String statement = "SELECT * FROM guardian "+condition;
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

            Guardian guardian = new Guardian();
            guardian.setGuardianID(this.getRs().getInt("guardianID"));
            guardian.setAddedDate(this.getRs().getTimestamp("addedDate"));
            guardian.setUser(new UserController().getUser(this.getRs().getInt("userID")));
            guardian.setStudent(new StudentController().getStudent(this.getRs().getInt("studentID")));
            guardian.setFullname(this.getRs().getString("fullname"));
            guardian.setRelationship(this.getRs().getString("relationship"));
            guardian.setContactNumber(this.getRs().getString("contactNumber"));
            guardian.setAddress(this.getRs().getString("address"));
            guardian.setActive(this.getRs().getBoolean("active"));
            guardian.setUsername(this.getRs().getString("username"));
            guardian.setPassword(this.getRs().getString("password"));
            guardians.add(guardian);

        }

        this.getPs().close();
        this.getRs().close();


        return guardians;
    }

    public void multipleInsertData(ArrayList<Guardian> guardians)throws Exception{

        String statement = "INSERT INTO guardian("+TABLEFIELDS+") VALUES(?,?,?,?,?,?,?,?,?,?)";

        for(int y = 0; y<guardians.size(); y++){

            setObject(guardians.get(y));
            setGuardianID();
            this.setPs(this.getConn().prepareStatement(statement));
            this.getPs().setInt(1, this.getObject().getGuardianID());
            this.getPs().setInt(2, this.getObject().getUser().getUserID());
            this.getPs().setInt(3, this.getObject().getStudent().getStudentID());
            this.getPs().setString(4, this.getObject().getFullname());
            this.getPs().setString(5, this.getObject().getRelationship());
            this.getPs().setString(6, this.getObject().getContactNumber());
            this.getPs().setString(7, this.getObject().getAddress());
            this.getPs().setBoolean(8, this.getObject().isActive());
            this.getPs().setString(9, this.getObject().getGuardianID()+"");
            this.getPs().setString(10, this.getObject().getGuardianID()+"");
            this.setSuccess(this.getPs().executeUpdate()>0);
        }
    }
}
