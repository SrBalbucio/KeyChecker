package balbucio.keychecker.admin;

import balbucio.datacrack.client.exception.DataNotExistsException;
import balbucio.datacrack.client.exception.InvalidCredentialException;
import balbucio.datacrack.client.exception.RequestErrorException;
import balbucio.datacrack.client.exception.UserInsufficientPermissionException;
import balbucio.datacrack.client.user.User;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        String appName = JOptionPane.showInputDialog("KeyChecker Admin V1\nQual é o Application Name?");
        String email = JOptionPane.showInputDialog("KeyChecker Admin V1\nQual é o Email do usuário a receber a Key?");
        String[] socket = JOptionPane.showInputDialog("KeyChecker Admin V1\nQual é o User/IP do Datacrack?\n(ex.: username:pass:127.0.0.1:25465").split(":");
        KeyCheckerAdmin admin = new KeyCheckerAdmin(new User(socket[0], socket[1]), socket[2], Integer.parseInt(socket[3]));
        String key = "NULL";
        try {
            key = admin.addKey(appName, email);
            JOptionPane.showMessageDialog(null, "Key criada com sucesso!\nKey: " + key);
        } catch (InvalidCredentialException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Esse usuário não tem permissão suficiente\nLembre-se o usuário deve ter permissão ADMIN!");
        } catch (RequestErrorException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível concluir a criação da Key!");
        } catch (DataNotExistsException e) {
            e.printStackTrace();
        } catch (UserInsufficientPermissionException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Esse usuário não tem permissão suficiente\nLembre-se o usuário deve ter permissão ADMIN!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível concluir a criação da Key!");
        }
    }
}
