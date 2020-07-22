package com.liqihua.demo.api.api;

import com.liqihua.demo.api.dto.OrderListDTO;
import com.liqihua.demo.api.vo.OrderVO;
import com.liqihua.demo.api.vo.ResultVO;

import java.util.List;

public interface OrderDubboApi {

    ResultVO<List<OrderVO>> list(OrderListDTO param);

}
