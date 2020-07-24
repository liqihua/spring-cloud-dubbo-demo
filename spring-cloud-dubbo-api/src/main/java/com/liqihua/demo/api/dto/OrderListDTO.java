package com.liqihua.demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDTO implements Serializable {

    private Long userId;

    @NotBlank
    private String productName;

}
