package geoservice;

import com.example.Example;
import com.example.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GeoJsonFactory {
	
	public static Example createAllResults(String location){
		GsonBuilder builder =new  GsonBuilder();
		Gson gson = builder.create();
		return gson.fromJson(GeoLoader.createJson(location), Example.class);
	}
	
	public static Result createFirstResult(String location){
		GsonBuilder builder =new  GsonBuilder();
		Gson gson = builder.create();
		Example example = gson.fromJson(GeoLoader.createJson(location), Example.class);
		Result result = example.getResults().get(0);
		return result;
	}
	
	public static void main(String[] args) {
//		Result result =createFirstResult("Hamburg");
//		Result result =createFirstResult("Lea-Grundig-Str 16, Berlin");
		Result result =createFirstResult("Leopoldstr 30, Berlin");
		
		System.out.println(result.getFormattedAddress());

		//		System.out.println(result.getAddressComponents().stream().forEach(System.out::println));
		//result.getAddressComponents().stream().forEach();

		System.out.println(result.getGeometry().getLocation());
		System.out.println(result.getAddressComponents().get(0).getLongName());
		System.out.println(result.getAddressComponents().get(2).getLongName());
	}

}
