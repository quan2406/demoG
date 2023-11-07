package com.example.nguyentientai_0000;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private Adapter_0000 listAdapter;
    private ArrayList<Taxi_0000> HoaDonList;
    private EditText etSearch;
    private ListView lstHoaDon;
    private Button btnDelete, btnVideo;
    private int SelectedItemId;
    private FloatingActionButton btnAdd;
    private SqliteDB_04 db;
    private final String DBName = "TaxiDB";
    IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);
        lstHoaDon = findViewById(R.id.lstHoaDon);
        btnAdd = findViewById(R.id.fbtnAdd);

        db = new SqliteDB_04(this,DBName,null,1);

        //them du lieu lan dau vao db
//        db.addHoaDon(new Taxi_0000("29L1-123.45",12.5f,10000,5));
//        db.addHoaDon(new Taxi_0000("29L5-323.44",10.5f,8000,6));
//        db.addHoaDon(new Taxi_0000("29C1-423.22",9.5f,12000,7));
//        db.addHoaDon(new Taxi_0000("29D1-111.11",8.5f,13000,8));
//        db.addHoaDon(new Taxi_0000("29X5-000.00",7.5f,11000,9));
//        db.addHoaDon(new Taxi_0000("29Z1-123.21",6.5f,14000,10));

        HoaDonList = db.getAllHoaDon();
        listAdapter=new Adapter_0000(HoaDonList,this);
        Collections.sort(HoaDonList,Taxi_0000.CompareSoxe);
        lstHoaDon.setAdapter(listAdapter);

        lstHoaDon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SelectedItemId = position;
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater= new MenuInflater(this);
        inflater.inflate(R.menu.contextmenu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Taxi_0000 hd = listAdapter.getItem(SelectedItemId);
        switch(item.getItemId()){
            case R.id.mnuSua:
                //1. Tạo intent để mở subactivity
                Intent intent = new Intent(MainActivity.this,SubActivity.class);
                //2.Truyền dữ liệu sang subactivity bằng bundle nếu cần
                Bundle b = new Bundle();
                b.putInt(SqliteDB_04.Ma,hd.getMa());
                b.putString(SqliteDB_04.SoXe,hd.getSoxe());
                b.putFloat(SqliteDB_04.QuangDuong,hd.getQuangduong());
                b.putInt(SqliteDB_04.KhuyenMai,hd.getKhuyenmai());
                b.putInt(SqliteDB_04.DonGia,hd.getDongia());
                intent.putExtras(b);
                //Mở subactivity bằng cách gọi hàm
                //startactivity hoặc startactivityforresult
                startActivityForResult(intent,200);
                break;
            case R.id.mnuXoa:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Thông báo");
                alertDialogBuilder.setMessage("Bạn có muốn xóa không?");
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Taxi_0000 hoadon = listAdapter.getItem(SelectedItemId);
                        db.deleteHoaDon(hoadon.getMa());
                        HoaDonList =db.getAllHoaDon();
                        listAdapter=new Adapter_0000(HoaDonList,MainActivity.this);
                        lstHoaDon.setAdapter(listAdapter);
                    }
                });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"You clicked over No",Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialogBuilder.show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Taxi_0000 newHD = new Taxi_0000();
        int id=-1;
        if(data!=null) {
            Bundle b = data.getExtras();
            String soxe = b.getString(SqliteDB_04.SoXe);
            float quangduong = b.getFloat(SqliteDB_04.QuangDuong);
            int dongia = b.getInt(SqliteDB_04.DonGia);
            int khuyenmai=b.getInt(SqliteDB_04.KhuyenMai);
            newHD = new Taxi_0000(soxe,quangduong,dongia,khuyenmai);
        }
       if(requestCode==200&&resultCode==150){
            // truong hop sua
            db.updateHoaDon(listAdapter.getItem(SelectedItemId).getMa(),newHD);
            db = new SqliteDB_04(this,DBName,null,1);
            HoaDonList = db.getAllHoaDon();
            listAdapter=new Adapter_0000(HoaDonList,this);
            Collections.sort(HoaDonList,Taxi_0000.CompareSoxe);
            lstHoaDon.setAdapter(listAdapter);

        }
    }
}