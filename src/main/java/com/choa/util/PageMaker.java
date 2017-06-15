package com.choa.util;

public class PageMaker {

	private int curPage;
	private int perPage;
	private int perBlock;
	private MakePage makePage;
	private RowMaker rowMaker;
	
	public PageMaker(int curPage) {
		this(curPage,10);
	}
	
	public PageMaker(int curPage, int perPage) {
		this(curPage,perPage,5);
	}
	
	public PageMaker(int curPage, int perPage, int perBlock) {
		this.perPage = perPage;
		this.curPage = curPage;
		this.perBlock = perBlock;
		
	}

	
	public MakePage getMakePage(int totalCount) {
		makePage = new MakePage();
		makePage.makePage(totalCount, curPage, perPage, perBlock);
		return makePage;
	}

	public RowMaker getRowMaker(String kind, String search) {
		rowMaker = new RowMaker();
		rowMaker.setRow(curPage, perPage);
		rowMaker.setKind(kind);
		rowMaker.setSearch(search);
		
		return rowMaker;
	}
	
	public RowMaker getRowMaker() {
		rowMaker = new RowMaker();
		rowMaker.setRow(curPage, perPage);	
		
		return rowMaker;
	}
	
	
}
