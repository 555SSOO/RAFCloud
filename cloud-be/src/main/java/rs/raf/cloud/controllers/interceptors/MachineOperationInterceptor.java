package rs.raf.cloud.controllers.interceptors;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import rs.raf.cloud.services.MachineService;
import rs.raf.cloud.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MachineOperationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(MachineOperationInterceptor.class);

    @Autowired
    @Getter
    MachineService machineService;

    @Autowired
    @Getter
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String requestUrl = request.getRequestURL().toString();
        if (requestUrl.endsWith("auth")){
            return true;
        }
        return getUserService().validateToken(request.getParameter("token"), request.getParameter("username"));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String requestUrl = request.getRequestURL().toString();
        if (requestUrl.endsWith("start")){
            getMachineService().startMachine(request.getParameter("machineUID"));
        } else if (requestUrl.endsWith("stop")) {
            getMachineService().stopMachine(request.getParameter("machineUID"));
        } else if (requestUrl.endsWith("restart")) {
            getMachineService().restartMachine(request.getParameter("machineUID"));
        }
    }

}
