package com.fih.auth.server.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fih.auth.server.common.Result;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author songxiaolong
 * @CreateDate: 2020-03-28
 */
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        gen.writeStartObject();
        gen.writeStringField("error", String.valueOf(value.getHttpErrorCode()));
        gen.writeStringField("message", value.getMessage());
        gen.writeStringField("path", request.getServletPath());
        gen.writeStringField("timestamp", String.valueOf(new Date().getTime()));
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeStringField(key, add);
            }
        }
        gen.writeEndObject();*/

        HttpServletResponse response=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        Result.print(response,Result.buildFail(value.getHttpErrorCode(),value.getMessage()));
    }
}
