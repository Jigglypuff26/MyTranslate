package hfad.com.mytranslate.storage;

import android.content.Context;
import android.content.SharedPreferences;

import hfad.com.mytranslate.models.User;

public class SharedPrefManager {

    //константа для сохранения дынных пользователя
    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    //создаем конструктор для сохранение данных
    private SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }


    //создаем синхронизацию с конструктором
    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }


    //сохранение данных пользователя
    public void saveUser(User user) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //в кавычках присваевается имя для переменной для метода, далее user это название класса User,
        // а все что начинается на get это переменные класса User (тоесть данные передаются из метода saveUser  в класс User)
        editor.putInt("id", user.getId());
        editor.putString("email", user.getEmail());
        editor.putString("name", user.getName());
        editor.putString("surname", user.getSurname());
        editor.putString("login", user.getLogin());

        //применяем все выше перечисленноев методе
        editor.apply();

    }

    //передача данных методу isLoggedIn
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;
    }

    //передача данных методу getUser по умолчанию все данные равны нулю
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("surname", null),
                sharedPreferences.getString("login", null)
        );
    }


    //отчитка данных пользователя
    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


}
