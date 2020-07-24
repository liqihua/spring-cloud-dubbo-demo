package com.liqihua.demo.provider.controller;

import com.alibaba.fastjson.JSON;
import com.liqihua.demo.api.api.OrderDubboApi;
import com.liqihua.demo.api.dto.OrderListDTO;
import com.liqihua.demo.api.vo.OrderVO;
import com.liqihua.demo.api.vo.ResultVO;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/**
 * validation = "true"
 * 会对dubbo接口方式的rpc调用进行参数对象的校验，校验注解起作用
 * @Valid
 * 会对http写法的调用（restTemplate、feign）进行参数对象的校验，校验注解起作用
 */
@Service(validation = "true")
@RestController
@RequestMapping("/order")
public class OrderController implements OrderDubboApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);


    @Override
    @PostMapping("/list")
    public ResultVO<List<OrderVO>> list(@RequestBody @Valid OrderListDTO param) {
        LOGGER.info("--- /order/list 被调用 ... 参数：{}", JSON.toJSONString(param));
        OrderVO vo1 = new OrderVO("11",11L,11L,"11", LocalDateTime.now());
        OrderVO vo2 = new OrderVO("22",22L,22L,"22", LocalDateTime.now());
        return new ResultVO<>(10000,"success",Arrays.asList(vo1,vo2));
    }

}
