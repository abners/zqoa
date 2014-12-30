package com.util;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @author peng
 * @since 2014年12月16日下午11:07:30
 */
public class SolrJUtil {
	private static HttpSolrServer solrServer;
	
	private static Logger logger = LoggerFactory.getLogger(SolrJUtil.class);
	
	public static HttpSolrServer getSolrInstance(){
		if(solrServer==null){
			initSolrServer();
		}
		return solrServer;
	}

	private static void initSolrServer() {
		solrServer = new HttpSolrServer(SystemProperties.getPropsValue("solrServerUrl"));
		solrServer.setConnectionTimeout(1000);
		solrServer.setSoTimeout(10000);
		solrServer.setMaxTotalConnections(100);
		solrServer.setParser(new XMLResponseParser());
	}
	public static void add(Collection<SolrInputDocument> docs){
		SolrServer solrServer = getSolrInstance();
		try {
			solrServer.add(docs);
			solrServer.commit();
		} catch (Exception e) {
			Log4j.errorLog(SolrJUtil.class,e);
		}
	}
	
	public static void deleteDocs(List<String> indexId) throws Exception{
		SolrServer solrServer = getSolrInstance();
		try {
			solrServer.deleteById(indexId);
			solrServer.commit();
		} catch (Exception e) {
			Log4j.errorLog(SolrJUtil.class,e);
			throw e;
		}
	}

	public static QueryResponse fullTextSearch(String searchContent) throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery("all:"+searchContent);
		QueryRequest qr = new QueryRequest(solrQuery); 
		QueryResponse qrs = null;
//		solrQuery.
		try {
			qrs = qr.process(getSolrInstance());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			Log4j.errorLog(SolrJUtil.class,e);
			throw e;
		}
		return qrs;
	}
}
