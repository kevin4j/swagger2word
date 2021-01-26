package org.word;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

import java.util.Map;

public class mianClass {


    public static void main(String[] args) {
        OpenAPI openAPI= new OpenAPIV3Parser().read("http://10.5.4.242:8081/v1//v2/api-docs");
        Paths paths = openAPI.getPaths();
        Components components = openAPI.getComponents();
        Map<String, Schema> schemas = components.getSchemas();
        paths.getExtensions();
//        SwaggerParseResult swaggerParseResult = new OpenAPIParser().readLocation("http://10.5.4.242:8081/v1//v2/api-docs", null, null);
//        OpenAPI openAPI1 = swaggerParseResult.getOpenAPI();
//        System.out.println(openAPI1);

    }
}
