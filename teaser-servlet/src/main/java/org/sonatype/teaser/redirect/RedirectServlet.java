package org.sonatype.teaser.redirect;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sonatype.teaser.AbstractTeaserServlet;

@Singleton
@SuppressWarnings( "serial" )
public class RedirectServlet
    extends AbstractTeaserServlet
{
    @Override
    protected void service( HttpServletRequest req, HttpServletResponse resp )
        throws ServletException, IOException
    {
        // the default value of 30 should freak out any sane client
        final int redirectCount = asInt( getFirstPathElementFromRequest( req ), 30 );

        if ( redirectCount > 0 )
        {
            // dec the count and bounce
            resp.sendRedirect( req.getContextPath() + req.getServletPath() + "/" + ( redirectCount - 1 ) );
        }
        else if ( redirectCount == 0 )
        {
            // send it somewhere
            resp.sendRedirect( req.getContextPath() + "/echo?from=redirect" );
        }
    }
}
