/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.server;

/**
 *
 * @author igor
 */
public class Start {
    final static int SERVER_PORT = 9876;
    public static void main(String[] args) {
        ServerHost serverHost = new ServerHost(SERVER_PORT);
    }
}
