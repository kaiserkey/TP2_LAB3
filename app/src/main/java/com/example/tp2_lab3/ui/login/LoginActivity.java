package com.example.tp2_lab3.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp2_lab3.databinding.ActivityLoginBinding;
import com.example.tp2_lab3.model.Usuario;
import com.example.tp2_lab3.ui.registro.MainActivity;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginActivityViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);


        binding.btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.confirmarLogin(binding.etMail.getText().toString(), binding.etClave.getText().toString());
                mv.getDataUsuarioMutable().observe(LoginActivity.this, new Observer<Usuario>() {
                    @Override
                    public void onChanged(Usuario usuario) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("usuario", usuario);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });



        binding.btnIrARegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}