package com.hotwaxsystems.productplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText password,username,mobileno;
    Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        password=(EditText)findViewById(R.id.password);
        username=(EditText)findViewById(R.id.username);
        mobileno=(EditText)findViewById(R.id.mobileNo);
        signUpBtn=(Button)findViewById(R.id.signUpBtn);

        signUpBtn.setEnabled(false);


        mobileno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(username.getText().toString().length()>0) {

                    if(password.getText().toString().length()>0) {

                        if(mobileno.getText().toString().length()==10) {
                            signUpBtn.setEnabled(true);
                        }
                        else {
                        }
                    }


                }
            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(username.getText().toString().length()>0) {

                }
                else {
                    signUpBtn.setEnabled(false);
                }

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(password.getText().toString().length()>0) {

                }
                else {
                    signUpBtn.setEnabled(false);
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,NearByPlacesActivity.class));
            }
        });



    }
}
