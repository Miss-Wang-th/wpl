package cn.app.dao.back;

import cn.app.pojo.BackendUser;

public interface BackUserMapper {
	
	//登陆 用户名是否存在
	public BackendUser back(String userCode);
	
}
