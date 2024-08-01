package riva.ticona.proyectoaplicativo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterGuardadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_guardado);

        // Handler para retrasar la transición a MainActivity
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent para iniciar MainActivity
                Intent intent = new Intent(RegisterGuardadoActivity.this, MainActivity.class);
                startActivity(intent);

                // Cierra RegisterGuardadoActivity después de iniciar MainActivity
                finish();
            }
        }, 3000); // Retraso de 1 segundo (1000 milisegundos)
    }
}
