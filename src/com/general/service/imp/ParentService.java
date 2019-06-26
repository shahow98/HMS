package com.general.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.general.dao.IParentDAO;
import com.general.dao.IResidentDAO;
import com.general.dao.imp.ParentDAO;
import com.general.dao.imp.ResidentDAO;
import com.general.model.Parent;
import com.general.model.Resident;
import com.general.service.IParentService;

public class ParentService extends BaseService<Parent, String> implements IParentService {
	private IParentDAO parentDAO = new ParentDAO();
	{
		super.setBaseDAO(parentDAO);
	}
	@Override
	public Map<Resident, String> searchRelationByResidentId(String resident_id) {
		// TODO Auto-generated method stub
		String[][] sheet = {
				{"祖父","祖母","外祖父","外祖母"},//4
				{"姑丈","姑妈","叔/伯","婶婶","父亲","母亲","舅舅","舅妈","姨丈","姨妈"},//9
				{"表兄弟","表姐妹","堂兄弟","堂姐妹","兄弟","配偶","姐妹"},//7
				{"侄子","侄女","儿","女","外甥","外甥女"},//6
				{"孙子","孙女","外孙子","外孙女"}//4
		};
		IResidentDAO residentDAO = new ResidentDAO();
		Map<Resident, String> relation = new HashMap<>();
		Parent parent1 = parentDAO.findById(resident_id);
		if(parent1 != null) {
			if(parent1.getFather_id() != null) {
				Resident father1 = residentDAO.findById(parent1.getFather_id());
				relation.put(father1, sheet[1][4]);//"父亲"
				
				List<Resident> father1_children = this.searchChildren(father1.getId());
				for(Resident resident : father1_children) {
					if(resident.getId().equals(resident_id))
						continue;
					if(resident.getSex() == 1) {
						relation.put(resident, sheet[2][4]);//"兄弟"
						
						List<Resident> father2_children = this.searchChildren(resident.getId());
						for(Resident resident2 : father2_children) {
							if(resident2.getSex() == 1) {
								relation.put(resident, sheet[3][0]);//"侄子"
							}else {
								relation.put(resident, sheet[3][1]);//"侄女"
							}
						}
					}else {
						relation.put(resident, sheet[2][6]);//"姐妹"
						
						List<Resident> father2_children = this.searchChildren(resident.getId());
						for(Resident resident2 : father2_children) {
							if(resident2.getSex() == 1) {
								relation.put(resident, sheet[3][4]);//"外甥"
							}else {
								relation.put(resident, sheet[3][5]);//"外甥女"
							}
						}
					}
				}
				
				Parent parent2 = parentDAO.findById(father1.getId());
				if (parent2 != null) {
					if(parent2.getFather_id() != null) {
						Resident father2 = residentDAO.findById(parent2.getFather_id());
						relation.put(father2, sheet[0][0]);//"祖父"
						
						List<Resident> father2_children = this.searchChildren(father2.getId());
						for(Resident resident : father2_children) {
							if(resident.getId().equals(father1.getId()))
								continue;
							if(resident.getSex() == 1) {
								relation.put(resident, sheet[1][2]);//"叔/伯"
								
								List<Resident> father3_children = this.searchChildren(resident.getId());
								for(Resident resident2 : father3_children) {
									if(resident2.getSex() == 1) {
										relation.put(resident, sheet[2][2]);//"堂兄弟"
									}else {
										relation.put(resident, sheet[2][3]);//"堂姐妹"
									}
								}
								
							}else {
								relation.put(resident, sheet[1][1]);//"姑妈"
								
								List<Resident> mother3_children = this.searchChildren(resident.getId());
								for(Resident resident2 : mother3_children) {
									if(resident2.getSex() == 1) {
										relation.put(resident, sheet[2][0]);//"表兄弟"
									}else {
										relation.put(resident, sheet[2][1]);//"表姐妹"
									}
								}
							}
						}
					}
					if(parent2.getMother_id() != null) {
						Resident mother2 = residentDAO.findById(parent2.getMother_id());
						relation.put(mother2, sheet[0][1]);//"祖母"
					}
				}
			}
			if(parent1.getMother_id() != null) {
				Resident mother1 = residentDAO.findById(parent1.getMother_id());
				relation.put(mother1, sheet[1][5]);//"母亲"
				
				Parent parent2 = parentDAO.findById(mother1.getId());
				if (parent2 != null) {
					if(parent2.getFather_id() != null) {
						Resident father2 = residentDAO.findById(parent2.getFather_id());
						relation.put(father2, sheet[0][2]);//"外祖父"
						
						List<Resident> father2_children = this.searchChildren(father2.getId());
						for(Resident resident : father2_children) {
							if(resident.getId().equals(father2.getId()))
								continue;
							if(resident.getSex() == 1) {
								relation.put(resident, sheet[1][6]);//"舅舅"
								
								List<Resident> father3_children = this.searchChildren(resident.getId());
								for(Resident resident2 : father3_children) {
									if(resident2.getSex() == 1) {
										relation.put(resident, sheet[2][0]);//"表兄弟"
									}else {
										relation.put(resident, sheet[2][1]);//"表姐妹"
									}
								}
							}else {
								relation.put(resident, sheet[1][9]);//"姨妈"
								
								List<Resident> mother3_children = this.searchChildren(resident.getId());
								for(Resident resident2 : mother3_children) {
									if(resident2.getSex() == 1) {
										relation.put(resident, sheet[2][0]);//"表兄弟"
									}else {
										relation.put(resident, sheet[2][1]);//"表姐妹"
									}
								}
							}
						}
					}
					if(parent2.getMother_id() != null) {
						Resident mother2 = residentDAO.findById(parent2.getMother_id());
						relation.put(mother2, sheet[0][3]);//"外祖母"
					}
				}
			}
			if(parent1.getSpouse_id() != null) {
				Resident spouse1 = residentDAO.findById(parent1.getSpouse_id());
				relation.put(spouse1, sheet[2][5]);//配偶
				
				List<Resident> mother1_children = this.searchChildren(spouse1.getId());
				for(Resident resident : mother1_children) {
					if(resident.getSex() == 1) {
						relation.put(resident, sheet[3][2]);//"儿"
						
						List<Resident> father2_children = this.searchChildren(resident.getId());
						for(Resident resident2 : father2_children) {
							if(resident2.getSex() == 1) {
								relation.put(resident2, sheet[4][0]);//"孙子"
							}else {
								relation.put(resident2, sheet[4][1]);//"孙女"
							}
						}
					}else {
						relation.put(resident, sheet[3][3]);//"女"
						
						List<Resident> mother2_children = this.searchChildren(resident.getId());
						for(Resident resident2 : mother2_children) {
							if(resident2.getSex() == 1) {
								relation.put(resident2, sheet[4][2]);//"外孙子"
							}else {
								relation.put(resident2, sheet[4][3]);//"外孙女"
							}
						}
					}
				}
			}
		}
		
		
		return relation;
	}
	@Override
	public List<Resident> searchChildren(String resident_id) {
		// TODO Auto-generated method stub
		IResidentDAO residentDAO = new ResidentDAO();
		Resident resident = residentDAO.findById(resident_id);
		List<Parent> parents = null;
		List<Resident> children = new ArrayList<>();
		if(resident != null) {
			if(resident.getSex() == 0) {
				String hql = "from Parent where mother_id = :mother_id";
				Map<String, Object> params = new HashMap<>();
				params.put("mother_id", resident.getId());
				parents = parentDAO.find(hql, params);
			}else {
				String hql = "from Parent where father_id = :father_id";
				Map<String, Object> params = new HashMap<>();
				params.put("father_id", resident.getId());
				parents = parentDAO.find(hql, params);
			}
			for (int i = 0; i < parents.size(); i++) {
				String children_id = parents.get(i).getSelf_id();
				Resident child = residentDAO.findById(children_id);
				if(child != null)
					children.add(child);
			}
		}
		return children;
	}
	
}
