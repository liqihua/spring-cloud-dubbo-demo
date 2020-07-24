package com.liqihua.demo.consumer.aop;

import com.liqihua.demo.api.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;
import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * 全局异常处理类
 */
@ControllerAdvice(basePackages = {"com"})
public class SysControllerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(SysControllerAdvice.class);

    /**
     * 其他异常
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResultVO runtimeException(Exception ex){
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw,true));
        LOG.error(ex.getClass().getName() + " " + sw.toString(), this.getClass());
        return new ResultVO(40000,"服务器发生异常，请联系工作人员",null);
    }


    /**
     * 缺少参数异常
     * @param e
     * @return
     */
    @ExceptionHandler({ValidationException.class})
    @ResponseBody
    public ResultVO rpcException(ValidationException e){
        String message = "参数校验不通过";
        if(e.getMessage().contains("propertyPath=")) {
            String field = e.getMessage().substring(e.getMessage().indexOf("propertyPath=") + "propertyPath=".length());
            field = field.substring(0,field.indexOf(","));
            message = message + "：" + field;
        }
        if(e.getMessage().contains("interpolatedMessage=")) {
            String error = e.getMessage().substring(e.getMessage().indexOf("interpolatedMessage=") + "interpolatedMessage=".length());
            error = error.substring(0,error.indexOf(",")).replace("'","");
            message = message + " " + error;
        }
        return new ResultVO(40001, message,null);
    }


    /*
    @ExceptionHandler({RpcException.class})
    @ResponseBody
    public ResultVO rpcException(RpcException ex){
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw,true));
        LOG.error(ex.getClass().getName() + " " + sw.toString(), this.getClass());
        return new ResultVO(40002,"远程调用异常",null);
    }*/


    /*public static void main(String[] args) {
        String message = "Failed to validate service: com.liqihua.demo.api.api.OrderDubboApi, method: list, cause: [ConstraintViolationImpl{interpolatedMessage='不能为空', propertyPath=productName, rootBeanClass=class com.liqihua.demo.api.dto.OrderListDTO, messageTemplate='{javax.validation.constraints.NotBlank.message}'}]";
        message = message.substring(message.indexOf("interpolatedMessage=") + "interpolatedMessage=".length());
        message = message.substring(0,message.indexOf(","));
        System.out.println(message);
    }*/
}
