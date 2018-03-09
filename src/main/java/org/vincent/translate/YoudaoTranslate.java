package org.vincent.translate;
 
import java.util.HashMap;
import java.util.Map;

public class YoudaoTranslate {

	  public static void main(String[] args) throws Exception {
	       String fromText="你爸爸";
	        System.out.println(TranslateUtils.translateFromEng(fromText));
	    }

	     

	   /* *//**
	     * 根据api地址和参数生成请求URL
	     * @param url
	     * @param params
	     * @return
	     *//*
	    public static String getUrlWithQueryString(String url, Map params) {
	        if (params == null) {
	            return url;
	        }

	        StringBuilder builder = new StringBuilder(url);
	        if (url.contains("?")) {
	            builder.append("&");
	        } else {
	            builder.append("?");
	        }
	        
	        int i = 0;
	        for (Object key : params.keySet()) {
	            String value = (String) params.get(key);
	            if (value == null) { // 过滤空的key
	                continue;
	            }

	            if (i != 0) {
	                builder.append('&');
	            }

	            builder.append(key);
	            builder.append('=');
	            builder.append(encode(value));

	            i++;
	        }

	        return builder.toString();
	    }
	    *//**
	     * 进行URL编码
	     * @param input
	     * @return
	     *//*
	    public static String encode(String input) {
	        if (input == null) {
	            return "";
	        }

	        try {
	            return URLEncoder.encode(input, "utf-8");
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }

	        return input;
	    }*/
}
