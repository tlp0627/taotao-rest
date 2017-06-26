package com.taotao.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {

	
	@Test
	public void addDocment() throws Exception{
		SolrServer solrServer = new HttpSolrServer("http://192.168.6.136:8080/solr");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "测试商品2");
		document.addField("item_price", 1111);
		solrServer.add(document);
		solrServer.commit();
	}
	@Test
	public void deleteDocument() throws Exception{
		SolrServer solrServer =new HttpSolrServer("http://192.168.6.136:8080/solr");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
	@Test
	public void queryDocument()throws Exception{
		SolrServer solrServer =new HttpSolrServer("http://192.168.6.136:8080/solr");
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setStart(20);
		solrQuery.setRows(50);
		QueryResponse response = solrServer.query(solrQuery);
		SolrDocumentList documentList = response.getResults();
		System.out.println("共查到记录:"+documentList.getNumFound());
		for ( SolrDocument solrDocument : documentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));

		}
		
	}
}
