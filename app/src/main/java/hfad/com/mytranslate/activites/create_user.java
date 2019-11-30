package hfad.com.mytranslate.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hfad.com.mytranslate.API.RetrofitClient;
import hfad.com.mytranslate.MainActivity;
import hfad.com.mytranslate.R;
import hfad.com.mytranslate.models.DefaultResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class create_user extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_create_email, edit_create_pass, edit_create_name, edit_create_surname, edit_create_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        edit_create_email = findViewById(R.id.ent_cr_email);
        edit_create_pass = findViewById(R.id.ent_cr_pass);
        edit_create_name = findViewById(R.id.ent_cr_name);
        edit_create_surname = findViewById(R.id.ent_cr_surname);
        edit_create_login = findViewById(R.id.ent_cr_login);

        findViewById(R.id.button_cr_create).setOnClickListener(this);
        findViewById(R.id.button_cr_log).setOnClickListener(this);

    }

    private void usercreate () {
        String email = edit_create_email.getText().toString().trim();
        String pass = edit_create_pass.getText().toString().trim();
        String name = edit_create_name.getText().toString().trim();
        String surname = edit_create_surname.getText().toString().trim();
        String login = edit_create_login.getText().toString().trim();

        if (email.isEmpty()) {
            edit_create_email.setError("Введите вашу электронную почту");
            edit_create_email.requestFocus();
            return;
        }

        if (email.length() > 32) {
            edit_create_email.setError("Очень длинная электронная почта");
            edit_create_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edit_create_email.setError("Введите правильную электронную почту");
            edit_create_email.requestFocus();
            return;
        }

        if (pass.length() < 6) {
            edit_create_pass.setError("Введите пароль должен быть не менее 6 и не более 16 символов");
            edit_create_pass.requestFocus();
            return;
        }

        if (pass.length() > 16) {
            edit_create_pass.setError("Введите пароль должен быть не менее 6 и не более 16 символов");
            edit_create_pass.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            edit_create_name.setError("Введите ваше Имя");
            edit_create_name.requestFocus();
            return;
        }

        if (name.length() > 32) {
            edit_create_name.setError("Очень длинное имя");
            edit_create_name.requestFocus();
            return;
        }

        if (surname.isEmpty()) {
            edit_create_surname.setError("Введите вашу Фамилию");
            edit_create_surname.requestFocus();
            return;
        }

        if (surname.length() > 32) {
            edit_create_surname.setError("Очень длинная Фамилия");
            edit_create_surname.requestFocus();
            return;
        }

        if (login.isEmpty()) {
            edit_create_login.setError("Введите ваш ник (логин) ");
            edit_create_login.requestFocus();
            return;
        }

        if (login.length() > 16) {
            edit_create_login.setError("Длинный логин");
            edit_create_login.requestFocus();
            return;
        }

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(email, pass, name, surname, login);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201) {
                    DefaultResponse dr = response.body();
                    Toast.makeText(create_user.this, dr.getMsg(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(create_user.this, MainActivity.class));
                } else if (response.code() == 422) {
                    Toast.makeText(create_user.this, "Пользователь уже существует", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(create_user.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_cr_create:
                usercreate();
                break;
            case R.id.button_cr_log:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
