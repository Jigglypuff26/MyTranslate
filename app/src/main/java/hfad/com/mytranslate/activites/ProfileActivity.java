package hfad.com.mytranslate.activites;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import hfad.com.mytranslate.Exchange_Rates.Fragment.Exchange_Rates;
import hfad.com.mytranslate.Fragments.FrazeFragment;
import hfad.com.mytranslate.Fragments.HomeFragment;
import hfad.com.mytranslate.Fragments.SettingsFragment;
import hfad.com.mytranslate.MainActivity;
import hfad.com.mytranslate.R;
import hfad.com.mytranslate.storage.SharedPrefManager;

public class ProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //_17_ добавляем нижнее меню в даный интент по id
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        //_17_добаляем слушетель для итемов меню
        //_17_в строке 20 . implements BottomNavigationView.OnNavigationItemSelectedListener .
        navigationView.setOnNavigationItemSelectedListener(this);

        //_17_ добавление фрагмента
        displayFragment(new FrazeFragment());

    }

    //_17_ вставка фрагмента в RelativeLayout в layout activity_profile
    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayout, fragment)
                .commit();
    }


    @Override
    protected void onStart() {
        super.onStart();

        //если в классе SharedPrefManager есть данные пользователя то запускается
        //ProfileActivity с данными пользователя которые были в SharedPrefManager
        //таже есть такойде метод в MainActivity 47-55 строка
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    //_17_ создание итемво ссылок на конкрентные фрагменты
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch(item.getItemId()){
            case R.id.menu_home:
                fragment = new HomeFragment();
                displayFragment(fragment);
                break;
            case R.id.ER_money:
                fragment = new Exchange_Rates();
                displayFragment(fragment);
                break;
            case R.id.menu_fraze:
                fragment = new FrazeFragment();
                displayFragment(fragment);
                break;
            case R.id.menu_settings:
                fragment = new SettingsFragment();
                displayFragment(fragment);
                break;
        }
        return true;
    }
}
