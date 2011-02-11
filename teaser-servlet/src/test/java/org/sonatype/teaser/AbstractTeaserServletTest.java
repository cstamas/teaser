package org.sonatype.teaser;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class AbstractTeaserServletTest
{
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper();

    @Before
    public void setUp()
    {
        helper.setUp();
    }

    @After
    public void tearDown()
    {
        helper.tearDown();
    }
}
