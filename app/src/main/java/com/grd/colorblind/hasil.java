package com.grd.colorblind;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class hasil extends Activity {
    private TextView tvhasil;
    private Button btnkembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        tvhasil = (TextView)findViewById(R.id.hsl);
        btnkembali = (Button)findViewById(R.id.kembali);
        Intent intent = getIntent();
        float jml=Float.valueOf(intent.getStringExtra("jumlah"));
        tvhasil.setText("Total benar : "+intent.getStringExtra("jumlah")+" dari 10 pertanyaan\n" +
                "Persentase keberhasilan : "+(jml/10)*100+"%\n"+
        "Terima kasih telah melakukan simulasi!");
        btnkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(hasil.this, MainActivity.class));
                finish();
            }
        });
    }
}
