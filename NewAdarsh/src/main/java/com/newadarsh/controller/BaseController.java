package com.newadarsh.controller;

import java.io.BufferedReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.newadarsh.model.TB_ITEM;
import com.newadarsh.model.TB_ITEM_MODEL;
import com.newadarsh.model.pagination.DataTableRequest;
import com.newadarsh.model.pagination.DataTableResults;
import com.newadarsh.model.pagination.Dt_req;
import com.newadarsh.model.pagination.PaginationCriteria;
import com.newadarsh.repository.GenericRepo;
import com.newadarsh.utils.AppUtil;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class BaseController {

	
	
	
	@Autowired
	private GenericRepo genericRepo;
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
		

	
//	@RequestMapping(value="/paginated", method=RequestMethod.POST)
//	@ResponseBody
//	public String listUsersPaginated(HttpServletRequest request, HttpServletResponse response, Model model) {
//
//		  StringBuffer jb = new StringBuffer();
//		  String line = null;
//		  try {
//		    BufferedReader reader = request.getReader();
//		    while ((line = reader.readLine()) != null)
//		      jb.append(line);
//		  } catch (Exception e) { /*report an error*/ }
//		  Dt_req<TB_ITEM> dataTableInRQ1 = new Gson().fromJson( jb.toString(), Dt_req.class);
//		  System.out.println("start=="+dataTableInRQ1.getStart());
//		  System.out.println("start=="+dataTableInRQ1.getLength());
//		  System.out.println("start=="+dataTableInRQ1.getColumns().size());
//		  
//		  PaginationCriteria pagination = dataTableInRQ1.getPaginationRequest();
////		DataTableRequest<TB_ITEM> dataTableInRQ = new DataTableRequest<TB_ITEM>(request);
////		PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
////		System.out.println("===========1"+pagination);
//		String baseQuery = "SELECT id,name, (SELECT COUNT(1) FROM items) AS total_records  FROM items";
//		String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);
//		
////		System.out.println("============"+paginatedQuery);
//		
//		Query query = entityManager.createNativeQuery(baseQuery, TB_ITEM_MODEL.class);
//		
//		@SuppressWarnings("unchecked")
//		List<TB_ITEM_MODEL> userList = query.getResultList();
//		
//		DataTableResults<TB_ITEM_MODEL> dataTableResult = new DataTableResults<TB_ITEM_MODEL>();
//		dataTableResult.setDraw(dataTableInRQ1.getDraw());
//		dataTableResult.setListOfDataObjects(userList);
//		if (!AppUtil.isObjectEmpty(userList)) {
//			dataTableResult.setRecordsTotal(userList.get(0).getTotalRecords()
//					.toString());
//			if (dataTableInRQ1.getPaginationRequest().isFilterByEmpty()) {
//				dataTableResult.setRecordsFiltered(userList.get(0).getTotalRecords()
//						.toString());
//			} else {
//				dataTableResult.setRecordsFiltered(Integer.toString(userList.size()));
//			}
//		}
//		return new Gson().toJson(dataTableResult);
//	}
//	
//	
	
//	@RequestMapping(value="/paginated", method=RequestMethod.POST)
//	@ResponseBody
//	public String listUsersPaginatedForOracle(HttpServletRequest request, HttpServletResponse response, Model model) {
//		
//		  StringBuffer jb = new StringBuffer();
//		  String line = null;
//		  try {
//		    BufferedReader reader = request.getReader();
//		    while ((line = reader.readLine()) != null)
//		      jb.append(line);
//		  } catch (Exception e) { /*report an error*/ }
//		  Dt_req<TB_ITEM> dataTableInRQ = new Gson().fromJson( jb.toString(), Dt_req.class);
//		  System.out.println("start=="+dataTableInRQ.getStart());
//		  System.out.println("start=="+dataTableInRQ.getLength());
//		  System.out.println("start=="+dataTableInRQ.getColumns().size());
//		  
//		  PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
//		String baseQuery = "SELECT name,id FROM items";
//		String paginatedQuery = AppUtil.buildPaginatedQueryForOracle(baseQuery, pagination);
//		
//		System.out.println(paginatedQuery);
//		
//		Query query = entityManager.createNativeQuery(paginatedQuery, TB_ITEM_MODEL.class);
//		
//		@SuppressWarnings("unchecked")
//		List<TB_ITEM_MODEL> userList = query.getResultList();
//		
//		DataTableResults<TB_ITEM_MODEL> dataTableResult = new DataTableResults<TB_ITEM_MODEL>();
//		dataTableResult.setDraw(dataTableInRQ.getDraw());
//		dataTableResult.setListOfDataObjects(userList);
//		if (!AppUtil.isObjectEmpty(userList)) {
//			dataTableResult.setRecordsTotal(userList.get(0).getTotalRecords()
//					.toString());
//			if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
//				dataTableResult.setRecordsFiltered(userList.get(0).getTotalRecords()
//						.toString());
//			} else {
//				dataTableResult.setRecordsFiltered(userList.get(0).getTotalFilterRecords()
//						.toString());
//			}
//		}
//		System.out.println("dsfsdf"+dataTableResult);
//		System.out.println("dsfsdf========"+ new Gson().toJson(dataTableResult));
//		return new Gson().toJson(dataTableResult);
//	}
}
