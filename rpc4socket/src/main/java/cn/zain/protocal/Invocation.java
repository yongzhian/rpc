/*
 * Copyright (c) 2016 www.yongzhian.cn. All Rights Reserved.
*/
package cn.zain.protocal;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Zain on 2016/8/10.
 */
public class Invocation  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Class interfaces;
    private Method method;
    private Object[] params;
    private Object result;

    public Class getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Class interfaces) {
        this.interfaces = interfaces;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Invocation{");
        sb.append("interfaces=").append(interfaces);
        sb.append(", method=").append(method);
        sb.append(", params=").append(params == null ? "null" : Arrays.asList(params).toString());
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
