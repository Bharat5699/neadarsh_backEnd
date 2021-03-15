package com.newadarsh.controller.order;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

import com.newadarsh.model.order.TB_ORDER;
import com.newadarsh.model.pagination.DataTableResults;
import com.newadarsh.model.pagination.Dt_req;
import com.newadarsh.model.pagination.PaginationCriteria;
import com.newadarsh.payload.response.MessageResponse;
import com.newadarsh.repository.GenericRepo;

import com.newadarsh.repository.order.OrderRepository;
import com.newadarsh.utils.AppUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class OrderController {

	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	GenericRepo genericRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	 @GetMapping("/order")
	    public List<TB_ORDER> getItems() {
	        return orderRepository.findAll();
	    }
	@PostMapping("/order")
	public ResponseEntity<?> saveItem(@Valid @RequestBody TB_ORDER tB_ORDER) {
//		if (orderRepository.existsByName(TB_ORDER.getName())) {
//			return ResponseEntity.ok(new MessageResponse("Item is already taken!"));
//		}
		// Create new user's account
		
		String paginatedQuery="select  id,order_no,name,from_date,to_date,mobile_no,address from tb_order order by id desc limit 1 ";
		
		Date date=new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String currentYear=String.valueOf(localDate.getYear()).substring(2, 4);
		int secondYear;
		String order_no;
		if(localDate.getMonthValue()>3) {
			secondYear=localDate.getYear()+1;
			order_no=String.valueOf(localDate.getYear()).substring(2, 4)+"-"+secondYear;
			
		}
		else {
			secondYear=localDate.getYear()-1;			
			order_no=String.valueOf(secondYear).substring(2, 4) +"-"+localDate.getYear();
			
		}
		
		List<TB_ORDER> orderList =genericRepo.getOrderList(paginatedQuery);
		if(orderList.size()!=0) {
		String lastOrder_no = orderList.get(0).getOrder_no();
		
	    String lastOrder_noYear=lastOrder_no.split("/")[1];
		System.out.println("lastOrder_noYear=="+lastOrder_noYear);
		
		if(lastOrder_noYear.equals(order_no)) {
			order_no=Integer.parseInt(lastOrder_no.split("/")[0])+1  +"/"+ order_no;
			
		}
		else {
			order_no=1 +"/"+ order_no;
		}
		}
		else {
			order_no=1 +"/"+ order_no;
		}
		tB_ORDER.setOrder_no(order_no);
		
		System.out.println("order no"+order_no);

		orderRepository.save(tB_ORDER);

		return ResponseEntity.ok(new MessageResponse("Item Saved successfully!"));
	}
	
	@RequestMapping(value="/getorderList", method=RequestMethod.POST)
	@ResponseBody
	public String getItemList(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		  StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }
		  Dt_req<TB_ORDER> dataTableInRQ = new Gson().fromJson( jb.toString(), Dt_req.class);
		  System.out.println("start=="+dataTableInRQ.getStart());
		  System.out.println("start=="+dataTableInRQ.getLength());
		  System.out.println("start=="+dataTableInRQ.getColumns().size());
		  
		  PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
		String baseQuery = "SELECT id,name,from_date,to_date,address,mobile_no FROM tb_order";
		String paginatedQuery = AppUtil.buildPaginatedQueryForOracle(baseQuery, pagination);
		
		System.out.println(paginatedQuery);
		
//		Query query = entityManager.createNativeQuery(paginatedQuery, TB_ORDER.class);
		
//		@SuppressWarnings("unchecked")
//		List<TB_ORDER> userList = query.getResultList();
		List<TB_ORDER> userList =genericRepo.getOrderList(paginatedQuery);
		DataTableResults<TB_ORDER> dataTableResult = new DataTableResults<TB_ORDER>();
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
	
	
	@RequestMapping(value="/Searchorder", method=RequestMethod.POST)
	@ResponseBody
	public List<TB_ORDER> SearchorderList(@Valid @RequestBody TB_ORDER tB_ORDER) {
		
		String paginatedQuery="select  id,order_no,name,from_date,to_date,mobile_no,address from tb_order where id!=0 #WHERECLAUSE";
		String Where="";
		System.out.println("number"+tB_ORDER.getMobile_no());
		
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); 
		 if(tB_ORDER.getFrom_date()!=null) {
		    String frDate= formatter.format(tB_ORDER.getFrom_date()); 
		    Where+=" and from_date > '"+frDate+"'";   
		 }
		 if(tB_ORDER.getTo_date()!=null) {
		    String toDate= formatter.format(tB_ORDER.getTo_date()); 
		    Where+=" and to_date < '"+toDate+"'";   
		 }
		
		if(tB_ORDER.getName()!=null && !tB_ORDER.getName().equals("")) {
			Where+=" and name like '%"+tB_ORDER.getName()+"%'";
		}
		if(tB_ORDER.getAddress()!=null && !tB_ORDER.getAddress().equals("")) {
			Where+=" and address like '%"+tB_ORDER.getAddress()+"%'";
		}
		if(tB_ORDER.getMobile_no()!=null) {
			Where+=" and mobile_no='"+tB_ORDER.getMobile_no()+"'";
		}
		
		
		paginatedQuery=paginatedQuery.replace("#WHERECLAUSE", Where);
		List<TB_ORDER> orderList =genericRepo.getOrderList(paginatedQuery);
		return orderList;
	}
	
	
	@PutMapping("/order/{id}")
	public ResponseEntity<?> updateItem(@PathVariable Long id,@Valid @RequestBody TB_ORDER tB_ORDER) {
//		if (orderRepository.existsByName(TB_ORDER.getName())) {
//			return ResponseEntity.ok(new MessageResponse("Item is already taken!"));
//		}
		// Create new user's account
		TB_ORDER torder = orderRepository.findById(id).orElseThrow();
		torder.setName(tB_ORDER.getName());
		torder.setMobile_no(tB_ORDER.getMobile_no());
		torder.setAddress(tB_ORDER.getAddress());
		torder.setFrom_date(tB_ORDER.getFrom_date());
		torder.setTo_date(tB_ORDER.getTo_date());
		
		orderRepository.save(torder);

		return ResponseEntity.ok(new MessageResponse("Item Updated successfully!"));
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable Long id) {		
		orderRepository.deleteById(id);

		return ResponseEntity.ok(new MessageResponse("Item Deleted successfully!"));
	}
}

