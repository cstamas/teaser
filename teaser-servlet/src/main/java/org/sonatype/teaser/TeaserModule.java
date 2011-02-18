package org.sonatype.teaser;

import org.sonatype.teaser.content.ContentServlet;
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

        // content retrieved via /static/filename (served up by container as static file)
        // and retrived via /content/filename (served up by the ContentServlet.class)
        // are the reference for "good" server.
        // Note: the /content requests does obey to X- headers, so this is true when NO X- header is passed!
        // See ContentServlet for current X- headers.
        serve( "/content/*" ).with( ContentServlet.class );

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
        // generated.ext -- a random sized generated content byte-stream

        // pours slowly
        serve( "/content/slow/all/*" ).with( ContentServlet.class );
        // pours as can, but defers "tail" (last few bytes)
        serve( "/content/slow/tail/*" ).with( ContentServlet.class );

        serve( "/content/last-modified/none/*" ).with( ContentServlet.class );
        serve( "/content/last-modified/zero/*" ).with( ContentServlet.class );
        serve( "/content/last-modified/old/*" ).with( ContentServlet.class );
        serve( "/content/last-modified/now/*" ).with( ContentServlet.class );
        serve( "/content/last-modified/future/*" ).with( ContentServlet.class );
        serve( "/content/last-modified/garbage/*" ).with( ContentServlet.class );

        serve( "/content/content-length/none/*" ).with( ContentServlet.class );
        serve( "/content/content-length/zero/*" ).with( ContentServlet.class );
        serve( "/content/content-length/less/*" ).with( ContentServlet.class );
        serve( "/content/content-length/more/*" ).with( ContentServlet.class );
        serve( "/content/content-length/garbage/*" ).with( ContentServlet.class );

        serve( "/content/content-type/none/*" ).with( ContentServlet.class );
        serve( "/content/content-type/wrong/*" ).with( ContentServlet.class );
        serve( "/content/content-type/garbage/*" ).with( ContentServlet.class );

        // TODO: take this from Maven packaged JAR
        // Add some extra info to response
        filter( "/*" ).through( VersioningFilter.class );
    }
}
