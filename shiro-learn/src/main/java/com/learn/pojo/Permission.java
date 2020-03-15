package com.learn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author
 * @description Permission
 * @date 2019/8/8 10:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Permission implements Serializable {
    private static final long serialVersionUID = -3676918243527052588L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;//名称

    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;// 资源类型，[menu|button]
    private String url;// 资源路径.
    private String permission; // 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    private Long parentId; // 父编号
    private String parentIds; // 父编号列表
    private Boolean available = Boolean.FALSE;

    @ManyToMany
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "permissionId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roles;
}
