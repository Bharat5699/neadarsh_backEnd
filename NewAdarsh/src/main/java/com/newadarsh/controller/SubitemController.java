package com.newadarsh.controller;

import java.io.BufferedReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.newadarsh.model.TB_ITEM;
import com.newadarsh.model.TB_SUBITEM;
import com.newadarsh.model.pagination.DataTableResults;
import com.newadarsh.model.pagination.Dt_req;
import com.newadarsh.model.pagination.PaginationCriteria;
import com.newadarsh.payload.response.MessageResponse;
import com.newadarsh.repository.GenericRepo;
import com.newadarsh.repository.ItemRepository;
import com.newadarsh.repository.SubitemReository;
import com.newadarsh.utils.AppUtil;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class SubitemController {
	@Autowired
	SubitemReository subItemRepository;
	
	@Autowired
	GenericRepo genericRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@GetMapping("/subitem")
    public List<TB_SUBITEM> getItems() {
        return subItemRepository.findAll();
    }
    
	@PostMapping("/subitem")
	public ResponseEntity<?> saveItem(@Valid @RequestBody TB_SUBITEM TB_SUBITEM) {
		if (subItemRepository.existsByName(TB_SUBITEM.getName())) {
			return ResponseEntity.ok(new MessageResponse("Item is already taken!"));
		}
		// Create new user's account
		
		subItemRepository.save(TB_SUBITEM);

		return ResponseEntity.ok(new MessageResponse("Item Saved successfully!"));
	}
	
	@RequestMapping(value="/getsubitemList", method=RequestMethod.POST)
	@ResponseBody
	public String getItemList(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		  StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }
		  Dt_req<TB_SUBITEM> dataTableInRQ = new Gson().fromJson( jb.toString(), Dt_req.class);
		  System.out.println("start=="+dataTableInRQ.getStart());
		  System.out.println("start=="+dataTableInRQ.getLength());
		  System.out.println("start=="+dataTableInRQ.getColumns().size());
		  
		  PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
		String baseQuery = "SELECT name,id FROM sub_item";
		String paginatedQuery = AppUtil.buildPaginatedQueryForOracle(baseQuery, pagination);
		
		System.out.println(paginatedQuery);
		
//		Query query = entityManager.createNativeQuery(paginatedQuery, TB_SUBITEM.class);
		
//		@SuppressWarnings("unchecked")
//		List<TB_SUBITEM> userList = query.getResultList();
		
		List<TB_SUBITEM> userList =genericRepo.getSubItemslist(paginatedQuery);
		DataTableResults<TB_SUBITEM> dataTableResult = new DataTableResults<TB_SUBITEM>();
		dataTableResult.setDraw(dataTableInRQ.getDraw());
		dataTableResult.setListOfDataObjects(userList);
		if (!AppUtil.isObjectEmpty(userList)) {
			dataTableResult.setRecordsTotal(userList.get(0).getTotalRecords()
					.toString());
			if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
				dataTableResult.setRecordsFiltered(userList.get(0).getTotalRecords()
						.toString());
			} else {
				dataTableResult.setRecordsFiltered(userList.get(0).getTotalFilterRecords()
						.toString());
			}
		}
		System.out.println("dsfsdf"+dataTableResult);
		System.out.println("dsfsdf========"+ new Gson().toJson(dataTableResult));
		return new Gson().toJson(dataTableResult);
	}
	
	
	
	@PutMapping("/subitem/{id}")
	public ResponseEntity<?> updateItem(@PathVariable Long id,@Valid @RequestBody TB_SUBITEM TB_SUBITEM) {
		if (subItemRepository.existsByName(TB_SUBITEM.getName())) {
			return ResponseEntity.ok(new MessageResponse("Item is already taken!"));
		}
		// Create new user's account
		TB_SUBITEM item = subItemRepository.findById(id).orElseThrow();
		item.setName(TB_SUBITEM.getName());
		subItemRepository.save(item);

		return ResponseEntity.ok(new MessageResponse("Item Updated successfully!"));
	}
	
	@DeleteMapping("/subitem/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable Long id) {		
		subItemRepository.deleteById(id);

		return ResponseEntity.ok(new MessageResponse("Item Deleted successfully!"));
	}
}
