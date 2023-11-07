package com.example.nguyentientai_0000;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class SqliteDB_04 extends SQLiteOpenHelper {
    public static final String TableName="Taxi_0000";
    public static final String Ma ="Ma";
    public static final String SoXe="SoXe";
    public static final String QuangDuong ="QuangDuong";
    public static final String DonGia="DonGia";
    public static final String KhuyenMai="KhuyenMai";

    public SqliteDB_04(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SqliteDB_04(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public SqliteDB_04(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // tao cau sql de tao bang TableContact
        String sqlCreate ="Create table if not exists "+ TableName +"("
                +Ma+ " Integer Primary key AUTOINCREMENT, "+
                SoXe + " Text, "
                + QuangDuong +" REAL, "
                + DonGia +" Integer, "
                + KhuyenMai + " Integer)";
        //chay cau tru van SQL de tao bang
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // xoa bang TableContact da co
        db.execSQL("Drop table if exists "+ TableName);
        //tao lai
        onCreate(db);
    }
    public ArrayList<Taxi_0000> getAllHoaDon(){
        ArrayList<Taxi_0000> list = new ArrayList<>();
        String sql = "Select * from "+ TableName;
        //cau truy van
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        // tao ArrayList<Contact> de tra ve
        if(cursor!=null){
            while (cursor.moveToNext()){
                Taxi_0000 hoadon = new Taxi_0000(cursor.getInt(0),cursor.getString(1),
                        cursor.getFloat(2),cursor.getInt(3),cursor.getInt(4));
                list.add(hoadon);
            }
        }
        return list;
    }
    public void addHoaDon(Taxi_0000 hoadon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(SoXe,hoadon.getSoxe());
        value.put(QuangDuong,hoadon.getQuangduong());
        value.put(DonGia,hoadon.getDongia());
        value.put(KhuyenMai, hoadon.getKhuyenmai());
        db.insert(TableName,null,value);
        db.close();
    }
    public void updateHoaDon(int id,Taxi_0000 hoadon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Ma,hoadon.getMa());
        value.put(SoXe,hoadon.getSoxe());
        value.put(QuangDuong,hoadon.getQuangduong());
        value.put(DonGia,hoadon.getDongia());
        value.put(KhuyenMai, hoadon.getKhuyenmai());
        db.update(TableName,value,Ma+ "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteHoaDon(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        db.delete(TableName,Ma+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
}
