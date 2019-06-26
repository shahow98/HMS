package com.general.action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.general.model.Address;
import com.general.model.Collective;
import com.general.model.Household;
import com.general.model.Resident;
import com.general.service.IAddressService;
import com.general.service.ICollectiveService;
import com.general.service.IHouseholdService;
import com.general.service.IResidentService;
import com.general.service.imp.AddressService;
import com.general.service.imp.CollectiveService;
import com.general.service.imp.HouseholdService;
import com.general.service.imp.ResidentService;


/**
 * Servlet implementation class HouseholdManage
 */
@WebServlet("/servlet/householdmanage")
public class HouseholdManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = request.getParameter("func");
		switch (param) {
		case "searchHouseholdDetail":
			searchHouseholdDetail(request, response);
			
			break;
		case "deleteHouseholdMember":
			deleteHouseholdMember(request, response);
			break;
		case "deleteHouseholdAddress":
			break;
		case "deleteHousehold":
			break;
		case "searchCollectiveDetail":
			searchCollectiveDetail(request, response);
			break;
		case "deleteCollectiveMember":
			deleteCollectiveMember(request, response);
			break;
		case "deleteCollectiveAddress":
			break;
		case "deleteCollective":
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param = request.getParameter("func");
		switch (param) {
		case "addHousehold":
			addHousehold(request, response);
			break;
		case "searchHousehold":
			searchHousehold(request, response);
			break;
		case "searchHouseholdAll":
			searchHouseholdAll(request, response);
			break;
		case "addHouseholdMember":
			addHouseholdMember(request, response);
			break;
		case "searchHouseholdDetail":
			searchHouseholdDetail(request, response);
			break;
		case "addCollective":
			addCollective(request, response);
			break;
		case "searchCollective":
			searchCollective(request, response);
			break;
		case "searchCollectiveAll":
			searchCollectiveAll(request, response);
			break;
		case "addCollectiveMember":
			addCollectiveMember(request, response);
			break;
		case "searchCollectiveDetail":
			searchCollectiveDetail(request, response);
			break;
		case "addAddress":
			addAddress(request,response);
			break;
		default:
			break;
		}
	}
	
	public void addHousehold(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//家庭户口注册
		String personal_id = request.getParameter("personal_id");
		Household hosehold = new Household();
		hosehold.setPersonal_id(personal_id);
		IHouseholdService householdService = new HouseholdService();
		if(householdService.findByPersonalId(personal_id) == null)
			householdService.insert(hosehold);
		hosehold = householdService.findByPersonalId(personal_id);
		List<String> members_id = new ArrayList<>();
		members_id.add(personal_id);
		householdService.batchUpdateResidentHouseholdId(members_id, hosehold);//修改户主的家庭户口id
		//
		response.sendRedirect("/HMS/HMS/admin/household_manage/add_housemember.html?personal_id="+personal_id);
	}
	public void searchHousehold(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String member_id = request.getParameter("member_id");
		IHouseholdService householdService = new HouseholdService();
		Household household = householdService.searchHousehold(member_id);
		if(household != null) {
			request.setAttribute("message", "successfully");
			IResidentService residentService = new ResidentService();
			Resident resident = residentService.findById(household.getPersonal_id());
			List<Resident> lists = new ArrayList<>();
			lists.add(resident);
			request.setAttribute("personals", lists);
		}else {
			request.setAttribute("message", "not find");
		}
		ServletContext application = this.getServletContext();
		RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/admin/household_manage/search_householdresult.jsp");
		requestDispatcher.forward(request, response);
		
	}
	public void searchHouseholdAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IHouseholdService householdService = new HouseholdService();
		IResidentService residentService = new ResidentService();
		List<Household> lists = householdService.findAll();
		List<Resident> residents = new ArrayList<>();
		for(int i=0; i<lists.size(); i++) {
			Resident resident = residentService.findById(lists.get(i).getPersonal_id());
			residents.add(resident);
		}
		if(!residents.isEmpty()) {
			request.setAttribute("personals", residents);
		}else {
			request.setAttribute("message", "not find");
		}
		ServletContext application = this.getServletContext();
		RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/admin/household_manage/search_householdresult.jsp");
		requestDispatcher.forward(request, response);
	}
	public void addHouseholdMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//家庭成员注册
		int count = Integer.parseInt(request.getParameter("count"));//人数
		String personal_id = request.getParameter("personal_id");
		List<String> members_id = new ArrayList<>(); 
		for(int i=0; i<count; i++) {
			String id = "member_id"+i;//成员
			members_id.add(request.getParameter(id));
		}
		IHouseholdService householdService = new HouseholdService();
		Household household = householdService.findByPersonalId(personal_id);
		if(household != null)
			householdService.batchUpdateResidentHouseholdId(members_id, household);
		searchHouseholdDetail(request, response);

		
	}
	
	public void searchHouseholdDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//成员
		String personal_id = request.getParameter("personal_id");
		String iden = request.getParameter("iden");// 户主名或者集体名
		if(iden != null)
			personal_id = iden;
		IHouseholdService householdService = new HouseholdService();
		List<Resident> lists = householdService.searcHouseholdMember(personal_id);
		IResidentService residentService = new ResidentService();
		request.setAttribute("personal_id", personal_id);
		request.setAttribute("personal_name", residentService.findById(personal_id).getName());
		request.setAttribute("members", lists);
		//地址
		Household household = householdService.findByPersonalId(personal_id);
		IAddressService addressService = new AddressService();
		List<Address> addresses = addressService.findByHouseholdId(household.getHouseholder_id());
		request.setAttribute("addresses", addresses);
		//
		request.setAttribute("message", "successfully");
		ServletContext application = this.getServletContext();
		RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/admin/household_manage/household_detail.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public void deleteHouseholdMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String personal_id = request.getParameter("personal_id");
		String member_id = request.getParameter("member_id");
		if(personal_id.equals(member_id)) {
			request.setAttribute("message", "failed");
		}else {
			IHouseholdService householdService = new HouseholdService();
			householdService.deleteHouseholdMember(member_id);
			searchHouseholdDetail(request, response);
		}
	}
	
	public void addCollective(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//集体户籍注册
		String collective_name = request.getParameter("collective_name");
		Collective collective = new Collective();
		collective.setCollective_name(collective_name);
		ICollectiveService collectiveService = new CollectiveService();
		if(collectiveService.findByCollectiveName(collective_name) == null)
			collectiveService.insert(collective);
		
		
		response.sendRedirect("/HMS/HMS/admin/household_manage/add_collmember.html?collective_name="+URLEncoder.encode(collective_name,"UTF-8"));
	}
	public void searchCollective(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String collective_name = request.getParameter("collective_name");
		String member_id = request.getParameter("member_id");
		ICollectiveService collectiveService = new CollectiveService();
		IResidentService residentService = new ResidentService();
		Collective collective1 = collectiveService.findByCollectiveName(collective_name);
		Resident resident = residentService.findById(member_id);
		Collective collective2 = collectiveService.findById(resident.getCollective_id());
		List<Collective> collectives = new ArrayList<>();
		int flag = 0;
		if(collective1 != null) {
			collectives.add(collective1);
			flag++;
		}
		if(collective2 != null) {
			collectives.add(collective2);
			flag++;
		}
		request.setAttribute("collectives", collectives);
		request.setAttribute("message", "successfully "+ flag);
		ServletContext application = this.getServletContext();
		RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/admin/household_manage/search_householdreult.jsp");
		requestDispatcher.forward(request, response);
	}
	public void searchCollectiveAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ICollectiveService collectiveService = new CollectiveService();
		List<Collective> collectives = collectiveService.findAll();
		request.setAttribute("collectives", collectives);
		request.setAttribute("message", "successfully ");
		ServletContext application = this.getServletContext();
		RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/admin/household_manage/search_collectiveresult.jsp");
		requestDispatcher.forward(request, response);
	}
	public void addCollectiveMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count = Integer.parseInt(request.getParameter("count"));
		String collective_name = request.getParameter("collective_name");
		List<String> members_id = new ArrayList<>();
		for(int i=0; i<count; i++) {
			String id = "member_id"+i;
			members_id.add(request.getParameter(id));
		}
		ICollectiveService collectiveService = new CollectiveService();
		Collective collective = collectiveService.findByCollectiveName(collective_name);
		if(collective != null)
			collectiveService.batchUpdateResidentCollectiveId(members_id, collective);
		searchCollectiveDetail(request, response);
	}
	
	public void searchCollectiveDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String collective_name = request.getParameter("collective_name");
		String iden = request.getParameter("iden");// 户主名或者集体名
		if(iden != null)
			collective_name = iden;
		ICollectiveService collectiveService = new CollectiveService();
		List<Resident> lists = collectiveService.searcCollectiveMember(collective_name);
		request.setAttribute("collective_name", collective_name);
		request.setAttribute("members", lists);
		//
		IAddressService addressService = new AddressService();
		Collective collective = collectiveService.findByCollectiveName(collective_name);
		List<Address> addresses = addressService.findByCollectiveId(collective.getCollective_id());
		request.setAttribute("addresses", addresses);
		request.setAttribute("message", "successfully");
		
		ServletContext application = this.getServletContext();
		RequestDispatcher requestDispatcher = application.getRequestDispatcher("/HMS/admin/household_manage/collective_detail.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public void deleteCollectiveMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String member_id = request.getParameter("member_id");
		ICollectiveService collectiveService = new CollectiveService();
		collectiveService.deleteCollectiveMember(member_id);
		searchCollectiveDetail(request, response);
	}

	public void addAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		String iden = request.getParameter("iden");// 户主名或者集体名
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String town = request.getParameter("town");
		String village = request.getParameter("village");
		String expand = request.getParameter("expand");
		if (iden != "") {
			Address address = new Address();
			address.setProvince_name(province);
			address.setCity_name(city);
			address.setDistrict_name(district);
			address.setTown_name(town);
			address.setVillage_name(village);
			address.setExpand(expand);
			address.setImmigratory_date(new Date());
			if (category.equals("household")) {
				IHouseholdService householdService = new HouseholdService();
				Household household = householdService.findByPersonalId(iden);
				address.setHouseholder_id(household.getHouseholder_id());
				IAddressService addressService = new AddressService();
				addressService.setHouseholdEvacuationDate(household.getHouseholder_id());
				addressService.insert(address);
				this.searchHouseholdDetail(request, response);

			}else if(category.equals("collective")) {
				ICollectiveService collectiveService = new CollectiveService();
				Collective collective = collectiveService.findByCollectiveName(iden);
				address.setCollective_id(collective.getCollective_id());
				IAddressService addressService = new AddressService();
				addressService.setCollectiveEvacuationDate(collective.getCollective_id());
				addressService.insert(address);
				this.searchCollectiveDetail(request,response);
			}
			
		}
	}
}
