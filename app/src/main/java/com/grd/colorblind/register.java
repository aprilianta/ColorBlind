package com.grd.colorblind;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Button;
import android.widget.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class register extends Activity {
    private EditText tfusername, tfemail, tfpass,tgl;
    private RadioButton p,w;
    private TextView masuk;
    private Button btndaftar;
    private DBHelper db;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DBHelper(this);

        tfusername = (EditText)findViewById(R.id.nama);
        tgl = (EditText) findViewById(R.id.aturtgl);
        tfemail = (EditText)findViewById(R.id.email);
        tfpass = (EditText)findViewById(R.id.pass);
        p = (RadioButton)findViewById(R.id.pria);
        w =(RadioButton)findViewById(R.id.wanita);
        btndaftar = (Button)findViewById(R.id.daftar);
        masuk = (TextView)findViewById(R.id.masuk);

        tfusername.requestFocus();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar.set(Calendar.YEAR,i);
                myCalendar.set(Calendar.MONTH,i1);
                myCalendar.set(Calendar.DAY_OF_MONTH,i2);
                updateLabel();
            }
        };

        tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(register.this, date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this, login.class));
                finish();
            }
        });
    }


    private void updateLabel() {
        String myFormat = "dd/MM/yyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tgl.setText(sdf.format(myCalendar.getTime()));
    }
    private void register() {
        String username = tfusername.getText().toString();
        String email = tfemail.getText().toString();
        String pass = tfpass.getText().toString();
        String tanggal = tgl.getText().toString();
        String jk="";
        if (p.isChecked()) {
            jk="Pria";
        }
        if (w.isChecked()) {
            jk="Wanita";
        }
        if (username.isEmpty() || email.isEmpty() || pass.isEmpty() || jk.equals("") || tanggal.isEmpty()) {
            displayToast("Terdapat field kosong");
        } else {
            db.addUser(username, email, pass, jk, tanggal);
            displayToast("Pendaftaran akun berhasil\n Silakan login");
            startActivity(new Intent(register.this, login.class));
            finish();
        }
    }
    public void onBackPressed() {
        startActivity(new Intent(register.this, login.class));
        finish();
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
