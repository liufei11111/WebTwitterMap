package com.web.map.app.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.web.map.app.model.KeywordTwitts;
import com.web.map.app.model.KeywordTwittsJsonObject;
import com.web.map.app.model.TwittEntry;
import com.web.map.controller.DynamoDbController;
import com.web.map.controller.InitEC2Controller;

/**
 * Servlet implementation class RetriveKeywordServlet
 */
public class RetriveKeywordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetriveKeywordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            response.getWriter().println("No keyword argument!");
            return;
        }
        InitEC2Controller.init();
        DynamoDbController dbController = new DynamoDbController()
                .withDbClient(InitEC2Controller.dynamo);
        KeywordTwitts keyTwitts = dbController.getKeywordTwittsById(keyword);
        
        if (keyTwitts != null) {
            Gson gson = new Gson();
            KeywordTwittsJsonObject ktjo=new KeywordTwittsJsonObject();
            ktjo.setId(keyTwitts.getId());
            ktjo.setKeyword(keyTwitts.getKeyword());
            ArrayList<TwittEntry> listOfEntries=new ArrayList<TwittEntry>();
            for (String str:keyTwitts.getTwitts().getListJsonTwitts()){
                Gson gsonBuilder = new GsonBuilder().create();
//                System.err.println(str);
                TwittEntry twittEntry = gsonBuilder.fromJson(str, TwittEntry.class);
//                String type = messageObjMap.get("messageType").toString();

//                switch (type) {
//                    case "song":
//                        try {
//                            Gson gson = new GsonBuilder().create();
//                            Song song = gson.fromJson(message, Song.class);
//                            ...
//                            ...
//                        } ...
//                    ...
//                }
//                TwittEntry twittEntry=gson.fromJson(str, TwittEntry.class);
                listOfEntries.add(twittEntry);
            }
            ktjo.setTwitts(listOfEntries);
            String jsonKeyTwitts=gson.toJson(ktjo);
                response.getWriter().println(jsonKeyTwitts);
        } else {
            response.getWriter().println("Keyword not found!");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.err
                .println("Client request for unimplemented Post at RetriveKeyServlet!");
    }

}
