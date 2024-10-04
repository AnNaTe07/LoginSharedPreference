package com.softannate.loginsharedpreference.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.softannate.loginsharedpreference.Modelo.Usuario;

public class ApiClient {
    private static SharedPreferences sp;

    private static SharedPreferences conectar (Context context){
        if(sp==null){
            sp= context.getSharedPreferences("datos",0);
        }
        return  sp;
    }
    public  static void guardar(Context context, Usuario usuario){

        SharedPreferences sp= conectar(context);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("nombre", usuario.getNombre());
        editor.putString("apellido", usuario.getApellido());
        editor.putLong("dni", usuario.getDni());
        editor.putString("email", usuario.getEmail());
        editor.putString("pass", usuario.getPass());
        editor.commit();
    }

    public static Usuario leer(Context context){
        SharedPreferences sp=conectar((context));
        String nombre= sp.getString("nombre", "-1");
        String apellido=sp.getString("apellido","-1");
        Long dni= sp.getLong("dni", -1);
        String email=sp.getString("email","-1");
        String pass=sp.getString("pass","-1");

        Usuario usuario=new Usuario(nombre, apellido, dni, email, pass);
        return usuario;
    }

    public static Usuario login(Context context, String email1, String pass1){
        Usuario usuario=null;
        SharedPreferences sp= conectar(context);
        String nombre=sp.getString("nombre","-1");
        String apellido=sp.getString("apellido","-1");
        Long dni=sp.getLong("dni", -1);
        String email=sp.getString("email","-1");
        String pass=sp.getString("pass", "-1");

        if(email1.equalsIgnoreCase(email) && pass1.equalsIgnoreCase(pass)){
            usuario=new Usuario(nombre, apellido, dni, email, pass);
        }
        return usuario;
    }
}
