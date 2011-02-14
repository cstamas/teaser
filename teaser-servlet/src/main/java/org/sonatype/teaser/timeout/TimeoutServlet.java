package org.sonatype.teaser.timeout;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sonatype.teaser.AbstractTeaserServlet;

@Singleton
@SuppressWarnings( "serial" )
public class TimeoutServlet
    extends AbstractTeaserServlet
{
    @Override
    protected void service( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        try
        {
            // This will not work on GAE!
            Thread.sleep( 10 * 60 * 1000 );
        }
        catch ( InterruptedException e )
        {
            throw new ServletException( e );
        }
    }
}
