package com.lvhong.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lvhong.core.pojo.PageList;
import com.lvhong.core.pojo.TaskImpl;
import com.lvhong.shiro.pojo.User;

@Controller
@RequestMapping("/urlInfo")
public class AgencyController {

	@Resource
	private RuntimeService runtimeService;

	@Resource
	private TaskService taskService;

	@RequestMapping("/agency")
	public String agency() {
		return "/views/pages/agency";
	}

	@RequestMapping("/agency/info")
	@ResponseBody
	public PageList<String> agencyInfo(HttpSession session, Integer page) {
		PageList<String> result = new PageList<String>();
		Integer size = 10;
		User user = (User) session.getAttribute("user");
		Long count = taskService.createTaskQuery().taskAssignee(user.getUserName()).count();
		if(count > 0) {			
			List<Task> list = taskService.createTaskQuery().taskAssignee(user.getUserName()).listPage((page - 1) * size , page * size);
			List<TaskImpl> list1 = new ArrayList<TaskImpl>();
			for (Task task : list) {
				list1.add(new TaskImpl(task.getName(), task.getDescription(), task.getId(), task.getCreateTime(),task.getProcessInstanceId()));
			}
			result.setTotal(count.intValue());
			String jsonString = JSON.toJSONString(list1, SerializerFeature.WriteDateUseDateFormat);
			result.setJsonStr(jsonString);
			Double ceil = Math.ceil(count*1.0/size);
			result.setPageSize(ceil.longValue());
			result.setCurrentPage(page);
		}
		return result;
	}

	@RequestMapping("/agencyUrl")
	public String agencyUrl(String id,String processInstanceId,Model model) {
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String businessKey = pi.getBusinessKey();
		model.addAttribute("businessKey",businessKey);
		model.addAttribute("taskId", id);
		return "/views/pages/agencyUrl";
	}
}
