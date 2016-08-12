/*
 * Copyright (c) 2016 www.yongzhian.cn. All Rights Reserved.
*/
package cn.zain.server;

import cn.zain.protocal.Invocation;

/**
 * Created by Zain on 2016/8/10.
 */
public interface IServer {
    public void stop();
    public void start();
    public void register(Class interfaceDefiner, Class impl);
    public void call(Invocation invo);
    public boolean isRunning();
    public int getPort();
}
