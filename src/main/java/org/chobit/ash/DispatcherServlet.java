package org.chobit.ash;

import org.chobit.ash.core.bean.Handler;
import org.chobit.ash.helper.BeanHelper;
import org.chobit.ash.helper.ClassHelper;
import org.chobit.ash.helper.ControllerHelper;
import org.chobit.ash.helper.IocHelper;
import org.chobit.ash.tools.ClassUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author robin
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {


    @Override
    public void init(ServletConfig servletConfig) {
        loadHelpers();
        ServletContext context = servletConfig.getServletContext();
        ServletRegistration jspServlet = context.getServletRegistration("jsp");
        jspServlet.addMapping("*");
        ServletRegistration defaultServlet = context.getServletRegistration("default");
        defaultServlet.addMapping("*");
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String method = req.getMethod();
        String path = req.getPathInfo();
        Handler handler = ControllerHelper.getHandler(method, path);

        if (null == handler) {
            return;
        }

        Class<?> controllerClass = handler.getControllerClass();
        Object bean = BeanHelper.getBean(controllerClass);

        Map<String, String> paramMap = new HashMap<>(2);
        Enumeration<String> paramNames = req.getParameterNames();

        while (paramNames.hasMoreElements()){
            String paramName = paramNames.nextElement();
            String paramValue = req.getParameter(paramName);

            paramMap.put(paramName, paramValue);
        }




    }


    private void loadHelpers() {
        Class<?>[] classes = {
                BeanHelper.class,
                ClassHelper.class,
                ControllerHelper.class,
                IocHelper.class
        };

        for (Class<?> cls : classes) {
            ClassUtils.loadClass(cls.getName());
        }
    }

}
