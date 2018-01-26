package cn.app.dao.devuser;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.app.pojo.AppCategory;
import cn.app.pojo.AppInfo;
import cn.app.pojo.DataDictionary;
import cn.app.pojo.DevUser;

public interface DevUserMapper {
	
	//登陆 查找用户名是否存在
	public DevUser login(String devCode);
	
	
	//获取状态列表
	public List<DataDictionary> ddt();
	
	//获取所属平台列表
	public List<DataDictionary> ddf();
	
	//一级分类
	public List<AppCategory> one();
	
	/*//二、三级分类
	public List<AppCategory> two(@Param("id") int id);*/
		
	//分页显示信息
	public List<AppInfo> apps(@Param("softwareName") String name,@Param("status") Integer state,@Param("flatformId") Integer platform,
			@Param("categoryLevel1") Integer category1,@Param("categoryLevel2") Integer category2,@Param("categoryLevel3") Integer category3,
			@Param("indexStart") int indexStart,@Param("pageSize") int pageSize);
	
	//查询总记录数
	public int totalCount();
	
}
