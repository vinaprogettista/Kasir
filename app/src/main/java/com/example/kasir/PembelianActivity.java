package com.example.kasir;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PembelianActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    TextView txtNamaBarang, txtHarga, txtStok,txtJumlahHarga;
    EditText edtPembelian;
    Button btnBeli, btnOke;
    int  stokLama,harganya,total;
    int idnya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembelian);

        dbHelper=new DataHelper(this);
        txtNamaBarang=(TextView)findViewById(R.id.txt_namaBarang);
        txtHarga=(TextView)findViewById(R.id.txt_harga);
        txtStok=(TextView)findViewById(R.id.txt_stok);
        txtJumlahHarga=(TextView)findViewById(R.id.txt_jumlahHarga1);
        edtPembelian=(EditText)findViewById(R.id.edt_jumlahPembeliaan);
        btnBeli=(Button)findViewById(R.id.btn_beli);
        btnOke=(Button)findViewById(R.id.btn_oke);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT* FROM kasir WHERE nama_barang='" +
                getIntent().getStringExtra("Nama")+"'", null);

        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            idnya=cursor.getInt(0);
            stokLama=cursor.getInt(4);
            harganya=cursor.getInt(3);
            txtHarga.setText(Integer.toString(harganya));
            txtStok.setText(Integer.toString(stokLama));
            txtNamaBarang.setText(cursor.getString(1));


        }

        btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total=harganya*Integer.parseInt(edtPembelian.getText().toString());
                String totalnya=Integer.toString(total);
                txtJumlahHarga.setText(totalnya);
            }
        });

        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getReadableDatabase();
                int jmlbeli1=Integer.parseInt(edtPembelian.getText().toString());
                int stokBaru1=stokLama-jmlbeli1;

                db.execSQL("update kasir set stok='"+ stokBaru1+"' where id='"+idnya+"'");
                finish();
            }
        });


    }
}
