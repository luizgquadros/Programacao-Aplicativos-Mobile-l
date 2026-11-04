package com.luiz.etimpamiluizquadrosappdelivery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtNome, edtEmail, edtSenha;
    Button btnCadastrar, btnVoltar;
    CheckBox checkBox;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicializa os componentes
        initComponents();

        // SharedPreferences
        preferences = getSharedPreferences("login", MODE_PRIVATE);

        // Botão CADASTRAR
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validarDados()) {

                    SharedPreferences.Editor editor = preferences.edit();

                    // IMPORTANTE:
                    // Essas chaves precisam ser iguais às usadas
                    // no LoginActivity e HomeActivity.
                    editor.putString("Nome", edtNome.getText().toString());
                    editor.putString("Email", edtEmail.getText().toString());
                    editor.putString("Senha", edtSenha.getText().toString());

                    // Salva a preferência de lembrar
                    editor.putBoolean("remember", checkBox.isChecked());

                    editor.apply();

                    Toast.makeText(
                            MainActivity.this,
                            "Conta criada com sucesso!",
                            Toast.LENGTH_SHORT
                    ).show();

                    // Vai para a tela de login
                    Intent intent = new Intent(
                            MainActivity.this,
                            LoginActivity.class
                    );

                    startActivity(intent);
                    finish();
                }
            }
        });

        // Botão VOLTAR
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        MainActivity.this,
                        LoginActivity.class
                );

                startActivity(intent);
                finish();
            }
        });
    }

    // Validação dos campos
    private boolean validarDados() {

        boolean retorno = true;

        if (edtNome.getText().toString().trim().isEmpty()) {
            retorno = false;
            edtNome.setError("Este campo não pode estar vazio!");
        }

        if (edtEmail.getText().toString().trim().isEmpty()) {
            retorno = false;
            edtEmail.setError("Este campo não pode estar vazio!");
        }

        if (edtSenha.getText().toString().trim().isEmpty()) {
            retorno = false;
            edtSenha.setError("Este campo não pode estar vazio!");
        }

        return retorno;
    }

    // Liga os componentes Java aos componentes do XML
    private void initComponents() {

        edtNome = findViewById(R.id.cad_nome);
        edtEmail = findViewById(R.id.cad_email);
        edtSenha = findViewById(R.id.cad_senha);

        btnVoltar = findViewById(R.id.cad_btn_voltar);
        btnCadastrar = findViewById(R.id.cad_btn_cadastrar);

        checkBox = findViewById(R.id.cad_box);
    }
}