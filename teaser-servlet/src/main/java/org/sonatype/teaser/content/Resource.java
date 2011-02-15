package org.sonatype.teaser.content;

import java.io.IOException;
import java.io.OutputStream;

public interface Resource
{
    int getLength();

    String getMimeType();

    long getModified();

    void write( OutputStream os )
        throws IOException;
}
