package balbucio.keychecker.app;

import balbucio.datacrack.client.user.User;

public class GenericApplication implements Application {

    private String appName;

    public GenericApplication(String appName){
        this.appName = appName;
    }

    @Override
    public String getApplicationName() {
        return appName;
    }

}
