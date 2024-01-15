Please update aws credentials in com.interview.demo.constants -> AWSConstants 
public static final String AWS_ACCESS_KEY = "";
public static final String AWS_SECRET_KEY = "";
public static final String AWS_BUCKET_NAME = "";
public static final String AWS_REGION_NAME = "";

and then use curl

curl --location 'http://localhost:8080/api/v1/searchLogs?search_keyword=Hello%20World&from=2023-12-31T23%3A00&to=2024-01-01T22%3A00' --header 'Content-Type: application/json'

from and to should be send in the format of "YYYY-MM-DDTHH:MM:SS" in UTC time zone without zone