package com.example.kasir;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PenjualanActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    int stokLama, harganya, total, idnya;
    TextView namaBarang1, harga1, jumlahHarga1,suplier;
    EditText jumlahPembelian1;
    Button beli, oke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan);
        dbHelper=new DataHelper(this);
        namaBarang1 = (TextView) findViewById(R.id.txt_namaBarang1);
        harga1 = (TextView) findViewById(R.id.txt_harga1);
        jumlahHarga1 = (TextView) findViewById(R.id.txt_jumlahHarga1);
        jumlahPembelian1 = (EditText) findViewById(R.id.edt_jumlahPembeliaan1);
        suplier=(TextView)findViewById(R.id.txt_suplier);
        beli = (Button) findViewById(R.id.btn_beli);
        oke = (Button) findViewById(R.id.btn_oke1);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM kasir WHERE nama_barang='" +
                getIntent().getStringExtra("Nama") + "'", null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            idnya = cursor.getInt(0);
            stokLama = cursor.getInt(4);
            harganya = cursor.getInt(3);
            suplier.setText(cursor.getString(2));
            harga1.setText(Integer.toString(harganya));
            namaBarang1.setText(cursor.getString(1));
        }
        oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total = harganya * Integer.parseInt(jumlahPembelian1.getText().toString());
                String totalnya = Integer.toString(total);
                jumlahHarga1.setText(totalnya);
            }
        });
        beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                int jmlbeli=Integer.parseInt(jumlahPembelian1.getText().toString());
                int stokbaru=stokLama+jmlbeli;

                db.execSQL("update kasir set stok='"+stokbaru+"' where id='"+idnya+"'");
                finish();
            }
        });
    }
}
