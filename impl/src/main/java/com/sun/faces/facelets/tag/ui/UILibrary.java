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

package com.sun.faces.facelets.tag.ui;

import com.sun.faces.facelets.component.UIRepeat;
import com.sun.faces.facelets.tag.AbstractTagLibrary;

/**
 * @author Jacob Hookom
 */
public final class UILibrary extends AbstractTagLibrary {

    public final static String Namespace = "http://java.sun.com/jsf/facelets";
    public final static String XMLNSNamespace = "http://xmlns.jcp.org/jsf/facelets";

    public final static UILibrary Instance = new UILibrary();

    public UILibrary() {
        this(Namespace);
    }
    
    public UILibrary(String namespace) {
        super(namespace);
        
        this.addTagHandler("include", IncludeHandler.class);

        this.addTagHandler("composition", CompositionHandler.class);
        
        this.addComponent("component", ComponentRef.COMPONENT_TYPE, null, ComponentRefHandler.class);
        
        this.addComponent("fragment", ComponentRef.COMPONENT_TYPE, null, ComponentRefHandler.class);

        this.addTagHandler("define", DefineHandler.class);

        this.addTagHandler("insert", InsertHandler.class);

        this.addTagHandler("param", ParamHandler.class);

        this.addTagHandler("decorate", DecorateHandler.class);
        
        this.addComponent("repeat", UIRepeat.COMPONENT_TYPE, null, RepeatHandler.class);
        
        this.addComponent("debug", UIDebug.COMPONENT_TYPE, null);     
    }
}
