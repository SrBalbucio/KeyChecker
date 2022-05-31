package balbucio.keychecker.admin;

import balbucio.datacrack.client.Datacrack;
import balbucio.datacrack.client.Manager;
import balbucio.datacrack.client.data.RootDataPack;
import balbucio.datacrack.client.user.User;
import java.util.UUID;

public class KeyCheckerAdmin {

    private User user;
    private Datacrack datacrack;
    private Manager manager;

    public KeyCheckerAdmin(User user, String ip, int port){
        this.user = user;
        this.datacrack = new Datacrack(user);
        this.manager = datacrack.getManager();
        manager.addSocket(ip, port);
    }

    public String addKey(String appName, String email) throws Exception {
        RootDataPack dataPack = manager.getRootPack(email);
        String key = UUID.randomUUID().toString();
        dataPack.setString(key, appName);
        return key;
    }
}
