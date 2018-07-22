package com.lvhong.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.activiti.engine.RepositoryService;
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
import com.lvhong.core.pojo.TmDictonary;
import com.lvhong.core.pojo.User;
import com.lvhong.core.service.SelectorsService;

@Controller
@RequestMapping("/urlInfo")
public class AgencyController {

	@Resource
	private RuntimeService runtimeService;

	@Resource
	private TaskService taskService;

	@Resource
	private SelectorsService selecetorsService;
	
	@Resource
	private RepositoryService repositoryService;
	
	@RequestMapping("/agency")
	public String agency() {
		return "/views/pages/agency";
	}

	/**
	 * 代办任务列表
	 * @param session
	 * @param page
	 * @return
	 */
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
				String substring = task.getProcessDefinitionId().substring(0,task.getProcessDefinitionId().indexOf(":"));
				String flowUrl = selecetorsService.queryFlowUrl(substring,task.getTaskDefinitionKey());
				list1.add(new TaskImpl(task.getName(), task.getDescription(), task.getId(), task.getCreateTime(),task.getProcessInstanceId(),flowUrl));
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

	/**
	 * 流程任务具体页面跳转
	 * @param processInstanceId
	 * @param taskId
	 * @param action
	 * @param model
	 * @return
	 */
	@RequestMapping("/agencyUrl")
	public String agencyUrl(String processInstanceId,String taskId,String action,Model model) {
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String businessKey = pi.getBusinessKey();
		TmDictonary tmDictionary = selecetorsService.queryDictInfo(businessKey);
		model.addAttribute("tmDictionary", tmDictionary);
		model.addAttribute("taskId", taskId);
		model.addAttribute("processInstanceId", processInstanceId);
		if("edit".equals(action)) {
			return "/views/pages/urlTypeEdit";
		}else {
			return "/views/pages/urlTypeView";
		}
		
	}
	
	/**
	 * 超级管理员审批流程
	 * @param taskId
	 * @param approveAdvice
	 * @param processInstanceId
	 * @param flags
	 * @return
	 */
	@RequestMapping("/agencyapprove")
	@ResponseBody
	public String agencyAdminapprove(String taskId,String approveAdvice,String processInstanceId,Boolean flags,Long dictId) {	
		selecetorsService.agencyAdminapprove(taskId,approveAdvice,processInstanceId,flags,dictId);
		return "";
	}
	
	/**
	 * 用户申请流程节点
	 * @param taskId
	 * @param approveAdvice
	 * @param processInstanceId
	 * @param flags
	 * @return
	 */
	@RequestMapping("/agencyApply")
	@ResponseBody
	public String agencyApply(String taskId,String approveAdvice,String processInstanceId,Boolean flags,TmDictonary tmDictionary) {
		selecetorsService.agencyApply(taskId,approveAdvice,processInstanceId,flags,tmDictionary);
		return "";
	}
	
}
