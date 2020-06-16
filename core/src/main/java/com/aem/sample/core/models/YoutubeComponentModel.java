package com.aem.sample.core.models;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import com.aem.sample.core.models.YoutubePojo;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables=Resource.class)
public class YoutubeComponentModel {
	protected static final Logger log = LoggerFactory.getLogger(YoutubeComponentModel.class.getClass());
	@Inject
	@Optional
	private String videoid;
	
	private YoutubePojo youtubepoj = null;
	
	public YoutubePojo getYoutubepoj() {
		return youtubepoj;
	}
	public void setYoutubepoj(YoutubePojo youtubepoj) {
		this.youtubepoj = youtubepoj;
	}
	private String videohtml;
	private String videotitle;
	private String video_author_name;
	private String jsonstr;
	
	 public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}
	public String getJsonstr() {
		return jsonstr;
	}
	@PostConstruct
	    protected void init() throws Exception {
	        log.info("videoid"+videoid);
	        jsonstr = getJSON(videoid);
	        this.youtubepoj = new YoutubePojo();
	        JSONObject youtubeJson = new JSONObject(jsonstr);
	        this.youtubepoj.setVideotitle(youtubeJson.get("title").toString());
	        this.youtubepoj.setVideo_author_name(youtubeJson.get("author_name").toString());
	        this.youtubepoj.setVideohtml(youtubeJson.getString("html").toString());
	        log.info("videoid"+jsonstr);
	    }
	 public static String getJSON(String videoid)
	  {
	    try
	    {
	      DefaultHttpClient httpClient = new DefaultHttpClient();
	        
	      HttpGet getRequest = new HttpGet("https://www.youtube.com/oembed?url=http://www.youtube.com/watch?v=" + videoid + "&format=json");
	      getRequest.addHeader("accept", "application/json");
	        
	      HttpResponse response = httpClient.execute(getRequest);
	      if (response.getStatusLine().getStatusCode() != 200)
	      {
	        log.info("exception");
	          
	        throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
	      }
	      BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	        
	      String myJSON = "";
	      String output;
	      while ((output = br.readLine()) != null) {
	        myJSON = myJSON + output;
	      }
	      log.info("**** Myjson: " + myJSON);
	      httpClient.getConnectionManager().shutdown();
	        
	      return myJSON;
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    return null;
	  }
	public String getVideoid() {
		return videoid;
	}

}
