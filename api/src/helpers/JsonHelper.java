package helpers;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

abstract public class JsonHelper {
	static public String getJsonRequest(HttpServletRequest request) throws Exception {
		StringBuilder stringJson = new StringBuilder();
		BufferedReader reader = request.getReader();
		String linha;
		
		while((linha = reader.readLine()) != null) {
			stringJson.append(linha);
		}
		
		return stringJson.toString();
	}
}
