/*
 * Copyright (c) 2016 www.yongzhian.cn. All Rights Reserved.
*/
package cn.zain.client;

import cn.zain.protocal.Invocation;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Zain on 2016/8/10.
 */
public class RpcClient {
    private static Logger logger = Logger.getLogger(RpcClient.class);
    private String host;
    private int port;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void init() throws IOException {
        socket = new Socket(host,port);
        oos = new ObjectOutputStream(socket.getOutputStream());
    }

    public void invoke(Invocation invocation) throws IOException, ClassNotFoundException {
        init();
        System.out.println("写入数据");
        oos.writeObject(invocation);
        oos.flush();
        ois = new ObjectInputStream(socket.getInputStream());

        Invocation result = (Invocation) ois.readObject();

        invocation.setResult(result.getResult());

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RpcClient{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append(", socket=").append(socket);
        sb.append(", oos=").append(oos);
        sb.append(", ois=").append(ois);
        sb.append('}');
        return sb.toString();
    }
}
