
package com.wstutorial.ws;

import com.contentservice.contenttype.ContentType;
import com.contentservice.contenttype.ContentInsights;
import com.wstutorial.ws.contentservice.*;
//import jdk.jfr.ContentType;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Endpoint
public class ContentServiceEndpoint {
	private static final String NAMESPACE_URI = "http://www.wstutorial.com/ws/ContentService";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "CheckAvailabilityRequest" )
	@ResponsePayload
	public CheckAvailabilityResponse getContents(@RequestPayload CheckAvailabilityRequest request)throws Exception  {
		ObjectFactory factory = new ObjectFactory();
		CheckAvailabilityResponse response = factory.createCheckAvailabilityResponse();

		List<ContentType> allContents = getContents();

		response.getAvailableContents().addAll(allContents);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "InsightsRequest")
	@ResponsePayload
	public InsightsResponse getContentInsights(@RequestPayload InsightsRequest request) throws Exception {
		ObjectFactory factory = new ObjectFactory();
		InsightsResponse response = factory.createInsightsResponse();

		Long serialNumber = request.getContentNumber();
		ContentInsights contentInsights = getContentsInsights(serialNumber);

		if (contentInsights != null) {
			response.setInsightsInfo(contentInsights);
		}
		return response;
	}


	private List<ContentType> getContents() {
		List<ContentType> contents = new ArrayList<ContentType>();
		ContentType content1 = new ContentType();
		content1.setContentNumber(1l);
		content1.setType("sports");
		content1.setHeadline("Bangladesh vs Afghanistan ");
		content1.setContentText("It's the last Super Eight game of the T20 World Cup 2024 and it's open season as far as the fourth semi-final spot is concerned, with Afghanistan and Bangladesh, who will be out in the middle, and Australia, who can at best wait and watch, all in the fray.");

		ContentType content2 = new ContentType();
		content1.setContentNumber(2l);
		content1.setType("sports");
		content1.setHeadline("Afghanis are in Semis");
		content1.setContentText("It's simple for Afghanistan - win and get into the semi-finals; lose and they are out, since their NRR will stay below Australia's even with a super-over defeat.");

		contents.add(content1);
		contents.add(content2);
		return contents;
	}

    private ContentInsights getContentsInsights(Long serialNumber) {

        // Dummy data for demonstration
        ContentInsights insights1 = new ContentInsights();
        insights1.setContentNumber(1L);
        insights1.setReach(new BigDecimal("10000"));
        insights1.setEngagement(new BigDecimal("1000"));
        insights1.setClicks(new BigDecimal("10"));

        ContentInsights insights2 = new ContentInsights();
        insights2.setContentNumber(2L);
        insights2.setReach(new BigDecimal("20000"));
        insights2.setEngagement(new BigDecimal("2000"));
        insights2.setClicks(new BigDecimal("20"));

        if (insights1.getContentNumber() == serialNumber) {
            return insights1;
        }

        if (insights2.getContentNumber() == serialNumber) {
            return insights2;
        }
		return null;
    }

}