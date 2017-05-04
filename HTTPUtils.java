package APITestCases;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;


	public class HTTPUtils {
		public  int HTTP_OK = 200;
		public  String SERVER_URL ;

		
		
		public HTTPUtils(String url){
			super();
			SERVER_URL = url;
		}
		
		public static void GetMoviesTests(String[] args){
			try{
			/*HTTPUtils h = new HTTPUtils("");
			HTTPOut o = h.doGet("https://api.themoviedb.org/3/movie/550?api_key=%api_key%");
			
			System.out.println(o.responseString);
			System.out.println(o.statusCode);
			System.out.println(o.authToken);
			System.out.println(o.authStatus);*/
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		public  HTTPOut doGet( String url) throws HttpException,
				IOException, URISyntaxException {
			HTTPOut o = null;
			try{
				System.out.println(url);
				HttpClient httpClient = new DefaultHttpClient();
				HttpConnectionParams
						.setConnectionTimeout(httpClient.getParams(), 10000);
				HttpGet httpget = new HttpGet(url);
				BasicHeader basicHeader = new BasicHeader("Accept","application/json");
				httpget.setHeader(basicHeader);
				GetMoviesTests.firstTime =  GetMoviesTests.getTimestampInMilliSec();
				HttpResponse response = httpClient.execute(httpget);
				GetMoviesTests.secondTime =  GetMoviesTests.getTimestampInMilliSec();
				HttpEntity entity = response.getEntity();
				InputStream instream = entity.getContent();
				o = new HTTPOut();
				o.statusCode = response.getStatusLine().getStatusCode();
				//System.out.println(o.statusCode);
				o.responseString=read(instream);
			}
			catch(Exception ex){
				System.out.println("Exception in http get "+ex);
			
			}
			return o;
		}
		

		
		public HTTPOut doGet(String url, String authToken) {
			try{
				return doGet(url+"api_key="+authToken);
			}
			catch(Exception e) {
				HTTPOut o2 = new HTTPOut();
				o2.statusCode = -1;
				o2.responseString="error";
				return o2;
			}
		}
		
		public  HTTPOut doPost( String url,  String POSTText) throws URISyntaxException, HttpException, IOException {
			HTTPOut o = new HTTPOut();
			try{
				HttpClient httpClient = new DefaultHttpClient();
				HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000);
		 
				HttpPost httpPost = new HttpPost(url);
				StringEntity entity = new StringEntity(POSTText);
				
				BasicHeader basicHeader = new BasicHeader(HTTP.CONTENT_TYPE,"application/json");
				
				//httpPost.getParams().setBooleanParameter("http.protocol.expect-continue", false);
				entity.setContentType(basicHeader);
				httpPost.setEntity(entity);
				GetMoviesTests.firstTime =  GetMoviesTests.getTimestampInMilliSec();
				HttpResponse response = httpClient.execute(httpPost);
				GetMoviesTests.secondTime = GetMoviesTests.getTimestampInMilliSec();
				InputStream instream = response.getEntity().getContent();
				
				o.statusCode = response.getStatusLine().getStatusCode();
				o.responseString=read(instream);
				
			}
			catch(Exception ex){
				System.out.println("Exception in HTTP POST "+ex);
				
			}
			return o;
		}


			
		public HTTPOut doPost(String url, String POSTText, String authToken) {
			try{
				return doPost(url+"authtoken="+authToken,POSTText);
			}
			catch(Exception e) {
				HTTPOut o2 = new HTTPOut();
				o2.statusCode = -1;
				o2.responseString="error";
				return o2;
			}
		}
			
		public  HTTPOut doFormPost( String url,  String POSTText)
				throws URISyntaxException, HttpException, IOException {
	 
			final HttpClient httpClient = new DefaultHttpClient();
			HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000);
			
			HttpPost httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(POSTText, "UTF-8");
			BasicHeader basicHeader = new BasicHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded");
			httpPost.getParams().setBooleanParameter("http.protocol.expect-continue", false);
			entity.setContentType(basicHeader);
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			InputStream instream = response.getEntity().getContent();
			HTTPOut o = new HTTPOut();
			o.statusCode = response.getStatusLine().getStatusCode();
			o.responseString=read(instream);
			return o;

			}
		
		
		public HTTPOut doFormPost(String url, String POSTText, String authToken) {
			try{
				return doFormPost(url+"authtoken="+authToken,POSTText);
			}
			catch(Exception e) {
				HTTPOut o2 = new HTTPOut();
				o2.statusCode = -1;
				o2.responseString="error";
				return o2;
			}
		}
		
		public  HTTPOut doPut( String url,  String PUTText)
				throws URISyntaxException, HttpException, IOException {
			 HttpClient httpClient = new DefaultHttpClient();
			HttpConnectionParams
					.setConnectionTimeout(httpClient.getParams(), 10000);
	 
			HttpPut httpPut = new HttpPut(url);
			httpPut.addHeader("Accept", "application/json");
			httpPut.addHeader("Content-Type", "application/json");
			StringEntity entity = new StringEntity(PUTText, "UTF-8");
			entity.setContentType("application/json");
			httpPut.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPut);
			InputStream instream = response.getEntity().getContent();
			HTTPOut o = new HTTPOut();
			o.statusCode = response.getStatusLine().getStatusCode();
			o.responseString=read(instream);
			return o;

			}
		
		public HTTPOut doPut(String url, String PutText, String authToken) {
			try{
				return doPut(url+"authtoken="+authToken,PutText);
			}
			catch(Exception e) {
				HTTPOut o2 = new HTTPOut();
				o2.statusCode = -1;
				o2.responseString="error";
				return o2;
			}
		}

		public  HTTPOut doDelete( String url) throws HttpException,
				IOException, URISyntaxException {
			 HttpClient httpClient = new DefaultHttpClient();
			HttpConnectionParams
					.setConnectionTimeout(httpClient.getParams(), 10000);
	 
			HttpDelete httpDelete = new HttpDelete(url);
			httpDelete.addHeader("Accept",
					"text/html, image/jpeg, *; q=.2, */*; q=.2");
			HttpResponse response = httpClient.execute(httpDelete);
			InputStream instream = response.getEntity().getContent();
			HTTPOut o = new HTTPOut();
			o.statusCode = response.getStatusLine().getStatusCode();
			o.responseString=read(instream);
			return o;


			}
		
		public HTTPOut doDelete(String url, String authToken) {
			try{
				return doDelete(url+"authtoken="+authToken);
			}
			catch(Exception e) {
				HTTPOut o2 = new HTTPOut();
				o2.statusCode = -1;
				o2.responseString="error";
				return o2;
			}
		}

		private  String read(InputStream in) throws IOException {
			StringBuilder sb = new StringBuilder();
			BufferedReader r = new BufferedReader(new InputStreamReader(in), 1000);
			for (String line = r.readLine(); line != null; line = r.readLine()) {
				sb.append(line);
			}
			in.close();
			return sb.toString();
		}
	}

