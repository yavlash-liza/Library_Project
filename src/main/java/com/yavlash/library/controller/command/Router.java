package com.yavlash.library.controller.command;

public class Router {
    public enum RouterType {
        FORWARD, REDIRECT
    }

    private String pagePath = PagePath.HOME;
    private RouterType routerType = RouterType.FORWARD;

    public Router() {
    }

    public Router(String pagePath, RouterType routerType) {
        if (pagePath != null) {
            this.pagePath = pagePath;
        }
        if (routerType != null) {
            this.routerType = routerType;
        }
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        if (pagePath != null) {
            this.pagePath = pagePath;
        }
    }

    public RouterType getRouterType() {
        return routerType;
    }
}