
package com.forensictrack.forensictrackhibernate;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebListener
public class SpringContextListener implements ServletContextListener {

    public static final String SPRING_CONTEXT =
            "springApplicationContext";

    @Override
    public void contextInitialized(ServletContextEvent event) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ServletContext servletContext = event.getServletContext();

        servletContext.setAttribute(SPRING_CONTEXT, context);

        System.out.println("Spring Application Context Started Successfully!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        ServletContext servletContext = event.getServletContext();

        Object contextObject =
                servletContext.getAttribute(SPRING_CONTEXT);

        if (contextObject instanceof AnnotationConfigApplicationContext) {
            ((AnnotationConfigApplicationContext) contextObject).close();
        }

        System.out.println("Spring Application Context Closed.");
    }
}

