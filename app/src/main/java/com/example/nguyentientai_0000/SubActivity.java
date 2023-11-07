package com.example.nguyentientai_0000;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {
    private EditText etSoXe,etDonGia,etQuangDuong,etKhuyenMai;
    private Button btnSua,btnQuayVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        etSoXe = findViewById(R.id.etSoXe);
        etDonGia = findViewById(R.id.etDonGia);
        etQuangDuong = findViewById(R.id.etQuangDuong);
        etKhuyenMai = findViewById(R.id.etKhuyenMai);
        btnSua = findViewById(R.id.btnSua);
        btnQuayVe = findViewById(R.id.btnQuayVe);
        //lấy intent từ MainActivity chuyển sang
        Intent intent = getIntent();
        //Lấy bundle
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            int ma = bundle.getInt(SqliteDB_04.Ma);
            String soxe = bundle.getString(SqliteDB_04.SoXe);
            float quangduong = bundle.getFloat(SqliteDB_04.QuangDuong);
            int dongia = bundle.getInt(SqliteDB_04.DonGia);
            int khuyenmai=bundle.getInt(SqliteDB_04.KhuyenMai);
            etSoXe.setText(soxe);
            etKhuyenMai.setText(String.valueOf(khuyenmai));
            etDonGia.setText(String.valueOf(dongia));
            etQuangDuong.setText(String.valueOf(quangduong));
        }

        btnQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lấy dữ liệu và gửi về cho MainActivity
                String soxe = etSoXe.getText().toString();
                float quangduong = Float.valueOf(etQuangDuong.getText().toString());
                int dongia = Integer.valueOf(etDonGia.getText().toString());
                int khuyenmai = Integer.valueOf(etKhuyenMai.getText().toString());
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putString(SqliteDB_04.SoXe,soxe);
                b.putFloat(SqliteDB_04.QuangDuong,quangduong);
                b.putInt(SqliteDB_04.KhuyenMai,dongia);
                b.putInt(SqliteDB_04.DonGia,khuyenmai);
                intent.putExtras(b);
                setResult(150,intent);
                finish();
            }
        });
    }
}