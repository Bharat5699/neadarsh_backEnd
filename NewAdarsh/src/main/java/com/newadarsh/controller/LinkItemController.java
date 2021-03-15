package com.newadarsh.controller;

import java.io.BufferedReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.newadarsh.model.LINK_ITEM_MODEL;
import com.newadarsh.model.TB_ITEM;
import com.newadarsh.model.TB_LINK_ITEM;
import com.newadarsh.model.pagination.Dt_req;
import com.newadarsh.payload.response.MessageResponse;
import com.newadarsh.repository.GenericRepo;
import com.newadarsh.repository.LinkItemRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class LinkItemController {

	@Autowired
	LinkItemRepository linkItemRepository;
	
	@Autowired
	GenericRepo genericRepo;
	
	 @GetMapping("/linkitem")
	    public List<TB_LINK_ITEM> getItems() {
	        return linkItemRepository.findAll();
	    }
	    
	    @GetMapping("/linkitem/{id}")
		public List<TB_LINK_ITEM> getTutorialById(@PathVariable("id") long id) {
			String query="select b.id,b.item_id,b.subitem_id from link_item b where b.item_id="+id;
			return genericRepo.getlinkItemslist(query);

		
		}
	    
	@Transactional
	@PostMapping("/linkitem")
	public ResponseEntity<?> saveItem(HttpServletRequest request ) {
		
		// Create new user's account
		System.out.println("link==="+request.getParameter("linkitem"));
		 StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }
		  System.out.println("shdvhf"+jb.toString());
		 LINK_ITEM_MODEL link_model  = new Gson().fromJson( jb.toString(), LINK_ITEM_MODEL.class);
		 System.out.println("link=="+link_model.getLinkitem().size());
		 List<TB_LINK_ITEM> tlink=link_model.getLinkitem();
		  
 linkItemRepository.deleteLinkItem(tlink.get(0).getItem_id());
//		    System.out.println(deletedRecords);
		 
		 for(int i=0;i<tlink.size();i++) {
			 TB_LINK_ITEM tli=new TB_LINK_ITEM();
//			 tli.setItem_id(tlink.get(i).getItem_id());
//			 tli.setSubitem_id(tlink.get(i).getSubitem_id());
			 System.out.println("shkfjhbkf"+tlink.get(i).getSubitem_id());
			 tli=tlink.get(i);
			 linkItemRepository.save(tli);
			 
		 }

		return ResponseEntity.ok(new MessageResponse("Item Saved successfully!"));
	}
	
}
