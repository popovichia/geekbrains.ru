package java2.chat.server.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java2.chat.server.JFXController;
import java2.chat.server.ServerMain;
import java2.chat.server.entity.BlackList;
import java2.chat.server.entity.User;

public class ClientService extends Thread {
    private ServerMain serverMain;
    private Socket socket;
    private JFXController jfxController;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private User user;
    private BlackList blackList;

    public ClientService(ServerMain serverMain, Socket socket, JFXController jfxController) {
        this.serverMain = serverMain;
        this.socket = socket;
        this.jfxController = jfxController;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private boolean isAuth() {
        AuthService.connect();
        try {
            while (true) {
                String messageFromClient = dataInputStream.readUTF();
                if (messageFromClient.startsWith("/auth")) {
                    String[] authArray = messageFromClient.split(" ");
                    if (authArray.length != 3) {
                        sendMsg("Вы не ввели данные для авторизации");
                        continue;
                    }
                    String nickName = AuthService.getNickByLoginAndPassword(authArray[1], authArray[2]);
                    if (nickName == null) {
                        sendMsg("Неверный логин/пароль");
                    } else if (serverMain.isLoggedIn(nickName)){
                        sendMsg("Данный пользователь уже в сети");
                    } else {
                        sendMsg("/authok");
                        serverMain.subscribe(ClientService.this);
                        ClientService.this.user = new User(nickName);
                        ClientService.this.blackList = new BlackList(user);
                        ClientService.this.jfxController.writeLog("Вошёл пользователь: "
                                + ClientService.this.user.getNickName() + "; "
                                + "чёрный список пользователя: "
                                + ClientService.this.blackList.getBlackList());
                        return true;                       
                    }
                }
            }
        } catch(Exception exception) {
            exception.printStackTrace();
            return false;
        } finally {
            AuthService.disconnect();
        }
    }
    private void waitMessage() {
        try {
            while (true) {
                String messageFromClient = dataInputStream.readUTF();
                String[] messageArray = messageFromClient.split(" ");
                if (messageArray[0].startsWith("/")) {
                    if(messageArray[0].equals("/tonick")) {
                        if (messageArray.length > 2 && serverMain.isLoggedIn(messageArray[1])) {
                            serverMain.sendMsg(this, messageArray[1], messageArray[2]);
                        }
                    }
                    if(messageArray[0].equals("/block")) {
                        if (messageArray.length == 2) {
                            serverMain.addUserToBlackList(this, messageArray[1]);
                            this.jfxController.writeLog(this.blackList.getBlackList().toString());
                        }                        
                    }
                    if (messageFromClient.equals("/end")) {
                        dataOutputStream.writeUTF("/serverClosed");
                        return;
                    }
//                    String nickNameTo = commandArray[2];
//                    if (commandArray.length > 2 && commandArray[0].equals("/w")) {
//                        if (serverMain.isLoggedIn(nickNameTo)) {
//                            String message = "";
//                            for (int i = 2; i < commandArray.length; i++) {
//                                message += commandArray[i] + " ";
//                            }
//                            serverMain.sendMsg(ClientService.this, message);
//                        } else {
//                            sendMsg("Нет в сети пользователя с ником: " + commandArray[1]);
//                        }
//                    } else if (commandArray[0].equals("/w") && commandArray.length < 3) {
//                        sendMsg("Ошибка при вводе команды. Введите: /w nick сообщение. Для отправки личного сообщения.");
//                    } else {
//                        serverMain.sendMsg(nickNameTo + ": " + messageFromClient);
//                    }
                } else {
                    serverMain.sendMsg(this, messageFromClient);
                }
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }
    public void sendMsg(String message) {
        try {
            dataOutputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public User getUser() {
        return this.user;
    }
    public BlackList getBlackList() {
        return this.blackList;
    }
    @Override
    public void run() {
        if (!isAuth()) {
            return;
        }
        waitMessage();
    }
}
