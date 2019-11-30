package hfad.com.mytranslate.models;

public class Phrase {

    int ID_view_array;
    boolean Favorit_view_array;
    private  String RUS_Phrases_view_array, TUR_Phrases_view_array, Transcription_view_array, Categoty_view_array;

    public Phrase (int ID_view_array, String RUS_Phrases_view_array, String TUR_Phrases_view_array, String Transcription_view_array, String Categoty_view_array,
                   boolean Favorit_view_array) {

        this.ID_view_array = ID_view_array;
        this.RUS_Phrases_view_array = RUS_Phrases_view_array;
        this.TUR_Phrases_view_array = TUR_Phrases_view_array;
        this.Transcription_view_array = Transcription_view_array;
        this.Categoty_view_array = Categoty_view_array;
        this.Favorit_view_array = Favorit_view_array;

    }

    public Phrase () {}


    public int getID_view_array() {
        return ID_view_array;
    }

    public void setID_view_array(int ID_view_array) {
        this.ID_view_array = ID_view_array;
    }

    public  String getRUS_Phrases_view() {
        return RUS_Phrases_view_array;
    }
    public void setRUS_Phrases_view(String RUS_Phrases_view_array) {
        this.RUS_Phrases_view_array = RUS_Phrases_view_array;
    }

    public  String getTUR_Phrases_view() {
        return TUR_Phrases_view_array;
    }
    public void setTUR_Phrases_view(String TUR_Phrases_view_array) {
        this.TUR_Phrases_view_array = TUR_Phrases_view_array;
    }

    public  String getTranscription_view() {
        return Transcription_view_array;
    }
    public void setTranscription_view(String Transcription_view_array) {
        this.Transcription_view_array = Transcription_view_array;
    }

    public  String getCategoty_view() {
        return Categoty_view_array;
    }
    public void setCategoty_view(String Categoty_view_array) {
        this.Categoty_view_array = Categoty_view_array;
    }

    public boolean isFavorit_view_array() {
        return Favorit_view_array;
    }
    public void setFavorit_view_array(boolean favorit_view_array) {
        Favorit_view_array = favorit_view_array;
    }
}
