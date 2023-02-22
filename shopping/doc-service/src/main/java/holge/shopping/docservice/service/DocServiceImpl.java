package holge.shopping.docservice.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import commons.dto.ApiResponse;

@Service
public class DocServiceImpl {
	
	private DiscoveryClient discoveryClient;
	
	public DocServiceImpl(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}
	
	public ApiResponse getSwaggerURLs() {
		List<SwaggerUrl> urls = new LinkedList<>();
		
		List<String> blackListDoc = Arrays.asList("api-gateway");
		
		List<String> goodService = discoveryClient.getServices();
		
		goodService.stream()
			.filter(service -> !blackListDoc.contains(service))
			.forEach(serviceName -> 
				discoveryClient.getInstances(serviceName).forEach(ServiceIntance -> 
				urls.add(new SwaggerUrl(serviceName, ServiceIntance.getUri()+"/swagger-ui.html", serviceName)))
			);
		
		return new ApiResponse(false, "", Map.of("urls", urls));		
	}

}
