package hfad.com.mytranslate.weather.Models.Five_Days;

import java.util.List;

import hfad.com.mytranslate.weather.Models.Clouds;
import hfad.com.mytranslate.weather.Models.Main;
import hfad.com.mytranslate.weather.Models.Sys;
import hfad.com.mytranslate.weather.Models.Weather;
import hfad.com.mytranslate.weather.Models.Wind;

public class MyList {

    public int dt ;
    public Main main ;
    public List<Weather> weather;
    public Clouds clouds ;
    public Wind wind ;
    public Rain rain ;
    public Sys sys ;
    public String dt_txt ;


}
