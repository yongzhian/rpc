/*
 * Copyright (c) 2016 www.yongzhian.cn. All Rights Reserved.
*/
package cn.zain.server;

import cn.zain.protocal.Invocation;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Zain on 2016/8/10.
 */
public class RpcServerThread extends Thread {
    private static Logger logger = Logger.getLogger(RpcServerThread.class);

    private ServerSocket serverSocket;
    private IServer server;

    public RpcServerThread(IServer server) {
        this.server = server;
    }

    @Override
    public void run() {
        logger.info("启动服务器中，打开端口" + server.getPort());
        try {
            serverSocket = new ServerSocket(server.getPort());
        } catch (IOException e) {
            logger.error("socket server 创建失败。", e);
            return;
        }
        while (server.isRunning()) {
            try {
                logger.info("等待请求");
                Socket client = serverSocket.accept();
                logger.info("请求到来");
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                Invocation invo = (Invocation) ois.readObject();
                System.out.println("远程调用:" + invo);

                server.call(invo);

                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                oos.writeObject(invo);
                oos.flush();
                oos.close();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        try {
            if (serverSocket != null && !serverSocket.isClosed())
                serverSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
