package org.sonatype.teaser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings( "serial" )
public class AbstractTeaserServlet
    extends HttpServlet
{
    protected String getRemainingPath( final HttpServletRequest req )
    {
        final String contextPath = req.getContextPath();

        final String servletPath = req.getServletPath();

        final String requestUri = req.getRequestURI();

        return requestUri.substring( contextPath.length() + servletPath.length() );
    }

    protected String[] getPathElements( final String str )
    {
        if ( str == null || str.length() == 0 )
        {
            return new String[0];
        }
        else
        {
            if ( str.startsWith( "/" ) )
            {
                return str.substring( 1 ).split( "/" );
            }
            else
            {
                return str.split( "/" );
            }
        }
    }

    protected String getFirstPathElementFromRequest( final HttpServletRequest req )
    {
        final String[] elements = getPathElements( getRemainingPath( req ) );

        if ( elements.length == 0 )
        {
            return "";
        }
        else
        {
            return elements[0];
        }
    }

    protected int asInt( final String str, final int defaultValue )
    {
        try
        {
            return Integer.parseInt( str );
        }
        catch ( NumberFormatException e )
        {
            return defaultValue;
        }
    }
}
