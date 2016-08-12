/*
 * Copyright (c) 2016 www.yongzhian.cn. All Rights Reserved.
*/
package cn.zain;

import cn.zain.op.IAction;
import cn.zain.op.RemoteActionImpl;
import cn.zain.server.IServer;
import org.junit.Test;

/**
 * Created by Zain on 2016/8/10.
 */
public class ServerTest {
    @Test
    public void ServerStart() throws Exception {
        IServer server = new RPC.RPCServer();
        server.register(IAction.class, RemoteActionImpl.class);
        server.start();
        while(true){ //需要一直运行，否则主线程结束会导致服务器终止

        }

    }
}
