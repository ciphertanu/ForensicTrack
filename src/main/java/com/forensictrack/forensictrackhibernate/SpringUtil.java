
package com.forensictrack.forensictrackhibernate;

import jakarta.servlet.ServletContext;
import org.springframework.context.ApplicationContext;

public class SpringUtil {

    private SpringUtil() {
    }

    public static <T> T getBean(ServletContext servletContext,
                                Class<T> beanClass) {

        ApplicationContext context =
                (ApplicationContext) servletContext.getAttribute(
                        SpringContextListener.SPRING_CONTEXT);

        if (context == null) {
            throw new IllegalStateException(
                    "Spring Application Context is not initialized.");
        }

        return context.getBean(beanClass);
    }
}
