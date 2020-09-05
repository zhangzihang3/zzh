package com.zzh.config;

import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JWTFilter extends AccessControlFilter {

    /**
     * 请求到来以后响应的方法
     *
     * @param servletRequest
     * @param servletResponse
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        return false;
    }

    /**
     * token 认证未通过，执行下面的方法
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        return true;
    }
}
