import java.util.Optional;

public class User {

    private String username;
    private String mail;

    public Optional<String> getMail() {
        return Optional.ofNullable(mail);
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
