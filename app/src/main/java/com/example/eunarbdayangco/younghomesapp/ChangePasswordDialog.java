package com.example.eunarbdayangco.younghomesapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eunarbdayangco.younghomesapp.Controller.AccountController;
import com.example.eunarbdayangco.younghomesapp.Model.Guardian;
import com.example.eunarbdayangco.younghomesapp.R;
import com.example.eunarbdayangco.younghomesapp.Section.StationSection;

public class ChangePasswordDialog extends AlertDialog.Builder implements DialogInterface.OnShowListener {

    private EditText currentpassword,newpassword,confirmpassword;
    private AlertDialog alertDialog;
    private AccountController controller;

    public ChangePasswordDialog(Context context) {
        super(context);
        setTitle("Change Password");
        View mainView = LayoutInflater.from(getContext())
                .inflate(R.layout.layout_changepassword, null);

        currentpassword = (EditText)mainView.findViewById(R.id.changepassword_txtcurrentpassword);
        newpassword = (EditText)mainView.findViewById(R.id.changepassword_txtnewpassword);
        confirmpassword = (EditText)mainView.findViewById(R.id.changepassword_txtconfirmpassword);
        setView(mainView);

        setPositiveButton("OK", null);
        setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog = create();
        alertDialog.setOnShowListener(this);

    }


    public void onShow(){
        alertDialog.show();
    }

    public void setMessageDisplay(String message){

        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onShow(DialogInterface dialog) {
        Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(validateValues()){


                    controller = new AccountController();
                    controller.updateProcess(ChangePasswordDialog.this);

                }



            }
        });

    }

    public Guardian getUpdatedGuardian(){

        Guardian guardian = StationSection.getUserGuardian();
        guardian.setPassword(newpassword.getText().toString());
        return guardian;
    }

    public boolean validateValues(){

        boolean stat = currentpassword.getText().toString().isEmpty() ||
                newpassword.getText().toString().isEmpty() ||
                confirmpassword.getText().toString().isEmpty();

        if(stat){

            Toast.makeText(getContext(),"Field/s is empty",Toast.LENGTH_SHORT).show();
            return false;
        }else{

            String onPassword = StationSection.getUserGuardian().getPassword();

            if (!currentpassword.getText().toString().equals(onPassword)) {
                Toast.makeText(getContext(),"Invalid current password",Toast.LENGTH_SHORT).show();
                return false;
            } else if(!newpassword.getText().toString().equals(confirmpassword.getText().toString())){
                Toast.makeText(getContext(),"New and Confirm password not match!",Toast.LENGTH_SHORT).show();
                return false;

            }else{

                return  true;
            }
        }


    }

    public void displayMessage(String message){

        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
}
