package org.sonatype.teaser.content;

public abstract class AbstractResource
    implements Resource
{
    private final String name;

    private final int length;

    private final String mimeType;

    private final long modified;

    public AbstractResource( final String name, final int length, final String mimeType, final long modified )
    {
        this.name = name;
        this.length = length;
        this.mimeType = mimeType;
        this.modified = modified;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public int getLength()
    {
        return length;
    }

    @Override
    public String getMimeType()
    {
        return mimeType;
    }

    @Override
    public long getModified()
    {
        return modified;
    }
}
