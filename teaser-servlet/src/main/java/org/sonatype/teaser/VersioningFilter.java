package org.sonatype.teaser;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

@Singleton
public class VersioningFilter
    implements Filter
{
    private final String name;

    private final String version;

    public VersioningFilter()
    {
        // TODO: this needs to come from Maven POM
        this( "teaser-servlet", "1.0.0-SNAPSHOT" );
    }

    public VersioningFilter( String name, String version )
    {
        this.name = name;
        this.version = version;
    }

    @Override
    public void init( FilterConfig filterConfig )
        throws ServletException
    {
    }

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
        throws IOException, ServletException
    {
        if ( response instanceof HttpServletResponse )
        {
            HttpServletResponse hresp = (HttpServletResponse) response;

            hresp.addHeader( "X-Server", name + "/" + version );
        }

        chain.doFilter( request, response );

    }

    @Override
    public void destroy()
    {
    }
}
