package org.lzy.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.lzy.api.StatusCode;
import org.lzy.api.model.Media;
import org.lzy.core.exception.InsideInterfaceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping(value="/showLogList",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String, Object>  showLogList2(HttpServletRequest req){
		System.out.println("test====");
		return null;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/testException",produces={"application/json;charset=UTF-8"})
	public String  testStr(HttpServletRequest req) throws Exception{
		System.out.println("test====");
		throw new InsideInterfaceException(StatusCode.SC_WARNING, "您导出的数据为空,请检查查询条件是否正确！");
	}
	
	/**
	 * 返回json字符串
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/testStr",produces={"application/json;charset=UTF-8"})
	public String  testPage(HttpServletRequest req) throws Exception{
		System.out.println("testStr====");
		return "testStrssss";
	}
	
	/**
	 * 简单跳转
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testPage")
	public String  testPage2(HttpServletRequest req) throws Exception{
		System.out.println("testPage====");
		return "test/testPage";
	}
	
	@RequestMapping(value="/testPageDetail")
	public ModelAndView testPageDetail(){
		System.out.println("testPageDetail in");
		Media media=new Media();
		media.setDesc("dddd");
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("media", media);
		modelMap.addAttribute("testvalue", "zzz");
		return new ModelAndView("test/testPageDetail",modelMap);
	}
	
}
