package com.general.action;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.general.model.Parent;
import com.general.model.Resident;
import com.general.service.IParentService;
import com.general.service.imp.ParentService;

/**
 * Servlet implementation class RelationManage
 */
@WebServlet("/servlet/relationmanage")
public class RelationManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = request.getParameter("func");
		switch (param) {
		case "addRelation":
			addRelation(request, response);
			break;
		case "searchRelation":
			searchRelation(request,response);
			break;
		default:
			break;
		}
	}
	
	public void addRelation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String self_id = request.getParameter("self_id");
		String father_id = request.getParameter("father_id");
		String mother_id = request.getParameter("mother_id");
		String spouse_id = request.getParameter("spouse_id");
		Parent parent = new Parent();
		parent.setSelf_id(self_id);
		parent.setFather_id(father_id);
		parent.setMother_id(mother_id);
		if(spouse_id != null)
			parent.setSpouse_id(spouse_id);
		parent.setUpdate_date(new Date());
		IParentService parentService = new ParentService();
		parentService.insert(parent);
		this.searchRelation(request, response);
	}
	public void searchRelation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String self_id = request.getParameter("self_id");
		String id = request.getParameter("id");
		if(id == null) {
			id = self_id;
		}
		IParentService parentService = new ParentService();
		Map<Resident, String> relations = parentService.searchRelationByResidentId(id);
		request.setAttribute("relations", relations);
		ServletContext application = this.getServletContext();
		RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/admin/relation_manage/search_result.jsp");
		requestDispatcher.forward(request, response);
	}
}
