package xyz.hollysys.ms.emotion.config;

import java.net.URI;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author lanyonm
 */
@Configuration
@PropertySource(value = { "classpath:ms.properties" })
public class HttpClientConfig {
	private static Logger logger = Logger.getLogger(HttpClientConfig.class);

	@Value("${emotion.key}")
	private String emotion_key;

	@Value("${emotion.url}")
	private String emotion_url;
	
	@Value("${emotion.faceRectangles:left;top;width;height}")
	private String faceRectangles;

	// 必须要有这一行，否则上面的＠VALUE无法注入
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public CloseableHttpClient httpClient() {
		HttpClientBuilder builder = HttpClientBuilder.create();
		// builder.setEverything(everything); // configure it
		CloseableHttpClient httpClient = builder.build();

		return httpClient;
	}

	@Bean
	public HttpPost httpPost() {
		try {
			URIBuilder builder = new URIBuilder(emotion_url);

			//builder.setParameter("faceRectangles", faceRectangles);
			URI uri = builder.build();
			HttpPost post = new HttpPost(uri);
			post.setHeader("Content-Type", "application/json");
			post.setHeader("Ocp-Apim-Subscription-Key", emotion_key);
			
			return post;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
