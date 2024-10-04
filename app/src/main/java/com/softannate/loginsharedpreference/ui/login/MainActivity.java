package com.softannate.loginsharedpreference.ui.login;

import static com.softannate.loginsharedpreference.request.ApiClient.login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softannate.loginsharedpreference.Modelo.Usuario;
import com.softannate.loginsharedpreference.R;
import com.softannate.loginsharedpreference.databinding.ActivityMainBinding;
import com.softannate.loginsharedpreference.request.ApiClient;
import com.softannate.loginsharedpreference.ui.register.RegistroActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);

        setContentView(binding.getRoot());
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);


        binding.btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= binding.etEmail.getText().toString();
                String pass=binding.etPass.getText().toString();

                vm.login(MainActivity.this, email, pass);

            }
        });
        vm.getMMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });

        vm.getMUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                Intent intent= new Intent(MainActivity.this, RegistroActivity.class);
                Log.d("MainActivity", "Usuario: " + usuario.toString());
                intent.putExtra("usuario",usuario);
                Log.d("MainActivity", "Usuario: " + usuario.toString());

                startActivity(intent);
                limpiar();
            }
        });

        //para agregar a el texto el subrayado
        binding.tvRegistrar.setPaintFlags(binding.tvRegistrar.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        binding.tvRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, RegistroActivity.class);

                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void limpiar(){
        binding.etEmail.setText("");
        binding.etPass.setText("");
    }
}