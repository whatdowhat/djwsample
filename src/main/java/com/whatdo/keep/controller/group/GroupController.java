package com.whatdo.keep.controller.group;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.util.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.whatdo.keep.controller.MotherController;
import com.whatdo.keep.controller.member.SpecificationMmemberVO;
import com.whatdo.keep.service.dao.AddressCodeDAO;
import com.whatdo.keep.vo.AddressCodeVO;
import com.whatdo.keep.vo.GroupVO;
import com.whatdo.keep.vo.MemberVO;


@Controller
public class GroupController extends MotherController{
	
	@Autowired
	private AddressCodeDAO dao;
	
	private static Logger LOGGER = LoggerFactory.getLogger(GroupController.class);
	
	@RequestMapping(value = "/admin/group/page.do", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView grouppage(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session){
		
		
		LOGGER.debug("##grouppage enter");
		
		List<AddressCodeVO> citys = dao.getCitys();
		Map<String,String> param = new HashMap();
		param.put("cityCode", citys.get(0).getCityCode());
		List<AddressCodeVO> gus = dao.getGus(param);
		
		param = new HashMap();
		param.put("cityCode", citys.get(0).getCityCode());
		param.put("gunCode", gus.get(0).getGunCode());
		List<AddressCodeVO> dongs = dao.getDongs(param);
		
		modelAndView.addObject("cities", citys );
		modelAndView.addObject("gus",gus );
		modelAndView.addObject("dongs",dongs );
		modelAndView.setViewName("group/page");
		return modelAndView;
	}
	

	@RequestMapping(value = "/admin/group/commit", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map admingroupcommit(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,GroupVO inputform	) throws InterruptedException{
		
		LOGGER.debug("##admingroupcommit enter");
		LOGGER.debug("##data {}",inputform);
		GroupVO vo;
		
		vo = groupVORepository.findByGroupKey(inputform.getGroupKey());
		Map<String, Object> result =  new HashMap();
		if(vo == null) {
			result.put("already", false);
			Map<String,Object> condition = new HashMap();
			condition.put("name", inputform.getRepresentiveName());
			condition.put("phone", inputform.getRepresentiveCode());
			List<MemberVO> l = memVoRepository.findAll(SpecificationMmemberVO.withCondition(condition));
			System.out.println("MEMBER SHOW!!!" + l.toString());
			if(l.size() == 0) {
				result.put("exist", false);
			}else {
				inputform.setRegDt(new Date());
				vo = groupVORepository.save(inputform);
				result.put("exist", true);
			}
			
		}else {
			result.put("already", true);	
		}

		

		
		
		result.put("vo", vo);
		
		return result;
	}
	
	@RequestMapping(value = "/admin/group/list.do", method = { RequestMethod.GET})
	public ModelAndView admingrouplist(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,GroupVO vo
			) {		
		
		LOGGER.debug("##admingrouplist enter");
		
//		Map<String,Object> condition = new HashMap();//전체 검색
//		LOGGER.debug("##vo.getLength() enter"+ vo.getLength());
//		LOGGER.debug("##vo.getStart()  enter"+vo.getStart());
//		PageRequest p = PageRequest.of(vo.getStart(), vo.getLength() == 0 ? 10 : vo.getLength());
//		Page<GroupVO> page = groupVORepository.findAll(SpecificationGroupVO.withCondition(condition), p);
//		LOGGER.debug("##page  enter"+page.getContent().size());
		//modelAndView.addObject("page", page);
		modelAndView.setViewName("group/list");
		return modelAndView;
	}
	
	
	//finall
	@RequestMapping(value = "/admin/group/listtable.do", method = { RequestMethod.POST})
	@ResponseBody
	public Map admingrouplist2(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			, GroupVO vo ) throws JsonProcessingException {		
		
		LOGGER.debug("##admingrouplist enter" +vo.toString() );
		
		Map resultMap = new HashMap<String, Object>();
		Pageable p = getPageable(req,  vo.getStart(), vo.getLength());
		Map<String,Object> condition = new HashMap();//전체 검색
		Page<GroupVO> page = groupVORepository.findAll(SpecificationGroupVO.withCondition(condition), p);
		
		resultMap.put("recordsFiltered",page.getTotalElements());
		resultMap.put("recordsTotal",page.getContent().size());
		resultMap.put("data", page.getContent());
		
		return resultMap;
	}
	//finall
//	@RequestMapping(value = "/admin/group/listtable.do", method = { RequestMethod.POST})
//	@ResponseBody
//	public Map admingrouplist2(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
//			, GroupVO vo ) throws JsonProcessingException {		
//		
//		
////		int draw = Integer.parseInt(req.getParameter("draw"));
////		int start = Integer.parseInt(req.getParameter("start"));
////		int length = Integer.parseInt(req.getParameter("length"));
////		
//		LOGGER.debug("##admingrouplist enter" +vo.toString() );
//		LOGGER.debug("##admingrouplist enter" +vo);
//		
//		Map resultMap = new HashMap<String, Object>();
//		
//		Map<String, String[]> m = req.getParameterMap();
//		LOGGER.debug("##admingrouplist drawdrawdraw : {}",m);
//		
//		String[] columSize = m.get("columnsize");
//		int columSizeInt = Integer.valueOf(columSize[0]);
//		
//		String colmunNames[] = new String[columSizeInt];
//		for(int i=0; i<columSizeInt;i++) {
//			String[]  tem = m.get("columns["+i+"][data]");
//			colmunNames[i] = tem[0]; //컬럼 이름  및 순서 가져옴
//		}
//
//		String[]  orderTarget = m.get("order[0][column]");
//		String[]  orderDirectionTarget = m.get("order[0][dir]");
//		int orderTargetIndex= Integer.valueOf(orderTarget[0]);
//		String targetColumnName = colmunNames[orderTargetIndex];
//		System.out.println("final target column name :: "+targetColumnName);
//		
//		String orderDirectionTargetstr = orderDirectionTarget[0];
//		int editPage = vo.getStart() / vo.getLength(); 
//		Map<String,Object> condition = new HashMap();//전체 검색
//		Pageable p = PageRequest.of(editPage, vo.getLength() == 0 ? 3 : vo.getLength());
//		if(orderDirectionTargetstr.contentEquals("asc")) {
//			p = PageRequest.of(editPage, vo.getLength() == 0 ? 3 : vo.getLength(),  Sort.Direction.ASC,targetColumnName);
//		}else {
//			p = PageRequest.of(editPage, vo.getLength() == 0 ? 3 : vo.getLength(), Direction.DESC,targetColumnName);
//		}
//		
//		
//		Page<GroupVO> page = groupVORepository.findAll(SpecificationGroupVO.withCondition(condition), p);
//		
//		
//		LOGGER.debug("##vo.getLength() enter"+ vo.getLength());
//		LOGGER.debug("##vo.getStart()  enter"+vo.getStart());
//		LOGGER.debug("##page  enter"+page.getContent().size());
//		
//		
////		resultMap.put("draw",1);
////		resultMap.put("recordsTotal",page.getTotalElements());
////		resultMap.put("recordsFiltered",page.getContent().size());
////		
//		resultMap.put("recordsFiltered",page.getTotalElements());
//		resultMap.put("recordsTotal",page.getContent().size());
//		
//		resultMap.put("data", page.getContent());
//		
//		return resultMap;
////		return modelAndView;
//	}
//	@PostMapping("/admin/group/listtable.do")
//	public ResponseEntity<?> admingrouplist2(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session) throws JsonProcessingException {		
//		
//		
//		int draw = Integer.parseInt(req.getParameter("draw"));
//		int start = Integer.parseInt(req.getParameter("start"));
//		int length = Integer.parseInt(req.getParameter("length"));
//		
//		LOGGER.debug("##admingrouplist enter");
//		
//		
//		/*  Below is the example of datatables data we should return
//		*  [
//		*    { "col1" : "1", "col2" : "test1" },
//		*    { "col1" : "2", "col2" : "test2" }
//		*  ]
//		*/
//		
//		Map<String,Object> condition = new HashMap();//전체 검색
//		PageRequest p = PageRequest.of(0, 10);
//		Page<GroupVO> page = groupVORepository.findAll(SpecificationGroupVO.withCondition(condition), p);
//		
//		List<GroupVO> list = new ArrayList();
//		list = page.getContent();
//		JsonArray array = new JsonArray(list.size());
//		Gson gson = new Gson();
//		ObjectMapper mapper = new ObjectMapper();
//		res.setContentType("application/json");
//		StringBuilder sb = new StringBuilder();
//		sb.append("{");
//		
//		sb.append("\"draw\":");
//		sb.append(draw);
//		sb.append(",");
//		
//		sb.append("\"recordsTotal\":");
//		sb.append(page.getTotalElements());
//		sb.append(",");
//		
//		sb.append("\"recordsFiltered\":");
//		sb.append(page.getContent().size());
//		sb.append(",");
//		
//		sb.append("\"length\":");
//		sb.append(page.getTotalElements());
//		sb.append(",");
//		
//		sb.append("\"start\":");
//		sb.append(page.getPageable().getPageNumber());
//		sb.append(",");
//		
//		sb.append("\"data\":");
//		sb.append(mapper.writeValueAsString(list));
//		
//		sb.append("}");
//		
//		return ResponseEntity.ok(sb.toString());
////		return modelAndView;
//	}
	
	
	@RequestMapping(value = "/admin/group/getcity", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map getcity(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,AddressCodeVO inputform	) throws InterruptedException{
		
		
		LOGGER.debug("##getcity enter");
		LOGGER.debug("## !!!!!!!!!{} ", inputform);
		List<AddressCodeVO> citys = dao.getCitys();
		
		Map<String,String> param = new HashMap();
		param.put("cityCode", inputform.getCityCode());
		List<AddressCodeVO> gus = dao.getGus(param);
		
		param = new HashMap();
		param.put("cityCode", gus.get(0).getCityCode());
		param.put("gunCode", gus.get(0).getGunCode());
		List<AddressCodeVO> dongs = dao.getDongs(param);
		
		
		Map<String, Object> result =  new HashMap();
		result.put("cities", citys );
		result.put("gus",gus );
		result.put("dongs",dongs );
		
		return result;
	}
//	
	@RequestMapping(value = "/admin/group/getgun", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map getgun(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
			,AddressCodeVO inputform	) throws InterruptedException{
		
		
		LOGGER.debug("##getcity enter");
		LOGGER.debug("## !!!!!!!!!{} ", inputform);
		Map<String,String> param = new HashMap();
		param.put("cityCode", inputform.getCityCode());
		param.put("gunCode", inputform.getGunCode());
		List<AddressCodeVO> dongs = dao.getDongs(param);
		Map<String, Object> result =  new HashMap();
		result.put("dongs",dongs );
		
		return result;
	}
	
//	@RequestMapping(value = "/admin/getcity", method = { RequestMethod.GET,RequestMethod.POST })
//	@ResponseBody
//	public Map getcity(ModelAndView modelAndView, HttpServletRequest req, HttpServletResponse res,HttpSession session
//			,AddressCodeVO inputform	) throws InterruptedException{
//		
//		List<AddressCodeVO> citys = dao.getCitys();
//		LOGGER.debug("##getcity enter");
//		LOGGER.debug("## !!!!!!!!!{} ", inputform);
//		Map<String,String> param = new HashMap();
//		param.put("cityCode", inputform.getCityCode());
//		List<AddressCodeVO> gus = dao.getGus(param);
//		param = new HashMap();
//		param.put("cityCode", gus.get(0).getCityCode());
//		param.put("gunCode", gus.get(0).getGunCode());
//		List<AddressCodeVO> dongs = dao.getDongs(param);
//		
//		Map<String, Object> result =  new HashMap();
//		result.put("cities", citys );
//		result.put("gus",gus );
//		result.put("dongs",dongs );
//		return result;		
//	}

}
