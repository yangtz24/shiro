package com.rabbitmq.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @ClassName: mail
 * @Description: 邮件
 * @author: yangtianzeng
 * @date: 2020/4/4 8:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail implements Serializable {
    private static final long serialVersionUID = -8764236902289867298L;

    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    private String to;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "正文不能为空")
    private String content;

    /**
     * 消息id
     */
    private String msgId;

}
