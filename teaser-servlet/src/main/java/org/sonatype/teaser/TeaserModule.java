package org.sonatype.teaser;

import org.sonatype.teaser.echo.EchoServlet;
import org.sonatype.teaser.never.NeverServlet;
import org.sonatype.teaser.redirect.RedirectServlet;
import org.sonatype.teaser.status.StatusServlet;
import org.sonatype.teaser.timeout.TimeoutServlet;

import com.google.inject.servlet.ServletModule;

public class TeaserModule
    extends ServletModule
{
    @Override
    protected void configureServlets()
    {
        serve( "/echo/*" ).with( EchoServlet.class, new MapBuilder().put( "prefix", "/echo" ).build() );
        serve( "/status/*" ).with( StatusServlet.class, new MapBuilder().put( "prefix", "/status" ).build() );
        serve( "/never/*" ).with( NeverServlet.class, new MapBuilder().put( "prefix", "/never" ).build() );
        serve( "/redirect/*" ).with( RedirectServlet.class, new MapBuilder().put( "prefix", "/redirect" ).build() );
        serve( "/timeout/*" ).with( TimeoutServlet.class, new MapBuilder().put( "prefix", "/timeout" ).build() );

        // TODO: take this from Maven packaged JAR
        filter( "/*" ).through( new VersioningFilter( "teaser-servlet", "1.0.0-SNAPSHOT" ) );
    }
}
