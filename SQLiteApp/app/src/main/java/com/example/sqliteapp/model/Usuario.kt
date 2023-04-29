package com.example.sqliteapp.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.sqliteapp.db.HelperDB

class Usuario(context: Context) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    init {
        helper = HelperDB(context)
        db = helper!!.getWritableDatabase()
    }
    companion object {
        val TABLE_NAME_USERS = "usuarios"
        val COL_ID = "idusuario"
        val COL_NOMBRE = "nombre"
        val COL_CONTRA = "contra"
        //sentencia SQL para crear la tabla
        val CREATE_TABLE_USERS = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USERS + "("
                        + COL_ID + " integer primary key autoincrement,"
                        + COL_NOMBRE + " varchar(50) NOT NULL,"
                        + COL_CONTRA + " varchar(50) NOT NULL);"
                )
    }
    fun generarContentValues(nombre: String?,
                             contra: String?
    ): ContentValues? {
        val valores = ContentValues()
        valores.put(COL_NOMBRE, nombre)
        valores.put(COL_CONTRA, contra)
        return valores
    }
    //Agregar un nuevo registro
    fun addNewUser(nombre: String?, contra: String?,) {
        db!!.insert(
            Usuario.TABLE_NAME_USERS,
            null,
            generarContentValues(nombre, contra)
        )
    }
    fun searchUser(nombre: String?,contra: String?): Cursor? {
        val columns = arrayOf(
            COL_ID,
            COL_NOMBRE,
            COL_CONTRA,
        )
        return db!!.query(
            Usuario.TABLE_NAME_USERS, columns,
            "${Usuario.COL_NOMBRE}=? AND ${Usuario.COL_CONTRA}=?", arrayOf(nombre,contra), null, null, null
        )
    }
}