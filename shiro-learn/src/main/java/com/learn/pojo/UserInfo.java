package com.learn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author
 * @description UserInfo
 * @date 2019/8/8 10:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -2016337276316788174L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String username;

    private String pwd;

    private String salt;

    private byte state;//用户状态 0:未认证 1:正常 2:已锁定

    @ManyToMany(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "uid")}, inverseJoinColumns = {
            @JoinColumn(name = "roleId")})
    private List<Role> roleList;

    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

}
