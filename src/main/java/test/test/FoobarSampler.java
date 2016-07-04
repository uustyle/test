package test.test;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;


public class FoobarSampler extends AbstractJavaSamplerClient {

//	@Override
	public SampleResult runTest(JavaSamplerContext ctx) {
		String searchWord = ctx.getParameter("searchWord");

		String s = String.format("search?setmkt=ja-JP&q=%s", searchWord);

		SampleResult sr = new SampleResult();
		sr.setSuccessful(true);
		sr.setResponseCodeOK();
		sr.setResponseMessageOK();
		sr.setResponseData(String.format("query=%s", s), "UTF-8");
		sr.setDataType(SampleResult.TEXT);

		return sr;
	}

	@Override
	public Arguments getDefaultParameters()
	{
		Arguments params = new Arguments();
		params.addArgument("searchWord", "linux");

		return params;

	}

}