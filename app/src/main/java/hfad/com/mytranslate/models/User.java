package hfad.com.mytranslate.models;

public class User {


    private int id;
    private String email, name, surname, login;

    //Конструктор для метода getUser в классе  SharedPrefManager
    public User(int id, String email, String name, String surname, String login) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.login = login;
    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

}
