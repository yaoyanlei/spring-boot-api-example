package com.apelab.example.helper;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
public class TenantHolder {

  private static final ThreadLocal<String> tenant = new ThreadLocal<>();

  public static void put(String tenantId){
    if(null == tenant.get()) {
      tenant.set(tenantId);
    }
  }

  public static String get(){
    return tenant.get();
  }

  public static void clear(){
    tenant.remove();
  }

}
