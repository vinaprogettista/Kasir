package com.example.kasir;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="kasir.db";
    private static final int DATABASE_VERSION=1;
    public DataHelper(Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="Create Table kasir(id integer primary key,nama_barang text null,suplier text null,harga integer, stok integer);";
        Log.d("Data", "onCreate"+sql);
        db.execSQL(sql);
        sql="INSERT INTO kasir(id, nama_barang, suplier, harga, stok) VALUES(111,'Chocolatos','PT. Jaya Abadi',5000,250);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }
}
