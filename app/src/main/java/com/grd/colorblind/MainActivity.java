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
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button btnmulai, btntentang, btnkeluar;
    private DBHelper db;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        btnmulai = (Button)findViewById(R.id.mulai);
        btntentang = (Button)findViewById(R.id.tentang);
        btnkeluar = (Button)findViewById(R.id.keluar);
        session = new Session(this);
        btnmulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,simulasi1.class));
                finish();
            }
        });
        btntentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,about.class));
                finish();
            }
        });
        btnkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Keluar");
                alert.setMessage("Yakin ingin keluar dari akun?");
                alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                });
                alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });
    }
    private void logout() {
        session.setLoggedin(false);
        finish();
        Toast.makeText(getApplicationContext(), "Anda telah berhasil keluar dari akun",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,login.class));
        finish();
    }
}
