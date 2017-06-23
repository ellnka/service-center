package com.source.it.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextInitializerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("!!!!!!-----Context Inisialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
