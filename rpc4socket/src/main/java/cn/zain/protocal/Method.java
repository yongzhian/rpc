/*
 * Copyright (c) 2016 www.yongzhian.cn. All Rights Reserved.
*/
package cn.zain.protocal;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Zain on 2016/8/10.
 */
public class Method implements Serializable {
    private static final long serialVersionUID = 1L;

    private String methodName;
    private Class[] params;

    public Method(String methodName, Class[] params) {
        this.methodName = methodName;
        this.params = params;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParams() {
        return params;
    }

    public void setParams(Class[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Method{");
        sb.append("methodName='").append(methodName).append('\'');
        sb.append(", params=").append(params == null ? "null" : Arrays.asList(params).toString());
        sb.append('}');
        return sb.toString();
    }
}
