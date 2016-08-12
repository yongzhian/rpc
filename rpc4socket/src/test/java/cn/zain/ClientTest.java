/*
 * Copyright (c) 2016 www.yongzhian.cn. All Rights Reserved.
*/
package cn.zain;

import cn.zain.op.IAction;
import org.junit.Test;

/**
 * Created by Zain on 2016/8/10.
 */
public class ClientTest {
    @Test
    public void clientStart() throws Exception {
        IAction echo = RPC.getProxy(IAction.class, "192.168.1.235", 20382); //动态代理，实质使用RpcClient的invoke，传入一个本地的invocatidon，并通过socket取得服务器处理后的结果

        System.out.println(echo.sayHello("hello,yongzhia 1031"));

    }
}
