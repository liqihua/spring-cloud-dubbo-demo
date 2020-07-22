package com.liqihua.demo.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO implements Serializable {

    private String orderNo;

    private Long userId;

    private Long amount;

    private String productName;

    private LocalDateTime createTime;
}
