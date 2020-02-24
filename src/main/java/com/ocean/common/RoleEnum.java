package com.ocean.common;

/**
 * 角色枚举类，其中运用了多态
 */
public enum RoleEnum implements RoleInterface {

    MENU_ADMIN_ROLE {
        @Override
        public String op() {
            return "菜单管理角色";
        }
    },
    ORDER_ADMIN_ROLE {
        @Override
        public String op() {
            return "订单管理角色";
        }
    };

    public String getAdminRole(String name) {
        return RoleEnum.valueOf(name).op();
    }

}
