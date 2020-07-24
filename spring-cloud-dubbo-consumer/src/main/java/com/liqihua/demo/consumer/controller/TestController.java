package com.liqihua.demo.consumer.controller;


import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import com.liqihua.demo.api.api.OrderDubboApi;
import com.liqihua.demo.api.dto.OrderListDTO;
import com.liqihua.demo.api.vo.OrderVO;
import com.liqihua.demo.api.vo.ResultVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Reference(validation = "true")
    private OrderDubboApi orderDubboApi;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderHTTPFeignApi orderHTTPFeignApi;

    @Autowired
    private OrderRPCFeignApi orderRPCFeignApi;

    /**
     * dubbo写法，rpc 方式调用 dubbo 服务
     * http://localhost:9001/test/test1
     * @return
     */
    @RequestMapping("/test1")
    public ResultVO test1() {
        return orderDubboApi.list(new OrderListDTO(1111L,null));
    }

    /**
     * restTemplate 写法，http 方式调用 dubbo 服务
     * http://localhost:9001/test/test2
     * @return
     */
    @RequestMapping("/test2")
    public ResultVO test2() {
        return restTemplate.postForObject("http://spring-cloud-dubbo-provider/order/list",new OrderListDTO(2222L,null),ResultVO.class);
    }

    /**
     * feign 写法，http 方式调用 dubbo 服务
     * @return
     */
    @RequestMapping("/test3")
    public ResultVO test3() {
        return orderHTTPFeignApi.list(new OrderListDTO(3333L,null));
    }


    /**
     * feign 写法，rpc 方式调用 dubbo 服务
     * @return
     */
    @RequestMapping("/test4")
    public ResultVO test4() {
        return orderRPCFeignApi.list(new OrderListDTO(4444L,null));
    }






    @FeignClient("spring-cloud-dubbo-provider")
    public interface OrderHTTPFeignApi {
        @PostMapping("/order/list")
        ResultVO<List<OrderVO>> list(@RequestBody  OrderListDTO param);
    }



    @FeignClient("spring-cloud-dubbo-provider")
    @DubboTransported(protocol = "dubbo")
    public interface OrderRPCFeignApi {
        @PostMapping("/order/list")
        ResultVO<List<OrderVO>> list(@RequestBody OrderListDTO param);
    }


}
