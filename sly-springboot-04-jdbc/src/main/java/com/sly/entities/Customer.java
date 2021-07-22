package com.sly.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/16 17:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer cid;

    private String custName;

    private String custMobile;

    private String custAddress;

    private Integer custTicket;
}
