package hfad.com.mytranslate.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import hfad.com.mytranslate.Fragments.Phrase.Favorite_Phrases;
import hfad.com.mytranslate.models.Phrase;

public class Phrases_SQL extends SQLiteOpenHelper {

    Context context_SQL;

    private static final String DB_NAME = "Phrases_name1"; // Имя базы данных
    private static final int DB_VERSION = 1; // Версия базы данных

    public Phrases_SQL(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Метод updateMyDatabase()
        //вызывается из onUpgrade()
        //с передачей параметров
        updateMyDatabase(db, oldVersion, newVersion);
    }

    //метод добавления данных в БД
    private boolean insertPhrase(SQLiteDatabase db,String RUS_Phrases_view, String TUR_Phrases_view, String Transcription_view, String Categoty_view) {
        //создание обекта для добаления данных
        ContentValues PhrasesValues = new ContentValues();
        //добаление данных
        PhrasesValues.put("RUS_Phrases", RUS_Phrases_view);
        PhrasesValues.put("TUR_Phrases", TUR_Phrases_view);
        PhrasesValues.put("Transcription", Transcription_view);
        PhrasesValues.put("Categoty", Categoty_view);
        long result = db.insert("PHRASES_SQL", null, PhrasesValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        //если версия БД ментьше 1 ой то....
        if (oldVersion < 1) {
            //Создание таблици
            db.execSQL("CREATE TABLE PHRASES_SQL ( ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "RUS_Phrases TEXT, "
                    + "TUR_Phrases TEXT, "
                    + "Transcription TEXT, "
                    + "Categoty TEXT,"
                    + "FAVORITE NUMERIC);");

            //Required_Phrases
            insertPhrase(db, "Место встречи с автобусом", "Otobüs ile buluşma yeri", "Отобюс иле булушма йери", "Required_Phrases");
            insertPhrase(db, "Вход", "GİRİŞ", "Гириш", "Required_Phrases");
            insertPhrase(db, "Выход", "ÇIKIŞ", "Чыкыш", "Required_Phrases");
            insertPhrase(db, "Аварийный выход", "ACİL ÇIKIŞ", "Аджил чыкыш", "Required_Phrases");
            insertPhrase(db, "Вход воспрещен", "GİRİŞ YASAK", "Гириш ясак", "Required_Phrases");
            insertPhrase(db, "Туалеты", "WC", "Туалетлер", "Required_Phrases");
            insertPhrase(db, "Женский туалет", "Kadın tuvalet", "Кадын Туалет", "Required_Phrases");
            insertPhrase(db, "Мужской туалет ", "Erkekler tuvaleti", "Айкекляй Туалетэ", "Required_Phrases");
            insertPhrase(db, "Не курить", "SİGARA KULANMAK YASAKTIR", "Сигара кулланмак ясак", "Required_Phrases");
            insertPhrase(db, "От себя/ от меня", "Benden", "Бенден", "Required_Phrases");
            insertPhrase(db, "К себе/ мне", "Bana", "Бана", "Required_Phrases");
            insertPhrase(db, "Открыто", "AÇIK", "Ачык", "Required_Phrases");
            insertPhrase(db, "Закрыто", "KAPALI", "Капалы", "Required_Phrases");
            insertPhrase(db, "Да", "Evet", "Евет", "Required_Phrases");
            insertPhrase(db, "Хорошо", "Tamam", "Тамам", "Required_Phrases");
            insertPhrase(db, "Конечно", "Tabii ki", "табий", "Required_Phrases");
            insertPhrase(db, "С удовольствием", "Memnuniyetle", "Мемнуниетле", "Required_Phrases");
            insertPhrase(db, "Я тоже", "Ben de", "Бен де", "Required_Phrases");
            insertPhrase(db, "Нет", "Hayır", "Хаыр", "Required_Phrases");
            insertPhrase(db, "Нет, спасибо", "Hayır teşekkür ederim", "Хаыр, Тешекюр едерим", "Required_Phrases");
            insertPhrase(db, "К сожалению, не смогу", "Üzgünüm yapamam", "Юзгюнюм япамам", "Required_Phrases");
            insertPhrase(db, "Доброе утро", "Günaydın", "Гюнайдын", "Required_Phrases");
            insertPhrase(db, "Добрый день", "İyi günler", "Ийи гюнлер", "Required_Phrases");
            insertPhrase(db, "Добрый вечер", "İyi akşamlar", "Ийи акшамлар", "Required_Phrases");
            insertPhrase(db, "Привет", "Merhaba", "Мерхаба", "Required_Phrases");
            insertPhrase(db, "До свидания", "Güle güle", "Гюле гюле", "Required_Phrases");
            insertPhrase(db, "Пока", "Görüşürüz", "Гйорюшюрюз", "Required_Phrases");
            insertPhrase(db, "Спасибо", "Teşekkür ederim", "Тешекюр едерим", "Required_Phrases");
            insertPhrase(db, "Большое спасибо", "Çok teşekkür ederim", "Чок Тешекюр едерим", "Required_Phrases");
            insertPhrase(db, "Пожалуйста", "Lütfen / buyurun", "Лютфен / Буюрун", "Required_Phrases");
            insertPhrase(db, "Извините", "Özür dilerim", "Йозюр дилерим", "Required_Phrases");
            insertPhrase(db, "Простите!", "Affedersiniz", "Афедерсиниз ", "Required_Phrases");
            insertPhrase(db, "Сколько это стоит?", "Fiyatı ne kadar", "Фияты не кадар", "Required_Phrases");
            insertPhrase(db, "Кто?", "Kim", "Ким", "Required_Phrases");
            insertPhrase(db, "Что?", "Ne", "Не", "Required_Phrases");
            insertPhrase(db, "Как?", "Nasıl", "Насыл", "Required_Phrases");
            insertPhrase(db, "Какой?/Который?", "Hangi", "Ханги", "Required_Phrases");
            insertPhrase(db, "От себя/ от меня", "Benden", "Бенден", "Required_Phrases");
            insertPhrase(db, "Чей?", "Kimin", "Кимин", "Required_Phrases");
            insertPhrase(db, "Где?", "Nerede", "Нереде", "Required_Phrases");
            insertPhrase(db, "Куда?", "Nereye", "Нерейе", "Required_Phrases");
            insertPhrase(db, "Откуда?", "Nereden", "Нерден", "Required_Phrases");
            insertPhrase(db, "Когда?", "Ne zaman", "Не заман", "Required_Phrases");
            insertPhrase(db, "Почему?", "Neden", "Неден", "Required_Phrases");
            insertPhrase(db, "Зачем?", "Niye", "Нийе", "Required_Phrases");
            insertPhrase(db, "Сколько?", "Ne kadar", "Не кадар", "Required_Phrases");
            insertPhrase(db, "Что случилось?", "Ne oldu", "Не олду", "Required_Phrases");
            insertPhrase(db, "В чем дело?", "Sorun ne", "Сорун не", "Required_Phrases");
            insertPhrase(db, "Что Вы сказали?", "Ne dediniz ", "Не дединиз", "Required_Phrases");
            insertPhrase(db, "Что Вы хотите?", "Ne istiyorsunuz", "Не истерсиниз", "Required_Phrases");
            insertPhrase(db, "Помогите мне, пожалуйста", "Lütfen yardım eder misiniz ", "Лютфен Ярдым едер мисиниз", "Required_Phrases");
            insertPhrase(db, "Вы можете мне помочь?", "Yardım edebilir misiniz", "Ярдым едебилир мисиниз ", "Required_Phrases");
            insertPhrase(db, "У меня к Вам большая просьба", "Sizden ricam var", "Сизден риджам вар", "Required_Phrases");
            insertPhrase(db, "Подождите, минуточку, пожалуйста!", "Bir Dakka bekler misiniz lütfen", "Бир дакка беклер мисиниз лютфен", "Required_Phrases");


            //PersonalCommunication
            insertPhrase(db, "Я немного говорю по-турецки.", "Türkçe  az konuşuyorum", "Тюркче аз конушуйорум", "PersonalCommunication");
            insertPhrase(db, "Кто-нибудь говорит по-русски?", "Rusça konuşan var mı", "Русча конушан вар мы", "PersonalCommunication");
            insertPhrase(db, "Вы говорите по-английски?", "Ingilizce konuşuyor musunuz", "Ингилиздже конушуйор мусунуз", "PersonalCommunication");
            insertPhrase(db, "Я не говорю по-английски.", "Ingilizce konuşmuyorum", "Ингилиздже конушмуйорум", "PersonalCommunication");
            insertPhrase(db, "Я Вас/тебя понимаю.", "Ben sizi anlıyorum", "Бен сизи анлыйорум", "PersonalCommunication");
            insertPhrase(db, "Я Вас/тебя не понимаю.", "Sizi  anlayamıyorum", "Сизи анламыйорум", "PersonalCommunication");
            insertPhrase(db, "Простите? (когда не расслышали)", "Affedersiniz", "Афедерсиниз", "PersonalCommunication");
            insertPhrase(db, "Господин... (фамилия)", "Bay", "Бай", "PersonalCommunication");
            insertPhrase(db, "Госпожа... (фамилия)", "Bayan", "Баян", "PersonalCommunication");
            insertPhrase(db, "Как Вас зовут?", "Adınız ne", "Адыныз не", "PersonalCommunication");
            insertPhrase(db, "Как тебя зовут?", "Adın ne", "Адын не", "PersonalCommunication");
            insertPhrase(db, "Меня зовут...", "Benim adım…", "Беним адым…", "PersonalCommunication");
            insertPhrase(db, "Приятно познакомиться.", "Memnun oldum", "Мемнун олдум", "PersonalCommunication");

            //PersonalCommunication
            insertPhrase(db, "Закрыто", "KAPALI", "Капалы", "PersonalCommunication");
            insertPhrase(db, "Да", "Evet", "Евет", "PersonalCommunication");
            insertPhrase(db, "Хорошо", "Tamam", "Тамам", "PersonalCommunication");
            insertPhrase(db, "Конечно", "Tabii ki", "табий", "PersonalCommunication");
            insertPhrase(db, "С удовольствием", "Memnuniyetle", "Мемнуниетле", "PersonalCommunication");
            insertPhrase(db, "Я тоже", "Ben de", "Бен де", "PersonalCommunication");
            insertPhrase(db, "Нет", "Hayır", "Хаыр", "PersonalCommunication");
            insertPhrase(db, "Нет, спасибо", "Hayır teşekkür ederim", "Хаыр, Тешекюр едерим", "PersonalCommunication");
            insertPhrase(db, "К сожалению, не смогу", "Üzgünüm yapamam", "Юзгюнюм япамам", "PersonalCommunication");

            //AirplaneCommunication
            insertPhrase(db, "Аэропорт", "Havalimanı", "Хавалиманы", "AirplaneCommunication");
            insertPhrase(db, "Вход", "Giriş", "Гириш", "AirplaneCommunication");
            insertPhrase(db, "Выход", "Çıkış", "Чыкыш", "AirplaneCommunication");
            insertPhrase(db, "Регистрация", "Kayıt", "Кайыт", "AirplaneCommunication");
            insertPhrase(db, "Прием багажа", "Bagaj alımı", "Багаж алымы", "AirplaneCommunication");
            insertPhrase(db, "Выдача багажа", "Bagaj teslim yeri", "Багаж теслим йери", "AirplaneCommunication");
            insertPhrase(db, "Информация, справочное бюро", "Bilgi, danışma", "Билги , данышма", "AirplaneCommunication");
            insertPhrase(db, "Паспортный контроль", "Pasaport kontrol", "Пасапорт контрол", "AirplaneCommunication");
            insertPhrase(db, "Ваш паспорт, пожалуйста!", "Pasaportunuz lütfen", "Пасапортунуз лютфен", "AirplaneCommunication");
            insertPhrase(db, "Ваш билет, пожалуйста!", "Biletiniz lütfen", "Билетиниз лютфен", "AirplaneCommunication");
            insertPhrase(db, "Таможня", "Gümrük", "Гюмрюк", "AirplaneCommunication");
            insertPhrase(db, "У вас багаж сверх нормы", "Bagajınız fazla", "Багажыныз фазла", "AirplaneCommunication");
            insertPhrase(db, "Откройте, пожалуйста... (этот чемодан, эту сумку)", "Açabilirmisiniz lütfen... (Valizi,Çantayı)", "Ачармысыныз лютфен... (Вализи, Чантауы)", "AirplaneCommunication");
            insertPhrase(db, "Вы должны заплатить пошлину", "Vergi ödemeniz gerek", "Верги йодемениз герек", "AirplaneCommunication");
            insertPhrase(db, "Обмен валюты", "Döviz", "Дйовиз", "AirplaneCommunication");

            //HotelChat
            insertPhrase(db, "Отель", "Otel", "отел", "HotelChat");
            insertPhrase(db, "Не могли бы Вы поменять гостям комнату?", "Misafirlerin odasını değiştirebilir misiniz lütfen", "Мисафирлерин одасыны дегиштиребилир мисиниз лютфен", "HotelChat");
            insertPhrase(db, "Гостям не нравится номер", "Misafirler odasını beğenmedi", "Мисафирлер одасыны бейенмеди", "HotelChat");
            insertPhrase(db, "Гостям не нравится вид из окна", "Misafirler manzarayı beğenmiyor", "Мисафирлер манзарайы бегенмийор", "HotelChat");
            insertPhrase(db, "У туристов очень маленький номер", "Misafirlerin odası küçük", "Мисафирлерин одасы кючюк", "HotelChat");
            insertPhrase(db, "У туристов в номере вид на кондиционер соседнего отеля", "Yan taraf otelin kliması görünüyor", "Ян тараф отелин климасы гйорюйор", "HotelChat");
            insertPhrase(db, "У туристов в номере вид на дорогу", "Misafirlerin odası yola bakıyor", "Мисафирлерин одасы йола бакыйор", "HotelChat");
            insertPhrase(db, "Туристы хотят номер с видом на море", "Turistler Deniz manzaralı oda istiyor", "Туристлер дениз манзаралы ода истийор", "HotelChat");
            insertPhrase(db, "Туристы хотят номер с видом в сад", "Turistler bahçe manzaralı oda istiyor", "Туристлер бахче манзаралы ода истийор", "HotelChat");
            insertPhrase(db, "Туристы хотят номер подальше от анимации", "Turistler аnimasyondan uzak bir oda istiyorlar", "Туристлер анимасйондан изак ода истийор", "HotelChat");
            insertPhrase(db, "Туристам необходимо принести в номер детскую кровать", "Turistlerin odasına çocuk yatak gerek", "Туристлере чоджук ятагы герек", "HotelChat");
            insertPhrase(db, "Туристам необходимо принести в номер дополнительный комплект постельного белья", "Turistlerin odasına ek çarşaf getirilmesi gerek", "Туристлерин одасына ек Чаршаф гетирилмеси герек", "HotelChat");
            insertPhrase(db, "Туристы хотят номер поближе к морю", "Turistler denize yakın oda istiyor", "Туристлер Денизе  якын ода истийор", "HotelChat");
            insertPhrase(db, "Туристы хотят номер на первом этаже", "Turistelr birinci katta oda istiyor", "Туристлер биринджи катта  ода истийор", "HotelChat");
            insertPhrase(db, "Туристы хотят номер на втором этаже", "Turistelr ikinci katta oda istiyor", "Туристлер икинджи катта ода истийор", "HotelChat");
            insertPhrase(db, "Туристы хотят вызвать в номер носильщика", "Turistler belboy istiyor", "Туристлер белбой истийор", "HotelChat");
            insertPhrase(db, "Туристы хотят, чтобы носильщик отнес их багаж в номер", "Turistler bagajını odalarına  çıkartılmasını istiyor", "Туристлер  багажларыны одаларына  чыкартылмасыны истийор", "HotelChat");
            insertPhrase(db, "Туристы хотят, чтобы носильщик принес их багаж из номера", "Turistler  bagajlarını odalarından çıkartılmasını istiyor", "Туристлер  багажларыны одаларындан  чыкартылмасыны  истийоp", "HotelChat");
            insertPhrase(db, "Какие услуги в отеле бесплатные для туристов", "Otelin ücretsiz hizmetleri hangisi", "Отелин юджретсиз хизметлери хангиси", "HotelChat");
            insertPhrase(db, "Где туристы могут взять бесплатное мороженое", "Ücretsiz dondurma nerden alabilirler", "Туристлер юджретсиз дондурма нерден алабилирлер", "HotelChat");
            insertPhrase(db, "Туристам необходимо принести в номер дополнительное полотенце", "Turistlerin odasına ek havlu gerek", "Туристлерин одасына ек хавлу герек", "HotelChat");
            insertPhrase(db, "Двухместный номер", "İki kişilik oda ", "Ики кишилик ода", "HotelChat");
            insertPhrase(db, "Трехместный номер", "Üç kişilik oda", "Юч кишилик ода", "HotelChat");
            insertPhrase(db, "Одноместный номер", "Tek kişilik oda ", "Тек кишилик ода", "HotelChat");
            insertPhrase(db, "В номере № не работает кондиционер", "Odada klima çalışmıyor", "Одада клима чалышмыйор", "HotelChat");
            insertPhrase(db, "В номере № не работает минибар", "Odada minibar çalışmıyor", "Одада минибар чалышмыйор", "HotelChat");
            insertPhrase(db, "В номере № не убрали", "Oda temizlenmemiş", "Ода темизленмемиш", "HotelChat");
            insertPhrase(db, "В номере № нет полотенец", "Odada havlu yok", "Одада хавлу йок", "HotelChat");
            insertPhrase(db, "В номере № нет шампуня, геля, мыла", "Odada şampuan,jöle,sabun yok", "Одада шамруан,жоле,сабун уок", "HotelChat");
            insertPhrase(db, "Туристы забыли вещи в ресторане", "Turistler restoranda eşyalarını unutmuşlar", "Туристлер ресторанда ещяларыны унутмушлар", "HotelChat");


            //ShoppingCommunication
            insertPhrase(db, "Не могли бы Вы поменять гостям комнату?", "Misafirlerin odasını değiştirebilir misiniz lütfen", "Мисафирлерин одасыны дегиштиребилир мисиниз лютфен", "ShoppingCommunication");
            insertPhrase(db, "Гостям не нравится номер", "Misafirler odasını beğenmedi", "Мисафирлер одасыны бейенмеди", "ShoppingCommunication");
            insertPhrase(db, "Гостям не нравится вид из окна", "Misafirler manzarayı beğenmiyor", "Мисафирлер манзарайы бегенмийор", "ShoppingCommunication");
            insertPhrase(db, "У туристов очень маленький номер", "Misafirlerin odası küçük", "Мисафирлерин одасы кючюк", "ShoppingCommunication");
            insertPhrase(db, "У туристов в номере вид на кондиционер соседнего отеля", "Yan taraf otelin kliması görünüyor", "Ян тараф отелин климасы гйорюйор", "ShoppingCommunication");
            insertPhrase(db, "У туристов в номере вид на дорогу", "Misafirlerin odası yola bakıyor", "Мисафирлерин одасы йола бакыйор", "ShoppingCommunication");
            insertPhrase(db, "Туристы хотят номер с видом на море", "Turistler Deniz manzaralı oda istiyor", "Туристлер дениз манзаралы ода истийор", "ShoppingCommunication");
            insertPhrase(db, "Туристы хотят номер с видом в сад", "Turistler bahçe manzaralı oda istiyor", "Туристлер бахче манзаралы ода истийор", "ShoppingCommunication");
            insertPhrase(db, "Туристы хотят номер подальше от анимации", "Turistler аnimasyondan uzak bir oda istiyorlar", "Туристлер анимасйондан изак ода истийор", "ShoppingCommunication");
            insertPhrase(db, "Туристам необходимо принести в номер детскую кровать", "Turistlerin odasına çocuk yatak gerek", "Туристлере чоджук ятагы герек", "ShoppingCommunication");
            insertPhrase(db, "Туристам необходимо принести в номер дополнительный комплект постельного белья", "Turistlerin odasına ek çarşaf getirilmesi gerek", "Туристлерин одасына ек Чаршаф гетирилмеси герек", "ShoppingCommunication");
            insertPhrase(db, "Туристы хотят номер поближе к морю", "Turistler denize yakın oda istiyor", "Туристлер Денизе  якын ода истийор", "ShoppingCommunication");
            insertPhrase(db, "Туристы хотят номер на первом этаже", "Turistelr birinci katta oda istiyor", "Туристлер биринджи катта  ода истийор", "ShoppingCommunication");
            insertPhrase(db, "Туристы хотят номер на втором этаже", "Turistelr ikinci katta oda istiyor", "Туристлер икинджи катта ода истийор", "ShoppingCommunication");
            insertPhrase(db, "Туристы хотят вызвать в номер носильщика", "Turistler belboy istiyor", "Туристлер белбой истийор", "ShoppingCommunication");
            insertPhrase(db, "Туристы хотят, чтобы носильщик отнес их багаж в номер", "Turistler bagajını odalarına  çıkartılmasını istiyor", "Туристлер  багажларыны одаларына  чыкартылмасыны истийор", "ShoppingCommunication");
            insertPhrase(db, "Туристы хотят, чтобы носильщик принес их багаж из номера", "Turistler  bagajlarını odalarından çıkartılmasını istiyor", "Туристлер  багажларыны одаларындан  чыкартылмасыны  истийоp", "ShoppingCommunication");
            insertPhrase(db, "Какие услуги в отеле бесплатные для туристов", "Otelin ücretsiz hizmetleri hangisi", "Отелин юджретсиз хизметлери хангиси", "ShoppingCommunication");
            insertPhrase(db, "Где туристы могут взять бесплатное мороженое", "Ücretsiz dondurma nerden alabilirler", "Туристлер юджретсиз дондурма нерден алабилирлер", "ShoppingCommunication");
            insertPhrase(db, "Туристам необходимо принести в номер дополнительное полотенце", "Turistlerin odasına ek havlu gerek", "Туристлерин одасына ек хавлу герек", "ShoppingCommunication");
            insertPhrase(db, "Двухместный номер", "İki kişilik oda ", "Ики кишилик ода", "ShoppingCommunication");
            insertPhrase(db, "Трехместный номер", "Üç kişilik oda", "Юч кишилик ода", "ShoppingCommunication");
            insertPhrase(db, "Одноместный номер", "Tek kişilik oda ", "Тек кишилик ода", "ShoppingCommunication");
            insertPhrase(db, "В номере № не работает кондиционер", "Odada klima çalışmıyor", "Одада клима чалышмыйор", "ShoppingCommunication");
            insertPhrase(db, "В номере № не работает минибар", "Odada minibar çalışmıyor", "Одада минибар чалышмыйор", "ShoppingCommunication");
            insertPhrase(db, "В номере № не убрали", "Oda temizlenmemiş", "Ода темизленмемиш", "ShoppingCommunication");
            insertPhrase(db, "В номере № нет полотенец", "Odada havlu yok", "Одада хавлу йок", "ShoppingCommunication");
            insertPhrase(db, "В номере № нет шампуня, геля, мыла", "Odada şampuan,jöle,sabun yok", "Одада шамруан,жоле,сабун уок", "ShoppingCommunication");
            insertPhrase(db, "Туристы забыли вещи в ресторане", "Turistler restoranda eşyalarını unutmuşlar ", "Туристлер ресторанда ещяларыны унутмушлар", "ShoppingCommunication");

            //CommunicationCity
            insertPhrase(db, "Извините, не могли бы Вы мне помочь?", "Afedersiniz  yardım  edebilir misniz lütfen?", "Афедерсиниз бана ярдым едебилир мисиниз?", "CommunicationCity");
            insertPhrase(db, "Извините, можно Вас спросить?", "Afedersiniz size sorabili rmiyim?", "Афедерсиниз сизе сорабилир миим?", "CommunicationCity");
            insertPhrase(db, "Где находится... ?", "Nerede", "Нереде...", "CommunicationCity");
            insertPhrase(db, "Как мне добраться до... ?", "...Nasıl gidebilirim?", "...Насыл гидебилирим?", "CommunicationCity");
            insertPhrase(db, "Я ищу...", "...Ben arıyorum", "...Бен арыйорум", "CommunicationCity");
            insertPhrase(db, "Я могу дойти пешком?", "Yürüyerek gidebilir miyim?", "Юрюйерек гидебилир миим ?", "CommunicationCity");
            insertPhrase(db, "Идите прямо.", "Düz gidiniz", "Дюз гидиниз", "CommunicationCity");
            insertPhrase(db, "В этом направлении.", "Bu tarafa", "Бу тарафа", "CommunicationCity");
            insertPhrase(db, "Поверните направо/налево.", "Sağa / sola dönünüz", "Cаа/Cола Дйонюниз ", "CommunicationCity");
            insertPhrase(db, "Где я могу позвонить?", "Arama nerden yapabilirim", "Арама нерден япабилирим", "CommunicationCity");
            insertPhrase(db, "Где здесь поблизости (таксофон, карточный таксофон, монетный таксофон) ?", "Buralarda nerede (ödemeli telefon, kart ödemeli telefon, kontörlü  telefon) ?", "Бураларда нереде (Йодемели телефон, Карт йодемели телефон, Koнтёрлю  телефон) ?", "CommunicationCity");
            insertPhrase(db, "Сколько стоит минута разговора?", "Bir Dakika ücreti ne kadar ", "Бир дакка юджрети некадар", "CommunicationCity");
            insertPhrase(db, "Мой номер телефона...", "Benim telefon numaram...", "Беним  телефон нумарам...", "CommunicationCity");
            insertPhrase(db, "Где я могу купить... ?", "Turistelr ikinci katta oda istiyor", "Туристлер икинджи катта ода истийор", "CommunicationCity");
            insertPhrase(db, "Где находится... ?", "....Nerede bulunuyor", "...Нерeде булунуйор", "CommunicationCity");
            insertPhrase(db, "Магазин одежды", "Giysi mağazası", "Гийиси магазасы", "CommunicationCity");
            insertPhrase(db, "Магазин сувениров", "Hediye eşya mağazası", "Хедие ешя магазасы", "CommunicationCity");
            insertPhrase(db, "Магазин фототоваров", "Fotoğraf  dükkanı ", "Фотораф дюканы", "CommunicationCity");
            insertPhrase(db, "Обувной магазин", "Ayakkabı mağazası", "Аяккабы магазасы", "CommunicationCity");
            insertPhrase(db, "Туристам необходимо принести в номер дополнительное полотенце", "Turistlerin odasına ek havlu gerek", "Туристлерин одасына ек хавлу герек", "CommunicationCity");
            insertPhrase(db, "Продуктовый магазин", "Bakkal", "Баккал", "CommunicationCity");
            insertPhrase(db, "Рынок", "Pazar", "Пазар", "CommunicationCity");
            insertPhrase(db, "Супермаркет", "Süper market", "Супермаркет", "CommunicationCity");
            insertPhrase(db, "Универмаг", "Alış veriş merkezi", "Алыш вериш меркези", "CommunicationCity");
            insertPhrase(db, "Цветочный магазин", "Çiçek dükkanı", "Чичек дюкяны", "CommunicationCity");

            //Num_Fragment начать
            insertPhrase(db, "0", "Sıfır", "Сыфыр", "Num_Fragment");
            insertPhrase(db, "1", "Bir", "Бир", "Num_Fragment");
            insertPhrase(db, "2", "Iki", "Ики", "Num_Fragment");
            insertPhrase(db, "3", "Üç", "Юч", "Num_Fragment");
            insertPhrase(db, "4", "Dört", "Дйорт", "Num_Fragment");
            insertPhrase(db, "5", "Beş", "Беш", "Num_Fragment");
            insertPhrase(db, "6", "Altı", "Алты", "Num_Fragment");
            insertPhrase(db, "7", "Yedi", "Йеди", "Num_Fragment");
            insertPhrase(db, "8", "Sekiz", "Секиз", "Num_Fragment");
            insertPhrase(db, "9", "Dokuz", "Докуз", "Num_Fragment");
            insertPhrase(db, "10", "On", "Он", "Num_Fragment");
            insertPhrase(db, "11", "On bir", "Он бир", "Num_Fragment");
            insertPhrase(db, "12", "On iki", "Он ики", "Num_Fragment");
            insertPhrase(db, "13", "On üç", "Он юч", "Num_Fragment");
            insertPhrase(db, "14", "On dört", "Он дйорт", "Num_Fragment");
            insertPhrase(db, "15", "On beş", "Он беш", "Num_Fragment");
            insertPhrase(db, "16", "On altı", "Он алты", "Num_Fragment");
            insertPhrase(db, "17", "On yedi", "Он йеди", "Num_Fragment");
            insertPhrase(db, "18", "On sekiz", "Он сексз", "Num_Fragment");
            insertPhrase(db, "19", "On dokuz", "Он докуз", "Num_Fragment");
            insertPhrase(db, "20", "Yirmi", "Йирми", "Num_Fragment");
            insertPhrase(db, "30", "Otuz", "Отуз", "Num_Fragment");
            insertPhrase(db, "40", "Kırk", "Кырк", "Num_Fragment");
            insertPhrase(db, "50", "Elli", "Елли", "Num_Fragment");
            insertPhrase(db, "60", "Altmış", "Алтмыш", "Num_Fragment");
            insertPhrase(db, "70", "Yetmiş", "Йетмиш", "Num_Fragment");
            insertPhrase(db, "80 ", "Seksen", "Сексен", "Num_Fragment");
            insertPhrase(db, "90", "Doksan", "Доксан", "Num_Fragment");
            insertPhrase(db, "100", "Yüz", "Юз", "Num_Fragment");
            insertPhrase(db, "200", "İki yüz", "Ики Юз", "Num_Fragment");
            insertPhrase(db, "300", "Üç yüz", "Юч Юз", "Num_Fragment");
            insertPhrase(db, "400", "Dört yüz", "Дйорт Юз", "Num_Fragment");
            insertPhrase(db, "500", "Beş yüz", "Беш Юз", "Num_Fragment");
            insertPhrase(db, "600", "Altı yüz", "Алты Юз", "Num_Fragment");
            insertPhrase(db, "700", "Yedi yüz", "Йеди Юз", "Num_Fragment");
            insertPhrase(db, "800", "Sekiz yüz", "Секиз Юз", "Num_Fragment");
            insertPhrase(db, "900", "Dokuz yüz", "Докуз Юз", "Num_Fragment");
            insertPhrase(db, "1000", "Bin", "Бин", "Num_Fragment");
            insertPhrase(db, "2000", "İki bin", "Ики бин", "Num_Fragment");
            insertPhrase(db, "3000", "Üç bin", "Юч Бин", "Num_Fragment");
            insertPhrase(db, "4000", "Dört bin", "Дйорт Бин", "Num_Fragment");
            insertPhrase(db, "5000", "Beş bin", "Беш Бин", "Num_Fragment");
            insertPhrase(db, "6000", "Altı bin", "Алты Бин", "Num_Fragment");
            insertPhrase(db, "7000", "Yedi bin", "Йеди Бин", "Num_Fragment");
            insertPhrase(db, "8000", "Sekiz bin", "Секиз Бин", "Num_Fragment");
            insertPhrase(db, "9000", "Dokuz bin", "Докуз Бин", "Num_Fragment");
            insertPhrase(db, "1 000 000", "Bir milyon", "Бир Милйон", "Num_Fragment");
            insertPhrase(db, "1 000 000 000", "Bir milyar", "Бир Миляр", "Num_Fragment");

            insertPhrase(db, "Понедельник", "Pazartesi", "Пазартеси", "Num_Fragment");
            insertPhrase(db, "Вторник", "Salı", "Салы", "Num_Fragment");
            insertPhrase(db, "Среда", "Çarşamba", "Чаршамба", "Num_Fragment");
            insertPhrase(db, "Четверг", "Perşembe", "Першембе", "Num_Fragment");
            insertPhrase(db, "Пятница", "Cuma", "Джума", "Num_Fragment");
            insertPhrase(db, "Суббота", "Cumartesi", "Джумартеси", "Num_Fragment");
            insertPhrase(db, "Воскресенье", "Pazar", "Пазар", "Num_Fragment");

            insertPhrase(db, "Январь", "Ocak", "Оджак", "Num_Fragment");
            insertPhrase(db, "Февраль", "Şubat ", "Шубат", "Num_Fragment");
            insertPhrase(db, "Март ", "Mart", "Март", "Num_Fragment");
            insertPhrase(db, "Апрель ", "Nisan", "Нисан", "Num_Fragment");
            insertPhrase(db, "Май", "Mayıs", "Майыс", "Num_Fragment");
            insertPhrase(db, "Июнь", "Haziran", "Хазиран", "Num_Fragment");
            insertPhrase(db, "Июль", "Temmuz", "Теммуз", "Num_Fragment");
            insertPhrase(db, "Август", "Ağustos", "Аугустос", "Num_Fragment");
            insertPhrase(db, "Сентябрь", "Eylül", "Ейлюл", "Num_Fragment");
            insertPhrase(db, "Октябрь", "Ekim ", "Еким", "Num_Fragment");
            insertPhrase(db, "Ноябрь", "Kasım", "Ласым", "Num_Fragment");
            insertPhrase(db, "Декабрь", "Aralık", "аралык", "Num_Fragment");

            //придумать про авто и автоьбусы
            insertPhrase(db, "Ведет ли эта дорога к... ?", "Bu yol...", "Бу йол ...", "Auto_chat");
            insertPhrase(db, "Здесь можно припарковать машину?", "Buraya park edebirsiniz", "Бурая парк едебилирсиниз", "Auto_chat");
            insertPhrase(db, "Как долго здесь разрешена парковка?", "Park süresi ne kadar ", "Парк сюреси не кадар", "Auto_chat");
            insertPhrase(db, "Здесь поблизости есть автостоянка?", "Yakında park yeri mevcut mı", "Якында парк йери мевджут", "Auto_chat");
            insertPhrase(db, "Поверните направо, пожалуйста", "Sağa dönün lütfen", "Сага дйонюн лютфен", "Auto_chat");
            insertPhrase(db, "Поверните налево, пожалуйста", "Sola dönün lütfen", "Сола дйонюн лютфен", "Auto_chat");
            insertPhrase(db, "Необходимо ехать прямо", "Düz gitmeniz gerek", "Дюз гитмек герек", "Auto_chat");
            insertPhrase(db, "Необходимо ехать назад", "Geri gitmeniz gerek", "Гери гитмек герек", "Auto_chat");
            insertPhrase(db, "Пожалуйста, остановитесь здесь!", "Lütfen durunuz ", "Лютфен дурунуз", "Auto_chat");
            insertPhrase(db, "Пожалуйста, подождите!", "Lütfen bekleyiniz", "Лютфен беклейиниз", "Auto_chat");
            insertPhrase(db, "Сколько минут остановка?", "Kaç dakka kalınacak/kalacağız", "Кач дакка калынаджак/калыджаз", "Auto_chat");
            insertPhrase(db, "Когда мы приедем в …?", "Ne zaman… oluruz?", "Не заман…. Олуруз?", "Auto_chat");
            insertPhrase(db, "Включите кондиционер, пожалуйста", "Klimayı açar mısınız lütfen", "Климайы ачар мысыныз лютфен", "Auto_chat");
            insertPhrase(db, "Выключите кондиционер, пожалуйста", "Klimayı kapatır mısnız lütfen", "Климайы капатыр мысыныз лютфен", "Auto_chat");
            insertPhrase(db, "Включите свет, пожалуйста", "Işıkları açar mısınız", "Ышыклары  ачар мысыныз ", "Auto_chat");
            insertPhrase(db, "Выключите свет, пожалуйста", "Işıkları kapatır mısınız ", "Ышыклары капатыр мысыныз ", "Auto_chat");
            insertPhrase(db, "Откройте переднюю дверь, пожалуйста", "Ön kapıyı açar mısınız", "Йон капыйы ачар мысыныз ", "Auto_chat");
            insertPhrase(db, "Закройте переднюю дверь, пожалуйста", "Ön kapıyı kapatır mısınız lütfen", "Йон капыйы капатыр мысыныз лютфен", "Auto_chat");
            insertPhrase(db, "Откройте заднюю дверь, пожалуйста", "Arak kapıyı açar mısınz", "Арка  капыйы ачар мысыныз ", "Auto_chat");
            insertPhrase(db, "Закройте заднюю дверь, пожалуйста", "Arka kapıyı kapatır mısınız lütfen", "Арка  капыйы капатыр мысыныз лютфен", "Auto_chat");
            insertPhrase(db, "В автобусе очень холодно", "Otobüste çok soğuk", "Отобюсте чок согук", "Auto_chat");
            insertPhrase(db, "В автобусе очень жарко", "Otobüste çok sıcak", "Отобюсте чок сыджак", "Auto_chat");
            insertPhrase(db, "Все туристы собрались. Можем ехать", "Tüm turistler burada, gidebiliriz", "Тюм туристлер бурда, гидебилириз", "Auto_chat");
            insertPhrase(db, "Этот отель по программе первый", "Programa göre bu ilk otel", "Програма гёре бу ильк отель", "Auto_chat");
            insertPhrase(db, "Затем едем в отель...", "Sonraki... Otele gidiyoruz", "Сонраки... отеле гидийоруз…", "Auto_chat");
            insertPhrase(db, "Есть ли в автобусе мини-бар", "Otobüste minibar var mı?", "Отобюсте минибар вар мы?", "Auto_chat");
            insertPhrase(db, "Сколько стоит (вода, кола, пиво)", "Fiyatı ne kadar (su, cola, bira)", "Фияты не кадар  (су, кола, бира)", "Auto_chat");
            insertPhrase(db, "Нам нужно сделать остановку на 5 минут", "Beş Dakika mola yapmamız gerek", "Беш дакка мола япмамыз герек", "Auto_chat");
            insertPhrase(db, "Откройте отделение для багажа, пожалуйста", "Bagaj bölümünü açar mısınız lütfen", "Багаж бйолюмюню ачармысыныз лютфен", "Auto_chat");
            insertPhrase(db, "Мы оставили туриста в аэропорту, надо вернуться за ним", "Havalimanında kalan turist var, geri dönmemiz gerek", "Хавалиманында калан турист вар, гери дйонмемиз герек", "Auto_chat");
            insertPhrase(db, "Сбавьте скорость, пожалуйста", "Hızı düşürebilir misiniz lütfen", "Хызы дюшюребилир мисиниз лютфен", "Auto_chat");
            insertPhrase(db, "Не надо делать остановку. Мы опаздываем в аэропорт", "Mola yapılmaması gerek,havalimanına geç kalıyoruz", "Мола япылмасы герек йок,хавалиманына геч калыёруз", "Auto_chat");
            insertPhrase(db, "Вы знаете дорогу до ...", " ...yolu biliyor musunuz?", " ...Йолу  билийор мусунуз?", "Auto_chat");
        }
        //если больше то....
        if (oldVersion < 2) {
            //Код добавления нового столбца
        }
    }

    public ArrayList<Phrase> getCategotyFavorite_Phrases() {
        ArrayList<Phrase> arrayList_SQL = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PHRASES_SQL WHERE FAVORITE = '1';", null);
        while (cursor.moveToNext()) {
            int ID_view_array = cursor.getInt(0);
            String RUS_Phrases_view_array = cursor.getString(1);
            String TUR_Phrases_view_array = cursor.getString(2);
            String Transcription_view_array = cursor.getString(3);
            String Categoty_view_array = cursor.getString(4);
            boolean Favorit_view_array = (cursor.getInt(5) == 1);
            Phrase phrase = new Phrase(ID_view_array, RUS_Phrases_view_array, TUR_Phrases_view_array, Transcription_view_array, Categoty_view_array, Favorit_view_array);
            arrayList_SQL.add(phrase);
        }
        cursor.close();
        db.close();
        return arrayList_SQL;
    }

    public ArrayList<Phrase> getCategotyRequired() {
        ArrayList<Phrase> arrayList_SQL = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PHRASES_SQL WHERE Categoty = 'Required_Phrases';", null);
        while (cursor.moveToNext()) {
            int ID_view_array = cursor.getInt(0);
            String RUS_Phrases_view_array = cursor.getString(1);
            String TUR_Phrases_view_array = cursor.getString(2);
            String Transcription_view_array = cursor.getString(3);
            String Categoty_view_array = cursor.getString(4);
            boolean Favorit_view_array = (cursor.getInt(5) == 1);
            Phrase phrase = new Phrase(ID_view_array, RUS_Phrases_view_array, TUR_Phrases_view_array, Transcription_view_array, Categoty_view_array, Favorit_view_array);
            arrayList_SQL.add(phrase);
        }
        cursor.close();
        db.close();
        return arrayList_SQL;
    }

    public ArrayList<Phrase> getCategotyPersonalCommunication() {
        ArrayList<Phrase> arrayList_SQL = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PHRASES_SQL WHERE Categoty = 'PersonalCommunication';", null);
        while (cursor.moveToNext()) {
            int ID_view_array = cursor.getInt(0);
            String RUS_Phrases_view_array = cursor.getString(1);
            String TUR_Phrases_view_array = cursor.getString(2);
            String Transcription_view_array = cursor.getString(3);
            String Categoty_view_array = cursor.getString(4);
            boolean Favorit_view_array = (cursor.getInt(5) == 1);
            Phrase phrase = new Phrase(ID_view_array, RUS_Phrases_view_array, TUR_Phrases_view_array, Transcription_view_array, Categoty_view_array, Favorit_view_array);
            arrayList_SQL.add(phrase);
        }
        cursor.close();
        db.close();
        return arrayList_SQL;
    }

    public ArrayList<Phrase> getCategotyAirplaneCommunication() {
        ArrayList<Phrase> arrayList_SQL = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PHRASES_SQL WHERE Categoty = 'AirplaneCommunication';", null);
        while (cursor.moveToNext()) {
            int ID_view_array = cursor.getInt(0);
            String RUS_Phrases_view_array = cursor.getString(1);
            String TUR_Phrases_view_array = cursor.getString(2);
            String Transcription_view_array = cursor.getString(3);
            String Categoty_view_array = cursor.getString(4);
            boolean Favorit_view_array = (cursor.getInt(5) == 1);
            Phrase phrase = new Phrase(ID_view_array, RUS_Phrases_view_array, TUR_Phrases_view_array, Transcription_view_array, Categoty_view_array, Favorit_view_array);
            arrayList_SQL.add(phrase);
        }
        cursor.close();
        db.close();
        return arrayList_SQL;
    }

    public ArrayList<Phrase> getCategotyHotelChat() {
        ArrayList<Phrase> arrayList_SQL = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PHRASES_SQL WHERE Categoty = 'HotelChat';", null);
        while (cursor.moveToNext()) {
            int ID_view_array = cursor.getInt(0);
            String RUS_Phrases_view_array = cursor.getString(1);
            String TUR_Phrases_view_array = cursor.getString(2);
            String Transcription_view_array = cursor.getString(3);
            String Categoty_view_array = cursor.getString(4);
            boolean Favorit_view_array = (cursor.getInt(5) == 1);
            Phrase phrase = new Phrase(ID_view_array, RUS_Phrases_view_array, TUR_Phrases_view_array, Transcription_view_array, Categoty_view_array, Favorit_view_array);
            arrayList_SQL.add(phrase);
        }
        cursor.close();
        db.close();
        return arrayList_SQL;
    }

    public ArrayList<Phrase> getCategotyShoppingCommunication() {
        ArrayList<Phrase> arrayList_SQL = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PHRASES_SQL WHERE Categoty = 'ShoppingCommunication';", null);
        while (cursor.moveToNext()) {
            int ID_view_array = cursor.getInt(0);
            String RUS_Phrases_view_array = cursor.getString(1);
            String TUR_Phrases_view_array = cursor.getString(2);
            String Transcription_view_array = cursor.getString(3);
            String Categoty_view_array = cursor.getString(4);
            boolean Favorit_view_array = (cursor.getInt(5) == 1);
            Phrase phrase = new Phrase(ID_view_array, RUS_Phrases_view_array, TUR_Phrases_view_array, Transcription_view_array, Categoty_view_array, Favorit_view_array);
            arrayList_SQL.add(phrase);
        }
        cursor.close();
        db.close();
        return arrayList_SQL;
    }

    public ArrayList<Phrase> getCategotyCommunicationCity() {
        ArrayList<Phrase> arrayList_SQL = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PHRASES_SQL WHERE Categoty = 'CommunicationCity';", null);
        while (cursor.moveToNext()) {
            int ID_view_array = cursor.getInt(0);
            String RUS_Phrases_view_array = cursor.getString(1);
            String TUR_Phrases_view_array = cursor.getString(2);
            String Transcription_view_array = cursor.getString(3);
            String Categoty_view_array = cursor.getString(4);
            boolean Favorit_view_array = (cursor.getInt(5) == 1);
            Phrase phrase = new Phrase(ID_view_array, RUS_Phrases_view_array, TUR_Phrases_view_array, Transcription_view_array, Categoty_view_array, Favorit_view_array);
            arrayList_SQL.add(phrase);
        }
        cursor.close();
        db.close();
        return arrayList_SQL;
    }

    public ArrayList<Phrase> getCategotyNum_Fragment() {
        ArrayList<Phrase> arrayList_SQL = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PHRASES_SQL WHERE Categoty = 'Num_Fragment';", null);
        while (cursor.moveToNext()) {
            int ID_view_array = cursor.getInt(0);
            String RUS_Phrases_view_array = cursor.getString(1);
            String TUR_Phrases_view_array = cursor.getString(2);
            String Transcription_view_array = cursor.getString(3);
            String Categoty_view_array = cursor.getString(4);
            boolean Favorit_view_array = (cursor.getInt(5) == 1);
            Phrase phrase = new Phrase(ID_view_array, RUS_Phrases_view_array, TUR_Phrases_view_array, Transcription_view_array, Categoty_view_array, Favorit_view_array);
            arrayList_SQL.add(phrase);
        }
        cursor.close();
        db.close();
        return arrayList_SQL;
    }

    public ArrayList<Phrase> getCategotyAuto_chat() {
        ArrayList<Phrase> arrayList_SQL = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PHRASES_SQL WHERE Categoty = 'Auto_chat';", null);
        while (cursor.moveToNext()) {
            int ID_view_array = cursor.getInt(0);
            String RUS_Phrases_view_array = cursor.getString(1);
            String TUR_Phrases_view_array = cursor.getString(2);
            String Transcription_view_array = cursor.getString(3);
            String Categoty_view_array = cursor.getString(4);
            boolean Favorit_view_array = (cursor.getInt(5) == 1);
            Phrase phrase = new Phrase(ID_view_array, RUS_Phrases_view_array, TUR_Phrases_view_array, Transcription_view_array, Categoty_view_array, Favorit_view_array);
            arrayList_SQL.add(phrase);
        }
        cursor.close();
        db.close();
        return arrayList_SQL;
    }

}
