
package test.test;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

/**
 * Example Sampler (non-Bean version)
 *
 * JMeter creates an instance of a sampler class for every occurrence of the
 * element in every thread. [some additional copies may be created before the
 * test run starts]
 *
 * Thus each sampler is guaranteed to be called by a single thread - there is no
 * need to synchronize access to instance variables.
 *
 * However, access to class fields must be synchronized.
 *
 * @version $Revision$
 */
public class ExampleSampler extends  AbstractSampler implements TestBean{
//AbstractSampler
    private static final long serialVersionUID = 240L;

    private static final Logger log = LoggingManager.getLoggerForClass();

    // The name of the property used to hold our data
    public static final String DATA = "ExampleSampler.data"; //$NON-NLS-1$

    private static AtomicInteger classCount = new AtomicInteger(0); // keep track of classes created

    // (for instructional purposes only!)

    public ExampleSampler() {
        classCount.incrementAndGet();
        trace("ExampleSampler()");
    }

    /**
     * {@inheritDoc}
     */
//    @Override
    public SampleResult sample(Entry e) {
        trace("sample()");
        SampleResult res = new SampleResult();
        boolean isOK = false; // Did sample succeed?
        String data = getData(); // Sampler data
        String response = null;

        res.setSampleLabel(getTitle());

//        org.apache.jmeter.engine.StandardJMeterEngine.stopThread(Thread.currentThread().getName());
        /*
         * Perform the sampling
         */
        res.sampleStart(); // Start timing
        try {

            System.out.println("aaa");

            JMeterVariables vars = JMeterContextService.getContext().getVariables();
            String checkpoint = vars.get("test");

            vars.put("testA","testAAA");

            System.out.println(checkpoint);

        	// Do something here ...

            response = Thread.currentThread().getName();

            /*
             * Set up the sample result details
             */
            res.setSamplerData(data);
            res.setResponseData(response, null);
            res.setDataType(SampleResult.TEXT);

            res.setResponseCodeOK();
            res.setResponseMessage("OK");// $NON-NLS-1$
            isOK = true;
        } catch (Exception ex) {
            log.debug("", ex);
            res.setResponseCode("500");// $NON-NLS-1$
            res.setResponseMessage(ex.toString());
        }
        res.sampleEnd(); // End timimg

        res.setSuccessful(isOK);

        return res;
    }

    /**
     * @return a string for the sampleResult Title
     */
    private String getTitle() {
        return this.getName();
    }

    /**
     * @return the data for the sample
     */
    public String getData() {
        return getPropertyAsString(DATA);
    }

    /*
     * Helper method
     */
    private void trace(String s) {
        String tl = getTitle();
        String tn = Thread.currentThread().getName();
        String th = this.toString();
        log.debug(tn + " (" + classCount.get() + ") " + tl + " " + s + " " + th);
    }
}
