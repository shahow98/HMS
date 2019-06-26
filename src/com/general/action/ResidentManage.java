package com.general.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.general.model.Resident;
import com.general.service.IResidentService;
import com.general.service.imp.ResidentService;
import com.general.util.DateUtil;


/**
 * Servlet implementation class ResidentManage
 */
@WebServlet(description = "居民信息管理", urlPatterns = { "/servlet/resident" })
public class ResidentManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("func");
		switch (param) {
		case "searchResidentById":
			searchResidentById(request, response);
			break;
		default:
			break;
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = request.getParameter("func");

		// 拓展权限认证
		switch (param) {
		case "searchResidentAll":
			searchResidentAll(request, response);
			break;
		case "searchResidentById":
			searchResidentById(request, response);
			break;
		case "searchResidentOrderRule":
			searchResidentOrderRule();
			break;
		case "addResident":
			addResident(request, response);
			break;
		case "deleteResidentById":
			deleteResidentById(request, response);
			break;
		case "updateResident":
			updateResident(request, response);
			break;
		default:
			break;
		}
	}

	public void searchResidentAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 查询全部居民信息
		IResidentService residentService = new ResidentService();
		List<Resident> residents = residentService.findAll();
		if (residents.size() > 0) {
			request.setAttribute("message", "scessfully");
			for(int i=0; i<residents.size(); i++) {
				if(residents.get(i).getId().equals("1")) {
					residents.remove(i);
					break;
				}
			}
			request.setAttribute("residents", residents);
		} else {
			request.setAttribute("message", "not find");
		}
		ServletContext application = this.getServletContext();
		RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/admin/resident_manage/search_result.jsp");
		requestDispatcher.forward(request, response);
	}

	public void searchResidentById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 按身份证号查询居民信息
		String id = request.getParameter("id");

		IResidentService residentService = new ResidentService();
		Resident resident = residentService.findById(id);
		if (resident != null) {
			request.setAttribute("message", "scessfully");
			request.setAttribute("resident", resident);
		} else {
			request.setAttribute("message", "not find");
		}
		ServletContext application = this.getServletContext();
		RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/admin/resident_manage/resident_detail.jsp");
		requestDispatcher.forward(request, response);
	}

	public void searchResidentOrderRule() {// 多约束查询居民信息

	}

	public void addResident(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// 添加居民信息
		// 可以判断对象是否有值
		Resident resident = this.getResident(request, response);
		IResidentService residentService = new ResidentService();
		residentService.insert(resident);
		this.searchResidentById(request, response);

	}

	public void deleteResidentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// 按身份证号删除居民信息
		String id = request.getParameter("id");
		IResidentService residentService = new ResidentService();
		residentService.delete(id);
		this.searchResidentAll(request, response);
	}

	public void updateResident(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// 按身份证号更新居民信息
		// 可以判断对象是否有值
		Resident resident = this.getResident(request, response);
		IResidentService residentService = new ResidentService();
		residentService.update(resident);
		this.searchResidentById(request, response);
	}

	public Resident getResident(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String birthday = request.getParameter("birthday");
		//String evacuation_date = request.getParameter("evacuation_date");
		//String immigratory_date = request.getParameter("immigratory_date");
		//String registry_date = request.getParameter("registry_date");
		String collective_id = request.getParameter("collective_id");
		String householder_id = request.getParameter("householder_id");
		String former_name = request.getParameter("former_name");
		String religion = request.getParameter("religion");
		String national = request.getParameter("national");
		String native_place = request.getParameter("native_place");
		String birthplace = request.getParameter("birthplace");
		String stature = request.getParameter("stature");
		String education = request.getParameter("education");
		String phone = request.getParameter("phone");
		Resident resident = new Resident();
		resident.setName(name);
		resident.setId(id);
		if(sex.equals("男"))
			resident.setSex(1);
		else
			resident.setSex(0);
		resident.setAge(Integer.parseInt(age));
		resident.setBirthday(DateUtil.getInstance().getDate(birthday, "yyyy-MM-dd"));
		//resident.setEvacuation_date(DateUtil.getInstance().getDate(evacuation_date, "yyyy-MM-dd"));
		//resident.setImmigratory_date(DateUtil.getInstance().getDate(immigratory_date, "yyyy-MM-dd"));
		resident.setRegistry_date(DateUtil.getInstance().getSqlDate(new Date(), "yyyy-MM-dd"));
		if(collective_id != "")
			resident.setCollective_id(collective_id);
		if(householder_id != "")
			resident.setHouseholder_id(householder_id);
		if(former_name != "")
			resident.setFormer_name(former_name);
		if(religion != "")
			resident.setReligion(religion);
		if(national != "")
			resident.setNational(national);
		if(native_place != "")
			resident.setNative_place(native_place);
		if(birthplace != "")
			resident.setBirthplace(birthplace);
		if(stature != "")
			resident.setStature(Float.parseFloat(stature));
		else
			resident.setStature(0);
		if(education != "")
			resident.setEducation(education);
		if(phone != "")
			resident.setPhone(phone);
		return resident;
	}

}
