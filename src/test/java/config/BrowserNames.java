package config;

public enum BrowserNames {
    FIREFOX("firefox"),
    IE("ie"),
    CHROME("chrome");

    private final String browserName;

    BrowserNames(String browserName) {
            this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }
}
