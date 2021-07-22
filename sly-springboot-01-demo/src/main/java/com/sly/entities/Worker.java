package com.sly.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @Data 能够帮我们生成get和set，toString方法
 * @NoArgsConstructor 能够帮我们生成无参构造方法
 * @AllArgsConstructor 能够帮我们生成有参构造方法
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    private Integer id;

    private String workerName;

    private Integer wokerAge;
}
