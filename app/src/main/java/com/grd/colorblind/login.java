package com.grd.colorblind;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.*;

public class login extends Activity {
    private EditText etUsername, etPass;
    private Button btnlogin;
    private TextView tvforget, tvcreate;
    private DBHelper db;
    private Session session;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);
        session = new Session(this);
        btnlogin = (Button) findViewById(R.id.login);
        tvcreate = (TextView) findViewById(R.id.create);
        etUsername = (EditText) findViewById(R.id.username);
        etPass = (EditText) findViewById(R.id.password);
        tvforget = (TextView) findViewById(R.id.forget);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        if(session.loggedin()){
            startActivity(new Intent(login.this,MainActivity.class));
            finish();
        }
        tvcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,register.class));
                finish();
            }
        });

        tvforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,lupa.class));
                finish();
            }
        });
    }

    private void login(){
        String username = etUsername.getText().toString();
        String pass = etPass.getText().toString();

        if(db.getUser(username,pass)){
            session.setLoggedin(true);
            Toast.makeText(getApplicationContext(), "Login berhasil",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(login.this, MainActivity.class));
            finish();
        }
        else if (username.equals("") || pass.equals("")) {
            Toast.makeText(getApplicationContext(), "Terdapat field kosong",Toast.LENGTH_SHORT).show();
        }
        else if (db.getUser(username,pass) == false) {
            Toast.makeText(getApplicationContext(), "Username/password tidak terdaftar atau salah",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
