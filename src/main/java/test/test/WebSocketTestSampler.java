package test.test;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;


public class WebSocketTestSampler extends AbstractJavaSamplerClient {

//	@Override
	public SampleResult runTest(JavaSamplerContext ctx) {
		String searchWord = ctx.getParameter("searchWord");

		String s = String.format("search?setmkt=ja-JP&q=%s", searchWord);

        System.out.println("1");

        try {
            // open websocket
//            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("wss://real.okcoin.cn:10440/websocket/okcoinapi"));
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:8080/WebSocket2/wssdemo"));

            System.out.println("2");

            // add listener
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            System.out.println("3");

            // send message to websocket
            clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");

            System.out.println("4");


            // wait 5 seconds for messages from websocket
            Thread.sleep(50000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }

        System.out.println("5");

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