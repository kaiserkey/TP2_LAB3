package com.example.tp2_lab3.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tp2_lab3.model.Usuario;
import com.example.tp2_lab3.ui.login.LoginActivity;
import com.example.tp2_lab3.ui.request.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient apiClient;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = new ApiClient();
    }

    public void registrarUsuario(String nombre, String apellido, String dni, String mail, String clave) {
        try {
            Log.d("Usuario Login: ", nombre + " " + apellido + " " + dni + " " + mail + " " + clave);
            Usuario usuarioRegistrado = apiClient.login(context, mail, clave);
            Log.d("Usuario ToString: ", usuarioRegistrado.toString());
            if (usuarioRegistrado.getMail().equals(mail)) {
                Toast.makeText(context, "El E-Mail ingresado ya se encuentra registrado.", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.d("Usuario Login: ", "Registrando...");
            Usuario usuario = new Usuario(nombre, apellido, dni, mail, clave);
            apiClient.registrar(context, usuario);

            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);

        }catch (Exception e) {
            Log.d("Error", e.getMessage() + " " + e.getCause() + " " + e.getStackTrace() + " " + e.getLocalizedMessage() + " " + e.getSuppressed() );
            Toast.makeText(context, "Error al registrar el usuario.", Toast.LENGTH_SHORT).show();
        }
    }
}
