package com.senontech.listener;


import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

public class SystemSpringContextListener extends ContextLoaderListener {

    private WebApplicationContext context;


    private void initBeforeSpring(){

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        initBeforeSpring();
        super.contextInitialized(event);
        context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        destroyBeforeSpring();
        super.contextDestroyed(event);
    }

    private void destroyBeforeSpring(){
       /* try {
            ((Scheduler) context.getBean("taskPush")).shutdown(true);
        } catch (SchedulerException e) {
            Log.addError(e.getMessage(),e);

        }*/
    }
}
