package com.codingworld.multitenant.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.codingworld.multitenant.config.TenantDataSource;
import com.codingworld.multitenant.exceptions.TenantNotFoundException;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Autowired
    private ApplicationContext context;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) throws Exception {
        System.out.println("In preHandle we are Intercepting the Request");
        System.out.println("____________________________________________");
        String requestURI = request.getRequestURI();
        String tenantID = request.getHeader("X-TenantID");
        System.out.println("RequestURI::" + requestURI +" || Search for X-TenantID  :: " + tenantID);
        System.out.println("____________________________________________");
        if (tenantID == null || tenantID.isEmpty()) {
            throw new TenantNotFoundException();
           
        }

        TenantDataSource tenantDataSource = context.getBean(TenantDataSource.class);
        Map<String, DataSource> tenants = tenantDataSource.getAll();

        if (!tenants.containsKey(tenantID)) {
            throw new TenantNotFoundException();
        }
        TenantContext.setCurrentTenant(tenantID);
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        TenantContext.clear();
    }

}
