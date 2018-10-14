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
import android.widget.EditText;
import android.widget.*;

public class simulasi1 extends Activity {
    private EditText jwb;
    private Button btnkirim;
    private RadioButton tdk;
    String jawaban="",ttl="",tidak="";
    String cekjawaban="12";
    int benar=0, salah=0, total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulasi1);
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
                Intent intent = new Intent(simulasi1.this, simulasi2.class);
                intent.putExtra("jumlah",ttl);
                startActivity(intent);
                finish();
            }
        });
    }
    public void cek() {
        jawaban = jwb.getText().toString();
        if (jawaban.equals(cekjawaban)) {
            benar++;
            salah=salah+0;
        } else if (tdk.isChecked()){
            benar=0;
            salah++;
        }
        else if (jawaban.equals("")) {
            Toast.makeText(getApplicationContext(), "Masukkan angka",Toast.LENGTH_SHORT).show();
        }
        else {
            benar=0;
            salah++;
        }
        total=benar-salah;
        ttl = String.valueOf(total);
    }
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(simulasi1.this);
        alert.setTitle("Konfirmasi");
        alert.setMessage("Yakin ingin mengakhiri simulasi?");
        alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                total=0;
                startActivity(new Intent(simulasi1.this, MainActivity.class));
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
