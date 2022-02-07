public final class UserHolder {

    /**
     * singleton class to pass login and password data between scenes when needed
     * @author Martyna Grygiel
     * @version 1.0
     * @since 04.02.2022
     */
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
