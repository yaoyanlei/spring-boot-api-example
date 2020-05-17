package com.apelab.example.repository.mapper.provider;

import com.apelab.example.bean.SearchSampleReq;
import java.util.List;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by yaoyaolei on 2020/5/16 17:26
 */
public class SampleProvider {

  public String selectSamples(SearchSampleReq sampleReq) {
    return new SQL() {
      {
        SELECT(
            "c.id channel_id, c.channel_name, cc.id channel_company_id, cc.channel_company_name, cc.business_license,"
                + " c.channel_type, c.channel_code, c.auth_name, c.auth_app_id, c.docking_method, "
                + " NULL product_name, c.create_time, c.create_by");
        FROM("channel c");
        INNER_JOIN("channel_company cc ON c.channel_company_id = cc.id");

        ORDER_BY("c.create_time DESC");
      }
    }.toString();
  }

  private String formatStr(List<String> list) {
    StringBuilder sb = new StringBuilder();
    for (String type : list) {
      sb.append("'");
      sb.append(type);
      sb.append("',");
    }
    String types = sb.toString();
    return types.substring(0, types.length() - 1);
  }
}
