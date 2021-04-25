package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaApplicationMenu;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() throws InvalidStateException {
        final NassaApplicationMenu nasaApplicationMenu = NassaApplicationMenu.getInstance();
        final Supplier<ApplicationContext> applicationContextSupplier = nasaApplicationMenu::getApplicationContext; // todo
        final NassaContext nassaContext = NassaContext.getInstance();

        nassaContext.init();
        nasaApplicationMenu.printAvailableOptions();
        return applicationContextSupplier::get;
    }
}
