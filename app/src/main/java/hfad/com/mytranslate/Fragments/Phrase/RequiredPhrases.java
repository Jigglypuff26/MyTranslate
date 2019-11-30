package hfad.com.mytranslate.Fragments.Phrase;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import hfad.com.mytranslate.Fragments.FrazeFragment;
import hfad.com.mytranslate.R;
import hfad.com.mytranslate.SQLite.Phrases_SQL;
import hfad.com.mytranslate.SQLite.SQL_adapter;
import hfad.com.mytranslate.models.Phrase;

public class RequiredPhrases extends Fragment implements View.OnClickListener  {

    Phrases_SQL phrases_sql;
    ArrayList<Phrase> Phrase_array;
    ListView yourListView;
    SQL_adapter sql_adapter;
    //display panel
    ProgressBar loading;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.required_phrases, container, false);
        yourListView = (ListView)view.findViewById(R.id.list_item);
        phrases_sql = new Phrases_SQL(getContext());
        Phrase_array = new ArrayList<>();
        //display panel
        loading = (ProgressBar) view.findViewById(R.id.loading_f);
        loadDatainViewn();
        return view;
    }

    private void loadDatainViewn() {
        Phrase_array = phrases_sql.getCategotyRequired();
        sql_adapter = new SQL_adapter(getContext(), Phrase_array);
        yourListView.setAdapter(sql_adapter);
        sql_adapter.notifyDataSetChanged();
        //display panel
        yourListView.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }

    private void BackToFrazeFragment() {
        int ft = getFragmentManager().beginTransaction().replace(R.id.relativeLayout, new FrazeFragment())
                .commit();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //слушетели для кнопок
        view.findViewById(R.id.back_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                BackToFrazeFragment();
                break;
        }
    }
}

