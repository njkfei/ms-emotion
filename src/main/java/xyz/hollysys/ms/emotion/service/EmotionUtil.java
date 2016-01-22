package xyz.hollysys.ms.emotion.service;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import xyz.hollysys.ms.emotion.model.Emotion;

/**
 * 情感api
 * @author sanhao
 *
 */
@Service
public class EmotionUtil {
	private static Logger logger = Logger.getLogger(EmotionUtil.class);
	@Autowired
	private CloseableHttpClient httpClient;
	
	@Autowired
	private HttpPost httpPost;
	
	private Emotion emotion;
	
	public String getResult(String imgUrl){
		String jsonBody = String.format("{\"url\":\"%s\"}", imgUrl);
		String result = null;
		try {
			logger.debug(jsonBody);
			httpPost.setEntity(new StringEntity(jsonBody));
			CloseableHttpResponse response = httpClient.execute(httpPost);
			 
			 try{
			 result = IOUtils.toString(response.getEntity().getContent());
			 logger.info(result);
			 }finally{
				 response.close();
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Emotion> getEmotion(String imgUrl){
		String jsonBody = String.format("{\"url\":\"%s\"}", imgUrl);
		String jsonresult = null;
		List<Emotion> emotions = null;
		try {
			logger.debug(jsonBody);
			httpPost.setEntity(new StringEntity(jsonBody));
			CloseableHttpResponse response = httpClient.execute(httpPost);
			 
			 try{
				 jsonresult = IOUtils.toString(response.getEntity().getContent());
			 logger.info(jsonresult);
			 }finally{
				 response.close();
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(jsonresult != null){
			emotions =  JSON.parseArray(jsonresult,Emotion.class);
		}
		
		return emotions;
	}
	
	public static void main(String[] args){
		System.out.println( String.format("{\"url\":\"%s\"}", "hello"));
	}
}
