package com.liqihua.demo.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;
}
