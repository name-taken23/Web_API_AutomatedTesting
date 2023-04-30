package api.components;

import java.util.List;

import api.BrandTests.BrandsItem;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{

	@JsonProperty("brands")
	private List<BrandsItem> brands;

	@JsonProperty("responseCode")
	private Integer responseCode;

	public List<BrandsItem> getBrands(){
		return brands;
	}

	public Integer getResponseCode(){
		return responseCode;
	}
}