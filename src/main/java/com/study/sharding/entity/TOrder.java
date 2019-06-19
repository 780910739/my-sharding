package com.study.sharding.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @Description: 用户
 * @Author: pukaiquan
 * @CreateDate: 2019年4月9日
 * @Version: V1.0
 *
 */
@Data
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1560751247289L;

    private Long orderId;

    private Long userId;

    private Integer status;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
