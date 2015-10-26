package com.web.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;

import com.web.service.EmployeeService;
import com.web.util.JsonUtil;

@SuppressWarnings("deprecation")
@Service
public class EmployeeServiceImpl extends GenericServiceClient implements EmployeeService {

	@Override
	public Boolean addEmployeeToRoster(String rosterId, String employeeId, Integer sequenceNo) throws Exception {
		LOGGER.debug("Inside addEmployeeToRoster");
		loadProperties();
		DefaultHttpClient httpClient = null;
		try {
			String method = "addEmployeeToRoster";
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(url + "/employee/" + method);
			LOGGER.debug("END POINT URL: " + postRequest.getURI());
			Map<String, Object> values = new HashMap<>();
			values.put("roster_id", rosterId);
			values.put("employee_id", employeeId);
			values.put("sequence_no", sequenceNo);
			String body = "{\"" + method + "\": " + JsonUtil.getJson(values) + "}";
			LOGGER.debug("Request body: " + body);
			StringEntity entity = new StringEntity(body);
			entity.setContentType("application/json");
			postRequest.setEntity(entity);
			HttpResponse response = httpClient.execute(postRequest);
			String string = IOUtils.toString(response.getEntity().getContent());
			LOGGER.debug("Response body: " + string);
			if (response.getStatusLine().getStatusCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			httpClient.getConnectionManager().shutdown();
			return Boolean.TRUE;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.close();
		}
		return Boolean.FALSE;
	}

}
