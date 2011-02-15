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
        // just accept anything starting with "/echo"
        serve( "/echo" ).with( EchoServlet.class );
        serve( "/echo/*" ).with( EchoServlet.class );

        // explicitly need which status to return
        serve( "/status/*" ).with( StatusServlet.class );

        // without number in path, redirect will freak you out
        serve( "/redirect/*" ).with( RedirectServlet.class );

        // without number in path, timeout will freak you out
        serve( "/timeout/*" ).with( TimeoutServlet.class );

        // exact path
        serve( "/never" ).with( NeverServlet.class );

        // content delivery
        
        serve( "/content/*" ).with( NeverServlet.class );

        // "static" resources:
        // text.txt -- a static text doco
        // text.html -- a static HTML doco
        // text.pdf -- a static PDF document
        // image.jpg -- a static JPEG picture
        // image.png -- a static PNG picture
        // image.gif -- a static GIF picture
        // image.svg -- a static SVG picture
        // archive.zip -- a static ZIP file
        
        // "generated" resources:
        // random.ext -- a random sized random content byte-stream
        
        // pours slowly
        serve( "/content/slow/all/*" ).with( NeverServlet.class );
        // pours as can, but defers "tail" (last few bytes)
        serve( "/content/slow/tail/*" ).with( NeverServlet.class );

        serve( "/content/last-modified/none/*" ).with( NeverServlet.class );
        serve( "/content/last-modified/zero/*" ).with( NeverServlet.class );
        serve( "/content/last-modified/old/*" ).with( NeverServlet.class );
        serve( "/content/last-modified/now/*" ).with( NeverServlet.class );
        serve( "/content/last-modified/future/*" ).with( NeverServlet.class );
        serve( "/content/last-modified/garbage/*" ).with( NeverServlet.class );

        serve( "/content/content-length/none/*" ).with( NeverServlet.class );
        serve( "/content/content-length/zero/*" ).with( NeverServlet.class );
        serve( "/content/content-length/less/*" ).with( NeverServlet.class );
        serve( "/content/content-length/more/*" ).with( NeverServlet.class );
        serve( "/content/content-length/garbage/*" ).with( NeverServlet.class );

        serve( "/content/content-type/none/*" ).with( NeverServlet.class );
        serve( "/content/content-type/wrong/*" ).with( NeverServlet.class );
        serve( "/content/content-type/garbage/*" ).with( NeverServlet.class );

        // TODO: take this from Maven packaged JAR
        // Add some extra info to response
        filter( "/*" ).through( new VersioningFilter( "teaser-servlet", "1.0.0-SNAPSHOT" ) );
    }
}
