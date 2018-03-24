package org.vincent.translate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.vincent.translate.bean.TranslateBean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class TranslateUtils {

	private static String YOUDAOYUN="http://openapi.youdao.com/api";
	private static String from = "zh-CHS";
	private static String to = "EN";
	private static String Secretkey="68RcD5OFjj5tnLqgIBgPmmUNoLcV05DP";
	private static String appKey ="2dc5812808d70acc";
	
	
	/**
	 * 翻译文本多线程版本
	 * @param translateBeans
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public List<TranslateBean> multiTranslateToEng(List<TranslateBean> translateBeans) throws InterruptedException, ExecutionException {
		ExecutorService executor=Executors.newFixedThreadPool(3);
		if (translateBeans != null && translateBeans.size() > 0 ) {
			for (TranslateBean translateBean : translateBeans) {
			Future<String> result =	executor.submit(new Callable<String>() {
					@Override
					public String call() throws Exception {
						String engText =translateFromEng(translateBean.getChText());
						return engText;
					}
				});
			String engText = result.get();
			translateBean.setEnText(engText);
			}
			return translateBeans;
		}else {
			return null;
		}
	}
	/**
	 *  接受一个中文并文本并返回英文文本
	 * @param chineseText
	 * @return String 翻译会的文本
	 * @throws Exception
	 */
	public static String translateFromEng(String chineseText) throws Exception{
			if (null !=chineseText && "".equals(chineseText)) {
				return "";
			}
			/** 将平台下字符编码文本编码为UTF-8编码 */
			String text=new String(chineseText.getBytes(Charset.defaultCharset()), "UTF-8");
		    String salt = String.valueOf(System.currentTimeMillis() + (int)(1+Math.random()*10));
		    String sign = TranslateUtils.md5(appKey + chineseText + salt+ Secretkey);
	        Map<String,Object> params = new HashMap<String,Object>();
	        params.put("q", text);
	        params.put("from", from);
	        params.put("to", to);
	        params.put("sign", sign);
	        params.put("salt", salt);
	        params.put("appKey", appKey);
	        System.out.println("翻译查询参数 ："+JSON.toJSONString(params));
	        String result=requestForHttp(YOUDAOYUN, params);
	        Map<String, QueryPair>  map=parseJson(result);
	        QueryPair pair=map.get("query");
	        return pair.getTo();
	}
	private static class QueryPair{
		private String to;
		private String from;
		public QueryPair(){
			
		}
		@SuppressWarnings("unused")
		public QueryPair(String from, String to) {
			super();
			this.to = to;
			this.from = from;
		}
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
	}

		/**
		 * 将翻译后的结果json字符串解析，并取得解析后的英文翻译
		 * @param parseStr
		 * @return
		 */
	@SuppressWarnings("unchecked")
	private static Map<String, QueryPair> parseJson(String parseStr){
		 Map<String, QueryPair> result =new HashMap<>(1);
		if ((null != parseStr) && ("".equals(parseStr))) {
			return result;
		}
		Map<String, Object> map=new HashMap<>(16);
		//JSONObject jsonObject=JSONObject.
		map = (Map<String, Object>) JSON.parse(parseStr);
		System.out.println("query : "+ map.get("query") + ". result: "+map.get("translation"));
		TranslateUtils.QueryPair pair=new TranslateUtils.QueryPair();
		pair.setFrom((String)map.get("query"));
		JSONArray jsonArray=(JSONArray) map.get("translation");
		pair.setTo(jsonArray.getString(0));
		result.put("query",  pair);
		return result;
	}
	
	private static String requestForHttp(String url,Map<String, Object> requestParams) throws Exception{
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /**HttpPost*/
        HttpPost httpPost = new HttpPost(url);
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        Iterator<Entry<String, Object>> it = requestParams.entrySet().iterator();
        while (it.hasNext()) {
        	Entry<String, Object> en = (Entry<String, Object>) it.next();
            String key = (String) en.getKey();
            String value = (String) en.getValue();
            if (value != null) {
                params.add(new BasicNameValuePair(key, value));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
        /**HttpResponse*/
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try{
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, "utf-8");
            EntityUtils.consume(httpEntity);
        }finally{
            try{
                if(httpResponse!=null){
                    httpResponse.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 生成32位MD5摘要
     * @param string
     * @return
     */
    public static String md5(String string) {
        if(string == null){
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};

        try{
            byte[] btInput = string.getBytes("utf-8");
            /** 获得MD5摘要算法的 MessageDigest 对象 */
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            /** 使用指定的字节更新摘要 */
            mdInst.update(btInput);
            /** 获得密文 */
            byte[] md = mdInst.digest();
            /** 把密文转换成十六进制的字符串形式 */
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }catch(NoSuchAlgorithmException | UnsupportedEncodingException e){
            return null;
        }
    }
}
