package com.grd.colorblind;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class lupa extends Activity {
    private EditText etUsername, etPass;
    private Button btnreset;
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa);
        db = new DBHelper(this);
        etUsername = (EditText)findViewById(R.id.username);
        etPass = (EditText)findViewById(R.id.password);
        btnreset = (Button)findViewById(R.id.reset);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }
    public void update() {
        String namabaru = etUsername.getText().toString();
        String passbaru = etPass.getText().toString();
        if (namabaru.equals("") || passbaru.equals("")) {
            Toast.makeText(lupa.this,"Terdapat field kosong",Toast.LENGTH_SHORT).show();
        } else if (db.cocok(namabaru)){
            db.updateUser(namabaru,passbaru);
            Toast.makeText(lupa.this,"Username/password telah diubah\nSilakan login",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(lupa.this,login.class));
            finish();
        } else {
            Toast.makeText(lupa.this,"Username tidak terdaftar",Toast.LENGTH_SHORT).show();
        }
    }
    public void onBackPressed() {
        startActivity(new Intent(lupa.this, login.class));
        finish();
    }
}
