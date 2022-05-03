package com.base.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

//   In the YML file, information is read on the environment basis.

public class AppSettings {
    private static String configFileName;
    public static AppSettings Instance;

    /* The environment has to be specified as the VM Option parameter.
       If no environment is specified, the tests will run for the QA environment.
        For Prom VM Option: -Denv=prod
        */
    static {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String env = System.getProperty("env");
        try {
            if (env.equals("prod")) {
                configFileName = "config-prod.yml";
            } else if (env.equals("qa")) {
                configFileName = "config-qa.yml";
            }
        } catch (NullPointerException e) {
            configFileName = "config-qa.yml";
        }
        try {
            Instance = mapper.readValue(getConfigFile(), AppSettings.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getConfigFile() {
        File configFile = new File(configFileName);
        System.out.println(configFileName);
        if (!configFile.exists()) {
            System.out.println("Environment config file does not exist");
            try {
                configFile = new File("CONFIG_FILE_NAME");
            } catch (NullPointerException e) {
                configFile = new File("CONFIG_FILE_NAME");
            }
        }
        return configFile;
    }

    @JsonProperty
    public AppConfig uiHomePage;

    @JsonProperty
    public AppConfig reqresApi;
}
