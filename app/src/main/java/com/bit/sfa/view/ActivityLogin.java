package com.bit.sfa.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bit.sfa.DataControl.SalRepDS;
import com.bit.sfa.Models.FmSalRep;
import com.bit.sfa.R;
import com.bit.sfa.Settings.SharedPreferencesClass;
import com.bit.sfa.Settings.UserSessionManager;

import java.util.ArrayList;

public class ActivityLogin extends AppCompatActivity {

    private LinearLayout footer;
    private EditText userName, password;
    private String repCode;
    private Context context = this;
    UserSessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //view initializations
        footer = (LinearLayout) findViewById(R.id.footer);
        userName = (EditText) findViewById(R.id.editusername);
        password = (EditText) findViewById(R.id.editpassword);
        //footer xml animation

        sessionManager = new UserSessionManager(context);
        if(sessionManager.isLogged()){

            Intent mainActivity = new Intent(this, ActivityHome.class);
            startActivity(mainActivity);
            finish();

        }else{

            //do nothing now
        }

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_up);
        footer.startAnimation(animation1);

        SalRepDS ds = new SalRepDS(getApplicationContext());
        ArrayList<FmSalRep> list = ds.getSaleRepDetails();
        FmSalRep salRep = list.get(0);
        userName.setText(salRep.getRepNamem());
        repCode = salRep.getRepCodem();

        //login button
        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName.getText().toString().length() == 0) {
                    userName.setError("Username empty not allowed");
                } else if (password.getText().toString().length() == 0) {
                    password.setError("Password empty not allowed");
                } else {
                    LoginValidation();
                }

            }
        });


    }

    private void LoginValidation() {
        // TODO Auto-generated method stub
        SalRepDS ds = new SalRepDS(getApplicationContext());
        ArrayList<FmSalRep> list = ds.getSaleRepDetails();
        FmSalRep salRep = list.get(0);
        System.out.println(">>>" + salRep.getRepNamem());

        if (repCode.equalsIgnoreCase(salRep.getRepCodem().trim()) && password.getText().toString().equalsIgnoreCase(salRep.getPassword().trim())) {


            sessionManager.createLogin(repCode, password.getText().toString());

            SharedPreferencesClass.setLocalSharedPreference(getApplicationContext(), "first_login", "Success");

            Intent mainActivity = new Intent(this, ActivityHome.class);
            startActivity(mainActivity);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Invalid username or password.", Toast.LENGTH_LONG).show();

        }

    }
}
