package org.chobit.ash;

import org.chobit.ash.core.bean.Data;
import org.chobit.ash.core.bean.Handler;
import org.chobit.ash.core.bean.Param;
import org.chobit.ash.core.bean.View;
import org.chobit.ash.helper.BeanHelper;
import org.chobit.ash.helper.ClassHelper;
import org.chobit.ash.helper.ControllerHelper;
import org.chobit.ash.helper.IocHelper;
import org.chobit.ash.tools.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.chobit.ash.core.enums.MediaType.APPLICATION_JSON;
import static org.chobit.ash.tools.StringUtils.isBlank;

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
        Object controllerBean = BeanHelper.getBean(controllerClass);

        Map<String, String> paramMap = new HashMap<>(2);
        Enumeration<String> paramNames = req.getParameterNames();

        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = req.getParameter(paramName);

            paramMap.put(paramName, paramValue);
        }

        String url = CodecUtils.decodeURL(IOUtils.read(req.getInputStream()));
        if (isBlank(url)) {
            return;
        }

        paramMap.putAll(UrlUtils.parse(url));

        Param param = new Param(paramMap);
        Method requestMethod = handler.getRequestMethod();

        Object result = ReflectionUtils.invokeMethod(controllerBean, requestMethod, param);

        if (result instanceof View) {
            View view = (View) result;
            System.out.println(view);
        } else if (result instanceof Data) {
            Data data = (Data) result;
            Object model = data.getModel();
            if (null != model) {
                resp.setContentType(APPLICATION_JSON.value);
                resp.setCharacterEncoding("UTF-8");
                System.out.println(model);
            }
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
