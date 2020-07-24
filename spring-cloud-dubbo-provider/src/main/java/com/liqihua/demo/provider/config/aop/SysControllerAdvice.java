package com.liqihua.demo.provider.config.aop;

import com.liqihua.demo.api.vo.ResultVO;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.bind.ValidationException;
import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * 全局异常处理类
 */
//@ControllerAdvice(basePackages = {"com"})
public class SysControllerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(SysControllerAdvice.class);



    /**
     * 其他异常
     * @param ex
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public ResultVO runtimeException(Exception ex){
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw,true));
        LOG.error(ex.getClass().getName() + " " + sw.toString(), this.getClass());
        return new ResultVO(41000,"服务器发生异常，请联系工作人员",null);
    }

    
}
