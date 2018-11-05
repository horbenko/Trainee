package config;

public class SiteConfig {

    public static class Config {
        private static final String HOME_URL = "https://smt.ua/";
        private static final String USERNAME = "my_test_mail@meta.ua";
        private static final String PASSWORD = "mytestpassword";

        static String getHomeUrl() {
            return HOME_URL;
        }

        public static String getUsername() {
            return USERNAME;
        }

        public static String getPassword() {
            return PASSWORD;
        }
    }

}
