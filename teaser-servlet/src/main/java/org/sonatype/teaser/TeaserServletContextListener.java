package org.sonatype.teaser;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class TeaserServletContextListener
    extends GuiceServletContextListener
{
    @Override
    protected Injector getInjector()
    {
        return Guice.createInjector( new TeaserModule() );
    }
}
