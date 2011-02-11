package org.sonatype.teaser;

import org.sonatype.teaser.echo.EchoServlet;
import org.sonatype.teaser.status.StatusServlet;

import com.google.inject.servlet.ServletModule;

public class TeaserModule
    extends ServletModule
{
    @Override
    protected void configureServlets()
    {
        serve( "/echo/*" ).with( EchoServlet.class, new MapBuilder().put( "prefix", "/echo" ).build() );
        serve( "/status/*" ).with( StatusServlet.class, new MapBuilder().put( "prefix", "/status" ).build() );
    }
}
