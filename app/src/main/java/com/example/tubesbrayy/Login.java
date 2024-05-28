package com.example.tubesbrayy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    private TextInputEditText inputUsername, inputPassword;
    private Button btnLogin, btnRegister;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        databaseHelper = new DatabaseHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = inputUsername.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Upss Anda harus mengisi semua kolom!", Toast.LENGTH_LONG).show();
                } else {
                    boolean isValid = databaseHelper.checkUser(username, password);
                    if (isValid) {
                        Intent intent = new Intent(Login.this, TampilanAwal.class);
                        startActivity(intent);
                        finish();  // Optional: to prevent user from going back to login screen with back button
                    } else {
                        Toast.makeText(Login.this, "Username atau password anda salah!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
