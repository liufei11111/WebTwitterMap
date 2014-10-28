package com.web.map.app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.map.controller.DynamoDbController;
import com.web.map.controller.InitEC2Controller;

/**
 * Servlet implementation class InitServlet
 */

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    if (request.getMethod().equals("HEAD")) return;
	    else {
	        InitEC2Controller.init();
	        DynamoDbController dbController=new DynamoDbController().withDbClient(InitEC2Controller.dynamo);
	        dbController.createDynamoDbTable();
//	        String[] keywords=Constants.keywords;
//	        File file=new File("./hello.txt");
//	        System.out.println(file.getAbsolutePath());
//	        PrintWriter pw=new PrintWriter(new File("./hello.txt"));
//	        pw.println("dsf");
//	        pw.close();
//	        for (String key:keywords){
//	            
//	            KeywordTwitts temp=ProcessData.processDataFile("save_"+key+".txt", key);
//	            dbController.saveKeywordTwitts(temp);
//	        }
//	        Twitts twit=new Twitts();
//	        ArrayList<String> listTwitts=new ArrayList<String>();
//	        listTwitts.add("jfslakjflsjdfklsaklf");
//	        listTwitts.add("sfsafssd");
//	        listTwitts.add("1123213123112");
//	        twit.setListJsonTwitts(listTwitts);
//	        KeywordTwitts kTwitts=new KeywordTwitts();
//	        kTwitts.setId("food");
//	        kTwitts.setKeyword("food");
//	        kTwitts.setTwitts(twit);
//	        dbController.saveKeywordTwitts(kTwitts);
	        response.getWriter().println("Table created!");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    if (request.getMethod().equals("HEAD")) return;
	    System.err.println("Client request for unimplemented Post at InitServlet!");
	}

}
