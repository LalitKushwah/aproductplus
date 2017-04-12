package com.hotwaxsystems.productplus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hotwaxsystems.productplus.network.LoginCalls;
import com.hotwaxsystems.productplus.pojo.utilities.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button login;
    TextView textView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        textView=(TextView)findViewById(R.id.textViewSignup);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void login(View v) {

        final ProgressDialog loginDialog = new ProgressDialog(v.getContext());
        loginDialog.setMessage("Log In...");
        loginDialog.setCanceledOnTouchOutside(false);
        loginDialog.show();


        final String URL = "http://192.168.43.73/FoodLive/";
        SharedPreferences sessionIdStore = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = sessionIdStore.edit();
        editor.putString("posURL",URL);
        editor.commit();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(new MainActivity().getUnsafeOkHttpClient())
                .build();
        LoginCalls services = retrofit.create(LoginCalls.class);
        Call<Login> result = services.authorizeUser(username.getText().toString(),password.getText().toString());
        result.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                String token = response.body().getAuthorizeUserResult().getToken();
                String jSessionId = response.body().getSessionId();
                editor.putString("jsessionid", jSessionId);
                editor.putString("token", token);
                editor.commit();

                startActivity(new Intent(LoginActivity.this, NearByPlacesActivity.class));
                loginDialog.dismiss();
                finish();
            }
            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                loginDialog.dismiss();
                Toast.makeText(getApplicationContext(),t.toString() , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, NearByPlacesActivity.class));
            }
        });
    }


    }

