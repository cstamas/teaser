package org.sonatype.teaser.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StaticResource
    extends AbstractResource
{
    private final InputStream is;

    public StaticResource( final Resource resource, final InputStream is )
    {
        super( resource.getName(), resource.getLength(), resource.getMimeType(), resource.getModified() );

        this.is = is;
    }

    public StaticResource( final String name, final int length, final String mimeType, final long modified,
                           final InputStream is )
    {
        super( name, length, mimeType, modified );

        this.is = is;
    }

    @Override
    public void write( OutputStream os )
        throws IOException
    {
        copy( is, os );

        is.close();

        os.flush();
        os.close();
    }

    private static final int IO_BUFFER_SIZE = 4 * 1024;

    private static void copy( InputStream in, OutputStream out )
        throws IOException
    {
        byte[] b = new byte[IO_BUFFER_SIZE];
        int read;
        while ( ( read = in.read( b ) ) != -1 )
        {
            out.write( b, 0, read );
        }
    }
}
