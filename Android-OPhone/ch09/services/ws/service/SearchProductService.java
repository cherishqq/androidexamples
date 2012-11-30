package service;
import java.util.HashMap;
import java.util.Map;


public class SearchProductService
{
	private static Map<String, Product> products = new HashMap<String, Product>();
	static
	{
		Product product = new Product();
		product.setName("ACA���� ");
		product.setProductNumber(200);
		product.setPrice(299);
		products.put("ACA����", product);
		
		product = new Product();
		product.setName("HTC Hero�ֻ�");
		product.setProductNumber(12);
		product.setPrice(3210);
		products.put("htc hero", product);
		
		product = new Product();
		product.setName("iphone�ֻ�");
		product.setProductNumber(20);
		product.setPrice(5100);
		products.put("iphone", product);
	}
    public Product getProduct(String productName)
    {
    	
    	return products.get(productName);
    }
}
