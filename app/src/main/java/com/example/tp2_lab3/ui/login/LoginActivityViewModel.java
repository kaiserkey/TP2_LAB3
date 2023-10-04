package com.example.tp2_lab3.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp2_lab3.model.Usuario;
import com.example.tp2_lab3.ui.registro.MainActivity;
import com.example.tp2_lab3.ui.request.ApiClient;

public class LoginActivityViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient apiClient;
    private MutableLiveData<Usuario> dataUsuarioMutable;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = new ApiClient();
        dataUsuarioMutable = new MutableLiveData<>();
    }
    public LiveData<Usuario> getDataUsuarioMutable() {
        if (dataUsuarioMutable == null) {
            dataUsuarioMutable = new MutableLiveData<>();
        }
        return dataUsuarioMutable;
    }

    public void confirmarLogin(String mail, String clave) {
        Usuario usuario = apiClient.login(context ,mail, clave);
        if (usuario != null) {
            dataUsuarioMutable.setValue(usuario);
            Intent intent = new Intent(getApplication(), MainActivity.class);
            intent.putExtra("usuario", usuario);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getApplication().startActivity(intent);
        } else {
            Toast.makeText(getApplication(), "Usuario o Contraseña Inválidos", Toast.LENGTH_SHORT).show();
        }
    }
}
