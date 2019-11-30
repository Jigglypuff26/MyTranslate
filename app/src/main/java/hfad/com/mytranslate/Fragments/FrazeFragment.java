package hfad.com.mytranslate.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hfad.com.mytranslate.Fragments.Phrase.AirplaneCommunication;
import hfad.com.mytranslate.Fragments.Phrase.Auto_chat;
import hfad.com.mytranslate.Fragments.Phrase.CommunicationCity;
import hfad.com.mytranslate.Fragments.Phrase.Favorite_Phrases;
import hfad.com.mytranslate.Fragments.Phrase.HotelChat;
import hfad.com.mytranslate.Fragments.Phrase.Num_Fragment;
import hfad.com.mytranslate.Fragments.Phrase.PersonalCommunication;
import hfad.com.mytranslate.Fragments.Phrase.PhrazeBottomsFragment;
import hfad.com.mytranslate.Fragments.Phrase.RequiredPhrases;
import hfad.com.mytranslate.Fragments.Phrase.ShoppingCommunication;
import hfad.com.mytranslate.R;
import hfad.com.mytranslate.weather.Fragments.weather;


public class FrazeFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.phraze_fragment, container, false);
        //слушетели для кнопок
        itemView.findViewById(R.id.test_back_button).setOnClickListener(this);
        itemView.findViewById(R.id.test_back_button1).setOnClickListener(this);
        itemView.findViewById(R.id.test_back_button2).setOnClickListener(this);
        itemView.findViewById(R.id.test_back_button3).setOnClickListener(this);
        itemView.findViewById(R.id.test_back_button4).setOnClickListener(this);
        itemView.findViewById(R.id.test_back_button5).setOnClickListener(this);
        itemView.findViewById(R.id.test_back_button6).setOnClickListener(this);
        itemView.findViewById(R.id.test_back_button7).setOnClickListener(this);
        itemView.findViewById(R.id.test_back_button_auto).setOnClickListener(this);
        return itemView;
    }

    //вставка фрагмента по нажатию
    private void displayFragment_Praze(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.relativeLayout, fragment)
                .commit();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View v) {

        Fragment fragment = null;

        switch (v.getId()) {
            case R.id.test_back_button:
                fragment = new RequiredPhrases();
                break;
            case R.id.test_back_button1:
                fragment = new PersonalCommunication();
                break;
            case R.id.test_back_button2:
                fragment = new AirplaneCommunication();
                break;
            case R.id.test_back_button3:
                fragment = new HotelChat();
                break;
            case R.id.test_back_button4:
                fragment = new CommunicationCity();
                break;
            case R.id.test_back_button5:
                fragment = new ShoppingCommunication();
                break;
            case R.id.test_back_button6:
                fragment = new Favorite_Phrases();
                break;
            case R.id.test_back_button7:
                fragment = new Num_Fragment();
                break;
            case R.id.test_back_button_auto:
                fragment = new Auto_chat();
                break;

        }

        if(fragment != null){
            displayFragment_Praze(fragment);
        }
    }

}
