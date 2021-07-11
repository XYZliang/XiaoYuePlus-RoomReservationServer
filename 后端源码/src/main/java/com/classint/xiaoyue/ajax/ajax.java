package com.classint.xiaoyue.ajax;

import javax.servlet.http.HttpServletResponse;

public class ajax {
  public static void ajax(HttpServletResponse response) {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
    response.setHeader("Access-Control-Max-Age", "3600");
  }
}
