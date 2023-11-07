package com.example.nguyentientai_0000;

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
import java.util.Comparator;

public class Taxi_0000 implements Comparable<Taxi_0000>{
    int ma;
    String soxe;
    float quangduong;
    int dongia;
    int khuyenmai;

    public Taxi_0000() {
    }

    public Taxi_0000(int ma, String soxe, float quangduong, int dongia, int khuyenmai) {
        this.ma = ma;
        this.soxe = soxe;
        this.quangduong = quangduong;
        this.dongia = dongia;
        this.khuyenmai = khuyenmai;
    }

    public Taxi_0000(String soxe, float quangduong, int dongia, int khuyenmai) {
        this.soxe = soxe;
        this.quangduong = quangduong;
        this.dongia = dongia;
        this.khuyenmai = khuyenmai;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getSoxe() {
        return soxe;
    }

    public void setSoxe(String soxe) {
        this.soxe = soxe;
    }

    public float getQuangduong() {
        return quangduong;
    }

    public void setQuangduong(float quangduong) {
        this.quangduong = quangduong;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(int khuyenmai) {
        this.khuyenmai = khuyenmai;
    }

    @Override
    public int compareTo(Taxi_0000 o) {
        return 0;
    }
    public static Comparator<Taxi_0000> CompareSoxe =new Comparator<Taxi_0000>(){
        public int compare(Taxi_0000 hoadon1,Taxi_0000 hoadon2){

            return hoadon1.getSoxe().compareTo(hoadon2.getSoxe());
        }
    };
}
