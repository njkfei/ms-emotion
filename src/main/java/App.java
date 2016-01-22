

import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import xyz.hollysys.ms.emotion.config.HttpClientConfig;
import xyz.hollysys.ms.emotion.config.MybatisDataConfig;
import xyz.hollysys.ms.emotion.dao.TeacherDAO;
import xyz.hollysys.ms.emotion.model.Emotion;
import xyz.hollysys.ms.emotion.service.EmotionUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String args[]){
        AbstractApplicationContext  context = new AnnotationConfigApplicationContext(MybatisDataConfig.class,HttpClientConfig.class);
        
        TeacherDAO dao = (TeacherDAO) context.getBean("teacherDAO");
        CloseableHttpClient client = (CloseableHttpClient) context.getBean("httpClient");
        
        System.out.println(dao.getTeacher(1).getTeacher_pic());
        
        System.out.println(client == null);
        
        EmotionUtil emotionUtil = (EmotionUtil) context.getBean("emotionUtil");
        
        List<String> pics = dao.getPics();
        
        for(String pic : pics){
        	if(pic != null){
        		List<Emotion> result = emotionUtil.getEmotion(pic);
        		//System.out.println(result);
        	}
        }
        
        
/*        for( int i = 0; i < 10;i++){
        	String pic = dao.getPic(i);
        	if(pic != null){
        		String result = emotionUtil.getResult(pic);
        		System.out.println(result);
        	}
        }       */
    }
}
