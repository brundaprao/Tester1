

	
	package APITestCases;

	import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

	public class GetMoviesTests implements Runnable{
		public static String authToken = null;
		public static HTTPUtils httpUtilsObj = new HTTPUtils("");
		public static long firstTime,count=0;
		public long temp;
		public static long secondTime;
		public static String authKey,serviceURL;
		public static Integer numOfTimes=1;
		
		
		
		
		public void run(){
			Thread.currentThread().setName("Next thread "+this.temp);
			System.out.println("running: "+ this.temp+ " and "+Thread.currentThread().getName());
			try{
				if(authToken != null){
					
				
								
					System.out.println("Start.........");
					this.testMoviewithValidData();
					Thread.sleep(100);
					this.testMovieWithNoData();
					
				
				}else{
					System.out.println("Authtoken not generated");
				}
				
			}catch(InterruptedException ex){
				System.out.println(ex);
			}

			
		}
		
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			try{
				// readGengeralPropertyFile();
				ExecutorService es = Executors.newFixedThreadPool(numOfTimes);
				// getAuthToken();
				/*
				 * authToken and ServiceURL can be configured to be read from
				 * property file in further enhancements using readPropertyFile()
				 * However, it is defined as a parameter now for simplicity
				 */
				authToken = "944eb1fe24a809b621c5664936fdf371";
				serviceURL="api.themoviedb.org/3";
				 Thread.sleep(1000);
				 System.out.println("Start.........");
				 
				try{
							GetMoviesTests obj = new GetMoviesTests();
							obj.temp = count;
							es.execute(obj);
							
						}catch(Exception ex){
							System.out.println();
						}
				
				 System.out.println("Test complete");
				 es.shutdown();
				
			}catch(Exception ex){
				System.out.println("Exception in Main "+ex);
			}
			
		}
		
		/*
		 * Testing API with Movie ID 550 which has valid data and status code 200
		 * Movie ID has been hardcoded since test cases are for testing status code and aim is to develop framework code 
		 * Movie ID can be later designed to read from Excel or XML
		 */
		
		public void testMoviewithValidData(){
			try{
				System.out.println("Test-01: Testing for Movie ID 550 which has valid data");
				System.out.println("Expected Result: Status code 200");
				//https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
				HTTPOut response = httpUtilsObj.doGet("https://"+serviceURL+"/movie/550?" ,authToken);
				
				if(response.statusCode==200){
					System.out.println("Status code 200 - Actual As Expected");
					System.out.println("Time taken for getting results :"+(secondTime-firstTime)+" MilliSecs");
				}else{
					System.out.println("No movie data for requested Movie ID ..."+response.responseString);
				}
				
				
				
			}catch(Exception ex){
				System.out.println("Exception in GetMovie by MovieID "+ex);
			}
		}
		
		/*
		 * Testing API with Movie ID 550 which has valid data and status code 200
		 * Movie ID has been hardcoded since test cases are for testing status code and aim is to develop framework code 
		 * Movie ID can be later designed to read from Excel or XML
		 */
		
		
		public void testMovieWithNoData(){
			try{
				System.out.println("Test-02: Testing for Movie ID 1 which does not have data");
				System.out.println("Expected Result: Status code 404");
				//https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
				HTTPOut response = httpUtilsObj.doGet("https://"+serviceURL+"/movie/1?" ,authToken);
				
				if(response.statusCode==200){
					System.out.println("Status code 200 - Actual As Expected");
					System.out.println("Time taken for getting results :"+(secondTime-firstTime)+" MilliSecs");
				}else{
					System.out.println("Response code"+response.statusCode);
					System.out.println("No movie data for requested Movie ID ..."+response.responseString);
				}
				
			
				
			}catch(Exception ex){
				System.out.println("Exception in GetMovie by MovieID "+ex);
			}
			
		}
		
		
	/*	public ArrayList<String> readPropertyFile(String filePath){
			ArrayList StringObj = new ArrayList<String>();
			try{
				 Properties prop = new Properties();
	             try {
	                     prop.load(new FileInputStream(filePath));
	                     String json = prop.getProperty("json");
	                     String noOfTimes = prop.getProperty("num");
	                     StringObj.add(0, json);
	                     StringObj.add(1, noOfTimes);
	                     
	             } catch (Exception e) {
	             }
				
			}catch(Exception ex){
				System.out.println("Exception in readPropertyFile "+ex);
			}
			return StringObj;
		}
		
		public static void readGengeralPropertyFile(){
			try{
				 Properties prop = new Properties();
	            try {
	                    prop.load(new FileInputStream("src/main/resources/general.properties"));
	                    authKey=prop.getProperty("authKey");
	                    serviceURL = prop.getProperty("serviceURL");
	                    
	            } catch (Exception e) {
	            	System.out.println("Exception while reading general properties file "+e);
	            }
				
			}catch(Exception ex){
				System.out.println("Exception in readGengeralPropertyFile "+ex);
			}
		}
		*/
	 	public static String getAuthToken() {
			try{
				if(authToken==null){ 
					authToken = authKey;
				}
			}
			catch(Exception ex){
				System.out.println("Exception in generateAuthtoken");
			}
			
			return authToken;
		}

		//generate unique timestamp
		public static long getTimestamp(){
			long timestamp = System.currentTimeMillis()/1000;
		    return timestamp;
		}
		
		public static long getTimestampInMilliSec(){
			long timestamp = System.currentTimeMillis();
		    return timestamp;
		}
		

	}



