public final class UserHolder {

    //private User user;

    private static UserHolder instance;
    private static String haslo;
    private static String login;

    public UserHolder(String login, String haslo) {
        this.haslo = haslo;
        this.login = login;

    }

    public static UserHolder getInstance(){
        return instance;
    }

    public static String getHaslo() {
        return haslo;
    }

    public static String getLogin() {
        return login;
    }

    public void setUserHolder(UserHolder userHolder){
        this.instance = userHolder;
    }
}
