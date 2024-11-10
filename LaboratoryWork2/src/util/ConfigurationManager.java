package util;

import java.util.Properties;
import util.singleton.Logger;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private final Properties config;
    private final Logger logger;

    private ConfigurationManager() {
        this.config = new Properties();
        this.logger = Logger.getInstance();
        loadConfiguration();
    }

    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    private void loadConfiguration() {
        // Default configurations
        config.setProperty("notification.email.enabled", "true");
        config.setProperty("notification.sms.enabled", "true");
        config.setProperty("system.name", "Hospital Management System");
        logger.log("Configuration loaded");
    }

    public String getProperty(String key) {
        return config.getProperty(key);
    }

    public void setProperty(String key, String value) {
        config.setProperty(key, value);
        logger.log("Configuration updated: " + key);
    }
}