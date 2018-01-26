package cn.app.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.app.pojo.AppCategory;
import cn.app.pojo.AppInfo;
import cn.app.pojo.DataDictionary;
import cn.app.pojo.DevUser;
import cn.app.pojo.Page;
import cn.app.service.dev.UserService;
import cn.app.tool.Constants;

@RequestMapping("/dev")
@Controller
public class DevController {
	
	private Logger logger=Logger.getLogger(DevController.class);

	@Resource
	UserService devService;

	/**
	 * 首页
	 * 
	 * @return 系统入口
	 * @author th
	 */
	@RequestMapping("/home")
	protected String login() {
		logger.info("home======================================================>");
		return "login";
	}

	/**
	 * 开发用户登陆页面
	 * 
	 * @author th
	 */
	@RequestMapping("/login")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		logger.info("devlogin======================================================>");
		return new ModelAndView("devlogin");
	}

	/**
	 * 登陆验证
	 * 
	 * @author th
	 */
	@RequestMapping("/dologin")
	protected String dologin(@RequestParam("devCode") String devCode,
			@RequestParam("devPassword") String devPassword,HttpSession session) {
		logger.info("用户名：========================"+devCode+"======================");
		DevUser dev = devService.login(devCode);
		if (null != dev) {
			if (devPassword.equals(dev.getDevPassword())) {
				session.setAttribute(Constants.USER_SESSION, dev);
				// 登陆成功
				return "developer/main";
			} else {
				// 登陆失败
				session.setAttribute("error", "密码不正确！");
			}
		} else {
			// 用户名不存在
			session.setAttribute("error", "用户不存在！");
		}
		return "login";
	}

	/**
	 * 注销
	 * 
	 * @author th
	 */
	@RequestMapping("/logout")
	protected String logout() {
		return "redirect:home";
	}

	/**
	 * 应用管理--APP维护 分页显示全部信息
	 * 
	 * @author th
	 */
	/*
	  @RequestMapping("/view") protected String view(HttpServletRequest
	  request,HttpSession session){ session=request.getSession(); //显示全部信息
	  List<AppInfo> appInfoList=devService.appList();
	  session.setAttribute("appInfoList", appInfoList); //获取状态列表
	  List<DataDictionary> ddt=devService.ddt();
	  session.setAttribute("statusList", ddt); //获取所属平台列表 List<DataDictionary>
	  ddf=devService.ddf(); session.setAttribute("flatFormList", ddf);
	  
	  
	  
	  
	  return "developer/appinfolist"; }*/
	 /**
	 * 一级分类
	 */
	/*
	 * @RequestMapping("/one")
	 * 
	 * @ResponseBody protected String two(HttpServletRequest request,HttpSession
	 * session){ session=request.getSession(); //一级分类 List<AppCategory>
	 * one=devService.one(); session.setAttribute("categoryLevel1List", one);
	 * 
	 * return JSONArray.toJSONString(categoryLevel1List); }
	 *//**
	 * 二级分类
	 */
	/*
	 * @RequestMapping("/two") protected String two(HttpServletRequest
	 * request,HttpSession session,
	 * 
	 * @RequestParam(value="id",required=false) int id){
	 * session=request.getSession(); //二、三级分类 List<AppCategory>
	 * two=devService.two(id); session.setAttribute("categoryLevel2List", two);
	 * 
	 * //三级分类 List<AppCategory> three=devService.one();
	 * session.setAttribute("categoryLevel3List", three);
	 * 
	 * return "developer/appinfolist"; }
	 */
	/**
	 * 按查询条件 分页显示信息
	 * 
	 * @author th
	 */
	@RequestMapping("/list")
	protected String list(
			@RequestParam(value = "currentPageNo", required = false) String currentPageNo,
			@RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
			@RequestParam(value = "queryStatus", required = false) String queryStatus,
			@RequestParam(value = "queryFlatformId", required = false) String queryFlatformId,
			@RequestParam(value = "queryCategoryLevel1", required = false) String queryCategoryLevel1,
			@RequestParam(value = "queryCategoryLevel2", required = false) String queryCategoryLevel2,
			@RequestParam(value = "queryCategoryLevel3", required = false) String queryCategoryLevel3,
			HttpSession session) {
		// 加载状态
		List<DataDictionary> statusList = devService.ddt();
		if (session.getAttribute("statusList") != null) {
			session.removeAttribute("statusList");
		}
		session.setAttribute("statusList", statusList);
		// 加载所属平台
		List<DataDictionary> flatFormList = devService.ddf();
		if (session.getAttribute("flatFormList") != null) {
			session.removeAttribute("flatFormList");
		}
		session.setAttribute("flatFormList", flatFormList);
		// 加载一级分类
		List<AppCategory> categoryLevel1List = devService.one();
		if (session.getAttribute("categoryLevel1List") != null) {
			session.removeAttribute("categoryLevel1List");
		}
		session.setAttribute("categoryLevel1List", categoryLevel1List);

		Page page = new Page();
		// 页面大小
		page.setPageSize(Constants.pageSize);
		// 总记录数
		page.setAll(devService.totalCount());// int allinfo=devService.totalCount();
		
		
		
		// 当前页面
		if (currentPageNo == null || "".equals(currentPageNo)) {
			currentPageNo = "1";
		}
		int pageindex = Integer.parseInt(currentPageNo);
		page.setCurrentPageNo(pageindex);
		// AppInfo
		AppInfo app = new AppInfo();
		// app名称
		app.setSoftwareName(querySoftwareName);
		if (session.getAttribute("querySoftwareName") != null) {
			session.removeAttribute("querySoftwareName");
		}
		// 状态
		if (queryStatus == null || "".equals(queryStatus)) {
			app.setStatus(null);
		} else {
			app.setStatus(Integer.parseInt(queryStatus));
		}
		// 所属平台
		if (queryFlatformId == null || "".equals(queryFlatformId)) {
			app.setFlatformId(null);
		} else {
			app.setFlatformId(Integer.parseInt(queryFlatformId));
		}
		// 一级分类
		if (queryCategoryLevel1 == null || "".equals(queryCategoryLevel1)) {
			app.setCategoryLevel1Name(null);
		} else {
			app.setCategoryLevel1(Integer.parseInt(queryCategoryLevel1));
		}
		// 二级分类
		if (queryCategoryLevel2 == null || "".equals(queryCategoryLevel2)) {
			app.setCategoryLevel2(null);
		} else {
			app.setCategoryLevel2(Integer.parseInt(queryCategoryLevel2));
		}
		// 三级分类
		if (queryCategoryLevel3 == null || "".equals(queryCategoryLevel3)) {
			app.setCategoryLevel3(null);
		} else {
			app.setCategoryLevel3(Integer.parseInt(queryCategoryLevel3));
		}
		// 每页的索引
		int index = (page.getCurrentPageNo() - 1) * page.getPageSize();
		// 每页显示的内容
		List<AppInfo> list=
				devService.apps(querySoftwareName, app.getStatus(),app.getFlatformId(), app.getCategoryLevel1(),app.getCategoryLevel2(), app.getCategoryLevel3(), index,page.getPageSize());
		page.setInfos(list);
		session.setAttribute("appInfoList", page);

		session.setAttribute("querySoftwareName", querySoftwareName);
		return "developer/appinfolist";
	}

}
