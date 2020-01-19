package java2.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ServerMain serverMain;
    private String nickName;

    public ClientHandler(final ServerMain serverMain, final Socket socket) {
        try {
            this.socket = socket;
            this.serverMain = serverMain;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                String[] authArray = str.split(" ");
                                if (authArray.length != 3) {
                                    sendMsg("Вы не ввели данные для авторизации");
                                    continue;
                                }
                                String nickName = AuthService.getNickByLoginAndPass(authArray[1], authArray[2]);
                                if (nickName == null) {
                                    sendMsg("Неверный логин/пароль");
                                } else if (serverMain.isLoggedIn(nickName)){
                                    sendMsg("Данный пользователь уже в сети");
                                } else {
                                    sendMsg("/authok");
                                    serverMain.subscribe(nickName, ClientHandler.this);
                                    ClientHandler.this.nickName = nickName;
                                    break;                       
                                }
                            }
                        }
                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }
                            String[] commandArray = str.split(" ");
                            if (commandArray.length > 2 && commandArray[0].equals("/w")) {
                                if (serverMain.isLoggedIn(commandArray[1])) {
                                    String msg = "";
                                    for (int i = 2; i < commandArray.length; i++) {
                                        msg += commandArray[i] + " ";
                                    }
                                    serverMain.sendMsg(commandArray[1], msg);
                                } else {
                                    sendMsg("Нет в сети пользователя с ником: " + commandArray[1]);
                                }
                            } else if (commandArray[0].equals("/w") && commandArray.length < 3) {
                                sendMsg("Ошибка при вводе команды. Введите: /w nick сообщение. Для отправки личного сообщения.");
                            } else {
                                serverMain.sendMsg(nickName + ": " + str);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        serverMain.unsubscribe(nickName, ClientHandler.this);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMsg(String str) {
        try {
            out.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
