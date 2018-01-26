package cn.app.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.app.dao.back.BackUserMapper;
import cn.app.dao.devuser.DevUserMapper;
import cn.app.pojo.AppInfo;
import cn.app.pojo.BackendUser;
import cn.app.pojo.DevUser;
import cn.app.service.dev.UserService;

public class Test {

	public static void main(String[] args) {
		//appinfoli();
		appinfoli();
	}
	public static void testMybatis(){
		try {
			//获取myBatis-config.xml输入流
			InputStream is=Resources.getResourceAsStream("myBatis-config.xml");
			/*SqlSessionFactory factory=new SqlS*/
			//创建工厂
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
			SqlSession sqlSession=factory.openSession();
			DevUser dev=sqlSession.getMapper(DevUserMapper.class).login("test001");
			System.out.println(dev.getDevName());
			BackendUser back=sqlSession.getMapper(BackUserMapper.class).back("admin");
			System.out.println(back.getUserName());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testspringmybatis(){
		//读Spring文件
		ApplicationContext con=new ClassPathXmlApplicationContext("applicationcontext.xml");
		//拿出service bean
		UserService ser=(UserService) con.getBean("devServiceImpl");
		DevUser de=ser.login("test001");
		System.out.println(de.getDevName());
	}

	public static void appinfoli(){
		//获取myBatis-config.xml输入流
		InputStream is;
		try {
			is = Resources.getResourceAsStream("myBatis-config.xml");
		//创建工厂
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		SqlSession sqlSession=factory.openSession();
		List<AppInfo> apps=sqlSession.getMapper(DevUserMapper.class).apps(null, null,null,null,null,null,0,5);
		if(apps.size()>0){
			for(AppInfo app:apps){
				System.out.println(app.getAPKName());
			}
		}else{
			System.out.println("没有查询结果");
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
