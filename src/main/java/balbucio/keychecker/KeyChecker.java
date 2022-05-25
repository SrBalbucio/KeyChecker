package balbucio.keychecker;

import balbucio.datacrack.client.Datacrack;
import balbucio.datacrack.client.Manager;
import balbucio.datacrack.client.data.RootDataPack;
import balbucio.datacrack.client.socket.SocketInstance;
import balbucio.datacrack.client.user.User;
import balbucio.keychecker.app.Application;

public class KeyChecker {

    private Datacrack datacrack;
    private Manager manager;
    private Application app;
    private SocketInstance socket;

    public KeyChecker(Application app, String ip, int port){
        this.app = app;
        this.socket = new SocketInstance(ip, port);
    }

    public boolean validate(String email, String key){
        if(!email.contains("@")){ return false; }
        this.datacrack = new Datacrack(new User(email, key));
        this.manager = datacrack.getManager();
        manager.addSocket(socket);
        boolean v = false;
        try{
            RootDataPack keys = manager.getRootPack(email);
            if(keys.contains(key) && keys.getString(key).equals(app.getApplicationName())){
                v = true;
            }
        } catch(Exception e){
            System.out.print("Não foi possível carregar as Keys, contate o administrador.");
        }
        return v;
    }
}
