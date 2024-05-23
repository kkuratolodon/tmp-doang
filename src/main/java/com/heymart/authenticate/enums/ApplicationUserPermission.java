package com.heymart.authenticate.enums;

import lombok.Getter;

@Getter
public enum ApplicationUserPermission {
    AUTH_CREATE_MANAGER("auth:create_manager"),
    COUPON_READ("coupon:read"),
    COUPON_CREATE("coupon:create"),
    COUPON_UPDATE("coupon:update"),
    COUPON_DELETE("coupon:delete");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    }
