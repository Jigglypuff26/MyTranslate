package hfad.com.mytranslate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hfad.com.mytranslate.API.RetrofitClient;
import hfad.com.mytranslate.activites.ProfileActivity;
import hfad.com.mytranslate.activites.create_user;
import hfad.com.mytranslate.models.LoginResponse;
import hfad.com.mytranslate.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_reg_email, edit_reg_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_reg_email = findViewById(R.id.ent_email);
        edit_reg_pass = findViewById(R.id.ent_pass);

        findViewById(R.id.button_reg).setOnClickListener(this);
        findViewById(R.id.button_act_create).setOnClickListener(this);

    }


    //метод запуска ProfileActivity
    @Override
    protected void onStart() {
        super.onStart();

        //если в классе SharedPrefManager есть данные пользователя то запускается
        //ProfileActivity с данными пользователя которые были в SharedPrefManager
        //таже есть такойде метод в MainActivity 47-55 строка
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    private void userLogin() {
        String email = edit_reg_email.getText().toString().trim();
        String pass = edit_reg_pass.getText().toString().trim();

        if (email.isEmpty()) {
            edit_reg_email.setError("Введите вашу электронную почту");
            edit_reg_email.requestFocus();
            return;
        }

        if (email.length() > 32) {
            edit_reg_email.setError("Очень длинная электронная почта");
            edit_reg_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edit_reg_email.setError("Введите правильную электронную почту");
            edit_reg_email.requestFocus();
            return;
        }

        if (pass.length() < 6) {
            edit_reg_pass.setError("Введите пароль должен быть не менее 6 и не более 16 символов");
            edit_reg_pass.requestFocus();
            return;
        }

        if (pass.length() > 16) {
            edit_reg_pass.setError("Введите пароль должен быть не менее 6 и не более 16 символов");
            edit_reg_pass.requestFocus();
            return;
        }

        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().userLogin(email, pass);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                //если вы залогинились то запускается ProfileActivity
                if (!loginResponse.isError()) {
                    //методом saveUser передают данные пользователя  классу loginResponse и методу getUser
                    SharedPrefManager.getInstance(MainActivity.this)
                            .saveUser(loginResponse.getUser());
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_reg:
                userLogin();
                break;
            case R.id.button_act_create:
                startActivity(new Intent(this, create_user.class));
                break;
        }
    }
}
