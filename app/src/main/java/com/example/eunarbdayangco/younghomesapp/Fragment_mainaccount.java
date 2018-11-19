package com.example.eunarbdayangco.younghomesapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eunarbdayangco.younghomesapp.Controller.AccountController;
import com.example.eunarbdayangco.younghomesapp.Model.Guardian;
import com.example.eunarbdayangco.younghomesapp.Section.StationSection;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_mainaccount.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_mainaccount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_mainaccount extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button changePasswordbtn,savebtn;
    private EditText fullname,contactNum,username;
    private AccountController controller;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_mainaccount() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_mainaccount.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_mainaccount newInstance(String param1, String param2) {
        Fragment_mainaccount fragment = new Fragment_mainaccount();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_mainaccount, container, false);
        changePasswordbtn = (Button) view.findViewById(R.id.btnChangepassword);
        changePasswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePasswordDialog changePasswordDialog = new ChangePasswordDialog(getContext());
                changePasswordDialog.onShow();
            }
        });

        savebtn = (Button) view.findViewById(R.id.btnSave);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller = new AccountController();
                controller.updateProcess(Fragment_mainaccount.this);
            }
        });

        fullname = (EditText) view.findViewById(R.id.txtFullname);
        contactNum = (EditText) view.findViewById(R.id.txtContact);
        username =  (EditText) view.findViewById(R.id.txtUsername);
        attachData();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void attachData(){

        Guardian guardian = StationSection.getUserGuardian();
        fullname.setText(guardian.getFullname());
        contactNum.setText(guardian.getContactNumber());
        username.setText(guardian.getUsername());
    }

    public Guardian getUpdatedGuardian(){

        Guardian updatedGuardian = StationSection.getUserGuardian();

        updatedGuardian.setFullname(fullname.getText().toString());
        updatedGuardian.setContactNumber(contactNum.getText().toString());
        updatedGuardian.setUsername(username.getText().toString());

        return updatedGuardian;


    }

    public void displayMessage(String message){

        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }




}
