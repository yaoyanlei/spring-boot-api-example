package com.apelab.example.filter;

import com.apelab.example.helper.TenantHolder;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
public class TenantFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    try {
      HttpServletRequest req = (HttpServletRequest) request;
      String tenantId = req.getHeader("X-Tenant");
      if(StringUtils.isNoneEmpty(tenantId)){
        TenantHolder.put(tenantId);
        chain.doFilter(request, response);
      }else {
        chain.doFilter(request,response);
      }
    } finally {
      TenantHolder.clear();
    }
  }

  @Override
  public void destroy() {

  }
}
