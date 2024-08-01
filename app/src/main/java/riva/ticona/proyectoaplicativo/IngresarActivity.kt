package riva.ticona.proyectoaplicativo

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class IngresarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnIngreso = findViewById<Button>(R.id.btnIngreso)

        btnIngreso.setOnClickListener {
            val usernameOrEmail = etUsuario.text.toString()
            val password = etPassword.text.toString()

            if (validateCredentials(usernameOrEmail, password)) {
                // Credenciales válidas
                Toast.makeText(this, "Acceso concedido", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PrincipalActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Credenciales no válidas
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateCredentials(usernameOrEmail: String, password: String): Boolean {
        val dbHelper = DatabaseHelper(this)
        val db: SQLiteDatabase = dbHelper.readableDatabase

        val selection = "${DatabaseHelper.COLUMN_USERNAME} = ? OR ${DatabaseHelper.COLUMN_EMAIL} = ?"
        val selectionArgs = arrayOf(usernameOrEmail, usernameOrEmail)

        val cursor = db.query(
            DatabaseHelper.TABLE_NAME,
            arrayOf(DatabaseHelper.COLUMN_PASSWORD),
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var valid = false
        with(cursor) {
            if (moveToFirst()) {
                val storedPassword = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD))
                if (storedPassword == password) {
                    valid = true
                }
            }
            close()
        }

        return valid
    }
}
