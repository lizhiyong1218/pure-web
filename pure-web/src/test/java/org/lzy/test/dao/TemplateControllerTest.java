package org.lzy.test.dao;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.lzy.api.model.Media;
import org.lzy.core.service.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 * action测试例子
 * @author lzy
 *
 */
public class TemplateControllerTest extends JUnitActionBase {
	@Autowired 
	IMediaService mediaService;
	
	@Test
	public void testSearch() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		//设置controller路径
		request.setServletPath("/pub");
		//添加参数
		 ;
		request.addParameter("ftlName","index.ftl");
		//设置提交方式
		request.setMethod("post");
		request.setAttribute("msg", "测试action成功");
		final ModelAndView mav = this.excuteAction(request, response);
		//从modelmap中获取值
		List<Media> list=(List<Media>) mav.getModelMap().get("medialist"); 
		System.out.println(list.size()+"~~~~~~~~~~~~");
		Assert.assertEquals("pubsuccess", mav.getViewName());
		String msg=(String)request.getAttribute("msg");
		System.out.println(msg);
	}
}
