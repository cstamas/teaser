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
        // the default value of 600 seconds (10 minutes) should freak out any client
        final int seconds = asInt( getFirstPathElementFromRequest( req ), 600 );

        try
        {
            // GAE can "kill" us if seconds are more than 30, since it expects that processing ends in 30sec.
            Thread.sleep( seconds * 1000 );

            // send it somewhere
            resp.sendRedirect( req.getContextPath() + "/echo?from=timeout" );
        }
        catch ( InterruptedException e )
        {
            throw new ServletException( e );
        }
    }
}
