package com.apelab.example.exception;

import lombok.Getter;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
public class EntityNotFoundException extends RuntimeException {

  @Getter
  private String entityName;

  @Getter
  private ParamNameValue[] params;

  public EntityNotFoundException(String entityName, ParamNameValue... paramNameValues) {
    super("no such entity: [" + entityName + "] with params: " + describeParams(paramNameValues));
    this.entityName = entityName;
    this.params = paramNameValues;
  }

  @Getter
  public static class ParamNameValue {

    private String name;

    private Object value;

    public ParamNameValue(String name, Object value) {
      this.name = name;
      this.value = value;
    }

    @Override
    public String toString() {
      return name + " = " + value;
    }
  }

  private static String describeParams(ParamNameValue... paramNameValues) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < paramNameValues.length; i++) {
      if (i != 0) {
        sb.append(",");
      }
      sb.append(paramNameValues[i]);
    }
    sb.append("]");
    return sb.toString();
  }

}
