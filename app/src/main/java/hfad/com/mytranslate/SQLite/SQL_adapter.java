package hfad.com.mytranslate.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hfad.com.mytranslate.R;
import hfad.com.mytranslate.models.Phrase;

public class SQL_adapter extends BaseAdapter {

    Context context_SQL;
    ArrayList<Phrase> arrayList_SQL;

    public SQL_adapter(Context context_SQL, ArrayList<Phrase> arrayList_SQL) {
        this.context_SQL = context_SQL;
        this.arrayList_SQL = arrayList_SQL;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return arrayList_SQL.get(position);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

            LayoutInflater inflater_SQL = (LayoutInflater)context_SQL.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater_SQL.inflate(R.layout.list_items, null);

            final TextView textView_item_Turkey_SQL = (TextView)convertView.findViewById(R.id.textView_Turkey_in);
            final TextView textView_item_Rus_SQL = (TextView)convertView.findViewById(R.id.textView_Rus_in);
            final TextView textView_item_transkript_SQL = (TextView)convertView.findViewById(R.id.textView_transkript_in);
            final CheckBox checkBox_item_Favorit_SQL = (CheckBox)convertView.findViewById(R.id.checkBox_to_favorit);

            //обработка CheckBox checkBox_item_Favorit_SQL
            checkBox_item_Favorit_SQL.setChecked(true);
            checkBox_item_Favorit_SQL.setOnClickListener(new CompoundButton.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String RUS_data = textView_item_Rus_SQL.getText().toString().trim();

                    if (checkBox_item_Favorit_SQL.isChecked()) {

                        addItem(RUS_data);
                        arrayList_SQL.get(position).setFavorit_view_array(true);

                    } else {

                        dellItem(RUS_data);
                        arrayList_SQL.get(position).setFavorit_view_array(false);

                    }
                }
            });

            Phrase phrase = arrayList_SQL.get(position);

            textView_item_Rus_SQL.setText(phrase.getRUS_Phrases_view());
            textView_item_Turkey_SQL.setText(phrase.getTUR_Phrases_view());
            textView_item_transkript_SQL.setText(phrase.getTranscription_view());
            checkBox_item_Favorit_SQL.setChecked(phrase.isFavorit_view_array());



        return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList_SQL.size();
    }

    //для обработки CheckBox строка 58
    //добавить
    public void addItem(String RUS_data) {
        Toast.makeText(context_SQL, "Фраза " + RUS_data + " добавлена в избранное", Toast.LENGTH_SHORT).show();
        Phrases_SQL SQL = new Phrases_SQL(context_SQL);
        SQLiteDatabase db = SQL.getWritableDatabase();
        ContentValues PhrasesValues = new ContentValues();
        PhrasesValues.put("FAVORITE", 1);
        db.update("PHRASES_SQL", PhrasesValues, "RUS_Phrases = ? ", new  String[] {RUS_data});
        db.close();
    }

    //удалить
    public void dellItem(String RUS_data) {
        Toast.makeText(context_SQL, "Фраза " + RUS_data + " удалена из избранного", Toast.LENGTH_SHORT).show();
        Phrases_SQL SQL = new Phrases_SQL(context_SQL);
        SQLiteDatabase db = SQL.getWritableDatabase();
        ContentValues PhrasesValues = new ContentValues();
        PhrasesValues.put("FAVORITE", 0);
        db.update("PHRASES_SQL", PhrasesValues, "RUS_Phrases = ? ", new  String[] {RUS_data});
        db.close();
    }

}

