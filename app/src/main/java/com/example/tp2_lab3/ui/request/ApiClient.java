package com.example.tp2_lab3.ui.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.tp2_lab3.model.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {
    public static void registrar(Context context, Usuario usuario) {
        File archivo = new File(context.getFilesDir(), "usuario.dat");

        try {
            FileOutputStream fos = new FileOutputStream(archivo, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(usuario);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Usuario leer(Context context) {
        Usuario usuarioRegistrado = null;

        File archivo = new File(context.getFilesDir(), "usuario.dat");
        Log.d("Archivo: ", archivo.toString());

        if (!archivo.exists()) {
            Log.d("Existe: ", "No existe el archivo");
            return usuarioRegistrado;
        }

        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            usuarioRegistrado = (Usuario) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            Log.d("Error: ", "Error al leer el archivo");
            e.printStackTrace();
        }
        Log.d("Usuario Email: ", usuarioRegistrado.getMail());
        return usuarioRegistrado;
    }

    public static Usuario login(Context context, String mail, String password) {
        Usuario usuarioRegistrado = leer(context);

        if (mail.equals(usuarioRegistrado.getMail()) && password.equals(usuarioRegistrado.getClave())) {
            return usuarioRegistrado;
        }

        return usuarioRegistrado;
    }

}

