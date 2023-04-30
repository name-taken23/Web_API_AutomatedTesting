package api.BrandTests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrandsItem{


	@JsonProperty("id")
	private Integer id;

	@JsonProperty("brand")
	private String brand;

	public Integer getId(){
		return id;
	}

	public String getBrand(){
		return brand;
	}
}