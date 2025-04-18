public class UserApp {

    public static void main(String[] args) {
        User user = new User();
        user.setMail("mail@mail.com");
        if (user.getMail().isPresent()) {
            System.out.println("Die E-Mail-Adresse des Benutzers ist: " + user.getMail().get());
        } else {
            System.out.println("Keine E-Mail vorhanden.");
        }
    }

}
