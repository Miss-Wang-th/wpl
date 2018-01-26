package cn.app.service.dev;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.app.dao.devuser.DevUserMapper;
import cn.app.pojo.AppCategory;
import cn.app.pojo.AppInfo;
import cn.app.pojo.DataDictionary;
import cn.app.pojo.DevUser;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private DevUserMapper devUserMapper;

	//登陆 查找用户名是否存在
	@Override
	public DevUser login(String devCode) {
		return devUserMapper.login(devCode);
	}

	//获取状态列表
	@Override
	public List<DataDictionary> ddt() {
		return devUserMapper.ddt();
	}

	//获取所属平台列表
	@Override
	public List<DataDictionary> ddf() {
		return devUserMapper.ddf();
	}
	
	//一级分类
	@Override
	public List<AppCategory> one() {
		return devUserMapper.one();
	}

	/*//二、三级分类
	@Override
	public List<AppCategory> two(int id) {
		return devUserMapper.two(id);
	}*/
	/*
	//三级分类
	@Override
	public List<AppCategory> three() {
		return devUserMapper.three();
	}
	*/
	//分页显示信息
	@Override
	public List<AppInfo> apps(String name, Integer state, Integer platform,
			Integer category1, Integer category2, Integer category3,int indexStart,int pageSize) {
		return devUserMapper.apps(name, state, platform, category1, category2, category3,indexStart,pageSize);
	}

	//总记录数
	@Override
	public int totalCount() {
		int all=devUserMapper.totalCount();
		return all;
	}


	

	
	
	/*@Override
	public int totalCount() {
		
	}*/



}
