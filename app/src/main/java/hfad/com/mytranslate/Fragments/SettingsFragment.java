package hfad.com.mytranslate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hfad.com.mytranslate.API.RetrofitClient;
import hfad.com.mytranslate.MainActivity;
import hfad.com.mytranslate.R;
import hfad.com.mytranslate.activites.ProfileActivity;
import hfad.com.mytranslate.models.DefaultResponse;
import hfad.com.mytranslate.models.LoginResponse;
import hfad.com.mytranslate.models.User;
import hfad.com.mytranslate.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment implements View.OnClickListener {


    //данные пользователя
    private TextView textViewEmail, textViewName, textViewSurname, textViewlogin;

    //создаем внутренние переменные
    private EditText editTextEmail, editTextName, editTextSurname;
    private EditText editTextCurrentPassword, editTextNewPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        //_17_ передача значения переменных в view по методу id
//        textViewEmail = view.findViewById(R.id.textViewEmail);
//        textViewName = view.findViewById(R.id.textViewName);
//        textViewSurname = view.findViewById(R.id.textViewSurname);
//        textViewlogin = view.findViewById(R.id.textViewlogin);
        //_17_ получение и присвоение данных переменным из класса  SharedPrefManager
//        textViewEmail.setText(SharedPrefManager.getInstance(getActivity()).getUser().getEmail());
//        textViewName.setText(SharedPrefManager.getInstance(getActivity()).getUser().getName());
//        textViewSurname.setText(SharedPrefManager.getInstance(getActivity()).getUser().getSurname());
//        textViewlogin.setText(SharedPrefManager.getInstance(getActivity()).getUser().getLogin());

//        // присвоение данных из формы переменным
//        editTextEmail = view.findViewById(R.id.ent_cheng_email);
//        editTextName = view.findViewById(R.id.ent_cheng_name);
//        editTextSurname = view.findViewById(R.id.ent_cheng_surname);

        editTextCurrentPassword = view.findViewById(R.id.ent_cheng_old_pass);
        editTextNewPassword = view.findViewById(R.id.ent_cheng_new_pass);

        //слушетели для кнопок
//        view.findViewById(R.id.button_cheng_data).setOnClickListener(this);
        view.findViewById(R.id.button_cheng_pass).setOnClickListener(this);
        view.findViewById(R.id.button_out_log).setOnClickListener(this);

    }


//    private void updateProfile() {
//
//        String email = editTextEmail.getText().toString().trim();
//        String name = editTextName.getText().toString().trim();
//        String surname = editTextSurname.getText().toString().trim();
//
//        if (email.isEmpty()) {
//            editTextEmail.setError("Введите вашу электронную почту");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if (email.length() > 32) {
//            editTextEmail.setError("Очень длинная электронная почта");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            editTextEmail.setError("Введите правильную электронную почту");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if (name.isEmpty()) {
//            editTextName.setError("Введите ваше Имя");
//            editTextName.requestFocus();
//            return;
//        }
//
//        if (name.length() > 32) {
//            editTextName.setError("Очень длинное имя");
//            editTextName.requestFocus();
//            return;
//        }
//
//        if (surname.isEmpty()) {
//            editTextSurname.setError("Введите вашу Фамилию");
//            editTextSurname.requestFocus();
//            return;
//        }
//
//        if (surname.length() > 32) {
//            editTextSurname.setError("Очень длинная Фамилия");
//            editTextSurname.requestFocus();
//            return;
//        }
//
//        //Получение данных оп ользователе из SharedPrefManager методом getInstance(getActivity()).getUser()
//        User user = SharedPrefManager.getInstance(getActivity()).getUser();
//
//        //соеденение с базой данных методом RetrofitClient для дальнейшей обработки на сервер
//        Call<LoginResponse> call = RetrofitClient.getInstance()
//                //_19_ обращение в метод updateuser класса Api и передача ему данных
//                .getApi().updateUser(
//                        user.getId(),
//                        email,
//                        name,
//                        surname
//                );
//
//        //запись новых данных в  SharedPrefManager
//        call.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//
//                //выводит ошибку если чтото пошло не так
//                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getActivity(), ProfileActivity.class));
//
//                //если нет ошибок то данные передаются в класс SharedPrefManager методу saveUser
//                if (!response.body().isError()) {
//                    SharedPrefManager.getInstance(getActivity()).saveUser(response.body().getUser());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//
//            }
//        });
//
//    }

    private void updatePassword() {
        String currentpassword = editTextCurrentPassword.getText().toString().trim();
        String newpassword = editTextNewPassword.getText().toString().trim();

        if (currentpassword.length() < 6) {
            editTextCurrentPassword.setError("Введите пароль должен быть не менее 6 и не более 16 символов");
            editTextCurrentPassword.requestFocus();
            return;
        }

        if (currentpassword.length() > 16) {
            editTextCurrentPassword.setError("Введите пароль должен быть не менее 6 и не более 16 символов");
            editTextCurrentPassword.requestFocus();
            return;
        }

        if (newpassword.length() < 6) {
            editTextNewPassword.setError("Введите пароль должен быть не менее 6 и не более 16 символов");
            editTextNewPassword.requestFocus();
            return;
        }

        if (newpassword.length() > 16) {
            editTextNewPassword.setError("Введите пароль должен быть не менее 6 и не более 16 символов");
            editTextNewPassword.requestFocus();
            return;
        }

        if (currentpassword.equals(newpassword)) {
            editTextNewPassword.setError("Пароли не должны совпадать");
            editTextNewPassword.requestFocus();
            return;
        }

        User user = SharedPrefManager.getInstance(getActivity()).getUser();

        Call<DefaultResponse> call = RetrofitClient.getInstance().getApi()
                .updatePassword(currentpassword, newpassword, user.getEmail());

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }

    private void logout() {
        SharedPrefManager.getInstance(getActivity()).clear();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.button_cheng_data:
//                updateProfile();
//                break;
            case R.id.button_cheng_pass:
                updatePassword();
                break;
            case R.id.button_out_log:
                logout();
                break;
        }
    }
}
