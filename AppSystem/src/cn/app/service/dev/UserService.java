package cn.app.service.dev;

import java.util.List;

import cn.app.pojo.AppCategory;
import cn.app.pojo.AppInfo;
import cn.app.pojo.DataDictionary;
import cn.app.pojo.DevUser;

public interface UserService {
	
		//登陆 查找用户名是否存在
		public DevUser login(String devCode);
		
		//获取状态列表
		public List<DataDictionary> ddt();
		
		//获取所属平台列表
		public List<DataDictionary> ddf();
		
		//查询总记录数
		public int totalCount();
		
		//分页显示信息
		public List<AppInfo> apps(String name,Integer state,Integer platform,
				Integer category1,Integer category2,Integer category3,int indexStart,int pageSize);
		
		//一级分类
		public List<AppCategory> one();

		/*//二、三级分类
		public List<AppCategory> two(int id);*/
			
//		//三级分类
//		public List<AppCategory> three();
//		
		//总记录数
		//public int totalCount();
		

}
