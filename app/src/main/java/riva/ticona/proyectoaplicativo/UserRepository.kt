package riva.ticona.proyectoaplicativo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Patterns

class UserRepository(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertUser(name: String, username: String, email: String, password: String): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_NAME, name)
            put(DatabaseHelper.COLUMN_USERNAME, username)
            put(DatabaseHelper.COLUMN_EMAIL, email)
            put(DatabaseHelper.COLUMN_PASSWORD, password)
        }
        return db.insert(DatabaseHelper.TABLE_NAME, null, values)
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
