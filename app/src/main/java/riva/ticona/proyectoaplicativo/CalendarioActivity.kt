package riva.ticona.proyectoaplicativo

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarioActivity : AppCompatActivity() {

    private lateinit var uidUsuario: TextView
    private lateinit var correoUsuario: TextView
    private lateinit var fechaHoraActual: TextView
    private lateinit var fecha: TextView
    private lateinit var estado: TextView
    private lateinit var titulo: EditText
    private lateinit var descripcion: EditText
    private lateinit var btnCalendario: Button

    private var dia: Int = 0
    private var mes: Int = 0
    private var anio: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = ""
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        inicializarVariables()
        obtenerDatos()
        obtenerFechaHoraActual()

        btnCalendario.setOnClickListener {
            val calendario: Calendar = Calendar.getInstance()

            dia = calendario.get(Calendar.DAY_OF_MONTH)
            mes = calendario.get(Calendar.MONTH)
            anio = calendario.get(Calendar.YEAR)

            val datePickerDialog = DatePickerDialog(this, { _: DatePicker, anioSeleccionado: Int, mesSeleccionado: Int, diaSeleccionado: Int ->
                val diaFormateado = if (diaSeleccionado < 10) "0$diaSeleccionado" else "$diaSeleccionado"
                val mesFormateado = if (mesSeleccionado + 1 < 10) "0${mesSeleccionado + 1}" else "${mesSeleccionado + 1}"

                fecha.text = "$diaFormateado/$mesFormateado/$anioSeleccionado"
            }, anio, mes, dia)

            datePickerDialog.show()
        }
    }

    private fun inicializarVariables() {
        uidUsuario = findViewById(R.id.Uid_Usuario)
        correoUsuario = findViewById(R.id.Correo_usuario)
        fechaHoraActual = findViewById(R.id.Fecha_hora_actual)
        fecha = findViewById(R.id.Fecha)
        estado = findViewById(R.id.Estado)
        titulo = findViewById(R.id.Titulo)
        descripcion = findViewById(R.id.Descripcion)
        btnCalendario = findViewById(R.id.Btn_Calendario) // Asegúrate de que este id coincida con el botón en el XML
    }

    private fun obtenerDatos() {
        val uid = "12345" // Ejemplo de UID del usuario
        val correo = "usuario@example.com" // Ejemplo de correo del usuario

        uidUsuario.text = uid
        correoUsuario.text = correo
    }

    private fun obtenerFechaHoraActual() {
        val calendar: Calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val fechaHoraActualStr = dateFormat.format(calendar.time)

        fechaHoraActual.text = fechaHoraActualStr
    }
}
