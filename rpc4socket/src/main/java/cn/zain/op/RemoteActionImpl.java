/*
 * Copyright (c) 2016 www.yongzhian.cn. All Rights Reserved.
*/
package cn.zain.op;

/**
 * Created by Zain on 2016/8/10.
 */
public class RemoteActionImpl implements IAction {
    public String sayHello(String str) {
        return "from remote:"+str;

    }
}
