package com.example.kasir;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahBarangActivity extends AppCompatActivity {
    EditText edtid, edtNamaBarang,edtSuplier,edtHarga,edtStok;
    Button btnSave,btnCancel;
    protected Cursor cursor;
    DataHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);

        dbHelper=new DataHelper(this);
        edtid=(EditText)findViewById(R.id.edt_id);
        edtNamaBarang=(EditText)findViewById(R.id.edt_namabarang);
        edtSuplier=(EditText)findViewById(R.id.edt_suplier);
        edtHarga=(EditText)findViewById(R.id.edt_harga);
        edtStok=(EditText)findViewById(R.id.edt_stok);
        btnSave=(Button)findViewById(R.id.btn_save);
        btnCancel=(Button)findViewById(R.id.btn_cancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db=dbHelper.getReadableDatabase();
                db.execSQL("insert into kasir(id,nama_barang, suplier, harga, stok) values('"+
                        edtid.getText().toString()+"','"+
                        edtNamaBarang.getText().toString()+"','"+
                        edtSuplier.getText().toString()+"','"+
                        edtHarga.getText().toString()+"','"+
                        edtStok.getText().toString()+"')");

                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_LONG).show();
                MainActivity.utama.RefreshList();
                finish();
            }
        });

    }
}
