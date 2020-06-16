package com.aem.sample.core.models;
 
import com.adobe.cq.sightly.WCMUsePojo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.jcr.Node;
import javax.jcr.Property;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
  
public class YoutubeComponentC
  extends WCMUsePojo
{
  private YoutubePojo youtubepojo = null;
  protected static final Logger log = LoggerFactory.getLogger(YoutubeComponentC.class.getClass());
    
  public void activate()
    throws Exception
  {
    Node currentNode = (Node)getResource().adaptTo(Node.class);
    this.youtubepojo = new YoutubePojo();
      
    String videoid = "oeO6zwjxyQw";
     
    //Get the value that the author entered into the AEM dialog
    videoid = getProperties().get("videoid", "");
     
    log.info("**** Video ID " + videoid);
      
    String jsonstr = getJSON(videoid);
    JSONObject youtubeJson = new JSONObject(jsonstr);
    this.youtubepojo.setVideotitle(youtubeJson.get("title").toString());
    this.youtubepojo.setVideo_author_name(youtubeJson.get("author_name").toString());
    this.youtubepojo.setVideohtml(youtubeJson.getString("html").toString());
  }
    
  public YoutubePojo getYoutubepojo()
  {
    return this.youtubepojo;
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
}