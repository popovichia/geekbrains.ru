/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.client;

/**
 *
 * @author igor
 */
public class Start {
    final static String SERVER_ADDRESS = "127.0.0.1";
    final static int SERVER_PORT = 9876;
    public static void main(String[] args) {
        ClientHost clientHost = new ClientHost(SERVER_ADDRESS, SERVER_PORT);
    }
}
