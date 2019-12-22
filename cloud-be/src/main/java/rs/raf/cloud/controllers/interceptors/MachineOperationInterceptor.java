package rs.raf.cloud.controllers.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MachineOperationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(MachineOperationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (needInstantResponse(request.getRequestURL().toString())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return false;
        }
        return true;
    }

    private boolean needInstantResponse(String requestUrl) {
        return requestUrl.endsWith("start") || requestUrl.endsWith("stop") || requestUrl.endsWith("restart");
    }

}
