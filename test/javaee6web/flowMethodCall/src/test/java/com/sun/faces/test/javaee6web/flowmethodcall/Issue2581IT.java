/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.faces.test.javaee6web.flowmethodcall;

import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import static org.junit.Assert.assertTrue;

public class Issue2581IT {

    private String webUrl;
    private WebClient webClient;

    @Before
    public void setUp() {
        webUrl = System.getProperty("integration.url");
        webClient = new WebClient();
    }

    @After
    public void tearDown() {
        webClient.close();
    }

    @Test
    public void testBuilderDefinedFlowWithMethodCall() throws Exception {
        doTest("start_a");
    }

    @Test
    public void testXmlDefinedFlowWithMethodCall() throws Exception {
        doTest("start_b");
    }

    public void doTest(String startId) throws Exception {
        HtmlPage page = webClient.getPage(webUrl);

        assertTrue(page.getBody().asText().contains("Outside of flow"));

        HtmlSubmitInput button = (HtmlSubmitInput) page.getElementById(startId);
        page = button.click();

        button = (HtmlSubmitInput) page.getElementById("outcome-from-method");
        page = button.click();

        String pageText = page.asText();
        assertTrue(pageText.contains("Last page in the flow"));

        page = webClient.getPage(webUrl);

        button = (HtmlSubmitInput) page.getElementById("start_a");
        page = button.click();

        button = (HtmlSubmitInput) page.getElementById("outcome-from-markup");
        page = button.click();

        pageText = page.asText();
        assertTrue(pageText.contains("voidMethod called in flow-a"));
    }
}
