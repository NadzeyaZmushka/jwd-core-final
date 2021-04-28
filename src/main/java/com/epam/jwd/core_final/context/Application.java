package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NasaApplicationMenu;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

public interface Application {

    static void start() throws InvalidStateException {
        final NasaApplicationMenu nasaApplicationMenu = NasaApplicationMenu.getInstance();
        final NasaContext nasaContext = NasaContext.getInstance();

        nasaContext.init();
        nasaApplicationMenu.printAvailableOptions();
        nasaApplicationMenu.takeFirstUserInput();
    }

}
