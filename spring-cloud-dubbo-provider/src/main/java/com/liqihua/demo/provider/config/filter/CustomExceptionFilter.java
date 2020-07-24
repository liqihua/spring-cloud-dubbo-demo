package com.liqihua.demo.provider.config.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.filter.ExceptionFilter;

@Activate(group = CommonConstants.PROVIDER)
public class CustomExceptionFilter extends ExceptionFilter {


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = super.invoke(invoker, invocation);
        return result;
    }
}
