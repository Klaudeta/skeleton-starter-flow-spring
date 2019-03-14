package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.server.BootstrapListener;
import com.vaadin.flow.server.BootstrapPageResponse;
import org.jsoup.nodes.Document;

/**
 * Modifies the Vaadin bootstrap page (the HTTP response) in order to
 * <ul>
 * <li>add splash screen advert as the body background image until application
 * gets fully loaded</li>
 * 
 * </ul>
 */
public class CustomBootstrapListener implements BootstrapListener {
	@Override
	public void modifyBootstrapPage(BootstrapPageResponse response) {

	    String js = "<script  type=\"text/javascript\" src=\"https://js.chargebee.com/v2/chargebee.js\" data-cb-site=\"we-r-test\"></script>\n";

        Document document = response.getDocument();
        document.head().append(js);



	}

}
