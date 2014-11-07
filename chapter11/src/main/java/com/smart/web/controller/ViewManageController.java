package com.smart.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.smart.domain.CommentLog;
import com.smart.domain.ViewPoint;
import com.smart.domain.ViewSpace;
import com.smart.service.ViewSpaceService;
import com.smart.service.exception.IpCommentedException;
/**
 *<pre>
 *   景区管理控制器，这部分功能由景区管理操作，包括：景区、景点增删改查控制转发处理
 * </pre>
 */
@Controller
public class ViewManageController extends BaseController{
	@Autowired
	private ViewSpaceService viewSpaceService;

	// 显示所有景区的列表
	@RequestMapping(value = "/index", method = RequestMethod.GET)  
	public String listViewSpaces(HttpServletRequest request) {
		List<ViewSpace> viewSpaces = viewSpaceService.getAllViewSpaces();
		request.setAttribute("viewSpaces", viewSpaces);
		return "/listViewSpaces";
	}

	// 显示所有景区的列表
	@RequestMapping(value = "/vs/index", method = RequestMethod.GET)  
	public String listUserViewSpaces(HttpServletRequest request) {
		int userId = super.getSessionUser(request).getUserId();
		List<ViewSpace> viewSpaces = viewSpaceService
				.queryViewSpaceByUserId(userId);
		request.setAttribute("viewSpaces", viewSpaces);
		return "/listUserViewSpaces";
	}

	// 根据名称模糊查询景区
	@RequestMapping(value = "/search",method=RequestMethod.PUT)
	public String queryViewSpaces(HttpServletRequest request) {
		String spaceName = request.getParameter("spaceName");
		List<ViewSpace> viewSpaces = viewSpaceService
				.queryViewSpaceByName(spaceName);
		request.setAttribute("viewSpaces", viewSpaces);
		return "/listViewSpaces";
	}

	// 显示景区的详细信息
	@RequestMapping(value = "/vs/{id}", method = RequestMethod.GET)
	public String showViewSpace(@PathVariable Integer id,HttpServletRequest request) {
		ViewSpace viewSpace = viewSpaceService.getFullViewSpace(id);
		request.setAttribute("viewSpace", viewSpace);
		return "/showViewSpace";
	}

	// 打开新添景区页面
	@RequestMapping(value = "/vs/add", method = RequestMethod.GET)
	public String addViewSpacePage() {
		return "/addViewSpace";
	}

	//新添景区
	@RequestMapping(value = "/vs/save", method=RequestMethod.PUT)  
	public String addViewSpace(HttpServletRequest request,ViewSpace viewSpace) {
		viewSpace.setUser(getSessionUser(request));
		viewSpaceService.addViewSpace(viewSpace);
		String targetUrl = "/vs/index";
        return "redirect:"+targetUrl;
	}

	// 打开更改景区页面
	@RequestMapping(value="/vs/{id}/edit")  
	public String updateViewSpacePage(@PathVariable Integer id,HttpServletRequest request) {
		ViewSpace viewSpace = viewSpaceService.getFullViewSpace(id);
		request.setAttribute("viewSpace", viewSpace);
		return "/updateViewSpace";
	}

	// 更改景区
	@RequestMapping(value="/vs/{id}/update",method=RequestMethod.PUT)  
	public String updateViewSpace(@PathVariable Integer id,HttpServletRequest request, ViewSpace vs) {
		vs.setUser(getSessionUser(request));
		vs.setSpaceId(id);
		viewSpaceService.updateViewSpace(vs);
		String targetUrl = "/vs/index";
        return "redirect:"+targetUrl;
	}

	// 删除景区
	@RequestMapping(value="/vs/{id}/delete",method=RequestMethod.DELETE)  
	public String deleteViewSpace(@PathVariable Integer id) {
		viewSpaceService.deleteViewSpace(id);
		String targetUrl = "/vs/index";
        return "redirect:"+targetUrl;
	}

	// 对景区的评论
	@RequestMapping(value="/vs/comment/{id}/{commentType}",method=RequestMethod.GET)  
	public String commentViewSpace(@PathVariable Integer id,@PathVariable Integer commentType,HttpServletRequest request) {
		if(id == null){
		   return "/fail";	
		}
		CommentLog cl = new CommentLog();
		cl.setIp(request.getRemoteAddr());
		cl.setCommentType(commentType);
		ViewSpace vs = new ViewSpace();
		vs.setSpaceId(Integer.valueOf(id));
		cl.setViewSpace(vs);
		try {
			viewSpaceService.addCommentLog(cl);
		} catch (IpCommentedException ice) {
			request.setAttribute(ERROR_MSG_KEY, "您已经评论过该景区了。");
			return "/fail";
		}
        String targetUrl = "/vs/" + id;
        return "redirect:"+targetUrl;
	}

	// 打开添加景区的景点的页面
	@RequestMapping(value = "/vp/{id}/add", method = RequestMethod.GET)
	public String addViewPointPage(@PathVariable Integer id,HttpServletRequest request) {
		ViewSpace vs = new ViewSpace();
		vs.setSpaceId(id);
		request.setAttribute("viewSpace", vs);
		return "/addViewPoint";
	}

	// 添加景区的景点
	@RequestMapping(value = "/vp/save", method = RequestMethod.POST)
	public String addViewPoint(MultipartHttpServletRequest request, @RequestParam("spaceId") int spaceId,
			@RequestParam("pointName") String pointName,
			@RequestParam("ticketPrice") float ticketPrice,
			@RequestParam("description") String description) {
		ViewPoint vp = new ViewPoint();
		vp.getViewSpace().setSpaceId(spaceId);
		vp.setPointName(pointName);
		vp.setTicketPrice(ticketPrice);
		vp.setDescription(description);
		try {
			List<MultipartFile> files = request.getFiles("imgFile");
			for (int i = 0; i < files.size(); i++) {
				if (!files.get(i).isEmpty()) {
					byte[] bytes = files.get(i).getBytes();
					String srcFileName = files.get(i).getOriginalFilename();
					String fileExt = srcFileName.substring(srcFileName
							.lastIndexOf('.'));
					String fileName = getNewFileName() + fileExt;
					String fullFilePath = request.getSession()
							.getServletContext().getRealPath(
									"/uploads/" + fileName);
					FileOutputStream fos = new FileOutputStream(fullFilePath); // 写入文件
					fos.write(bytes);
					fos.close();
					vp.setImgFile(fileName);
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		viewSpaceService.addViewPoint(vp);
        String targetUrl = "/vs/" + spaceId  + "/edit";
        return "redirect:"+targetUrl;
	}

	// 产生一个由UUID生成的文件名
	private String getNewFileName() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().toUpperCase();
	}

	// 删除景区的景点
	@RequestMapping(value="/vp/{id}/delete",method=RequestMethod.DELETE)  
	public String deleteViewPoint(@PathVariable Integer id) {
		ViewPoint vp = viewSpaceService.getFullViewPoint(id);
		int spaceId = vp.getViewSpace().getSpaceId();
		viewSpaceService.deleteViewPoint(id);
        String targetUrl = "/vs/" + spaceId  + "/edit";
        return "redirect:"+targetUrl;
	}

	//更改景点的页面
	@RequestMapping(value="/vp/{id}/edit",method=RequestMethod.GET) 
	public String updateViewPointPage(@PathVariable Integer id,HttpServletRequest request) {
		ViewPoint vp = viewSpaceService.getFullViewPoint(id);
		request.setAttribute("viewPoint", vp);
		return "/updateViewPoint";
	}
	

	//更改景点
	@RequestMapping(value = "/vp/{id}/update", method = RequestMethod.POST)
	public String updateViewPoint(@PathVariable Integer id,MultipartHttpServletRequest request,
		    @RequestParam("spaceId") int spaceId,
			@RequestParam("pointName") String pointName,
			@RequestParam("ticketPrice") float ticketPrice,
			@RequestParam("description") String description) {
		ViewPoint vp = new ViewPoint();
		vp.setPointId(id);
		vp.getViewSpace().setSpaceId(spaceId);
		vp.setPointName(pointName);
		vp.setTicketPrice(ticketPrice);
		vp.setDescription(description);
		try {
			List<MultipartFile> files = request.getFiles("imgFile");
			for (int i = 0; i < files.size(); i++) {
				if (!files.get(i).isEmpty()) {
					byte[] bytes = files.get(i).getBytes();
					String srcFileName = files.get(i).getOriginalFilename();
					String fileExt = srcFileName.substring(srcFileName
							.lastIndexOf('.'));
					String fileName = getNewFileName() + fileExt;
					String fullFilePath = request.getSession()
							.getServletContext().getRealPath(
									"/uploads/" + fileName);
					FileOutputStream fos = new FileOutputStream(fullFilePath); // 写入文件
					fos.write(bytes);
					fos.close();
					vp.setImgFile(fileName);
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		viewSpaceService.updateViewPoint(vp);
        String targetUrl = "/vs/" + vp.getViewSpace().getSpaceId()  + "/edit";
        return "redirect:"+targetUrl;
	}
}