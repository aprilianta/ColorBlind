package com.grd.colorblind;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class simulasi6 extends Activity {
    private EditText jwb;
    private Button btnkirim;
    private RadioButton tdk;
    String jawaban="",ttl="",tidak="";
    String cekjawaban="73";
    int benar=0, salah=0, total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulasi6);
        jwb = (EditText)findViewById(R.id.jawab);
        tdk = (RadioButton)findViewById(R.id.tidak);
        if (tdk.isChecked()) {
            tidak="Tidak ada angka";
        } else {
            tidak="";
        }
        jwb.requestFocus();
        btnkirim = (Button)findViewById(R.id.kirim);
        btnkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cek();
                Intent intent = new Intent(simulasi6.this, simulasi7.class);
                intent.putExtra("jumlah",ttl);
                startActivity(intent);
                finish();
            }
        });
    }
    public void cek() {
        jawaban = jwb.getText().toString();
        Intent intent = getIntent();
        if (intent.getStringExtra("jumlah").equals("5")) {
            benar=5; salah=0;
            if (jawaban.equals(cekjawaban)) {
                benar++;
                salah=salah+0;
            } else if (tdk.isChecked()){
                benar=benar+0;
                salah++;
            } else {
                benar=benar+0;
                salah++;
            }
            total=benar;
            ttl = String.valueOf(total);
        }
        else if (intent.getStringExtra("jumlah").equals("4")) {
            benar=4; salah=1;
            if (jawaban.equals(cekjawaban)) {
                benar++;
                salah=salah+0;
            } else if (tdk.isChecked()){
                benar=benar+0;
                salah++;
            } else {
                benar=benar+0;
                salah++;
            }
            total=benar;
            ttl = String.valueOf(total);
        }
        else if (intent.getStringExtra("jumlah").equals("3")) {
            benar=3; salah=2;
            if (jawaban.equals(cekjawaban)) {
                benar++;
                salah=salah+0;
            } else if (tdk.isChecked()){
                benar=benar+0;
                salah++;
            } else {
                benar=benar+0;
                salah++;
            }
            total=benar;
            ttl = String.valueOf(total);
        }
        else if (intent.getStringExtra("jumlah").equals("2")) {
            benar=2; salah=3;
            if (jawaban.equals(cekjawaban)) {
                benar++;
                salah=salah+0;
            } else if (tdk.isChecked()){
                benar=benar+0;
                salah++;
            } else {
                benar=benar+0;
                salah++;
            }
            total=benar;
            ttl = String.valueOf(total);
        }
        else if (intent.getStringExtra("jumlah").equals("1")) {
            benar=1; salah=4;
            if (jawaban.equals(cekjawaban)) {
                benar++;
                salah=salah+0;
            } else if (tdk.isChecked()){
                benar=benar+0;
                salah++;
            } else {
                benar=benar+0;
                salah++;
            }
            total=benar;
            ttl = String.valueOf(total);
        }
        else if (jawaban.equals("")) {
            Toast.makeText(getApplicationContext(), "Masukkan angka",Toast.LENGTH_SHORT).show();
        }
        else {
            salah=5;
            if (jawaban.equals(cekjawaban)) {
                benar++;
                salah=salah+0;
            } else if (tdk.isChecked()){
                benar=0;
                salah++;
            } else {
                benar=0;
                salah++;
            }
            total=benar;
            ttl = String.valueOf(total);
        }
    }
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(simulasi6.this);
        alert.setTitle("Konfirmasi");
        alert.setMessage("Yakin ingin mengakhiri simulasi?");
        alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                total=0;
                startActivity(new Intent(simulasi6.this, MainActivity.class));
                finish();
            }
        });
        alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
