package com.redis.cache.message;

/**
 * @ClassName: StudentMessage
 * @Description:  消息对象
 * @author: yangtianzeng
 * @date: 2020/3/27 9:28
 */
public class StudentMessage {
        private String id;
        private Object data;

    @Override
    public String toString() {
        return "StudentMessage{" +
                "id='" + id + '\'' +
                ", data=" + data +
                '}';
    }

    public StudentMessage(String id, Object data) {
        this.id = id;
        this.data = data;
    }

    public StudentMessage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
