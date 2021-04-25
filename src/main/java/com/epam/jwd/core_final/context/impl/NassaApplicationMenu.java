package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;

public class NassaApplicationMenu implements ApplicationMenu {

    private static NassaApplicationMenu instance;

    private NassaApplicationMenu() {
    }

    public static NassaApplicationMenu getInstance() {
        if (instance == null) {
            instance = new NassaApplicationMenu();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return NassaContext.getInstance();
    }
}
