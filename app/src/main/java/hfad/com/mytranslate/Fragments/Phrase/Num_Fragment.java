package hfad.com.mytranslate.Fragments.Phrase;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import hfad.com.mytranslate.Fragments.FrazeFragment;
import hfad.com.mytranslate.R;
import hfad.com.mytranslate.SQLite.Phrases_SQL;
import hfad.com.mytranslate.SQLite.SQL_adapter;
import hfad.com.mytranslate.models.Phrase;

public class Num_Fragment extends Fragment  implements View.OnClickListener   {

    Phrases_SQL phrases_sql;
    ArrayList<Phrase> Phrase_array;
    ListView yourListView;
    SQL_adapter sql_adapter;


    public Num_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_num_, container, false);

        yourListView = (ListView)view.findViewById(R.id.list_item);
        phrases_sql = new Phrases_SQL(getContext());
        Phrase_array = new ArrayList<>();
        loadDatainViewn();

        return view;
    }

    private void loadDatainViewn() {

        Phrase_array = phrases_sql.getCategotyNum_Fragment();

        sql_adapter = new SQL_adapter(getContext(), Phrase_array);

        yourListView.setAdapter(sql_adapter);

        sql_adapter.notifyDataSetChanged();

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
