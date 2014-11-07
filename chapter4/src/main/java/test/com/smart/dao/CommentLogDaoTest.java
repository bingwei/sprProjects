package test.com.smart.dao;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * CommentLogDao Tester.
 *
 * @author <Authors name>
 * @since <pre>11/04/2012</pre>
 * @version 1.0
 */
public class CommentLogDaoTest extends TestCase {
    public CommentLogDaoTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }



    public static Test suite() {
        return new TestSuite(CommentLogDaoTest.class);
    }
}
