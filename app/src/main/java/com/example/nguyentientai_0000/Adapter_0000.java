package com.example.nguyentientai_0000;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_0000 extends BaseAdapter implements Filterable {
    //nguồn dữ liệu cho adapter
    private ArrayList<Taxi_0000> data;
    private ArrayList<Taxi_0000> databackup;
    //ngữ cảnh của ứng dụng
    private Activity context;
    // đối tượng để phân tích layout
    private LayoutInflater inflater;

    public Adapter_0000(ArrayList<Taxi_0000> data, Activity context) {
        this.data = data;
        this.context = context;
        this.inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Adapter_0000() {
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Taxi_0000 getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getMa();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= convertView;
        if(v==null){
            v=inflater.inflate(R.layout.list_item,null);
        }
        Taxi_0000 item = data.get(position);
        TextView tvSoxe = v.findViewById(R.id.tvSoXe);
        TextView tvQuangDuong = v.findViewById(R.id.tvQuangDuong);
        TextView tvTongTien = v.findViewById(R.id.tvTongTien);
        tvSoxe.setText(item.getSoxe());
        tvQuangDuong.setText("Quãng Đường: "+String.valueOf(item.getQuangduong()) +" km");
        int tongtien = (int) (item.getDongia()*item.getQuangduong()*(100-item.getKhuyenmai())/100);
        tvTongTien.setText(String.format("%,3d",tongtien));
        context.registerForContextMenu(v);
        return v;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
