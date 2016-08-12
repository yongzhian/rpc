/*
 * Copyright (c) 2016 www.yongzhian.cn. All Rights Reserved.
*/
package cn.zain;

import cn.zain.client.RpcClient;
import cn.zain.protocal.Invocation;
import cn.zain.server.IServer;
import cn.zain.server.RpcServerThread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zain on 2016/8/10.
 */
public class RPC {
    public static <T> T getProxy(final Class<T> clazz, String host, int port) {

        final RpcClient client = new RpcClient(host, port);
        InvocationHandler handler = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invo = new Invocation();
                invo.setInterfaces(clazz);
                invo.setMethod(new cn.zain.protocal.Method(method.getName(), method.getParameterTypes()));
                invo.setParams(args);
                client.invoke(invo);
                return invo.getResult();
            }
        };
        T t = (T) Proxy.newProxyInstance(RPC.class.getClassLoader(), new Class[]{clazz}, handler);
        return t;
    }


    public static class RPCServer implements IServer {
        private int port = 20382;
        private RpcServerThread rpcServerThread;
        private boolean isRuning = true;

        private Map<String, Object> serviceEngine = new HashMap<String, Object>();

        /**
         * @param isRunning the isRuning to set
         */
        public void setRunning(boolean isRunning) {
            this.isRuning = isRuning;
        }

        /**
         * @return the port
         */
        public int getPort() {
            return port;
        }

        /**
         * @param port the port to set
         */
        public void setPort(int port) {
            this.port = port;
        }


        @Override
        public void call(Invocation invocation) {
            System.out.println(invocation.getClass().getName());
            Object obj = serviceEngine.get(invocation.getInterfaces().getName()); //取得服务器注册的对象,接口-实现，故取得实现类，调用实现类的方法
            if (obj != null) {
                try {
                    Method m = obj.getClass().getMethod(invocation.getMethod().getMethodName(), invocation.getMethod().getParams());
                    Object result = m.invoke(obj, invocation.getParams()); //调用方法，并返回结果
                    invocation.setResult(result); //将结果封装到返回给客户端的对象中
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } else {
                throw new IllegalArgumentException("has no these class");
            }
        }

        @Override
        public void register(Class interfaceDefiner, Class impl) {
            try {
                this.serviceEngine.put(interfaceDefiner.getName(), impl.newInstance());
                System.out.println(serviceEngine);
            } catch (Throwable e) {

                e.printStackTrace();
            }
        }

        @Override
        public void start() {
            System.out.println("启动服务器");
            rpcServerThread = new RpcServerThread(this);
            this.isRuning = true;
            rpcServerThread.start();
        }

        @Override
        public void stop() {
            this.setRunning(false);
        }

        @Override
        public boolean isRunning() {
            return isRuning;
        }

    }

}
