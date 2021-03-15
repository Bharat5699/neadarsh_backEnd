package com.newadarsh.model.pagination;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.newadarsh.utils.AppUtil;

public class Dt_req<T> {
	/** The unique id. */

	
	/** The draw. */
	private String draw;
	
	/** The start. */
	private Integer start;
	
	/** The length. */
	private Integer length;
	
	/** The search. */
	private Search search;
	private  List<Order> order;
	
	/** The regex. */
	

	/** The columns. */
	private List<dt_column_spec> columns;
	
	/** The order. */

	/** The is global search. */
	private boolean isGlobalSearch;

	/**
	 * Instantiates a new data table request.
	 *
	 * @param request the request
	 */
//	public DataTableRequest(HttpServletRequest request) {
//		prepareDataTableRequest(request);
//	}
	
	/**
	 * Gets the unique id.
	 *
	 * @return the uniqueId
	 */

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}

	/**
	 * Sets the start.
	 *
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * Sets the length.
	 *
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * Gets the search.
	 *
	 * @return the search
	 */

	/**
	 * Sets the search.
	 *
	 * @param search the search to set
	 */
	

	/**
	 * Checks if is regex.
	 *
	 * @return the regex
	 */
	

	/**
	 * Gets the columns.
	 *
	 * @return the columns
	 */
	public List<dt_column_spec> getColumns() {
		return columns;
	}

	/**
	 * Sets the columns.
	 *
	 * @param columns the columns to set
	 */
	public void setColumns(List<dt_column_spec> columns) {
		this.columns = columns;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	

	/**
	 * Sets the order.
	 *
	 * @param order the order to set
	 */
	
	
	/**
	 * Gets the draw.
	 *
	 * @return the draw
	 */
	public String getDraw() {
		return draw;
	}

	/**
	 * Sets the draw.
	 *
	 * @param draw the draw to set
	 */
	public void setDraw(String draw) {
		this.draw = draw;
	}

	/**
	 * Checks if is global search.
	 *
	 * @return the isGlobalSearch
	 */
	public boolean isGlobalSearch() {
		return isGlobalSearch;
	}

	/**
	 * Sets the global search.
	 *
	 * @param isGlobalSearch the isGlobalSearch to set
	 */
	public void setGlobalSearch(boolean isGlobalSearch) {
		this.isGlobalSearch = isGlobalSearch;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public PaginationCriteria getPaginationRequest() {
		
		PaginationCriteria pagination = new PaginationCriteria();
		pagination.setPageNumber(this.getStart());
		pagination.setPageSize(this.getLength());
		
		SortBy sortBy = null;
		if(!AppUtil.isObjectEmpty(this.getOrder())) {
			sortBy = new SortBy();
			sortBy.addSort(this.getColumns().get(this.getOrder().get(0).getColumn()).getData(), this.getOrder().get(0).getDir());
		}
		System.out.println("====="+pagination.getPageSize());
		FilterBy filterBy = new FilterBy();
//		filterBy.setGlobalSearch(this.isGlobalSearch());
		for(dt_column_spec colSpec : this.getColumns()) {
		if(colSpec.isSearchable()) {
			if(!this.getSearch().getValue().equals("")) {
				if(!AppUtil.isObjectEmpty(this.getSearch()) || !AppUtil.isObjectEmpty(colSpec.getSearch())) {
				filterBy.addFilter(colSpec.getData(), this.getSearch().getValue());
				}
			}
		}
		}
		
		pagination.setSortBy(sortBy);
		pagination.setFilterBy(filterBy);
		
		return pagination;
	}
	

	/**
	 * Prepare data table request.
	 *
	 * @param request the request
	 */
	
	/** The max params to check. */

	
}
