package com.example.sqliteapp

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sqliteapp.db.HelperDB
import com.example.sqliteapp.model.Categoria
import com.example.sqliteapp.model.Usuario

class MainActivity2 : AppCompatActivity() ,View.OnClickListener{
    private var managerUsuario: Usuario? = null
    private var dbHelper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    private var cursor: Cursor? = null
    private var txtNombre: EditText? = null
    private var txtContra: EditText? = null
    private var btnRegistro: Button? = null
    private var btnLogin: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        dbHelper = HelperDB(this)
        db = dbHelper!!.writableDatabase
        txtNombre = findViewById(R.id.txtUsuario)
        txtContra = findViewById(R.id.txtPass)
        btnRegistro = findViewById(R.id.btnRegister)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegistro!!.setOnClickListener(this)
        btnLogin!!.setOnClickListener(this)
    }
    override fun onClick(view:View){
        managerUsuario= Usuario(this)
        val nombre: String = txtNombre!!.text.toString().trim()
        val contra: String = txtContra!!.text.toString().trim()
        if (db != null) {
            if (view==btnRegistro){
                managerUsuario!!.addNewUser(
                    nombre,
                    contra,
                )
                val i = Intent(this@MainActivity2,MainActivity::class.java)
                startActivity(i)
            }
            if (view==btnLogin){
                cursor = managerUsuario!!.searchUser(nombre,contra)
                if (cursor != null && cursor!!.count>0) {
                    Toast.makeText(this, "Bienvenido",
                        Toast.LENGTH_LONG)
                        .show()
                    val i = Intent(this@MainActivity2,MainActivity::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "No se encontro al usuario",
                        Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}